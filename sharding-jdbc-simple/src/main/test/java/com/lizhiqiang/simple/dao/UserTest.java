package com.lizhiqiang.simple.dao;

import com.lizhiqiang.simple.ShardingJdbcSimpleApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest(classes = {ShardingJdbcSimpleApplication.class})
public class UserTest {

    @Resource
    private UserDao userDao;
    @Test
    public void testInsertUser(){
        for(int i = 1; i < 11; i++){
            userDao.insertUser("李志强" + i, '1');
        }
    }

    @Test
    public void selectUserByIds(){
        List<Long> ids = new ArrayList<>();
        ids.add(591624376273600512L);
        ids.add(591624377129238529L);
        List<Map<String,Object>> result = userDao.selectUserByIds(ids);
        System.out.println(result);
    }

    //关联查询
    @Test
    public void selectUserInfoByIds(){
        List<Long> ids = new ArrayList<>();
        ids.add(591624376273600512L);
        ids.add(591624377129238529L);
        List<Map<String,Object>> result = userDao.selectUserInfoByIds(ids);
        System.out.println(result);
    }

}
