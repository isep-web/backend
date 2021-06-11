package com.isep.project.service;

import com.isep.project.entity.Permission;
import com.isep.project.entity.Role;
import com.isep.project.entity.User;
import com.isep.project.repository.PermissionRepository;
import com.isep.project.repository.RoleRepository;
import com.isep.project.repository.UserRepository;
import com.isep.project.vo.UserPrincipal;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 自定义UserDetails查询
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-12-10 10:29
 */
@Service
public class CustomUserDetailsService implements UserDetailsService
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
                        () -> new UsernameNotFoundException("Can't find user information:  " + usernameOrEmailOrPhone));
        List<Role> roles = roleRepository.selectByUserId(user.getId());
        user.setReceivedApplications(null);
        user.setSentApplications(null);
        user.setAvatar(null);
        return UserPrincipal.create(user, roles);
    }
}
