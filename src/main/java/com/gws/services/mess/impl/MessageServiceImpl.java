package com.gws.services.mess.impl;

import com.gws.entity.xinge.MessagesRecord;
import com.gws.entity.xinge.NoticesRecord;
import com.gws.entity.xinge.OperationMessRecord;
import com.gws.enums.DeleteEnum;
import com.gws.repositories.master.mess.MessagesRecordMaster;
import com.gws.repositories.master.mess.NoticesRecordMaster;
import com.gws.repositories.master.mess.OperationMessRecordMaster;
import com.gws.repositories.query.mess.MessagesRecordQuery;
import com.gws.repositories.query.mess.NoticesRecordQuery;
import com.gws.repositories.slave.mess.MessagesRecordSlave;
import com.gws.repositories.slave.mess.NoticesRecordSlave;
import com.gws.services.mess.MessageService;

import com.gws.utils.cache.IdGlobalGenerator;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * 消息记录管理的实现类
 * @author : Kumamon 熊本同学
 * @Description:
 * @Modified By:
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessagesRecordMaster messagesRecordMaster;

    @Autowired
    private NoticesRecordMaster noticesRecordMaster;

    @Autowired
    private OperationMessRecordMaster operationMessRecordMaster;

    @Autowired
    private MessagesRecordSlave messagesRecordSlave;

    @Autowired
    private NoticesRecordSlave noticesRecordSlave;

    @Autowired
    private IdGlobalGenerator idGen;


    @Override
    public MessagesRecord saveMessagesRecord(MessagesRecord messagesRecord) {

        if (null == messagesRecord || null != messagesRecord.getId()){
            return null;
        }

        messagesRecord.setId(idGen.getSeqId(MessagesRecord.class));
        MessagesRecord record = messagesRecordMaster.saveAndFlush(messagesRecord);

        return record;
    }

    @Override
    public List<MessagesRecord> saveMessagesRecord(List<MessagesRecord> messagesRecords) {

        if (CollectionUtils.isEmpty(messagesRecords)){
            return Collections.EMPTY_LIST;
        }

        List<MessagesRecord> records = messagesRecordMaster.save(messagesRecords);

        return CollectionUtils.isEmpty(records) ? Collections.EMPTY_LIST : records;
    }

    @Override
    public NoticesRecord saveNoticesRecord(NoticesRecord noticesRecord) {
        if (null == noticesRecord || null != noticesRecord.getId()){
            return null;
        }

        noticesRecord.setId(idGen.getSeqId(NoticesRecord.class));
        NoticesRecord record = noticesRecordMaster.saveAndFlush(noticesRecord);

        return record;
    }

    @Override
    public List<MessagesRecord> listMessagesRecord(Long uid, DeleteEnum deleteEnum) {

        if (null == uid || null == deleteEnum){
            return null;
        }

        MessagesRecordQuery query = new MessagesRecordQuery();
        query.setUid(uid);
        query.setIsDelete(deleteEnum.getCode());

        List<MessagesRecord> messagesRecords = messagesRecordSlave.findAll(query);

        return CollectionUtils.isEmpty(messagesRecords) ? Collections.EMPTY_LIST : messagesRecords;
    }

    @Override
    public List<NoticesRecord> listNoticesRecord(Integer minTime, Integer maxTime, DeleteEnum deleteEnum) {

        if (null == minTime || null == deleteEnum){
            return Collections.EMPTY_LIST;
        }

        NoticesRecordQuery query = new NoticesRecordQuery();
        query.setMinTime(minTime);
        if (null != maxTime){
            query.setMaxTime(maxTime);
        }
        query.setIsDelete(deleteEnum.getCode());

        List<NoticesRecord> noticesRecords = noticesRecordSlave.findAll(query);

        return CollectionUtils.isEmpty(noticesRecords) ? Collections.EMPTY_LIST : noticesRecords;
    }

    @Override
    public OperationMessRecord saveOperationMessRecord(OperationMessRecord record) {
        if (null == record || null != record.getId()){
            return null;
        }

        record.setId(idGen.getSeqId(NoticesRecord.class));

        return operationMessRecordMaster.saveAndFlush(record);
    }
}
