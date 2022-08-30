package com.baoshi.programmer.dao.admin;

import com.baoshi.programmer.entity.admin.Turnover;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TurnoverDao {
    @Select({"<script>"+
            "<if test='id == 1'>"+
            "SELECT sum(`order`.price) price,date from `order` LEFT JOIN venues on `order`.venuesid = venues.id " +
            "GROUP BY  date  HAVING DATEDIFF(date,NOW())&lt;=0 and DATEDIFF(date,NOW())&gt;=-30 order by date"+
            "</if>"+
            "<if test='id == 2'>"+
            "SELECT sum(`order`.price) price,date,venues.stadiumid from `order` LEFT JOIN venues on `order`.venuesid = venues.id "+
            "GROUP BY  stadiumid,date  HAVING  stadiumid=(SELECT stadiumid FROM cashier where id =${userid }) and DATEDIFF(date,NOW())&lt;=0 and DATEDIFF(date,NOW())&gt;=-30 order by date,stadiumid"+
            "</if>"+
            "<if test='id == 3'>"+
            "SELECT sum(`order`.price) price,date,`order`.memberid from `order` GROUP BY  date,memberid  HAVING DATEDIFF(date,NOW())&lt;=0 and DATEDIFF(date,NOW())&gt;=-30 and memberid=(SELECT id FROM member WHERE userid = ${userid } )"+
            "</if>"+
            "</script>"
            })
    List<Turnover> findturnover(Map<String,Object> querymap);
}
