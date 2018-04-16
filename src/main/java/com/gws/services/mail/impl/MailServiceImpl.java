package com.gws.services.mail.impl;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dm.model.v20151123.SingleSendMailRequest;
import com.aliyuncs.dm.model.v20151123.SingleSendMailResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.gws.dto.sms.SmsNotice;
import com.gws.enums.sms.AliSmsVarEnum;
import com.gws.services.mail.MailService;
import com.gws.utils.GwsLogger;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

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

    /**
     * 发送邮件
     * @param toAddress
     * @param subject
     * @param htmlBody
     * @return
     */
    @Override
    public Boolean sendAliMail(String toAddress, String subject, String htmlBody) {

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
}
