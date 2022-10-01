package com.baoshi.programmer.dao.admin;

import com.baoshi.programmer.entity.admin.Instuctor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface InstructororderDao {
    //通过球馆id，日期，时间id，订单id搜索出可预约和已预约的教练
    @Select({"<script>"+
                "select instructor.*,instructororder.T from instructor LEFT JOIN instructororder " +
                "ON instructor.id = instructororder.instructorid " +
                "AND instructororder.orderid = #{orderid } " +
                "where 1=1 and stadiumid = #{stadiumId } " +
                "and instructor.id not in " +
                    "(SELECT instructorid from instructororder where orderid in " +
                        "(SELECT id from `order` where timeid = #{timeid } and date = #{datestr } and venuesid IN " +
                            "(SELECT venuesid from stadium WHERE id = #{stadiumId }) and id != #{orderid })) "+
            "<if test='instuctorname != null'>"+
                "and name like '%${instuctorname}%'"+
            "</if>"+
            "<if test='sex != null'>"+
                "and sex = #{sex}"+
            "</if>"+
            "<if test='offset != null and pageSize != null'>"+
                "limit #{offset},#{pageSize}"+
            "</if>"+
            "</script>"
    })
    List<Instuctor> findList(Map<String, Object> queryMap);

    @Select({"<script>"+
            "select count(*) from instructor where 1 = 1 "+
            "<if test='instuctorname != null'>"+
            "and name like '%${instuctorname}%'"+
            "</if>"+
            "<if test='sex != null'>"+
            "and sex = #{sex}"+
            "</if>"+
            "<if test='stadiumId != null'>"+
            "and stadiumid = #{stadiumId}"+
            "</if>"+
            "</script>"
    })

    int getTotal(Map<String, Object> queryMap);

    @Insert("insert into instructororder(id,instructorid,orderid,T) values(null,#{instructorid},#{orderid},1)")

    int add(@Param("instructorid") Long instructorid,@Param("orderid") Long orderid);

    @Select("SELECT COUNT(*) from instructororder where  orderid = #{orderid} ")

    int find(@Param("instructorid") Long instructorid,@Param("orderid") Long orderid);

    @Delete("delete from instructororder where orderid = #{orderid} and instructorid = #{instructorid}")

    int delete(@Param("instructorid") Long instructorid,@Param("orderid") Long orderid);

    @Delete("delete from instructororder where orderid = #{orderid}")

    int deletebyorderid(int orderid);
}
