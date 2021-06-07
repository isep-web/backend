package com.isep.project.controller;

import com.isep.project.common.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 测试Controller
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-12-10 15:44
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController
{

    @GetMapping
    public ApiResponse list()
    {
        log.info("测试列表查询");
        return ApiResponse.ofMessage("测试列表查询");
    }

    @PostMapping
    public ApiResponse add()
    {
        log.info("测试列表添加");
        return ApiResponse.ofMessage("测试列表添加");
    }

    @PutMapping("/{id}")
    public ApiResponse update(@PathVariable Long id)
    {
        log.info("测试列表修改");
        return ApiResponse.ofSuccess("测试列表修改");
    }
}
