package com.gws.services.mess;


import com.gws.entity.xinge.MessagesRecord;
import com.gws.entity.xinge.NoticesRecord;
import com.gws.entity.xinge.OperationMessRecord;
import com.gws.enums.DeleteEnum;

import java.util.List;

/**
 * 消息记录的管理service
 * @author : Kumamon 熊本同学
 * @Description:
 * @Modified By:
 */
public interface MessageService {


    /**
     * 单个保存
     * 保存messageRecord
     * @param messagesRecord
     * @return
     */
    MessagesRecord saveMessagesRecord(MessagesRecord messagesRecord);


    /**
     * 批量保存
     * 保存messageRecord
     * @return
     */
    List<MessagesRecord> saveMessagesRecord(List<MessagesRecord> messagesRecords);


    /**
     * 保存系统消息
     * @param noticesRecord
     * @return
     */
    NoticesRecord saveNoticesRecord(NoticesRecord noticesRecord);

    /**
     * 获取个人消息记录
     * @param uid
     * @param deleteEnum
     * @return
     */
    List<MessagesRecord> listMessagesRecord(Long uid, DeleteEnum deleteEnum);

    /**
     * 根据时间区间获取
     * @param minTime 最早的时间
     * @param maxTime 最新的时间
     * @param deleteEnum
     * @return
     */
    List<NoticesRecord> listNoticesRecord(Integer minTime, Integer maxTime, DeleteEnum deleteEnum);

    /**
     * 保存运营消息
     * @param record
     * @return
     */
    OperationMessRecord saveOperationMessRecord(OperationMessRecord record);
}
