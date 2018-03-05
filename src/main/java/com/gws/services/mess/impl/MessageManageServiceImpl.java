package com.gws.services.mess.impl;

import com.gws.dto.message.HasNewMess;
import com.gws.dto.message.MessageResult;
import com.gws.dto.OperationResult;
import com.gws.dto.message.UserMessage;
import com.gws.entity.xinge.MessagesRecord;
import com.gws.entity.xinge.NoticesRecord;
import com.gws.entity.xinge.OperationMessRecord;
import com.gws.enums.*;
import com.gws.services.mess.MessageService;
import com.gws.services.mess.MessageManageService;
import com.gws.utils.DateUtil;
import com.gws.utils.GwsLogger;
import com.gws.utils.ListPageUtil;
import com.gws.utils.cache.IdGlobalGenerator;
import com.tencent.xinge.Message;
import com.tencent.xinge.XingeApp;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 信鸽管理的实现类
 * @author : Kumamon 熊本同学
 * @Description:
 * @Modified By:
 */
@Service
public class MessageManageServiceImpl implements MessageManageService {

    @Autowired
    private IdGlobalGenerator idGen;

    /**iosAccessId*/
    @Value("${ios.accessId}")
    private Long iosAccessId;

    /**iosSecretKey*/
    @Value("${ios.secretKey}")
    private String iosSecretKey;

    /**androidAccessId*/
    @Value("${android.accessId}")
    private Long andAccessId;

    /**androidsecretKey*/
    @Value("${android.secretKey}")
    private String andSecretKey;

    /**成功码*/
    private Integer succCode = 0;

    /**默认值为0,无需配置和更改*/
    private Integer deviceType = 0;

    /**超时时间*/
    @Value("${message.expireTime}")
    private Integer expireTime;

    /**ios消息环境*/
    @Value("${ios.environment}")
    private Integer iosEnvironment;

    /**
     * 信鸽消息成功码
     */
    private String retCode = "ret_code";


    @Autowired
    private MessageService messageService;

    @Override
    public OperationResult<Boolean> andSendMessageByUid(Long uid, String messTitle, Integer messType, String messContent, Long accountId, String sender, String url) {

        if (null == uid || StringUtils.isEmpty(messTitle) || null == messType ||
                StringUtils.isEmpty(messContent) ||  StringUtils.isEmpty(sender)){
            return new OperationResult<>(BizErrorCode.PARM_ERROR);
        }

        OperationResult<MessagesRecord> result = saveMessagesRecord(uid,messTitle,messType,OsTypeEnum.Android.getCode() , accountId,sender,url, messContent);

        XingeApp xingeApp = new XingeApp(andAccessId, andSecretKey);
        Message message = new Message();
        message.setType(Message.TYPE_NOTIFICATION);
        message.setExpireTime(expireTime);

        message.setTitle(messTitle);
        message.setContent(messContent);
        //附加参数
        Map<String,Object> paramMap = new HashMap<>();
        //yi ding yao string
        paramMap.put("uid",uid.toString());
        paramMap.put("messType",messType.toString());
        paramMap.put("accountId",accountId.toString());
        paramMap.put("sender",sender);
        paramMap.put("url",url);
        message.setCustom(paramMap);

        JSONObject ret = xingeApp.pushSingleAccount(deviceType, uid.toString(), message);
        GwsLogger.info("发送安卓的消息标题{}内容{}消息状态码{}",messTitle,messContent,ret.get(retCode));
        if(result.getSucc()){
            return new OperationResult<>(true);
        }else {
            return new OperationResult<>(BizErrorCode.SAVE_FAIL);
        }
    }

