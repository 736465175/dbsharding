package com.lizhiqiang.simple.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

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
    int insertOrder(@Param("price")BigDecimal price,@Param("userId")Long userId,@Param("status")String status);

    /**
     * 这里表写逻辑表
     * 根据ID列表查询订单,复杂的失去了使用<script>标签,传入列表查询使用foreach
     * 弹幕问题：1.如果查询的条件不是主键呢2.如果分页查询怎么办
     * 答案1： 不携带分片键的SQL则采用广播路由
     */
    @Select("<script>" +
                "select * from t_order t where t.order_id in " +
                "<foreach collection='orderIds' open='(' separator=',' close=')' item='id'>" +
                    "#{id}" +
                "</foreach>" +
            "</script>")
    List<Map<String,Object>> selectOrderByIds(@Param("orderIds")List<Long> orderIds);

    /**
     * 这里表写逻辑表
     * 根据OrderID列表、userID查询订单,复杂的失去了使用<script>标签,传入列表查询使用foreach
     * 弹幕问题：1.如果查询的条件不是主键呢2.如果分页查询怎么办
     * 答案1： 不携带分片键的SQL则采用广播路由
     */
    @Select("<script>" +
                "select * from t_order t where t.order_id in " +
                "<foreach collection='orderIds' open='(' separator=',' close=')' item='id'>" +
                    "#{id}" +
                "</foreach>" +
                " and t.user_id = #{userId}"  +
            "</script>")
    List<Map<String,Object>> selectOrderByIdsAndUserId(@Param("orderIds")List<Long> orderIds, @Param("userId")Long userId);

}
