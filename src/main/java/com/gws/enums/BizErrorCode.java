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
 * 业务错误状态码有6位，都是以40开头，中间两位表未业务（01：游戏，05：用户）
 *
 * @author
 */
public enum BizErrorCode implements CodeStatus {


    PARM_ERROR("400101", "参数非法"),
    SEND_FAIL("400102","发送失败"),
    SAVE_FAIL("400103","保存失败"),
    NO_MORE_MESSAGE("400104","没有更多消息了"),
    ;
    private String code;
    private String message;

    private BizErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
