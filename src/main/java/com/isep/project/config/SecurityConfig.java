package com.isep.project.config;

import com.isep.project.exception.handler.CustomAccessDeniedHandler;
import com.isep.project.filter.JwtAuthenticationFilter;
import com.isep.project.service.UserDetailsServiceImpl;
import javax.annotation.Resource;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Security config
 *
 * @author : Xuan MIAO
 * @version : 4.0.0
 * @date : 2021/6/11
 */
@Configuration
@EnableWebSecurity
@EnableConfigurationProperties(IgnoreConfig.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter
{

    @Resource
    private IgnoreConfig ignoreConfig;

    @Resource
    private CustomAccessDeniedHandler accessDeniedHandler;

    @Resource
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Resource
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public BCryptPasswordEncoder encoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception
    {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(encoder());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception
    {

        http.cors()
                .and()
                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .logout().disable()

                .authorizeRequests()
                .anyRequest()
                .access("@authorityService.hasPermission(request,authentication)")

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler)
                .and()
                .addFilterBefore(jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * Ignore config
     * @param web WebSecurity
     */
    @Override
    public void configure(WebSecurity web)
    {
        WebSecurity and = web.ignoring().and();

        ignoreConfig.getGet()
                .forEach(url -> and.ignoring().antMatchers(HttpMethod.GET, url));

        ignoreConfig.getPost()
                .forEach(url -> and.ignoring().antMatchers(HttpMethod.POST, url));

        ignoreConfig.getDelete()
                .forEach(url -> and.ignoring().antMatchers(HttpMethod.DELETE, url));

        ignoreConfig.getPut()
                .forEach(url -> and.ignoring().antMatchers(HttpMethod.PUT, url));

        ignoreConfig.getHead()
                .forEach(url -> and.ignoring().antMatchers(HttpMethod.HEAD, url));

        ignoreConfig.getPatch()
                .forEach(url -> and.ignoring().antMatchers(HttpMethod.PATCH, url));

        ignoreConfig.getOptions()
                .forEach(url -> and.ignoring().antMatchers(HttpMethod.OPTIONS, url));

        ignoreConfig.getTrace()
                .forEach(url -> and.ignoring().antMatchers(HttpMethod.TRACE, url));

        ignoreConfig.getPattern().forEach(url -> and.ignoring().antMatchers(url));

    }
}
