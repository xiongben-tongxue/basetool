package com.gws.services.sms.impl;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.gws.dto.sms.SmsNotice;
import com.gws.enums.sms.AliSmsVarEnum;
import com.gws.services.sms.SmsService;
import com.gws.utils.GwsLogger;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * 【短信服务类】
 *
 * @author yangjh  06/05/2017.
 */
@Configuration
@Service
public class SmsServiceImpl implements SmsService {

    private static IAcsClient acsClient;

    private static final String SIGN_NAME = "阿里云短信测试专用";

    //产品名称:云通信短信API产品,开发者无需替换
    static final String product = "Dysmsapi";
    //产品域名,开发者无需替换
    static final String domain = "dysmsapi.aliyuncs.com";

    @Value("${aliyun.sms.regionId}")
    private String smsRegionId;

    // TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    @Value("${ali.accessKey}")
    private String aliAccessKey;

    @Value("${ali.secretKey}")
    private String aliSecretKey;


    /**
     * 初始化阿里云短信客户端
     * IAcsClient 是线程安全的
     */
    @PostConstruct
    public void init() {

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");


        try {
            IClientProfile profile = DefaultProfile.getProfile(smsRegionId, aliAccessKey, aliSecretKey);
            DefaultProfile.addEndpoint(smsRegionId, smsRegionId, product, domain);
            acsClient = new DefaultAcsClient(profile);
        } catch (ClientException e) {
            System.exit(-1);
        }
    }

    /**
     * 【发送阿里云通知短信】
     *
     * @param mobile
     * @param templateCode
     * @param smsNotice
     * @return
     */
    @Override
    public Boolean sendAliNotice(String mobile, String templateCode, SmsNotice smsNotice) {

        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(templateCode)) {
            return false;
        }

        if (null != smsNotice) {

            String varOne = AliSmsVarEnum.getEnum(templateCode).getVarOne();
            String varTwo = AliSmsVarEnum.getEnum(templateCode).getVarTwo();

            Map<String, String> params = new HashMap<>();

            params.put(varOne, smsNotice.getVarOne());

            if (StringUtils.isNotEmpty(smsNotice.getVarTwo()) && StringUtils.isNotEmpty(varTwo)) {
                params.put(varTwo, smsNotice.getVarTwo());
            }
            sendSms(mobile, templateCode, params);
        } else {
            sendSms(mobile, templateCode, null);
        }
        return true;
    }

    /**
     * 发送阿里云验证码短信
     *
     * @param mobile
     * @param templateCode
     * @param code
     * @return
     */
    @Override
    public Boolean sendAliVcode(String mobile, String templateCode, String code) {
        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(templateCode) || StringUtils.isEmpty(code)) {
            return false;
        }

        Map<String, String> params = new HashMap<>();
        params.put("code", code);

        Boolean result = sendSms(mobile, templateCode, params);
        if (result) {
            return true;
        }else {
            return false;
        }
    }


    private Boolean sendSms(String mobile, String templateCode, Map<String, String> params) {

        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(templateCode)) {
            return false;
        }

        SendSmsRequest request = new SendSmsRequest();

        try {
            //必填:待发送手机号
            request.setPhoneNumbers(mobile);
            //必填:短信签名-可在短信控制台中找到
            request.setSignName(SIGN_NAME);
            //必填:短信模板-可在短信控制台中找到
            request.setTemplateCode(templateCode);

            if (null != params) {
                request.setTemplateParam(JSON.toJSONString(params));
            }
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

            if (null != sendSmsResponse) {
                GwsLogger.info("阿里云短信服务返回报文内容是:" + JSON.toJSONString(sendSmsResponse));
                if ("OK".equalsIgnoreCase(sendSmsResponse.getCode())) {
                    GwsLogger.info("给" + mobile + "发送短信成功");
                    return true;
                } else {
                    GwsLogger.error("给" + mobile + "发送短信失败！错误码是：" +
                            sendSmsResponse.getCode() + ",错误提示信息是：" + sendSmsResponse.getMessage());
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            GwsLogger.error("给手机{}发送短信失败,原因={}", mobile, e.getMessage());
            return false;
        }
    }
}
