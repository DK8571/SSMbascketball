package com.baoshi.programmer.dao.admin;

import com.baoshi.programmer.entity.admin.Time;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TimeDao {


    @Select({"<script>"+
            "select * from time where 1=1"+
            "<if test='time != null'>"+
            "and time like '%${time}%'"+
            "</if>"+
            "</script>"
    })

    List<Time> findList(Map<String, Object> queryMap);

    @Select({"<script>"+
            "select count(*) from time where 1 = 1 "+
            "<if test='time != null'>and time like '%${time}%'</if>"+
            "</script>"
    })

    int gettotal(Map<String, Object> queryMap);

    @Select("select * from time where time = #{time}")

    Time findBytimename(String time);

    @Insert("insert into time(time) values(#{time})")

    int add(Time time);

    @Update("update time set time = #{time} where id = #{id}")

    int edit(Time time);

    @Delete("delete from time where id in (${value})")

    int delete(String ids);
}
