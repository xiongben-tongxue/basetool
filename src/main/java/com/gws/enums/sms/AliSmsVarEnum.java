package com.gws.enums.sms;

/**
 * 【阿里云短信变量】
 *
 * @author yangjh
 */
public enum AliSmsVarEnum {

    //游戏上架通知预约用户
    USER_GAME_APPOINTMENT("SMS_83850008", "gameName", "osType"),
    MOBILE_UNBIND_NOTIFY("SMS_122290217", "newAccount", "oldAccount"),
    APPLY_PASS_FOR_MEMBER("SMS_100785088", "name", null),
    APP_UNBIND_THIRDLOGIN("SMS_122290675", "account", null),
    TO_BE_LEADER("SMS_99195081", null, null),

    // 回调金额与实际应付金额不一致
    TRADE_AMOUNT_VALID("SMS_126781290", "amount", "payFlowId"),
    // 回调金额超过支付限制
    TRADE_AMOUNT_LIMIT("SMS_126970796", "amount", "orderId"),;
    private String templateCode;
    private String varOne;
    private String varTwo;

    AliSmsVarEnum(String templateCode, String varOne, String varTwo) {
        this.templateCode = templateCode;
        this.varOne = varOne;
        this.varTwo = varTwo;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public String getVarOne() {
        return varOne;
    }

    public void setVarOne(String varOne) {
        this.varOne = varOne;
    }

    public String getVarTwo() {
        return varTwo;
    }

    public void setVarTwo(String varTwo) {
        this.varTwo = varTwo;
    }

    public static AliSmsVarEnum getEnum(String templateCode) {
        for (AliSmsVarEnum aliSmsVarEnum : AliSmsVarEnum.values()) {
            if (aliSmsVarEnum.getTemplateCode().equals(templateCode)) {
                return aliSmsVarEnum;
            }
        }
        return null;
    }
}
