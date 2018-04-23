package com.gws.controllers.api;

import com.gws.controllers.BaseController;
import com.gws.controllers.JsonResult;
import com.gws.services.mail.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : 王栋
 * @Description:阿里云邮件接口
 * @Modified By:
 */
@RestController
@RequestMapping("/api/mail/")
public class MailController extends BaseController {

    @Autowired
    private MailService mailService;

    /**
     * 发送邮件，采用API的方式发送邮件
     * @param toAddress
     * @param subject
     * @param htmlBody
     * @return
     */
    @RequestMapping("sendAliMailByApi")
    public JsonResult sendAliMailByApi(String toAddress, String subject, String htmlBody){

        Boolean result = mailService.sendAliMailByApi(toAddress,subject,htmlBody);

        return success(result);
    }


    /**
     * 发送邮件，采用Smtp的方式发送邮件
     * @param toAddress
     * @param subject
     * @param htmlBody
     * @return
     */
    @RequestMapping("sendAliMailBySmtp")
    public JsonResult sendAliMailBySmtp(String toAddress, String subject, String htmlBody){

        Boolean result = mailService.sendAliMailBySmtp(toAddress,subject,htmlBody);

        return success(result);
    }
}
