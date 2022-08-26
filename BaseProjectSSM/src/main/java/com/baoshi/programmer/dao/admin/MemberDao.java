package com.baoshi.programmer.dao.admin;

import com.baoshi.programmer.entity.admin.Member;
import com.baoshi.programmer.entity.admin.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface MemberDao {

    @Select("select * from user where username = #{username}")
    User findByUsername(String username);

    @Insert("insert into user(id,username,password,roleId,photo,sex,age,address) values(null,#{username},#{password},3,#{photo},#{sex},#{age},#{address})")
    @Options(useGeneratedKeys=true,keyProperty="id",keyColumn="id")
    int add(User user);
    @Insert("insert into member(balance,userid) values(0,#{id})")
    int addmember(long id);

    @Update("update user set username = #{username},photo = #{photo},sex = #{sex},age = #{age},address = #{address} where id = #{id}")
    int edit(User user);

    @Delete("delete from user where id in(${value})")
    int delete(String ids);

    @Select({"<script>"+
            "select user.*,member.balance,member.id memberid from user,member where user.roleId = 3 and user.id = member.userid"+
            "<if test='username != null'>"+
            "and username like '%${username}%'"+
            "</if>"+
            "<if test='sex != null'>"+
            "and sex = #{sex}"+
            "</if>"+
            "<if test='userid!= null'>"+
            "and member.userid = #{userid}"+
            "</if>"+
            "<if test='offset != null and pageSize != null'>"+
            "limit #{offset},#{pageSize}"+
            "</if>"+
            "</script>"
    })
    List<Member> findList(Map<String, Object> queryMap);

    @Select({"<script>"+
            "select count(*) from user,member where user.roleId = 3 and user.id = member.userid"+
            "<if test='username != null'>"+
            "and username like '%${username}%'"+
            "</if>"+
            "<if test='sex != null'>"+
            "and sex = #{sex}"+
            "</if>"+
            "<if test='userid!= null'>"+
            "and member.userid = #{userid}"+
            "</if>"+
            "<if test='offset != null and pageSize != null'>"+
            "limit #{offset},#{pageSize}"+
            "</if>"+
            "</script>"
    })
    int getTotal(Map<String, Object> queryMap);
    @Delete("delete from member where userid in(${value})")
    int deletemember(String ids);

    @Update("update member set balance = #{balance} where userid = #{id}")
    void editmember(@Param("balance") double balance, @Param("id") Long id);

    @Select("select id memberid,userid,balance from member where userid = #{value}")

    Member findbyuserid(long userid);

}
