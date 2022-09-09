package com.baoshi.programmer.dao.admin;

import com.baoshi.programmer.entity.admin.Stadium;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface StadiumDao {

    @Select({"<script>"+
            "select count(*) from stadium where 1 = 1 "+
            "<if test='stadiumname != null'>and stadiumname like '%${stadiumname}%'</if>"+
            "</script>"
    })

    int getTotal(Map<String, Object> queryMap);

    @Select({"<script>"+
            "select * from stadium where 1=1"+
            "<if test='stadiumname != null'>"+
            "and stadiumname like '%${stadiumname}%'"+
            "</if>"+
            "<if test='offset != null and pageSize != null'>"+
            "limit #{offset},#{pageSize}"+
            "</if>"+
            "</script>"
    })

    List<Stadium> findList(Map<String, Object> queryMap);

    @Delete("delete from stadium where id in(${value})")

    int delete(String stadium);

    @Update("update stadium set stadiumname = #{stadiumname},address = #{address} where id = #{id}")

    int edit(Stadium stadium);

    @Insert("insert into stadium(id,stadiumname,address) values(null,#{stadiumname},#{address})")

    int add(Stadium stadium);

    @Select("select * from stadium where stadiumname = #{stadiumname}")

    Stadium findByStadiumname(String stadiumname);

    @Select("SELECT COUNT(*) from venues WHERE stadiumid in (${value})")

    Integer findvenues(String ids);

//    @Select("SELECT stadiumname FROM stadium WHERE id = ${stadiumid }")
//
//    String findnamebyid(Long stadiumid);
}
