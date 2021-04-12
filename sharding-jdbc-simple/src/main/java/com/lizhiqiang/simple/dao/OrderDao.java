package com.lizhiqiang.simple.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * @author 侯文远
 * @version V1.0
 * @Package com.lizhiqiang.simple.dao
 * @date 2021-04-12 23:38
 * @Copyright © 李志强
 */
@Mapper
public interface OrderDao {

    //定义分片规则的时候已经定义主键自动生成，不需要传入；这里表写逻辑表，sharding-jdbc会自动帮你根据分片策略找到表
    @Insert("insert into t_order(price,user_id,status) values(#{price},#{userId},#{status})")
    int insertOrder(@Param("price")BigDecimal price,@Param("user_id")Long userId,@Param("status")String status);
}
