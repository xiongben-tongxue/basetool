package com.gws.services.fzm.impl;

import com.gws.entity.DateTest;
import com.gws.repositories.master.test.DateTestQuery;
import com.gws.repositories.slave.test.DateTestSlave;
import com.gws.services.fzm.TestService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * 查询原子的实现类
 * @author : Kumamon 熊本同学
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private DateTestSlave dateTestSlave;

    @Override
    public List<DateTest> listDateTest() {

        DateTestQuery query = new DateTestQuery();
        List<DateTest> dateTests = dateTestSlave.findAll(query);

        return CollectionUtils.isEmpty(dateTests) ? Collections.EMPTY_LIST : dateTests;
    }
}
