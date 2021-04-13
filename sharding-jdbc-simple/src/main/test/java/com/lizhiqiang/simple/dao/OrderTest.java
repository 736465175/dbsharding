package com.lizhiqiang.simple.dao;

import com.lizhiqiang.simple.ShardingJdbcSimpleApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.math.BigDecimal;

@SpringBootTest(classes = {ShardingJdbcSimpleApplication.class})
public class OrderTest {

    @Resource
    private OrderDao orderDao;
    @Test
    public void testInsertOrder(){
        for(int i = 1; i < 20; i++){
            orderDao.insertOrder( new BigDecimal(i), 1L, "SUCCESS");
        }
    }
}
