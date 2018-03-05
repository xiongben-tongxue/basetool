package com.gws.controllers.api;

import com.gws.base.annotation.Anonymous;
import com.gws.controllers.BaseApiController;
import com.gws.controllers.JsonResult;
import com.gws.dto.message.HasNewMess;
import com.gws.dto.message.MessageResult;
import com.gws.dto.OperationResult;
import com.gws.services.mess.MessageManageService;
import com.gws.utils.json.JsonParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 信鸽的基础消息接口
 * @author : Kumamon 熊本同学
 * @Description:
 * @Modified By:
 */
@RestController
@RequestMapping("/api/message/")
public class MessageApiController extends BaseApiController{

    @Autowired
    private MessageManageService messageManageService;

    /**
     * 个推
     * 根据uid推
     * 推安卓设备
     * 与个人消息相关的通知
     */
    @Anonymous(true)
    @RequestMapping("andSendMessageByUid")
    public JsonResult andSendMessageByUid(Long uid, String messTitle, Integer messType, String messContent,
                                           Long accountId, String sender, String url){

        OperationResult<Boolean> result = messageManageService.andSendMessageByUid(uid, messTitle, messType, messContent, accountId, sender, url);

        if (result.getSucc()){
            return success(result.getEntity());
        }

        return error(result.getErrorCode());
    }

    /**
     * 个推
     * 根据uid推
     * 推IOS设备和安卓
     * 与个人消息相关的通知
     */
    @Anonymous(true)
    @RequestMapping("sendMessToAllByUid")
    public JsonResult sendMessToAllByUid(Long uid, String messTitle, Integer messType, String messContent,
                                         Long accountId, String sender, String url){

        OperationResult<Boolean> result = messageManageService.sendMessToAllByUid(uid, messTitle, messType, messContent, accountId, sender, url);

        if (result.getSucc()){
            return success(result.getEntity());
        }

        return error(result.getErrorCode());
    }

    /**
     * 批量推
     * 根据uid的集合推
     * 推安卓设备
     * 与个人消息相关的通知
     */
    @Anonymous(true)
    @RequestMapping("andSendMessageByUids")
    public JsonResult andSendMessageByUids(@JsonParam List<Long> uids, String messTitle,
                                           Integer messType, String messContent, Long accountId, String sender, String url){

        OperationResult<Boolean> result = messageManageService.andSendMessageByUids(uids, messTitle, messType, messContent, accountId, sender, url, null);

        if (result.getSucc()){
            return success(result.getEntity());
        }

        return error(result.getErrorCode());
    }


    /**
     * 批量推
     * 根据uid的集合推
     * 推IOS设备和安卓
     * 与个人消息相关的通知
     */
    @Anonymous(true)
    @RequestMapping("sendMessToAllByUids")
    public JsonResult sendMessToAllByUids(@JsonParam List<Long> uids, String messTitle, Integer messType,
                                          String messContent, Long accountId, String sender, String url,String accounts){

        OperationResult<Boolean> result = messageManageService.sendMessToAllByUids(uids, messTitle, messType, messContent, accountId, sender, url, accounts);

        if (result.getSucc()){
            return success(result.getEntity());
        }

        return error(result.getErrorCode());
    }


    /**
     * 广播
     * 推安卓设备
     * 推通知类消息广播
     */
    @Anonymous(true)
    @RequestMapping("pushNoticesToAndroid")
    public JsonResult pushNoticesToAndroid(String messTitle, Integer messType, String messContent,
                                           Long accountId, String sender, String url){

        OperationResult<Boolean> result = messageManageService.pushNoticesToAndroid(messTitle, messType, messContent, accountId, sender, url, null);

        if (result.getSucc()){
            return success(result.getEntity());
        }

        return error(result.getErrorCode());
    }

    /**
     * 广播
     * 推IOS设备和安卓设备
     * 推通知类消息广播
     */
    @Anonymous(true)
    @RequestMapping("pushNoticesToAll")
    public JsonResult pushNoticesToAll(String messTitle, Integer messType, String messContent,
                                       Long accountId, String sender, String url,String accounts){

        OperationResult<Boolean> result = messageManageService.pushNoticesToAll(messTitle, messType, messContent, accountId, sender, url, accounts);

        if (result.getSucc()){
            return success(result.getEntity());
        }

        return error(result.getErrorCode());
    }

    /**
     * 根据uid获取消息信息
     * 给APP使用
     */
    @Anonymous(true)
    @RequestMapping("listMessagesByUid")
    public JsonResult listMessagesByUid(Long uid, Integer pageSize, Integer currentPage){

        OperationResult<MessageResult> result = messageManageService.listMessagesByUid(uid, pageSize, currentPage);

        if (result.getSucc()){
            return success(result.getEntity());
        }

        return error(result.getErrorCode());
    }

    /**
     * 判断是都有新消息
     * 给APP使用
     */
    @Anonymous(true)
    @RequestMapping("hasNewMessage")
    public JsonResult hasNewMessage(Long uid, Integer ctime){

        OperationResult<HasNewMess> result = messageManageService.hasNewMessage(uid, ctime);

        if (result.getSucc()){
            return success(result.getEntity());
        }

        return error(result.getErrorCode());
    }

}
