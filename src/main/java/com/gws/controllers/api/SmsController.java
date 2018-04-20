package com.gws.controllers.api;

import com.gws.base.annotation.Anonymous;
import com.gws.controllers.BaseApiController;
import com.gws.controllers.JsonResult;
import com.gws.dto.sms.SmsNotice;
import com.gws.services.sms.SmsService;
import com.gws.utils.json.JsonParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 阿里云短信接口
 * @author : 王栋
 * @Description:
 * @Modified By:
 */
@RestController
@RequestMapping("/api/sms/")
public class SmsController extends BaseApiController {

    @Autowired
    private SmsService smsService;

    /**
     * 发送通知短信
     */
    @RequestMapping("sendAliNotice")
    public JsonResult sendAliNotice(String mobile, String templateCode, @JsonParam SmsNotice smsNotice){

        Boolean result = smsService.sendAliNotice(mobile,templateCode,smsNotice);

        return success(result);
    }


    /**
     * 发送验证码短信
     */
    @RequestMapping("sendAliVcode")
    public JsonResult sendAliVcode(String mobile, String templateCode, String code){

        Boolean result = smsService.sendAliVcode(mobile,templateCode,code);

        return success(result);
    }
}
