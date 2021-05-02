package com.isep.project.service;

import com.isep.project.entity.User;
import java.util.List;

/**
 * @author : Xuan MIAO
 * @version : 1.0.0
 * @date : 2021/5/3
 */
public interface UserService
{
    User insertUser(User user);
    void deleteUser(Long id);
    User updateUser(User user);
    List<User> findAllUser();
    User findUserById(Long id);
}
