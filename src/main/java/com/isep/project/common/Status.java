package com.isep.project.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Status
 *
 * @author : Xuan MIAO
 * @version : 2.0.0
 * @date : 2021/6/5
 */
@Getter
@AllArgsConstructor
public enum Status
{
    /**
     * Success！
     */
    SUCCESS(200, "Success！"),

    /**
     * Error！
     */
    ERROR(500, "Error！"),

    /**
     * Logout success！
     */
    LOGOUT(200, "Logout success！"),

    /**
     * Please login first！
     */
    UNAUTHORIZED(401, "Please login first！"),

    /**
     * Access denied！
     */
    ACCESS_DENIED(403, "Access denied！"),

    /**
     * Request not found！
     */
    REQUEST_NOT_FOUND(404, "Request not found！"),

    /**
     * Method not supported！
     */
    HTTP_BAD_METHOD(405, "Method not supported！"),

    /**
     * Bad request！
     */
    BAD_REQUEST(400, "Bad request！"),

    /**
     * Parameters not match！
     */
    PARAM_NOT_MATCH(400, "Parameters not match！"),

    /**
     * Parameters cannot be empty！
     */
    PARAM_NOT_NULL(400, "Parameters cannot be empty！"),

    /**
     * User has been blocked！
     */
    USER_DISABLED(403, "User has been blocked！"),

    /**
     * Incorrect username or password！
     */
    USERNAME_PASSWORD_ERROR(5001, "Incorrect username or password！"),

    /**
     * Token Expired！
     */
    TOKEN_EXPIRED(5002, "Token Expired！"),

    /**
     * Token parsing failure！
     */
    TOKEN_PARSE_ERROR(5002, "token parsing failure！"),

    /**
     * User is logged in at another location！
     */
    TOKEN_OUT_OF_CTRL(5003, "User is logged in at another location！");


    /**
     * Status code
     */
    private final Integer status;

    /**
     * Information
     */
    private final String message;


    @Override
    public String toString()
    {
        return String.format(" Status:{code=%s, message=%s} ", getStatus(), getMessage());
    }

}