    @Override
    public OperationResult<Boolean> andSendMessageByUids(List<Long> uids, String messTitle, Integer messType, String messContent, Long accountId, String sender, String url, Long operationId) {

        if (CollectionUtils.isEmpty(uids) || StringUtils.isEmpty(messTitle) || null == messType ||
                StringUtils.isEmpty(messContent) ||  StringUtils.isEmpty(sender)){
            return new OperationResult<>(BizErrorCode.PARM_ERROR);
        }

        OperationResult<List<MessagesRecord>> result = saveMessagesRecord(uids,messTitle,messType, OsTypeEnum.Android.getCode(), accountId,sender,url, messContent, operationId);

        XingeApp xingeApp = new XingeApp(andAccessId, andSecretKey);
        Message message = new Message();
        message.setType(Message.TYPE_NOTIFICATION);
        message.setExpireTime(expireTime);

        message.setTitle(messTitle);
        message.setContent(messContent);
        //附加参数
        Map<String,Object> paramMap = new HashMap<>();
        //yi ding yao string
        paramMap.put("uids",uids.toString());
        paramMap.put("messType",messType.toString());
        if (null != accountId) {
            paramMap.put("accountId", accountId.toString());
        }
        paramMap.put("sender",sender);
        if (StringUtils.isNotEmpty(url)) {
            paramMap.put("url", url);
        }
        message.setCustom(paramMap);

        List<String> accountList = new ArrayList<>();
        uids.forEach(uid -> {
            accountList.add(uid.toString());
        });

        JSONObject ret = xingeApp.pushAccountList(deviceType, accountList, message);

        GwsLogger.info("发送安卓的消息标题{}内容{}消息状态码{}",messTitle,messContent,ret.get(retCode));

        if(result.getSucc()){
            return new OperationResult<>(true);
        }else {
            return new OperationResult<>(BizErrorCode.SAVE_FAIL);
        }
    }

    @Override
    public OperationResult<Boolean> andSendMessByUidsNoSave(List<Long> uids, String messTitle, Integer messType, String messContent, Long accountId, String sender, String url, Long operationId) {

        if (CollectionUtils.isEmpty(uids) || StringUtils.isEmpty(messTitle) || null == messType ||
                StringUtils.isEmpty(messContent) ||  StringUtils.isEmpty(sender)){
            return new OperationResult<>(BizErrorCode.PARM_ERROR);
        }
        XingeApp xingeApp = new XingeApp(andAccessId, andSecretKey);
        Message message = new Message();
        message.setType(Message.TYPE_NOTIFICATION);
        message.setExpireTime(expireTime);

        message.setTitle(messTitle);
        message.setContent(messContent);
        //附加参数
        Map<String,Object> paramMap = new HashMap<>();
        //yi ding yao string
        paramMap.put("uids",uids.toString());
        paramMap.put("messType",messType.toString());
        if (null != accountId) {
            paramMap.put("accountId", accountId.toString());
        }
        paramMap.put("sender",sender);
        if (StringUtils.isNotEmpty(url)) {
            paramMap.put("url", url);
        }
        message.setCustom(paramMap);

        List<String> accountList = new ArrayList<>();
        uids.forEach(uid -> {
            accountList.add(uid.toString());
        });

        JSONObject ret = xingeApp.pushAccountList(deviceType, accountList, message);

        GwsLogger.info("发送安卓的消息标题{}内容{}消息状态码{}",messTitle,messContent,ret.get(retCode));

        if(!ret.get(retCode).equals(succCode)){
            return new OperationResult<>(BizErrorCode.SEND_FAIL);
        }

        return new OperationResult<>(true);

    }

    @Override
    public OperationResult<MessagesRecord> saveMessagesRecord(Long uid, String messTitle, Integer messType,
                                                              Integer osType, Long accountId, String sender, String url, String messContent){
        if (null == uid || StringUtils.isEmpty(messTitle) || null == messType ||
                StringUtils.isEmpty(messContent) ||  StringUtils.isEmpty(sender) || null == osType){
            return new OperationResult<>(BizErrorCode.PARM_ERROR);
        }

        MessagesRecord messagesRecord = new MessagesRecord();
        messagesRecord.setUid(uid);
        messagesRecord.setTitle(messTitle);
        messagesRecord.setType(messType);
        messagesRecord.setOsType(osType);
        messagesRecord.setContent(messContent);
        messagesRecord.setAccountId(accountId);
        messagesRecord.setSender(sender);
        messagesRecord.setUrl(url);
        messagesRecord.setIsDelete(DeleteEnum.NOTDELETE.getCode());

        MessagesRecord record = messageService.saveMessagesRecord(messagesRecord);

        return new OperationResult<>(record);
    }

