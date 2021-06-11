package com.isep.project.payload;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Register request
 *
 * @author : Xuan MIAO
 * @version : 1.0.0
 * @date : 2021/6/10
 */
@Data
public class RegisterRequest implements Serializable
{

    private static final long serialVersionUID = -565756242815603076L;

    /**
     * 用户名
     */
    @NotBlank(message = "Username can't null")
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "Password can't null")
    private String password;

    @NotBlank(message = "Email can't null")
    private String email;

    @NotBlank(message = "Phone can't null")
    private String phone;
}
