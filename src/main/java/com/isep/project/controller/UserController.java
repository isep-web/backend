package com.isep.project.controller;

import com.isep.project.entity.User;
import com.isep.project.service.UserService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Xuan MIAO
 * @version : 1.0.0
 * @date : 2021/5/3
 */
@RestController
@RequestMapping("/user")
public class UserController
{

    @Resource
    private UserService userService;

    @PostMapping("")
    public User addUser(@RequestBody User user)
    {
        return userService.insertUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id)
    {
        userService.deleteUser(id);
    }

    @PutMapping("")
    public User updateUser(@RequestBody User user)
    {
        return userService.updateUser(user);
    }

    @GetMapping("")
    public List<User> findAll()
    {
        return userService.findAllUser();
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable("id") Long id)
    {
        return userService.findUserById(id);
    }

}