    @Override
    public OperationResult<List<MessagesRecord>> saveMessagesRecord(List<Long> uids, String messTitle, Integer messType, Integer osType, Long accountId, String sender, String url, String messContent, Long operationId){
        if (CollectionUtils.isEmpty(uids) || StringUtils.isEmpty(messTitle) || null == messType ||
                StringUtils.isEmpty(messContent) ||  StringUtils.isEmpty(sender)){
            return new OperationResult<>(BizErrorCode.PARM_ERROR);
        }
        List<MessagesRecord> messagesRecords = new ArrayList<>();
        uids.forEach(uid ->{
            MessagesRecord messagesRecord = new MessagesRecord();
            messagesRecord.setId(idGen.getSeqId(MessagesRecord.class));
            messagesRecord.setUid(uid);
            messagesRecord.setTitle(messTitle);
            messagesRecord.setType(messType);
            messagesRecord.setOsType(osType);
            messagesRecord.setContent(messContent);
            messagesRecord.setAccountId(accountId);
            messagesRecord.setSender(sender);
            messagesRecord.setUrl(url);
            messagesRecord.setIsDelete(DeleteEnum.NOTDELETE.getCode());
            messagesRecord.setOperationId(operationId);
            messagesRecords.add(messagesRecord);
        });


        List<MessagesRecord> records = messageService.saveMessagesRecord(messagesRecords);

        return new OperationResult<>(records);
    }



    @Override
    public OperationResult<Boolean> sendMessToAllByUid(Long uid, String messTitle, Integer messType, String messContent, Long accountId, String sender, String url) {
        if (null == uid || StringUtils.isEmpty(messTitle) || null == messType ||
                StringUtils.isEmpty(messContent) ||  StringUtils.isEmpty(sender)){
            return new OperationResult<>(BizErrorCode.PARM_ERROR);
        }

        OperationResult<Boolean> andResult = andSendMessageByUid(uid,messTitle,messType,messContent,accountId,sender,url);

        if (andResult.getSucc()){
            return new OperationResult<>(true);
        }else {
            return new OperationResult<>(false);
        }

    }

    @Override
    public OperationResult<Boolean> sendMessToAllByUids(List<Long> uids, String messTitle, Integer messType, String messContent, Long accountId, String sender, String url, String accounts) {

        if (CollectionUtils.isEmpty(uids) || StringUtils.isEmpty(messTitle) || null == messType ||
                StringUtils.isEmpty(messContent) ||  StringUtils.isEmpty(sender)){
            return new OperationResult<>(BizErrorCode.PARM_ERROR);
        }

        OperationResult<Long> result = saveOperationMessRecord(messTitle, messType, messContent, sender, accounts,DeleteEnum.NOTDELETE,url);
        Long operationId = null;
        if (null != result.getEntity()){
            operationId = result.getEntity();
        }

        //保存
        OperationResult<List<MessagesRecord>> record = saveMessagesRecord(uids, messTitle, messType, OsTypeEnum.ALLSYSTEM.getCode(), accountId, sender, url, messContent,operationId);

        //推安卓
        andSendMessByUidsNoSave(uids, messTitle, messType, messContent, accountId, sender, url, operationId);

        if (record.getSucc()){
            return new OperationResult<>(true);
        }else {
            return new OperationResult<>(false);
        }
    }

