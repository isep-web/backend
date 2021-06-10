package com.isep.project.payload;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author : Xuan MIAO
 * @version : 1.0.0
 * @date : 2021/6/10
 */
@Data
public class RegisterRequest implements Serializable
{

    private static final long serialVersionUID = -565756242815603076L;

    /**
     * 用户名或邮箱或手机号
     */
    @NotBlank(message = "用户名不能为空")
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;
}
