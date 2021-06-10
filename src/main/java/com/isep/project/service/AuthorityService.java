package com.isep.project.service;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.isep.project.common.Status;
import com.isep.project.entity.Permission;
import com.isep.project.entity.Role;
import com.isep.project.exception.JwtRuntimeException;
import com.isep.project.repository.ApplicationRepository;
import com.isep.project.repository.HouseRepository;
import com.isep.project.repository.PermissionRepository;
import com.isep.project.repository.RoleRepository;
import com.isep.project.vo.UserPrincipal;
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
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * <p>
 * 动态路由认证
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-12-10 17:17
 */
@Slf4j
@Component
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
    private PermissionRepository permissionRepository;

    @Resource
    private RequestMappingHandlerMapping mapping;

    @Resource
    private HouseRepository houseRepository;

    @Resource
    private ApplicationRepository applicationRepository;

    public boolean hasPermission(HttpServletRequest request, Authentication authentication)
    {
        checkRequest(request);

        Object userInfo = authentication.getPrincipal();
        boolean hasPermission = false;

        if (userInfo instanceof UserDetails)
        {
            UserPrincipal principal = (UserPrincipal) userInfo;
            Long userId = principal.getId();

            List<Role> roles = roleRepository.selectByUserId(userId);
            List<Long> roleIds = roles.stream().map(Role::getId).collect(Collectors.toList());
            List<Permission> permissions = permissionRepository.selectByRoleIdList(roleIds);

            //获取资源，前后端分离，所以过滤页面权限，只保留按钮权限
            List<Permission> perms = permissions.stream()
                    // 过滤 URL 为空
                    .filter(permission -> StrUtil.isNotBlank(permission.getUrl()))
                    // 过滤 METHOD 为空
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
                if (uris.length > 2)
                {
                    switch (uris[1])
                    {
                        case "users":
                            owner.add(Long.valueOf(uris[2]));
                            break;
                        case "houses":
                            owner.add(houseRepository.selectById(Long.valueOf(uris[2])).getUserId());
                            break;
                        case "applications":
                            owner.add(applicationRepository.selectById(Long.valueOf(uris[2]))
                                    .getSourceUser().getId());
                            break;
                        default:
                            owner.add(Long.valueOf(uris[2]));
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
     * 校验请求是否存在
     *
     * @param request 请求
     */
    private void checkRequest(HttpServletRequest request)
    {
        // 获取当前 request 的方法
        String currentMethod = request.getMethod();
        Multimap<String, String> urlMapping = allUrlMapping();

        log.debug(String.valueOf(urlMapping));

        for (String uri : urlMapping.keySet())
        {
            // 通过 AntPathRequestMatcher 匹配 url
            // 可以通过 2 种方式创建 AntPathRequestMatcher
            // 1：new AntPathRequestMatcher(uri,method) 这种方式可以直接判断方法是否匹配，因为这里我们把 方法不匹配
            // 自定义抛出，所以，我们使用第2种方式创建
            // 2：new AntPathRequestMatcher(uri) 这种方式不校验请求方法，只校验请求路径
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

        // 获取url与类和方法的对应信息
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = mapping.getHandlerMethods();

        handlerMethods.forEach((k, v) ->
        {
            // 获取当前 key 下的获取所有URL
            assert k.getPatternsCondition() != null;
            Set<String> url = k.getPatternsCondition().getPatterns();
            RequestMethodsRequestCondition method = k.getMethodsCondition();

            // 为每个URL添加所有的请求方法
            url.forEach(s -> urlMapping.putAll(s,
                    method.getMethods().stream().map(Enum::toString).collect(Collectors.toList())));
        });

        urlMapping.put("/amenities", "GET");
        urlMapping.putAll("/applications", CR);
        urlMapping.putAll("/applications/*/**", CURD);
        urlMapping.put("/constraints", "GET");
        urlMapping.putAll("/houses", CR);
        urlMapping.putAll("/houses/*/**", CURD);
        urlMapping.putAll("/pictures", CR);
        urlMapping.putAll("/pictures/*/**", CURD);
        urlMapping.put("/services", "GET");
        urlMapping.putAll("/users", CR);
        urlMapping.putAll("/users/*/**", CURD);

        return urlMapping;
    }
}