    @Override
    public OperationResult<Boolean> pushNoticesToAndroid(String messTitle, Integer messType, String messContent, Long accountId, String sender, String url, Long operationId) {

        if (StringUtils.isEmpty(messTitle) || null == messType ||
                StringUtils.isEmpty(messContent) ||  StringUtils.isEmpty(sender)){
            return new OperationResult<>(BizErrorCode.PARM_ERROR);
        }

        OperationResult<NoticesRecord> result = saveNoticesRecord(messTitle,messType,messContent,accountId,sender,url,OsTypeEnum.Android.getCode(), operationId);

        JSONObject ret = XingeApp.pushAllAndroid(andAccessId,andSecretKey,messTitle,messContent);
        
        GwsLogger.info("发送安卓的通知标题{}内容{}消息状态码{}",messTitle,messContent,ret.get(retCode));
        if(result.getSucc()){
            return new OperationResult<>(true);
        }else {
            return new OperationResult<>(BizErrorCode.SAVE_FAIL);
        }
    }

    @Override
    public OperationResult<Boolean> pushNotiToAndNoSave(String messTitle, Integer messType, String messContent, Long accountId, String sender, String url, Long operationId) {

        if (StringUtils.isEmpty(messTitle) || null == messType ||
                StringUtils.isEmpty(messContent) ||  StringUtils.isEmpty(sender)){
            return new OperationResult<>(BizErrorCode.PARM_ERROR);
        }

        JSONObject ret = XingeApp.pushAllAndroid(andAccessId,andSecretKey,messTitle,messContent);

        GwsLogger.info("发送安卓的通知标题{}内容{}消息状态码{}",messTitle,messContent,ret.get(retCode));
        if(!ret.get(retCode).equals(succCode)){
            return new OperationResult<>(BizErrorCode.SEND_FAIL);
        }else {
            return new OperationResult<>(true);
        }
    }


    @Override
    public OperationResult<Boolean> pushNoticesToAll(String messTitle, Integer messType, String messContent, Long accountId, String sender, String url, String accounts) {
        if (StringUtils.isEmpty(messTitle) || null == messType ||
                StringUtils.isEmpty(messContent) ||  StringUtils.isEmpty(sender)){
            return new OperationResult<>(BizErrorCode.PARM_ERROR);
        }
        OperationMessRecord record = conOperationMessRecord(messTitle, messType, messContent, sender, accounts,DeleteEnum.NOTDELETE.getCode(),url);
        //插入数据到运营消息的表
        OperationMessRecord operationResult = messageService.saveOperationMessRecord(record);
        Long operationId = null;
        if (null != operationResult) {
            operationId = operationResult.getId();
        }

        OperationResult<NoticesRecord> result = saveNoticesRecord(messTitle,messType,messContent,accountId,sender,url,OsTypeEnum.ALLSYSTEM.getCode(), operationId);

        //推安卓
        OperationResult<Boolean> andResult = pushNotiToAndNoSave(messTitle, messType, messContent, accountId, sender, url, operationId);
        if (null != result.getEntity()){
            return new OperationResult<>(true);
        }else {
            return new OperationResult<>(false);
        }
    }

    private OperationMessRecord conOperationMessRecord(String messTitle, Integer messType, String messContent, String sender, String accounts,Integer isDelete,String url){
        String allReceiver = "所有人";
        OperationMessRecord operationMessRecord = new OperationMessRecord();
        operationMessRecord.setTitle(messTitle);
        operationMessRecord.setType(messType);
        operationMessRecord.setContent(messContent);
        operationMessRecord.setSender(sender);
        if (StringUtils.isEmpty(accounts)){
            operationMessRecord.setReceiver(allReceiver);
        }else {
            operationMessRecord.setReceiver(accounts);
        }
        operationMessRecord.setIsDelete(isDelete);
        operationMessRecord.setUrl(url);
        return operationMessRecord;
    }

