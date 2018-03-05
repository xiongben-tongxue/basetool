package com.gws.services.mess;

import com.gws.dto.message.HasNewMess;
import com.gws.dto.message.MessageResult;
import com.gws.dto.OperationResult;
import com.gws.dto.message.UserMessage;
import com.gws.entity.xinge.MessagesRecord;
import com.gws.entity.xinge.NoticesRecord;
import com.gws.enums.DeleteEnum;

import java.util.List;

/**
 * 信鸽的管理service
 * @author : Kumamon 熊本同学
 * @Description:
 * @Modified By:
 */
public interface MessageManageService {

    /**
     *
     * 个推
     * 根据uid推
     * 推安卓设备
     * 与个人消息相关的通知
     *  @param uid
     * @param messTitle
     * @param messType
     * @param messContent
     * @param accountId @return
     * @param sender
     * @param url                         */
    OperationResult<Boolean> andSendMessageByUid(Long uid, String messTitle, Integer messType,
                                                 String messContent, Long accountId, String sender, String url);

    /**
     * 批量推
     * 根据uid的集合推
     * 推安卓设备
     * 与个人消息相关的通知
     */
    OperationResult<Boolean> andSendMessageByUids(List<Long> uids, String messTitle,
                                                  Integer messType, String messContent, Long accountId, String sender, String url, Long operationId);

    /**
     * 批量推
     * 根据uid的集合推
     * 推安卓设备
     * 与个人消息相关的通知
     * 不保存
     */
    OperationResult<Boolean> andSendMessByUidsNoSave(List<Long> uids, String messTitle,
                                                  Integer messType, String messContent, Long accountId, String sender, String url, Long operationId);


    /**
     * 单个uid的保存
     * 保存MessagesRecord
     * @param uid
     * @param messTitle
     * @param messType
     * @param osType
     *@param accountId
     * @param sender
     * @param url
     * @param messContent
     * @return
     */
    OperationResult<MessagesRecord> saveMessagesRecord(Long uid, String messTitle, Integer messType,
                                                       Integer osType, Long accountId, String sender, String url, String messContent);

    /**
     * 多个uid的保存
     * 保存MessagesRecord
     * @param uids
     * @param messTitle
     * @param messType
     * @param osType
     * @param accountId
     * @param sender
     * @param url
     * @param messContent
     * @param operationId
     * @return
     * */
    OperationResult<List<MessagesRecord>> saveMessagesRecord(List<Long> uids, String messTitle, Integer messType,
                                                             Integer osType, Long accountId, String sender, String url, String messContent, Long operationId);


    /**
     * 个推
     * 根据UID推送给安卓和IOS
     * @param uid
     * @param messTitle
     * @param messType
     * @param messContent
     * @param accountId
     * @param sender
     * @param url
     * @return
     */
    OperationResult<Boolean> sendMessToAllByUid(Long uid, String messTitle, Integer messType,
                                                String messContent, Long accountId, String sender, String url);

    /**
     * 批量推
     * 根据uid的集合推
     * 推IOS设备
     * 与个人消息相关的通知
     * @param uids
     * @param messTitle
     * @param messType
     * @param messContent
     * @param accountId
     * @param sender
     * @param url
     * @param accounts
     * @return
     */
    OperationResult<Boolean> sendMessToAllByUids(List<Long> uids, String messTitle, Integer messType, String messContent, Long accountId, String sender, String url, String accounts);

    /**
     * 广播
     * 推安卓设备
     * 推通知类消息广播
     * @param messTitle
     * @param messType
     * @param messContent
     * @param accountId
     * @param sender
     * @param url
     * @param operationId
     * @return
     */
    OperationResult<Boolean> pushNoticesToAndroid(String messTitle, Integer messType, String messContent, Long accountId, String sender, String url, Long operationId);

    /**
     * 广播
     * 推安卓设备
     * 推通知类消息广播
     * 不执行保存操作
     * @param messTitle
     * @param messType
     * @param messContent
     * @param accountId
     * @param sender
     * @param url
     * @param operationId
     * @return
     */
    OperationResult<Boolean> pushNotiToAndNoSave(String messTitle, Integer messType, String messContent, Long accountId, String sender, String url, Long operationId);

    /**
     * 保存系统消息通知
     * @param messTitle
     * @param messType
     * @param messContent
     * @param accountId
     * @param sender
     * @param url
     * @param osType
     * @param operationId
     * @return
     */
    OperationResult<NoticesRecord> saveNoticesRecord(String messTitle, Integer messType,
                                                     String messContent, Long accountId, String sender, String url, Integer osType, Long operationId);

    /**
     * 广播
     * 推安卓和ios设备
     * 推通知类消息广播
     * @param messTitle
     * @param messType
     * @param messContent
     * @param accountId
     * @param sender
     * @param url
     * @param accounts
     * @return
     */
    OperationResult<Boolean> pushNoticesToAll(String messTitle, Integer messType, String messContent, Long accountId, String sender, String url, String accounts);


    /**
     * 根据用户的uid获取用户的消息
     * @param uid
     * @param pageSize
     * @param currentPage
     * @return
     * */
    OperationResult<MessageResult> listMessagesByUid(Long uid, Integer pageSize, Integer currentPage);

    /**
     * 根据用户的uid获取用户的信息
     * @param uid
     * @return
     */
    List<UserMessage> listMessageResult(Long uid);

    /**
     * 保存运营消息
     * @param messTitle
     * @param messType
     * @param messContent
     * @param sender
     * @param accounts
     * @param deleteEnum
     * @param url
     * @return
     */
    OperationResult<Long> saveOperationMessRecord(String messTitle, Integer messType, String messContent, String sender, String accounts,DeleteEnum deleteEnum,String url);

    /**
     * 判断是否为最新的消息
     * @param uid
     * @param ctime
     * @return
     */
    OperationResult<HasNewMess> hasNewMessage(Long uid, Integer ctime);
}
