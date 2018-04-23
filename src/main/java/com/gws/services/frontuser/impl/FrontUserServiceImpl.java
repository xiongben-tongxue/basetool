package com.gws.services.frontuser.impl;

import com.google.common.collect.Lists;
import com.gws.dto.OperationResult;
import com.gws.entity.frontuser.UserBaseInfo;
import com.gws.enums.BizErrorCode;
import com.gws.repositories.master.frontuser.UserBaseInfoMaster;
import com.gws.repositories.query.frontuser.UserBaseInfoQuery;
import com.gws.repositories.slave.frontuser.UserBaseInfoSlave;
import com.gws.services.frontuser.FrontUserService;
import com.gws.utils.cache.IdGlobalGenerator;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author:wangdong
 * @description:
 */
@Service
public class FrontUserServiceImpl implements FrontUserService {

    @Autowired
    private UserBaseInfoMaster userBaseInfoMaster;

    @Autowired
    private UserBaseInfoSlave userBaseInfoSlave;

    @Autowired
    private IdGlobalGenerator idGen;

    @Override
    public UserBaseInfo updateUserBaseInfo(UserBaseInfo userBaseInfo) {
        if (null == userBaseInfo || null == userBaseInfo.getUid()){
            return null;
        }

        List<String> updateFields = Lists.newArrayList();
        if (StringUtils.isNotEmpty(userBaseInfo.getAvatar())){
            updateFields.add("avatar");
        }
        if (StringUtils.isNotEmpty(userBaseInfo.getUserName())){
            updateFields.add("userName");
        }
        if (null != userBaseInfo.getGender()){
            updateFields.add("gender");
        }
        if (StringUtils.isNotEmpty(userBaseInfo.getPhoneNumber())){
            updateFields.add("phoneNumber");
        }
        if (StringUtils.isNotEmpty(userBaseInfo.getEmailAddress())){
            updateFields.add("emailAddress");
        }
        if (null != userBaseInfo.getUserStatus()){
            updateFields.add("userStatus");
        }

        String[] array =new String[updateFields.size()];
        updateFields.toArray(array);

        userBaseInfoMaster.updateById(userBaseInfo,userBaseInfo.getUid(),array);

        return userBaseInfo;
    }

    /**
     * 新增用户的基本信息
     *
     * @param userBaseInfo
     * @return
     */
    @Override
    public UserBaseInfo saveUserBaseInfo(UserBaseInfo userBaseInfo) {
        if (null == userBaseInfo){
            return null;
        }

        userBaseInfo.setUid(idGen.getSeqId(UserBaseInfo.class));

        return userBaseInfoMaster.save(userBaseInfo);
    }

    /**
     * 获取用户信息，分页查询
     *
     * @param uid
     * @param userName
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public List<UserBaseInfo> listUserBaseInfo(Long uid, String userName, Integer currentPage, Integer pageSize) {
        if (null == currentPage || null == pageSize){
            return Collections.EMPTY_LIST;
        }
        UserBaseInfoQuery query = new UserBaseInfoQuery();
        if (null != uid) {
            query.setUid(uid);
        }
        if (StringUtils.isNotEmpty(userName)) {
            query.setUserName(userName);
        }
        Sort sort = new Sort(Sort.Direction.DESC,"ctime");

        Pageable pageable = new PageRequest(currentPage - 1, pageSize,sort);
        Page<UserBaseInfo> userBaseInfos = userBaseInfoSlave.findAll(query,pageable);

        return (null != userBaseInfos) ? userBaseInfos.getContent() : Collections.EMPTY_LIST;
    }

    /**
     * 根据条件执行删除的操作
     *
     * @param userStatus
     * @return
     */
    @Override
    public OperationResult<Boolean> deletedUserBaseInfo(Integer userStatus) {
        if (null == userStatus){
            return new OperationResult<>(BizErrorCode.PARM_ERROR);
        }
        UserBaseInfoQuery userBaseInfo = new UserBaseInfoQuery();

        userBaseInfo.setUserStatus(userStatus);
        List<UserBaseInfo> userBaseInfos = userBaseInfoSlave.findAll(userBaseInfo);
        if (CollectionUtils.isNotEmpty(userBaseInfos)){
            userBaseInfoMaster.delete(userBaseInfos);
        }

        return new OperationResult<>(true);
    }
}
