package com.baoshi.programmer.dao.admin;

import com.baoshi.programmer.entity.admin.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CashierDao {
    @Select("select * from user where username = #{username}")
    User findByCashiername(String username);

    @Insert("insert into user(id,username,password,roleId,photo,sex,age,address) values(null,#{username},#{password},2,#{photo},#{sex},#{age},#{address})")
    int add(User user);

    @Insert("update user set username = #{username},photo = #{photo},sex = #{sex},age = #{age},address = #{address} where id = #{id}")
    int edit(User user);

    @Delete("delete from user where id in(${value})")
    int delete(String ids);

    @Select({"<script>"+
            "select * from user where roleId = 2"+
            "<if test='username != null'>"+
            "and username like '%${username}%'"+
            "</if>"+
            "<if test='sex != null'>"+
            "and sex = #{sex}"+
            "</if>"+
            "<if test='offset != null and pageSize != null'>"+
            "limit #{offset},#{pageSize}"+
            "</if>"+
            "</script>"
    })
    List<User> findList(Map<String, Object> queryMap);
    @Select({"<script>"+
            "select count(*) from user where 1 = 1 "+
            "<if test='username != null'>and username like '%${username}%'</if>"+
            "<if test='sex != null'>"+
            "and sex = #{sex}"+
            "</if>"+
            "</script>"
    })
    int getTotal(Map<String, Object> queryMap);
}
