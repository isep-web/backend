package com.isep.project.service;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.isep.project.common.Status;
import com.isep.project.common.UserDetailsImpl;
import com.isep.project.entity.Permission;
import com.isep.project.entity.Role;
import com.isep.project.exception.JwtRuntimeException;
import com.isep.project.repository.ApplicationRepository;
import com.isep.project.repository.HouseRepository;
import com.isep.project.repository.PermissionRepository;
import com.isep.project.repository.PictureRepository;
import com.isep.project.repository.RoleRepository;
import com.isep.project.repository.UserRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * Permission authentication
 *
 * @author : Xuan MIAO
 * @version : 3.0.0
 * @date : 2021/6/10
 */
@Slf4j
@Service
public class AuthorityService
{

    private static final List<String> R = Lists
            .newArrayList("HEAD", "OPTIONS", "GET");
    private static final List<String> CR = Lists
            .newArrayList("HEAD", "OPTIONS", "GET", "POST");
    private static final List<String> CURD = Lists
            .newArrayList("HEAD", "OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE");
    /**
     * Roles that can edit resources not owned to them
     */
    private static final List<String> ADMIN = Lists.newArrayList("admin");
    /**
     * Methods ignored during resource owner checks
     */
    private static final List<String> METHOD_IGNORE = Lists.newArrayList("GET", "POST");

    @Resource
    private RoleRepository roleRepository;

    @Resource
    private UserRepository userRepository;

    @Resource
    private PermissionRepository permissionRepository;

    @Resource
    private RequestMappingHandlerMapping mapping;

    @Resource
    private HouseRepository houseRepository;

    @Resource
    private ApplicationRepository applicationRepository;

    @Resource
    private PictureRepository pictureRepository;

    @Resource
    private JwtService jetService;

    public boolean hasPermission(HttpServletRequest request, Authentication authentication)
    {
        checkRequest(request);

        Object userInfo = authentication.getPrincipal();
        boolean hasPermission = false;

        if (userInfo instanceof UserDetails)
        {
            UserDetailsImpl principal = (UserDetailsImpl) userInfo;
            Long userId = principal.getId();

            List<Role> roles = roleRepository.selectByUserId(userId);
            List<Long> roleIds = roles.stream().map(Role::getId).collect(Collectors.toList());
            List<Permission> permissions = permissionRepository.selectByRoleIdList(roleIds);
            List<Permission> perms = permissions.stream()
                    .filter(permission -> StrUtil.isNotBlank(permission.getUrl()))
                    .filter(permission -> StrUtil.isNotBlank(permission.getMethod()))
                    .collect(Collectors.toList());
            List<Permission> permsAll = new ArrayList<>();
            List<String> methods;
            for (Permission perm : perms)
            {
                switch (perm.getMethod())
                {
                    case "R":
                        methods = R;
                        break;
                    case "CR":
                        methods = CR;
                        break;
                    case "CURD":
                        methods = CURD;
                        break;
                    default:
                        methods = Collections.singletonList(perm.getMethod());
                }

                for (String method : methods)
                {
                    Permission p = (Permission) perm.clone();
                    p.setMethod(method);
                    permsAll.add(p);
                }

            }
            log.debug(String.valueOf(permsAll));

            for (Permission perm : permsAll)
            {
                AntPathRequestMatcher antPathMatcher = new AntPathRequestMatcher(perm.getUrl(),
                        perm.getMethod());
                if (antPathMatcher.matches(request))
                {
                    hasPermission = true;
                    break;
                }
            }

            if (Collections.disjoint(principal.getRoles(), ADMIN) && !METHOD_IGNORE
                    .contains(request.getMethod()))
            {
                Long id = principal.getId();
                List<Long> owner = Lists.newArrayList();
                String uri = request.getRequestURI();
                log.debug("URI: " + uri);
                String[] uris = uri.split("/");
                log.debug("URIS: " + Arrays.toString(uris));

                // Normal users can only access data created by itself, ADMIN can access all data
                if (uris.length > 2)
                {
                    switch (uris[1])
                    {
                        case "users":
                            owner.add(Long.valueOf(uris[2]));
                            break;
                        case "houses":
                            owner.add(
                                    houseRepository.selectById(Long.valueOf(uris[2])).getUserId());
                            break;
                        case "applications":
                            owner.add(applicationRepository.selectById(Long.valueOf(uris[2]))
                                    .getSourceUser().getId());
                            owner.add(applicationRepository.selectById(Long.valueOf(uris[2]))
                                    .getTargetUser().getId());
                            break;
                        case "pictures":
                            if (pictureRepository.selectById(Long.valueOf(uris[2])).getType() == 0)
                            {
                                owner.add(pictureRepository.selectById(Long.valueOf(uris[2]))
                                        .getUser()
                                        .getId());

                            } else
                            {
                                owner.add(pictureRepository.selectById(Long.valueOf(uris[2]))
                                        .getHouse()
                                        .getId());
                            }
                        default:

                            owner.add(userRepository.selectByName(jetService
                                    .getUsernameFromJwt(jetService.getJwtFromRequest(request)))
                                    .getId());
                            break;
                    }
                }
                log.debug("Owner:" + owner);
                if (!owner.contains(id))
                {
                    hasPermission = false;
                }
            }
            return hasPermission;
        } else
        {
            return false;
        }
    }

    /**
     * Check 404
     *
     * @param request HttpServletRequest
     */
    private void checkRequest(HttpServletRequest request)
    {
        String currentMethod = request.getMethod();
        Multimap<String, String> urlMapping = allUrlMapping();

        log.debug(String.valueOf(urlMapping));

        for (String uri : urlMapping.keySet())
        {
            AntPathRequestMatcher antPathMatcher = new AntPathRequestMatcher(uri);
            if (antPathMatcher.matches(request))
            {
                if (!urlMapping.get(uri).contains(currentMethod))
                {
                    throw new JwtRuntimeException(Status.HTTP_BAD_METHOD);
                } else
                {
                    return;
                }
            }
        }

        throw new JwtRuntimeException(Status.REQUEST_NOT_FOUND);
    }

    /**
     * Get all URL mapping
     *
     * @return {@link ArrayListMultimap} URL Mapping
     */
    private Multimap<String, String> allUrlMapping()
    {
        Multimap<String, String> urlMapping = ArrayListMultimap.create();
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = mapping.getHandlerMethods();

        handlerMethods.forEach((k, v) ->
        {
            assert k.getPatternsCondition() != null;
            Set<String> url = k.getPatternsCondition().getPatterns();
            RequestMethodsRequestCondition method = k.getMethodsCondition();

            url.forEach(s -> urlMapping.putAll(s,
                    method.getMethods().stream().map(Enum::toString).collect(Collectors.toList())));
        });

        //The above method cannot get the endpoint generated by Spring Data Rest, so add it here
        // manually
        urlMapping.put("/amenities", "GET");
        urlMapping.putAll("/applications", CR);
        urlMapping.putAll("/applications/*/**", CURD);
        urlMapping.put("/constraints", "GET");
        urlMapping.putAll("/houses", CR);
        urlMapping.putAll("/houses/*/**", CURD);
        urlMapping.putAll("/messages", CR);
        urlMapping.putAll("/messages/*/**", CURD);
        urlMapping.putAll("/pictures", CR);
        urlMapping.putAll("/pictures/*/**", CURD);
        urlMapping.put("/services", "GET");
        urlMapping.putAll("/users", CR);
        urlMapping.putAll("/users/*/**", CURD);
        urlMapping.putAll("/roles/**", CURD);

        return urlMapping;
    }
}
