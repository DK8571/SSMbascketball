package com.baoshi.programmer.dao.admin;

import com.baoshi.programmer.entity.admin.Instuctor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface InstuctorDao {

    @Select({"<script>"+
            "select * from instructor where 1=1 "+
            "<if test='roleId == 2'>"+
            "and stadiumid = (select stadiumid from cashier where id = #{cashierid })"+
            "</if>"+
            "<if test='instuctorname != null'>"+
            "and name like '%${instuctorname}%'"+
            "</if>"+
            "<if test='sex != null'>"+
            "and sex = #{sex}"+
            "</if>"+
            "<if test='stadiumId != null'>"+
            "and stadiumid = #{stadiumId}"+
            "</if>"+
            "<if test='offset != null and pageSize != null'>"+
            "limit #{offset},#{pageSize}"+
            "</if>"+
            "</script>"
    })

    List<Instuctor> getlist(Map<String, Object> queryMap);


    @Select({"<script>"+
            "select count(*) from instructor where 1 = 1 "+
            "<if test='instuctorname != null'>and name like '%${instuctorname}%'</if>"+
            "<if test='sex != null'>"+
            "and sex = #{sex}"+
            "</if>"+
            "<if test='stadiumId != null'>"+
            "and stadiumid = #{stadiumId}"+
            "</if>"+
            "</script>"
    })

    int getTotal(Map<String, Object> queryMap);

    @Select("select * from instructor where name = #{instuctorname}")

    Instuctor findByUsername(String instuctorname);

    @Insert("insert into instructor(id,name,photo,sex,age,stadiumid) values(null,#{name},#{photo},#{sex},#{age},#{stadiumid})")

    int add(Instuctor instuctor);

    @Update("update instructor set name = #{name},photo = #{photo},sex = #{sex},age = #{age},stadiumid=#{stadiumid} where id = #{id}")

    int edit(Instuctor instuctor);

    @Delete({"delete from instructor where id in (${value})"})

    int delete(String ids);
}
