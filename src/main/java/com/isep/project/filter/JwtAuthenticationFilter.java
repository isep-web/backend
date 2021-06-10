package com.isep.project.filter;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Sets;
import com.isep.project.common.Status;
import com.isep.project.config.IgnoreConfig;
import com.isep.project.exception.JwtRuntimeException;
import com.isep.project.service.CustomUserDetailsService;
import com.isep.project.service.JwtService;
import com.isep.project.service.ResponseService;
import java.io.IOException;
import java.util.Set;
import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * JWT filter
 *
 * @author : Xuan MIAO
 * @version : 1.0.0
 * @date : 2021/6/5
 */
@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter
{

    @Resource
    private CustomUserDetailsService customUserDetailsService;

    @Resource
    private JwtService jwtService;

    @Resource
    private IgnoreConfig ignoreConfig;

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull FilterChain filterChain) throws ServletException, IOException
    {

        if (isIgnored(request))
        {
            filterChain.doFilter(request, response);
            return;
        }

        String jwt = jwtService.getJwtFromRequest(request);

        if (StrUtil.isNotBlank(jwt))
        {
            try
            {
                String username = jwtService.getUsernameFromJwt(jwt);

                UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities());
                authentication
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
                filterChain.doFilter(request, response);
            } catch (JwtRuntimeException e)
            {
                ResponseService.renderJson(response, e.getStatus(),e.getData());
            }
        } else
        {
            ResponseService.renderJson(response, Status.UNAUTHORIZED, null);
        }

    }

    /**
     * Check if request is ignored bt Jwt filter
     *
     * @param request request
     * @return true - ignored，false - not ignored
     */
    private boolean isIgnored(HttpServletRequest request)
    {
        String method = request.getMethod();

        HttpMethod httpMethod = HttpMethod.resolve(method);
        if (ObjectUtil.isNull(httpMethod))
        {
            httpMethod = HttpMethod.GET;
        }

        Set<String> ignores = Sets.newHashSet();

        switch (httpMethod)
        {
            case GET:
                ignores.addAll(ignoreConfig.getGet());
                break;
            case PUT:
                ignores.addAll(ignoreConfig.getPut());
                break;
            case HEAD:
                ignores.addAll(ignoreConfig.getHead());
                break;
            case POST:
                ignores.addAll(ignoreConfig.getPost());
                break;
            case PATCH:
                ignores.addAll(ignoreConfig.getPatch());
                break;
            case TRACE:
                ignores.addAll(ignoreConfig.getTrace());
                break;
            case DELETE:
                ignores.addAll(ignoreConfig.getDelete());
                break;
            case OPTIONS:
                ignores.addAll(ignoreConfig.getOptions());
                break;
            default:
                break;
        }

        ignores.addAll(ignoreConfig.getPattern());

        if (CollUtil.isNotEmpty(ignores))
        {
            for (String ignore : ignores)
            {
                AntPathRequestMatcher matcher = new AntPathRequestMatcher(ignore, method);
                if (matcher.matches(request))
                {
                    return true;
                }
            }
        }

        return false;
    }

}
