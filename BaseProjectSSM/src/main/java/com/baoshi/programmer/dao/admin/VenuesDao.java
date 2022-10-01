package com.baoshi.programmer.dao.admin;

import com.baoshi.programmer.entity.admin.Stadium;
import com.baoshi.programmer.entity.admin.Venues;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface VenuesDao {

    @Select({"<script>"+
            "select count(*) from venues where 1 = 1 "+
            "<if test='venuesname != null'>and venuesname like '%${venuesname}%'</if>"+
            "<if test='stadiumId != null'>"+
            "and stadiumid = #{stadiumId}"+
            "</if>"+
            "</script>"
    })

    int getTotal(Map<String, Object> queryMap);

    @Select({"<script>"+
            "select venues.*,stadium.stadiumname from venues,stadium where 1=1 and venues.stadiumid=stadium.id"+
            "<if test='venuesname != null'>"+
            "and venuesname like '%${venuesname}%'"+
            "</if>"+
            "<if test='stadiumId != null'>"+
            "and stadiumid = #{stadiumId}"+
            "</if>"+
            "<if test='offset != null and pageSize != null'>"+
            "limit #{offset},#{pageSize}"+
            "</if>"+
            "</script>"
    })

    List<Venues> findList(Map<String, Object> queryMap);

    @Delete("delete from venues where id in(${value})")

    int delete(String ids);

    @Select("select * from venues where venuesname = #{venuesname}")

    Venues findByVenuesname(String venuesname);

    @Insert("insert into venues(id,venuesname,stadiumid,price,max,allprice) values(null,#{venuesname},#{stadiumid},#{price},#{max},#{allprice})")

    int add(Venues venues);

    @Update("update venues set venuesname = #{venuesname},stadiumid = #{stadiumid},price=#{price},max=#{max},allprice = #{allprice} where id = #{id}")

    int edit(Venues venues);

    @Select("select * from venues where id = #{venuesid}")

    Venues findbyVenusId(Long venuesid);

    @Select({"<script>"+
            "<if test='roleId == 1'>"+
                "select venues.*,stadium.stadiumname from venues,stadium where 1=1 and venues.stadiumid=stadium.id"+
            "</if>"+
            "<if test='roleId == 2'>"+
                "select venues.*,stadium.stadiumname from venues,cashier,stadium where 1=1 and venues.stadiumid=stadium.id and venues.stadiumid=cashier.stadiumid and cashier.id =${cashierid}"+
            "</if>"+
            "</script>"
    })
//    @Results({
//            @Result(property = "stadiumname",column = "stadiumid",javaType = Venues.class,
//                    one = @One(select = "com.baoshi.programmer.dao.admin.StadiumDao.findnamebyid"))
//    })
    List<Venues> findListbycashierid(Map<String, Object> queryMap);

    @Select("SELECT COUNT(*) from equipment WHERE venuesid in (${value})")

    Integer findequipment(String ids);

    @Select("SELECT COUNT(*) from `order` WHERE venuesid in (${value})")

    Integer findeorders(String ids);

}