    @Override
    public OperationResult<MessageResult> listMessagesByUid(Long uid, Integer pageSize, Integer currentPage) {

        if (null == uid || null == currentPage){
            return new OperationResult<>(BizErrorCode.PARM_ERROR);
        }
        MessageResult messageResult = new MessageResult();
        List<UserMessage> userMessageList = listMessageResult(uid);
        if (CollectionUtils.isEmpty(userMessageList)){
            messageResult.setHasNext(false);
            messageResult.setTotal(0);
            messageResult.setList(userMessageList);
            return new OperationResult<>(messageResult);
        }
        if (null == pageSize){
            pageSize = PageEnum.SIZE_10.getSize();
        }

        ListPageUtil indexPage = new ListPageUtil(userMessageList,pageSize);
        List<UserMessage> messages = indexPage.getPagedList(currentPage);
        List<UserMessage> messagesTwo = indexPage.getPagedList(currentPage+1);
        messageResult = conMessageResult(messages,messagesTwo);
        //判断是否有新消息
        if (CollectionUtils.isEmpty(messages)){
            return new OperationResult<>(BizErrorCode.NO_MORE_MESSAGE);
        }

        return new OperationResult<>(messageResult);
    }

    @Override
    public OperationResult<HasNewMess> hasNewMessage(Long uid, Integer ctime) {

        if (null == uid){
            return new OperationResult<>(BizErrorCode.PARM_ERROR);
        }
        HasNewMess hasNewMess = new HasNewMess();
        List<UserMessage> userMessageList = listMessageResult(uid);

        if (CollectionUtils.isEmpty(userMessageList)){
            hasNewMess.setCtime(ctime);
            hasNewMess.setHasNewMess(false);
            return new OperationResult<>(hasNewMess);
        }
        //列表中最新的时间
        Integer maxTime = userMessageList.get(0).getCtime();
        if (null == ctime || maxTime <= ctime){
            hasNewMess.setCtime(maxTime);
            hasNewMess.setHasNewMess(false);
            return new OperationResult<>(hasNewMess);
        }else {
            hasNewMess.setCtime(maxTime);
            hasNewMess.setHasNewMess(true);
            return new OperationResult<>(hasNewMess);
        }

    }

    @Override
    public List<UserMessage> listMessageResult(Long uid) {

        Integer halfYeartime = 60*60*24*180;
        List<UserMessage> userMessages = new ArrayList<>();

        //正序排的
        List<MessagesRecord> messagesRecords = messageService.listMessagesRecord(uid, DeleteEnum.NOTDELETE);

        messagesRecords.forEach(messagesRecord -> {
            UserMessage userMessage = new UserMessage();
            BeanUtils.copyProperties(messagesRecord,userMessage);
            userMessages.add(userMessage);
        });
        //现在的时间
        Integer maxTime = DateUtil.getUnixTimestamp();

        //半年前的时间
        Integer minTime = maxTime-halfYeartime;
        List<NoticesRecord> noticesRecords = messageService.listNoticesRecord(minTime,maxTime, DeleteEnum.NOTDELETE);

        noticesRecords.forEach(noticesRecord -> {
            UserMessage userMessage = new UserMessage();
            BeanUtils.copyProperties(noticesRecord,userMessage);
            userMessages.add(userMessage);
        });

        if (CollectionUtils.isEmpty(userMessages)){
            return Collections.EMPTY_LIST;
        }
        userMessages.forEach(userMessage -> {
            Integer ctime = userMessage.getCtime();
            userMessage.setTimePoint(conDiffTime(ctime));
        });

        List<UserMessage> userMessageList = userMessages.stream().sorted(Comparator.comparing(UserMessage :: getCtime).reversed()).collect(Collectors.toList());
        return userMessageList;
    }



    @Override
    public OperationResult<Long> saveOperationMessRecord(String messTitle, Integer messType, String messContent, String sender, String accounts, DeleteEnum deleteEnum, String url) {

        OperationMessRecord operationMessRecord = new OperationMessRecord();
        operationMessRecord.setTitle(messTitle);
        operationMessRecord.setType(messType);
        operationMessRecord.setContent(messContent);
        operationMessRecord.setSender(sender);
        operationMessRecord.setReceiver(accounts);
        operationMessRecord.setIsDelete(deleteEnum.getCode());
        operationMessRecord.setUrl(url);

        //插入数据到运营消息的表
        OperationMessRecord operationResult = messageService.saveOperationMessRecord(operationMessRecord);
        if (null == operationMessRecord){
            return new OperationResult<>(BizErrorCode.SAVE_FAIL);
        }else {
            return new OperationResult<>(operationMessRecord.getId());
        }

    }

