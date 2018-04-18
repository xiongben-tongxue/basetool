package com.gws.services.frontuser.impl;

import com.google.common.collect.Lists;
import com.gws.entity.user.UserBaseInfo;
import com.gws.repositories.master.frontuser.UserBaseInfoMaster;
import com.gws.services.frontuser.FrontUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author:wangdong
 * @description:
 */
@Service
public class FrontUserServiceImpl implements FrontUserService {

    @Autowired
    private UserBaseInfoMaster userBaseInfoMaster;

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
}
