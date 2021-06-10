package com.isep.project.controller;

import com.isep.project.common.Status;
import com.isep.project.exception.JwtRuntimeException;
import com.isep.project.payload.LoginRequest;
import com.isep.project.service.JwtService;
import com.isep.project.service.ResponseService;
import com.isep.project.payload.JwtResponse;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("/")
public class AuthController
{

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private JwtService jwtService;

    /**
     * 登录
     */
    @PostMapping("/auth")
    public void login(@Valid @RequestBody LoginRequest loginRequest, HttpServletResponse response)
    {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmailOrPhone(),
                        loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtService.createJwt(authentication, loginRequest.getRememberMe());
        ResponseService.renderJson(response, Status.SUCCESS, new JwtResponse(jwt));
    }

    @DeleteMapping("/auth")
    public void logout(HttpServletRequest request, HttpServletResponse response)
    {
        try
        {
            jwtService.invalidateJwt(request);
        } catch (JwtRuntimeException e)
        {
            throw new JwtRuntimeException(Status.UNAUTHORIZED);
        }
        ResponseService.renderJson(response, Status.LOGOUT, null);
    }

}
