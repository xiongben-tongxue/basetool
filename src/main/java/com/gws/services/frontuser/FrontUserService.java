package com.gws.services.frontuser;

import com.gws.entity.frontuser.UserBaseInfo;

import java.util.List;

/**
 * 前台用户的原子接口
 * @author wangdong
 */
public interface FrontUserService {


    /**
     * 更新用户的基本信息
     * @param userBaseInfo
     * @return
     */
    UserBaseInfo updateUserBaseInfo(UserBaseInfo userBaseInfo);

    /**
     * 新增用户的基本信息
     * @param userBaseInfo
     * @return
     */
    UserBaseInfo saveUserBaseInfo(UserBaseInfo userBaseInfo);

    /**
     * 获取用户信息，分页查询
     * @param uid
     * @param userName
     * @param currentPage
     * @param pageSize
     * @return
     */
    List<UserBaseInfo> listUserBaseInfo(Long uid, String userName, Integer currentPage, Integer pageSize);
}
