package com.isep.project.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.isep.project.common.Status;
import com.isep.project.config.JwtConfig;
import com.isep.project.exception.JwtRuntimeException;
import com.isep.project.common.UserDetailsImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

/**
 * @author : Xuan MIAO
 * @version : 2.0.0
 * @date : 2021/6/10
 */
@Slf4j
@Service
@EnableConfigurationProperties(JwtConfig.class)
public class JwtService
{

    private static final String TOKEN_PREFIX = "Bearer ";

    @Resource
    private JwtConfig jwtConfig;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * Create jwt
     *
     * @param rememberMe  remember me
     * @param id          user id
     * @param username    username
     * @param roles       user roles
     * @param authorities user authentication
     * @return Jwt
     */
    public String createJwt(Boolean rememberMe, Long id, String username, List<String> roles,
                            Collection<? extends GrantedAuthority> authorities)
    {
        Date now = new Date();
        JwtBuilder builder = Jwts.builder().setId(id.toString()).setSubject(username)
                .setIssuedAt(now).signWith(SignatureAlgorithm.HS256, jwtConfig.getKey())
                .claim("roles", roles).claim("authorities", authorities);

        // Set ttl or remember me ttl
        Long ttl = rememberMe ? jwtConfig.getRememberTtl() : jwtConfig.getTtl();
        if (ttl > 0)
        {
            builder.setExpiration(DateUtil.offsetMillisecond(now, ttl.intValue()));
        }

        String jwt = builder.compact();
        // Save jet to redis
        stringRedisTemplate.opsForValue()
                .set(jwtConfig.getRedisKeyPrefix() + username, jwt, ttl, TimeUnit.MILLISECONDS);
        return jwt;
    }

    /**
     * Create Jwt
     *
     * @param authentication User authentication
     * @param rememberMe     remember me
     * @return Jwt
     */
    public String createJwt(Authentication authentication, Boolean rememberMe)
    {
        UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authentication.getPrincipal();
        return createJwt(rememberMe, userDetailsImpl.getId(), userDetailsImpl.getUsername(),
                userDetailsImpl.getRoles(), userDetailsImpl.getAuthorities());
    }

    /**
     * Decode Jwt
     *
     * @param jwt Jwt
     * @return {@link Claims}
     */
    public Claims decodeJwt(String jwt)
    {
        try
        {
            Claims claims = Jwts.parser().setSigningKey(jwtConfig.getKey()).parseClaimsJws(jwt)
                    .getBody();

            String username = claims.getSubject();
            String redisKey = jwtConfig.getRedisKeyPrefix() + username;

            // Check if jwt expire
            Long expire = stringRedisTemplate.getExpire(redisKey, TimeUnit.MILLISECONDS);
            if (Objects.isNull(expire) || expire <= 0)
            {
                throw new JwtRuntimeException(Status.TOKEN_EXPIRED);
            }

            // Check if jwt valid
            String redisToken = stringRedisTemplate.opsForValue().get(redisKey);
            if (!StrUtil.equals(jwt, redisToken))
            {
                log.error("User is logged in at another location!");
                throw new JwtRuntimeException(Status.TOKEN_OUT_OF_CTRL);
            }
            return claims;
        } catch (ExpiredJwtException e)
        {
            log.error("Token Expired!");
            throw new JwtRuntimeException(Status.TOKEN_EXPIRED, "Token Expired!");
        } catch (UnsupportedJwtException e)
        {
            log.error("Unsupported Token!");
            throw new JwtRuntimeException(Status.TOKEN_PARSE_ERROR, "Unsupported Token!");
        } catch (MalformedJwtException e)
        {
            log.error("Token parsing failure!");
            throw new JwtRuntimeException(Status.TOKEN_PARSE_ERROR, "Token parsing failure!");
        } catch (SignatureException e)
        {
            log.error("Invalid Token signatures!");
            throw new JwtRuntimeException(Status.TOKEN_PARSE_ERROR, "Invalid Token signatures!");
        } catch (IllegalArgumentException e)
        {
            log.error("Token parameter does not exist!");
            throw new JwtRuntimeException(Status.TOKEN_PARSE_ERROR,
                    "Token parameter does not exist!");
        }
    }

    /**
     * Set jwt invalid
     *
     * @param request HttpServletRequest
     */
    public void invalidateJwt(HttpServletRequest request)
    {
        String jwt = getJwtFromRequest(request);
        String username = getUsernameFromJwt(jwt);
        // Delete from redis
        stringRedisTemplate.delete(jwtConfig.getRedisKeyPrefix() + username);
    }

    /**
     * Get username from jwt
     *
     * @param jwt Jwt
     * @return username
     */
    public String getUsernameFromJwt(String jwt)
    {
        Claims claims = decodeJwt(jwt);
        return claims.getSubject();
    }

    /**
     * Get jwt from request header
     *
     * @param request HttpServletRequest
     * @return Jwt
     */
    public String getJwtFromRequest(HttpServletRequest request)
    {
        String bearerToken = request.getHeader("Authorization");
        if (StrUtil.isNotBlank(bearerToken) && bearerToken.startsWith(TOKEN_PREFIX))
        {
            return bearerToken.substring(TOKEN_PREFIX.length());
        }
        return null;
    }

}
