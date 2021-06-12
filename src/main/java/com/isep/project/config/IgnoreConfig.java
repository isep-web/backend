package com.isep.project.config;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 * Filter ignore list
 *
 * @author : Xuan MIAO
 * @version : 1.0.0
 * @date : 2021/6/5
 */
@ConfigurationProperties(prefix = "custom.ignores")
@Data
public class IgnoreConfig
{

    /**
     * Ignored URL (All methods)
     */
    private List<String> pattern = new ArrayList<>();

    /**
     * Ignored GET
     */
    private List<String> get = new ArrayList<>();

    /**
     * Ignored POST
     */
    private List<String> post = new ArrayList<>();

    /**
     * Ignored DELETE
     */
    private List<String> delete = new ArrayList<>();

    /**
     * Ignored PUT
     */
    private List<String> put = new ArrayList<>();

    /**
     * Ignored HEAD
     */
    private List<String> head = new ArrayList<>();

    /**
     * Ignored PATCH
     */
    private List<String> patch = new ArrayList<>();

    /**
     * Ignored OPTIONS
     */
    private List<String> options = new ArrayList<>();

    /**
     * Ignored TRACE
     */
    private List<String> trace = new ArrayList<>();
}
