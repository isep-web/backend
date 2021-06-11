package com.isep.project.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Jwt response
 *
 * @author : Xuan MIAO
 * @version : 1.0.0
 * @date : 2021/6/11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse
{

    /**
     * token
     */
    private String token;
    /**
     * token
     */
    private String tokenType = "Bearer";

    public JwtResponse(String token)
    {
        this.token = token;
    }
}
