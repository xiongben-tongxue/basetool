package com.gws;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
public class SampleMail {
    private static final String ALIDM_SMTP_HOST = "smtpdm.aliyun.com";
    private static final int ALIDM_SMTP_PORT = 80;//或80
    public static void main(String[] args) {
        // 配置发送邮件的环境属性
        final Properties props = new Properties();
        // 表示SMTP发送邮件，需要进行身份验证
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", ALIDM_SMTP_HOST);
        props.put("mail.smtp.port", ALIDM_SMTP_PORT);
        // 发件人的账号
        props.put("mail.user", "usdg@mail.timesea.cn");
        // 访问SMTP服务时需要提供的密码
        props.put("mail.password", "HEllo123456");
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
//        mailSession.setDebug(true);
        // 创建邮件消息
        MimeMessage message = new MimeMessage(mailSession);
        try {
            // 设置发件人
            InternetAddress from = new InternetAddress("usdg@mail.timesea.cn");
            message.setFrom(from);
            Address[] a = new Address[1];
            a[0] = new InternetAddress("usdg@mail.timesea.cn");
            message.setReplyTo(a);
            // 设置收件人
            InternetAddress to = new InternetAddress("708934989@qq.com");
            message.setRecipient(MimeMessage.RecipientType.TO, to);
            // 设置邮件标题
            message.setSubject("测试邮件");
            // 设置邮件的内容体
            message.setContent("测试的HTML邮件", "text/html;charset=UTF-8");
            // 发送邮件
            Transport.send(message);
        }
        catch (MessagingException e) {
            String err = e.getMessage();
            // 在这里处理message内容， 格式是固定的
            System.out.println(err);
        }
    }
}

