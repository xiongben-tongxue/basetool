package com.gws.services.frontuser;

import com.gws.dto.sms.SmsNotice;
import com.gws.entity.user.UserBaseInfo;

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

}
