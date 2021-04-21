package com.lizhiqiang.simple.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
public interface UserDao {


    @Insert("insert into t_user(fullname,user_type) values(#{fullName},#{userType})")
    int insertUser(@Param("fullName")String fullName,@Param("userType")char userType);

    /**
     * 这里表写逻辑表
     * 根据ID列表查询订单,复杂的失去了使用<script>标签,传入列表查询使用foreach
     * 弹幕问题：1.如果查询的条件不是主键呢2.如果分页查询怎么办
     * 答案1： 不携带分片键的SQL则采用广播路由
     */
    @Select("<script>" +
                "select * from t_user t where t.user_id in " +
                "<foreach collection='userIds' open='(' separator=',' close=')' item='id'>" +
                    "#{id}" +
                "</foreach>" +
            "</script>")
    List<Map<String,Object>> selectUserByIds(@Param("userIds")List<Long> userIds);

    @Select("<script>" +
                " select t.*,d.value from t_user t " +
                " left join t_dict d on d.code = t.user_type " +
                " where t.user_id in " +
                " <foreach collection='userIds' open='(' separator=',' close=')' item='id'> " +
                    "#{id}" +
                " </foreach> " +
            "</script>")
    List<Map<String,Object>> selectUserInfoByIds(@Param("userIds")List<Long> userIds);
}
