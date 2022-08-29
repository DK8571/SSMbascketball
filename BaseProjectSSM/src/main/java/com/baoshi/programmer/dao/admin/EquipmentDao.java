package com.baoshi.programmer.dao.admin;

import com.baoshi.programmer.entity.admin.Equipment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface EquipmentDao {

    @Select({"<script>"+
            "select * from equipment where 1=1 "+
            "<if test='equipmentname != null'>"+
            "and equipmentname like '%${equipmentname}%'"+
            "</if>"+
            "<if test='roleId == 2'>"+
            "and venuesid in (select venues.id from venues,cashier where venues.stadiumid=cashier.stadiumid and cashier.id =${cashierid})"+
            "</if>"+
            "<if test='userid != null'>"+
            "and userid = #{userid}"+
            "</if>"+
            "<if test='venuesid != null'>"+
            "and venuesid = #{venuesid}"+
            "</if>"+
            "<if test='offset != null and pageSize != null'>"+
            "limit #{offset},#{pageSize}"+
            "</if>"+
            "</script>"
    })

    List<Equipment> findList(Map<String, Object> queryMap);

    @Select({"<script>"+
            "select count(*) from equipment where 1 = 1 "+
            "<if test='equipmentname != null'>"+
            "and equipmentname like '%${equipmentname}%'"+
            "</if>"+
            "<if test='userid != null'>"+
            "and userid = #{userid}"+
            "</if>"+
            "<if test='venuesid != null'>"+
            "and venuesid = #{venuesid}"+
            "</if>"+
            "</script>"
    })

    int gettotal(Map<String, Object> queryMap);

    @Select("select * from equipment where equipmentname = #{equipmentname}")

    Equipment findByequipmentname(String equipmentname);

    @Insert("insert into equipment(equipmentname,remark,userid,venuesid) values(#{equipmentname},#{remark},#{userid},#{venuesid})")

    int add(Equipment equipment);

    @Update("update equipment set equipmentname = #{equipmentname},remark = #{remark},userid = #{userid},venuesid = #{venuesid} where id = #{id}")

    int edit(Equipment equipment);

    @Delete("delete from equipment where id in (${value})")

    int delete(String ids);
}
