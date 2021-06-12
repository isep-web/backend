package com.isep.project.service;

import com.isep.project.common.UserDetailsImpl;
import com.isep.project.entity.Role;
import com.isep.project.entity.User;
import com.isep.project.repository.RoleRepository;
import com.isep.project.repository.UserRepository;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Response generate service
 *
 * @author : Xuan MIAO
 * @version : 1.0.0
 * @date : 2021/5/31
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService
{

    @Resource
    private UserRepository userRepository;

    @Resource
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmailOrPhone)
            throws UsernameNotFoundException
    {
        User user = userRepository
                .findByUsernameOrEmailOrPhone(usernameOrEmailOrPhone, usernameOrEmailOrPhone,
                        usernameOrEmailOrPhone).orElseThrow(
                        () -> new UsernameNotFoundException(
                                "Can't find user information:  " + usernameOrEmailOrPhone));
        List<Role> roles = roleRepository.selectByUserId(user.getId());
        //Avoid infinite loop
        user.setReceivedApplications(null);
        user.setSentApplications(null);
        user.setAvatar(null);
        return UserDetailsImpl.create(user, roles);
    }
}
