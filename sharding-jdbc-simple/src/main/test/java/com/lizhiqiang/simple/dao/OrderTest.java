package com.lizhiqiang.simple.dao;

import com.lizhiqiang.simple.ShardingJdbcSimpleApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest(classes = {ShardingJdbcSimpleApplication.class})
public class OrderTest {

    @Resource
    private OrderDao orderDao;
    @Test
    public void testInsertOrder(){
        for(int i = 1; i < 11; i++){
            orderDao.insertOrder( new BigDecimal(i), 1L, "SUCCESS");
        }
    }

    @Test
    public void selectOrderByIds(){
        List<Long> ids = new ArrayList<>();
        ids.add(588857808531750912L);
        ids.add(588857808422699009L);
        List<Map<String,Object>> result = orderDao.selectOrderByIds(ids);
        System.out.println(result);
    }

}
