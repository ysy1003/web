package com.ysy.pagedemo;

import com.ysy.pagedemo.mapper.AppMapper;
import com.ysy.pagedemo.mapper.EmpMapper;
import com.ysy.pagedemo.pojo.AppBean;
import com.ysy.pagedemo.pojo.Employee;
import com.ysy.pagedemo.utils.AppIsEnableUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class PagedemoApplicationTests {


    @Autowired
    AppIsEnableUtil appIsEnableUtil;

    @Autowired
    AppMapper appMapper;

    @Test
    public void test(){
        AppBean appBean = appMapper.selectBypgName("com.android.mms");
        //appIsEnableUtil.change(appBean);
    }

}
