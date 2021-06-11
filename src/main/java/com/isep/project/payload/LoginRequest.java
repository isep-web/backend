package com.isep.project.payload;

import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Login request
 *
 * @author : Xuan MIAO
 * @version : 1.0.0
 * @date : 2021/6/8
 */
@Data
public class LoginRequest
{

    @NotBlank(message = "usernameOrEmailOrPhone can't be null")
    private String usernameOrEmailOrPhone;

    @NotBlank(message = "password can't be null")
    private String password;

    private Boolean rememberMe = false;

}
