package com.gws.services.fzm;

import com.gws.dto.OperationResult;
import com.gws.dto.test.Year;
import com.gws.entity.DateTest;

import java.util.List;

/**
 * 查询的管理service
 * @author : Kumamon 熊本同学
 * @Description:
 * @Modified By:
 */
public interface TestManageService {

    /**
     * 管理查询类
     * @return
     */
    OperationResult<List<Year>> checkYears();

}
