package com.gws.services.mail;

import com.gws.dto.sms.SmsNotice;

/**
 * 邮件服务类
 * @author wangdong
 */
public interface MailService {

    /**
     * 发送邮件，采用API的方式发送邮件
     * @param toAddress
     * @param subject
     * @param htmlBody
     * @return
     */
    Boolean sendAliMailByApi(String toAddress, String subject, String htmlBody);

    /**
     * 发送邮件，采用Smtp的方式发送邮件
     * @param toAddress
     * @param subject
     * @param htmlBody
     * @return
     */
    Boolean sendAliMailBySmtp(String toAddress, String subject, String htmlBody);
}
