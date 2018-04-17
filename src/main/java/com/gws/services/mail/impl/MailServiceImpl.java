package com.gws.services.mail.impl;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dm.model.v20151123.SingleSendMailRequest;
import com.aliyuncs.dm.model.v20151123.SingleSendMailResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.gws.services.mail.MailService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * 邮件服务类
 * @author 邮件服务类
 */
@Configuration
@Service
public class MailServiceImpl implements MailService {

    @Value("${aliyun.mail.regionId}")
    private String mailRegionId;

    // TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    @Value("${ali.accessKey}")
    private String aliAccessKey;

    @Value("${ali.secretKey}")
    private String aliSecretKey;

    /**控制台创建的发信地址*/
    @Value("${ali.mail.accountName}")
    private String mailAccountName;

    /**发信人昵称*/
    @Value("${ali.mail.fromAlias}")
    private String mailFromAlias;

    /**控制台创建的标签*/
    @Value("${ali.mail.TagName}")
    private String mailTagName;

    /**阿里smtp主机*/
    private static final String ALIDM_SMTP_HOST = "smtpdm.aliyun.com";
    /**阿里smtp主机*/
    private static final int ALIDM_SMTP_PORT = 80;
    /**阿里smtp发邮件的密码*/
    @Value("${ali.mail.password}")
    private String mailPassword;

    /**
     * 发送邮件,采用API的方式发送邮件
     * @param toAddress
     * @param subject
     * @param htmlBody
     * @return
     */
    @Override
    public Boolean sendAliMailByApi(String toAddress, String subject, String htmlBody) {

        if (StringUtils.isEmpty(toAddress) || StringUtils.isEmpty(subject) || StringUtils.isEmpty(htmlBody)) {
            return false;
        }

        IClientProfile profile = DefaultProfile.getProfile(mailRegionId, aliAccessKey, aliSecretKey);

        IAcsClient client = new DefaultAcsClient(profile);
        SingleSendMailRequest request = new SingleSendMailRequest();
        try {
            request.setAccountName(mailAccountName);
            request.setFromAlias(mailFromAlias);
            request.setAddressType(1);
            request.setTagName(mailTagName);
            request.setReplyToAddress(true);
            request.setToAddress(toAddress);
            request.setSubject(subject);
            request.setHtmlBody(htmlBody);
            SingleSendMailResponse httpResponse = client.getAcsResponse(request);
        } catch (ServerException e) {
            e.printStackTrace();
            return false;
        } catch (ClientException e) {
            e.printStackTrace();
            return false;
        };
        return true;
    }

    /**
     * 发送邮件，采用Smtp的方式发送邮件
     *
     * @param toAddress
     * @param subject
     * @param htmlBody
     * @return
     */
    @Override
    public Boolean sendAliMailBySmtp(String toAddress, String subject, String htmlBody) {

        if (StringUtils.isEmpty(toAddress) || StringUtils.isEmpty(subject) || StringUtils.isEmpty(htmlBody)) {
            return false;
        }

        // 配置发送邮件的环境属性
        final Properties props = new Properties();
        // 表示SMTP发送邮件，需要进行身份验证
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", ALIDM_SMTP_HOST);
        props.put("mail.smtp.port", ALIDM_SMTP_PORT);
        // 发件人的账号
        props.put("mail.user", mailAccountName);
        // 访问SMTP服务时需要提供的密码
        props.put("mail.password", mailPassword);
        // 构建授权信息，用于进行SMTP进行身份验证
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // 用户名、密码
                String userName = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };
        // 使用环境属性和授权信息，创建邮件会话
        Session mailSession = Session.getInstance(props, authenticator);
        // 创建邮件消息
        MimeMessage message = new MimeMessage(mailSession);
        try {
            // 设置发件人
            InternetAddress from = new InternetAddress(mailAccountName);
            message.setFrom(from);
            Address[] a = new Address[1];
            a[0] = new InternetAddress(mailAccountName);
            message.setReplyTo(a);
            // 设置收件人
            InternetAddress to = new InternetAddress(toAddress);
            message.setRecipient(MimeMessage.RecipientType.TO, to);
            // 设置邮件标题
            message.setSubject(subject);
            // 设置邮件的内容体
            message.setContent(htmlBody, "text/html;charset=UTF-8");
            // 发送邮件
            Transport.send(message);
        }
        catch (MessagingException e) {
            String err = e.getMessage();
            // 在这里处理message内容， 格式是固定的
            System.out.println(err);
            return false;
        }
        return true;
    }
}
