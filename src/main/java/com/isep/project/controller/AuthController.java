package com.isep.project.controller;

import com.isep.project.common.ApiResponse;
import com.isep.project.common.Status;
import com.isep.project.exception.SecurityException;
import com.isep.project.payload.LoginRequest;
import com.isep.project.util.JwtUtil;
import com.isep.project.vo.JwtResponse;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 认证 Controller，包括用户注册，用户登录请求
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-12-07 17:23
 */
@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthController
{

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private JwtUtil jwtUtil;

    /**
     * 登录
     */
    @PostMapping("/login")
    public ApiResponse login(@Valid @RequestBody LoginRequest loginRequest)
    {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmailOrPhone(),
                        loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtil.createJWT(authentication, loginRequest.getRememberMe());
        return ApiResponse.ofSuccess(new JwtResponse(jwt));
    }

    @PostMapping("/logout")
    public ApiResponse logout(HttpServletRequest request)
    {
        try
        {
            // 设置JWT过期
            jwtUtil.invalidateJWT(request);
        } catch (SecurityException e)
        {
            throw new SecurityException(Status.UNAUTHORIZED);
        }
        return ApiResponse.ofStatus(Status.LOGOUT);
    }
}
