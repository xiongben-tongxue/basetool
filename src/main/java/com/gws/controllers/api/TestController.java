package com.gws.controllers.api;

import com.gws.controllers.BaseApiController;
import com.gws.controllers.JsonResult;
import com.gws.dto.OperationResult;
import com.gws.dto.test.Year;
import com.gws.services.fzm.TestManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * test接口
 * @author : Kumamon 熊本同学
 * @Description:
 * @Modified By:
 */
@RestController
@RequestMapping("/api/fzm/")
public class TestController extends BaseApiController {

    @Autowired
    private TestManageService testManageService;

    /**
     * 新用户首登欢迎语
     * @return
     */
    @RequestMapping("checkYears")
    public JsonResult checkYears(){

        OperationResult<List<Year>> result = testManageService.checkYears();

        if (result.getSucc()){
            return success(result.getEntity());
        }

        return error(result.getErrorCode());
    }
}
