package com.isep.project.controller;

import com.isep.project.common.Status;
import com.isep.project.entity.User;
import com.isep.project.payload.ChangePasswordRequest;
import com.isep.project.payload.RegisterRequest;
import com.isep.project.service.RegisterService;
import com.isep.project.service.ResponseService;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Register & change password controller
 *
 * @author : Xuan MIAO
 * @version : 1.0.0
 * @date : 2021/6/10
 */
@Slf4j
@RestController
@RequestMapping("/")
public class RegisterController
{

    @Resource
    private RegisterService registerService;

    @PatchMapping("/register")
    public void changePassword(@RequestBody ChangePasswordRequest password,
                               HttpServletRequest request,
                               HttpServletResponse response)
    {
        registerService.changePassword(request, password.getPassword());
        ResponseService.renderJson(response, Status.SUCCESS, null);

    }

    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest req)
    {
        return registerService.register(req.getUsername(),req.getPassword(),req.getEmail(),req.getPhone());
    }
}
