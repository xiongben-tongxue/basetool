package com.gws.controllers.api;

import com.gws.controllers.BaseApiController;
import com.gws.controllers.JsonResult;
import com.gws.dto.OperationResult;
import com.gws.services.mq.MqManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author:wangdong
 * @description:前台用户
 */
@RestController
@RequestMapping("/api/front/user/")
public class UserController extends BaseApiController {

    @Autowired
    private MqManageService mqManageService;

    /**
     * 根据Uid获取个人信息
     * @param uid
     * @return
     */
    @RequestMapping("getUserBaseInfo")
    public JsonResult getUserBaseInfo(Long uid){

       return null;
    }

    /**
     * 修改手机
     * @param mobile
     * @param vcode
     * @return
     */
    @RequestMapping("changeMobile")
    public JsonResult changeMobile(String mobile, String vcode){

        return null;
    }

    /**
     * 注册
     * @param mobile
     * @return
     */
    @RequestMapping("login")
    public JsonResult login(String mobile){
        //1.判断手机号是否已经注册
        //2.查数据库是否有这个手机号
        //3.有的话，就提示已经注册请登陆
        //4.没有的话，就
        return null;
    }

}
