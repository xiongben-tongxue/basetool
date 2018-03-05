/*
 * Copyright (C) 2016  HangZhou YuShi Technology Co.Ltd  Holdings Ltd. All rights reserved
 *
 * 本代码版权归杭州宇石科技所有，且受到相关的法律保护。
 * 没有经过版权所有者的书面同意，
 * 任何其他个人或组织均不得以任何形式将本文件或本文件的部分代码用于其他商业用途。
 *
 */
package com.gws.enums;

/**
 *【错误码枚举】
 *
 * @author
 */
public enum SystemCode implements CodeStatus {

    SUCCESS("000", "成功"),
    NEED_LOGIN("201", "未登录"),
    BAD_REQUEST("202", "参数非法"),
    NOT_IN_WHITELIST("203", "不在白名单内"),
    ILLEGAL_ACTION("204", "不合法的接口"),
    NOT_AUTH("205", "未授权");



    private String code;

    private String message;

    private SystemCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    public void setCode(String  code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