    //将list转成messageResult
    public MessageResult conMessageResult(List<UserMessage> userMessages,List<UserMessage> messagesTwo){
        MessageResult messageResult = new MessageResult();
        if (CollectionUtils.isNotEmpty(userMessages) && CollectionUtils.isNotEmpty(messagesTwo)){
            messageResult.setHasNext(true);
            messageResult.setTotal(userMessages.size());
            messageResult.setList(userMessages);
            messageResult.setCtime(userMessages.get(0).getCtime());
            return messageResult;
        }else if (CollectionUtils.isNotEmpty(userMessages) && CollectionUtils.isEmpty(messagesTwo)){
            messageResult.setHasNext(false);
            messageResult.setTotal(userMessages.size());
            messageResult.setList(userMessages);
            messageResult.setCtime(userMessages.get(0).getCtime());
            return messageResult;
        }else {
            messageResult.setHasNext(false);
            messageResult.setTotal(0);
            messageResult.setList(messagesTwo);
            messageResult.setCtime(0);
            return messageResult;
        }
    }



    /**
     * 根据消息发送的时间,将它分成各个时间点
     * @param time
     * @return
     */
    public String conDiffTime(Integer time){
        //获取系统当前时间
        Integer unixTimestamp = DateUtil.getUnixTimestamp();

        Integer diffTime = unixTimestamp - time;
        Integer dPlace = 2;
        Integer zero = 0;
        Integer min = 60;
        Integer hour = 60*60;
        Integer day = 60*60*24;
        //获取昨天零点
        Integer yesterEvening = DateUtil.getTimeEvening() - day;
        //现在的24小时之前
        Integer yesterNowTime = unixTimestamp - day;

        //一小时前
        if (diffTime > zero && diffTime < hour){
            Long dTime = Math.round(new BigDecimal((float)diffTime/min).setScale(dPlace,BigDecimal.ROUND_HALF_UP).doubleValue());
            return dTime + TimePointEnum.MINUTE.getMessage();
        }else if (diffTime > hour && diffTime < day){
            Long dTime = Math.round(new BigDecimal((float)diffTime/hour).setScale(dPlace,BigDecimal.ROUND_HALF_UP).doubleValue());
            return dTime + TimePointEnum.HOUR.getMessage();
        }else if (time > yesterEvening && time < yesterNowTime){
            return TimePointEnum.YESTERDAY.getMessage();
        }else {
            return DateUtil.formatDate(time);
        }

    }

    @Override
    public OperationResult<NoticesRecord> saveNoticesRecord(String messTitle, Integer messType, String messContent, Long accountId, String sender, String url, Integer osType, Long operationId) {
        if (StringUtils.isEmpty(messTitle) || null == messType ||
                StringUtils.isEmpty(messContent) ||  StringUtils.isEmpty(sender) || null == osType){
            return new OperationResult<>(BizErrorCode.PARM_ERROR);
        }

        NoticesRecord noticesRecord = new NoticesRecord();
        noticesRecord.setTitle(messTitle);
        noticesRecord.setType(messType);
        noticesRecord.setContent(messContent);
        noticesRecord.setAccountId(accountId);
        noticesRecord.setSender(sender);
        noticesRecord.setUrl(url);
        noticesRecord.setOsType(osType);
        noticesRecord.setIsDelete(DeleteEnum.NOTDELETE.getCode());
        noticesRecord.setOperationId(operationId);

        NoticesRecord record = messageService.saveNoticesRecord(noticesRecord);

        return new OperationResult<>(record);
    }
}
