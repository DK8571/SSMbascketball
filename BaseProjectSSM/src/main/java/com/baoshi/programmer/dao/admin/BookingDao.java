package com.baoshi.programmer.dao.admin;

import com.baoshi.programmer.entity.admin.Booking;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BookingDao {

    @Select("SELECT time.id timeid,time.time time,sum(`order`.Number) sum,`order`.quote FROM time LEFT OUTER JOIN `order` ON"+
            "`order`.timeid = time.id "+
            "AND `order`.venuesid =#{venuesid}  "+
            "and `order`.date = #{date} "+
            "GROUP BY time.id")

    List<Booking> findlist(Map<String, Object> queryMap);

    @Select("SELECT COUNT(*) FROM time ")

    int gettoal(Map<String, Object> queryMap);

    @Insert("insert into `order`(id,timeid,ordertypeid,number,memberid,price,venuesid,quote,date) values(null,#{timeid},#{ordertypeid},#{number},#{memberid},#{price},#{venuesid},#{quote},#{datestr})")

    int add(Booking booking);
}
