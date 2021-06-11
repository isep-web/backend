package com.isep.project.service;

import com.isep.project.common.Status;
import com.isep.project.entity.Role;
import com.isep.project.entity.User;
import com.isep.project.exception.JwtRuntimeException;
import com.isep.project.repository.RoleRepository;
import com.isep.project.repository.UserRepository;
import java.util.HashSet;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author : Xuan MIAO
 * @version : 1.0.0
 * @date : 2021/6/10
 */
@Service
public class RegisterService
{

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Resource
    private UserRepository userRepository;

    @Resource
    private RoleRepository roleRepository;

    @Resource
    private JwtService jwtService;

    public void changePassword(HttpServletRequest request, String password)
    {

        User user = userRepository
                .findByUsername(
                        jwtService.getUsernameFromJwt(jwtService.getJwtFromRequest(request)));
        user.setPassword(bCryptPasswordEncoder.encode(password));
        userRepository.save(user);
        try
        {
            jwtService.invalidateJwt(request);
        } catch (JwtRuntimeException e)
        {
            throw new JwtRuntimeException(Status.UNAUTHORIZED);
        }
    }

    public User register(String username, String password, String email, String phone)
    {
        User user = new User(username, bCryptPasswordEncoder.encode(password), email, phone);
        HashSet<Role> roles = new HashSet<>();
        roles.add(roleRepository.getById(2L));
        user.setRoles(roles);
        return userRepository.save(user);
    }
}
