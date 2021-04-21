package com.lizhiqiang.simple.dao;

import com.lizhiqiang.simple.ShardingJdbcSimpleApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest(classes = {ShardingJdbcSimpleApplication.class})
public class DictTest {

    @Resource
    private DictDao dictDao;
    @Test
    public void testInsertDict(){
        dictDao.insertDict(1L, "user_type", "0", "管理员");
        dictDao.insertDict(2L, "user_type", "1", "操作员");
        dictDao.insertDict(3L, "user_type", "2", "游客");
    }

    @Test
    public void testDeleteDict(){
        dictDao.deleteDict(3L);
    }

    @Test
    public void selectDictByIds(){
        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        ids.add(2L);
        List<Map<String,Object>> result = dictDao.selectDictByIds(ids);//查不到数据
        System.out.println(result);
    }

}
