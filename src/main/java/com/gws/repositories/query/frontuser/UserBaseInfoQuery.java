package com.gws.repositories.query.frontuser;

import com.gws.entity.frontuser.UserBaseInfo;
import com.gws.utils.query.Where;
import com.gws.utils.query.annotation.QBindAttrField;
import com.gws.utils.query.annotation.QBindEntity;
import com.gws.utils.query.core.BaseQuery;
import lombok.Data;

/**
 * UserBaseInfo 查询类
 * Created by wd on 17-7-18.
 */

@QBindEntity(entityClass = UserBaseInfo.class)
@Data
public class UserBaseInfoQuery extends BaseQuery {

    @QBindAttrField(fieldName = "uid", where = Where.equal)
    private Long uid;

    @QBindAttrField(fieldName = "userName", where = Where.equal)
    private String userName;

    @QBindAttrField(fieldName = "userStatus", where = Where.equal)
    private Integer userStatus;

}
