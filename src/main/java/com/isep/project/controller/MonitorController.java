package com.isep.project.controller;

import cn.hutool.core.collection.CollUtil;
import com.isep.project.common.ApiResponse;
import com.isep.project.common.PageResult;
import com.isep.project.common.Status;
import com.isep.project.exception.SecurityException;
import com.isep.project.payload.PageCondition;
import com.isep.project.service.MonitorService;
import com.isep.project.util.PageUtil;
import com.isep.project.util.SecurityUtil;
import com.isep.project.vo.OnlineUser;
import java.util.List;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 监控 Controller，在线用户，手动踢出用户等功能
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-12-11 20:55
 */
@Slf4j
@RestController
@RequestMapping("/monitor")
public class MonitorController
{

    @Resource
    private MonitorService monitorService;

    /**
     * 在线用户列表
     *
     * @param pageCondition 分页参数
     */
    @GetMapping("/")
    public ApiResponse onlineUser(PageCondition pageCondition)
    {
        PageUtil.checkPageCondition(pageCondition, PageCondition.class);
        PageResult<OnlineUser> pageResult = monitorService.onlineUser(pageCondition);
        return ApiResponse.ofSuccess(pageResult);
    }

    /**
     * 批量踢出在线用户
     *
     * @param names 用户名列表
     */
    @DeleteMapping("/")
    public ApiResponse kickoutOnlineUser(@RequestBody List<String> names)
    {
        log.error(String.valueOf(names));
        if (CollUtil.isEmpty(names))
        {
            throw new SecurityException(Status.PARAM_NOT_NULL);
        }
        if (names.contains(SecurityUtil.getCurrentUsername()))
        {
            throw new SecurityException(Status.KICKOUT_SELF);
        }
        monitorService.kickout(names);
        return ApiResponse.ofSuccess();
    }
}
