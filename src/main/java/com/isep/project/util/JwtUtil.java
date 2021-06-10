package com.isep.project.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.isep.project.common.Status;
import com.isep.project.config.JwtConfig;
import com.isep.project.exception.JwtRuntimeException;
import com.isep.project.vo.UserPrincipal;
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
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

/**
 * <p>
 * JWT 工具类
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-12-07 13:42
 */
@EnableConfigurationProperties(JwtConfig.class)
@Configuration
@Slf4j
public class JwtUtil
{

    @Resource
    private JwtConfig jwtConfig;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 创建JWT
     *
     * @param rememberMe  记住我
     * @param id          用户id
     * @param username     用户名
     * @param roles       用户角色
     * @param authorities 用户权限
     * @return JWT
     */
    public String createJWT(Boolean rememberMe, Long id, String username, List<String> roles,
            Collection<? extends GrantedAuthority> authorities)
    {
        Date now = new Date();
        JwtBuilder builder = Jwts.builder().setId(id.toString()).setSubject(username)
                .setIssuedAt(now).signWith(SignatureAlgorithm.HS256, jwtConfig.getKey())
                .claim("roles", roles).claim("authorities", authorities);

        // 设置过期时间
        Long ttl = rememberMe ? jwtConfig.getRememberTtl() : jwtConfig.getTtl();
        if (ttl > 0)
        {
            builder.setExpiration(DateUtil.offsetMillisecond(now, ttl.intValue()));
        }

        String jwt = builder.compact();
        // 将生成的JWT保存至Redis
        stringRedisTemplate.opsForValue()
                .set(jwtConfig.getRedisKeyPrefix() + username, jwt, ttl, TimeUnit.MILLISECONDS);
        return jwt;
    }

    /**
     * 创建JWT
     *
     * @param authentication 用户认证信息
     * @param rememberMe     记住我
     * @return JWT
     */
    public String createJWT(Authentication authentication, Boolean rememberMe)
    {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return createJWT(rememberMe, userPrincipal.getId(), userPrincipal.getUsername(),
                userPrincipal.getRoles(), userPrincipal.getAuthorities());
    }

    /**
     * 解析JWT
     *
     * @param jwt JWT
     * @return {@link Claims}
     */
    public Claims parseJWT(String jwt)
    {
        try
        {
            Claims claims = Jwts.parser().setSigningKey(jwtConfig.getKey()).parseClaimsJws(jwt)
                    .getBody();

            String username = claims.getSubject();
            String redisKey = jwtConfig.getRedisKeyPrefix() + username;

            // 校验redis中的JWT是否存在
            Long expire = stringRedisTemplate.getExpire(redisKey, TimeUnit.MILLISECONDS);
            if (Objects.isNull(expire) || expire <= 0)
            {
                throw new JwtRuntimeException(Status.TOKEN_EXPIRED);
            }

            // 校验redis中的JWT是否与当前的一致，不一致则代表用户已注销/用户在不同设备登录，均代表JWT已过期
            String redisToken = stringRedisTemplate.opsForValue().get(redisKey);
            if (!StrUtil.equals(jwt, redisToken))
            {log.error("User is logged in at another location!");
                throw new JwtRuntimeException(Status.TOKEN_OUT_OF_CTRL);
            }
            return claims;
        } catch (ExpiredJwtException e)
        {
            log.error("Token Expired!");
            throw new JwtRuntimeException(Status.TOKEN_EXPIRED,"Token Expired!");
        } catch (UnsupportedJwtException e)
        {
            log.error("Unsupported Token!");
            throw new JwtRuntimeException(Status.TOKEN_PARSE_ERROR,"Unsupported Token!");
        } catch (MalformedJwtException e)
        {
            log.error("Token parsing failure!");
            throw new JwtRuntimeException(Status.TOKEN_PARSE_ERROR,"Token parsing failure!");
        } catch (SignatureException e)
        {
            log.error("Invalid Token signatures!");
            throw new JwtRuntimeException(Status.TOKEN_PARSE_ERROR,"Invalid Token signatures!");
        } catch (IllegalArgumentException e)
        {
            log.error("Token parameter does not exist!");
            throw new JwtRuntimeException(Status.TOKEN_PARSE_ERROR,"Token parameter does not exist!");
        }
    }

    /**
     * 设置JWT过期
     *
     * @param request 请求
     */
    public void invalidateJWT(HttpServletRequest request)
    {
        String jwt = getJwtFromRequest(request);
        String username = getUsernameFromJWT(jwt);
        // 从redis中清除JWT
        stringRedisTemplate.delete(jwtConfig.getRedisKeyPrefix() + username);
    }

    /**
     * 根据 jwt 获取用户名
     *
     * @param jwt JWT
     * @return 用户名
     */
    public String getUsernameFromJWT(String jwt)
    {
        Claims claims = parseJWT(jwt);
        return claims.getSubject();
    }

    /**
     * 从 request 的 header 中获取 JWT
     *
     * @param request 请求
     * @return JWT
     */
    public String getJwtFromRequest(HttpServletRequest request)
    {
        String bearerToken = request.getHeader("Authorization");
        if (StrUtil.isNotBlank(bearerToken) && bearerToken.startsWith("Bearer "))
        {
            return bearerToken.substring(7);
        }
        return null;
    }

}
