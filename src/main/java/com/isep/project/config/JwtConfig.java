package com.isep.project.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Jwt config
 *
 * @author : Xuan MIAO
 * @version : 2.0.0
 * @date : 2021/6/8
 */
@ConfigurationProperties(prefix = "jwt.config")
@Data
public class JwtConfig
{

    /**
     * Jwt key, default: jwtKey
     */
    private String key = "jwtKey";

    /**
     * Jwt expiration time (millisecond), default: 600000 (10 minutes)
     */
    private Long ttl = 600000L;

    /**
     * Jwt expiration time with remember me (millisecond), default: 604800000 (7 days)
     */
    private Long rememberTtl = 604800000L;

    /**
     * Prefix of jwt key redis, default: "jwt:"
     */
    private String redisKeyPrefix = "jwt:";
}
