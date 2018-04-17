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
     * @return
     */
    @RequestMapping("getUserBaseInfo")
    public JsonResult getUserBaseInfo(){
        //uid不需要传，自己获取
        //1.需要的字段uid,手机号,邮箱,身份认证
        //2.查询user_base_info，获取用户的uid、手机号、邮箱号
        //3.判断手机号是否有，有（已认证），没有（去认证）
        //4.判断邮箱号是否有，有（已认证），没有(去认证)
        //5.查询user_identity，判断是否有该用户的身份认证信息，有（已认证），没有（去认证）
        //6.数据聚合，拼装，返回给前端
       return null;
    }

    /**
     * 获取用户最近的登陆历史
     * @return
     */
    @RequestMapping("getUserLoginHistory")
    public JsonResult getLoginHistory(){
        //uid不需要传，自己获取
        //1.需要字段：登陆时间、登陆方式、登陆的Ip
        //2.查询user_login_history
        //4.获取用户的登陆时间、登陆方式、IP
        //5.返回给前端
        return null;
    }

    /**
     * 获取用户的安全信息
     * @return
     */
    @RequestMapping("getSecuritySettings")
    public JsonResult getSecuritySettings(){
        //uid不需要传，自己获取
        //1.需要的字段：用户名、邮箱、手机号、登陆密码
        //2.查询user_base_info，获取用户的uid、用户名、手机号、邮箱号
        // 手机号和邮箱有（前端隐藏部分信息显示修改），没有（显示去设置）
        //3.查询user_pwd，查看用户是否已经设置过密码,有（隐藏），没有（显示去设置）
        //4.数据拼装返回给前端
        return null;
    }

    /**
     * 在处理邮箱业务的时候，发送短信验证码
     * 包括绑定或者修改邮箱时候
     * @param emailAddress
     * @return
     */
    @RequestMapping("getEcodeChangeEmail")
    public JsonResult getEcodeChangeEmail(String emailAddress){
        //uid不需要传，自己获取
        //1.在处理邮箱业务的时候
        //2.调用指定的模版，发送与邮件业务相关的验证码
        //3.将发送成功的验证码，写入到redis中
        return null;
    }



    /**
     * 修改邮箱
     * @param emailAddress
     * @param vcode
     * @return
     */
    @RequestMapping("changeUserEmail")
    public JsonResult changeUserEmail(String emailAddress, String vcode, String useMobile){
        //uid不需要传，自己获取
        //1.从redis中取出验证码，校验输入的验证码是否正确
        //2.验证码正确，往下走，错误，返回错误信息
        //3.根据用户的uid，查询user_base_info，获取用户旧的邮箱地址
        //4.将用户的邮件修改申请数据写入到user_changeinfo_apply
        return null;
    }

    /**
     * 在修改手机业务的时候，发送验证码
     * @param mobile
     * @return
     */
    @RequestMapping("getMcodeChangeMobile")
    public JsonResult getMcodeChangeMobile(String mobile){
        //uid不需要传，自己获取
        //1.在处理修改手机号业务的时候
        //2.调用指定的模版，发送与修改手机业务相关的短信验证码
        //3.将发送成功的验证码，写入到redis中
        return null;
    }

    /**
     * 修改手机
     * @param mobile
     * @param vcode
     * @return
     */
    @RequestMapping("changeUserMobile")
    public JsonResult changeUserMobile(String mobile, String vcode, String useMobile){
        //uid不需要传，自己获取
        //1.从redis中取出验证码，校验输入的验证码是否正确
        //2.验证码正确，往下走，错误，返回错误信息
        //3.根据用户的uid，查询user_base_info，获取用户旧的手机号
        //4.将用户的邮件修改申请数据写入到user_changeinfo_apply
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
