package com.lizhiqiang.simple.dao;

import org.apache.ibatis.annotations.*;

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
public interface DictDao {

    /**
     * 新增字典
     * @author Lizhiqiang
     * @date 2021/4/21 14:45
     * @param dictId , type[字典类型], code[字典编码],value[字典值]
     * @return int
     */
    @Insert("insert into t_dict(dict_id,type,code,value) values(#{dictId},#{type},#{code},#{value})")
    int insertDict(@Param("dictId")Long dictId,@Param("type")String type,@Param("code")String code,@Param("value")String value);

    @Delete("delete from t_dict where dict_id=#{dictId}")
    int deleteDict(@Param("dictId")Long dictId);


    @Select("<script>" +
            "select * from t_dict t where t.dict_id in " +
            "<foreach collection='dictIds' open='(' separator=',' close=')' item='id'>" +
                "#{id}" +
            "</foreach>" +
        "</script>")
    List<Map<String,Object>> selectDictByIds(@Param("dictIds") List<Long> dictIds);
}
