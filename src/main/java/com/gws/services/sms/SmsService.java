package com.gws.services.sms;

import com.gws.dto.sms.SmsNotice;

/**
 * 【短信服务类】
 *
 * @author
 */
public interface SmsService {

    /**
     * 发送短信通知业务
     * @param mobile
     * @param templateCode
     * @param smsNotice
     * @return
     */
    Boolean sendAliNotice(String mobile, String templateCode, SmsNotice smsNotice);

    /**
     * 发送短信验证码业务
     * @param mobile
     * @param templateCode
     * @param code
     * @return
     */
    Boolean sendAliVcode(String mobile, String templateCode, String code);

}
