package com.isep.project.service;

import com.isep.project.entity.User;
import com.isep.project.repository.UserRepository;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author : Xuan MIAO
 * @version : 1.0.0
 * @date : 2021/5/3
 */

@Service
public class UserServiceImpl implements UserService
{
    @Resource
    private UserRepository userRepository;

    @Override
    public User insertUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

}
