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
            orderDao.insertOrder( new BigDecimal(i), 2L, "SUCCESS");
        }
    }

    @Test
    public void selectOrderByIds(){
        List<Long> ids = new ArrayList<>();
        ids.add(591584576665550849L);
        ids.add(591584578028699648L);
        List<Map<String,Object>> result = orderDao.selectOrderByIds(ids);
        System.out.println(result);
    }

    @Test
    public void selectOrderByIdsAndUserId(){
        List<Long> ids = new ArrayList<>();
        ids.add(591584576665550849L);
        ids.add(591584578028699648L);
        Long userId = 2L;
        List<Map<String,Object>> result = orderDao.selectOrderByIdsAndUserId(ids, userId);
        System.out.println(result);
    }

    //关联t_user表 ,测试失败
    @Test
    public void selectOrderByIdsAndUserIdUnionUser(){
        List<Long> ids = new ArrayList<>();
        ids.add(591593198632042496L);
        ids.add(591593200062300160L);
        Long userId = 591624376273600512L;
        List<Map<String,Object>> result = orderDao.selectOrderByIdsAndUserIdUnionUser(ids, userId);
        System.out.println(result);
    }

    //关联t_user表和广播表t_dict,测试失败
    @Test
    public void selectOrderByIdsAndUserIdUnionUserAndDict(){
        List<Long> ids = new ArrayList<>();
        ids.add(591584576665550849L);
        ids.add(591584578028699648L);
        Long userId = 2L;
        List<Map<String,Object>> result = orderDao.selectOrderByIdsAndUserIdUnionUserAndDict(ids, userId);
        System.out.println(result);
    }

}
