package com.baoshi.programmer.dao.admin;

import com.baoshi.programmer.entity.admin.Order;
import com.baoshi.programmer.entity.admin.Turnover;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface OrderDao {

    @Select({"<script>"+
            "select * from `order` where 1=1 "+
            "<if test='date != null'>"+
            "and date = #{date}"+
            "</if>"+
            "<if test='ordertypeid != null'>"+
            "and ordertypeid = #{ordertypeid}"+
            "</if>"+
            "<if test='timeid != null'>"+
            "and timeid = #{timeid}"+
            "</if>"+
            "<if test='memberid != null'>"+
            "and memberid = #{memberid}"+
            "</if>"+
            "<if test='venuesid != null'>"+
            "and venuesid = #{venuesid}"+
            "</if>"+
            "<if test='date != null'>"+
            "and date = #{date}"+
            "</if>"+
            "<if test='offset != null and pageSize != null'>"+
            "limit #{offset},#{pageSize}"+
            "</if>"+
            "</script>"
    })

    List<Order> findlist(Map<String, Object> queryMap);

    @Select({"<script>"+
            "select count(*) from `order` where 1=1 "+
            "<if test='ordertypeid != null'>"+
            "and ordertypeid = #{ordertypeid}"+
            "</if>"+
            "<if test='timeid != null'>"+
            "and timeid = #{timeid}"+
            "</if>"+
            "<if test='memberid != null'>"+
            "and memberid = #{memberid}"+
            "</if>"+
            "<if test='venuesid != null'>"+
            "and venuesid = #{venuesid}"+
            "</if>"+
            "<if test='date != null'>"+
            "and date = #{date}"+
            "</if>"+
            "</script>"
    })

    int gettoal(Map<String, Object> queryMap);

    @Select({"<script>"+
            "select count(*) from `order` where 1=1 "+
            "and memberid = #{memberid}"+
            "<if test='ordertypeid != null'>"+
            "and ordertypeid = #{ordertypeid}"+
            "</if>"+
            "<if test='timeid != null'>"+
            "and timeid = #{timeid}"+
            "</if>"+
            "<if test='venuesid != null'>"+
            "and venuesid = #{venuesid}"+
            "</if>"+
            "<if test='date != null'>"+
            "and date = #{date}"+
            "</if>"+
            "</script>"
    })

    int getTotalbyuserid(Map<String, Object> queryMap);


    @Select({"<script>"+
            "select * from `order` where 1=1 "+
            "and memberid = #{memberid}"+
            "<if test='date != null'>"+
            "and date = #{date}"+
            "</if>"+
            "<if test='ordertypeid != null'>"+
            "and ordertypeid = #{ordertypeid}"+
            "</if>"+
            "<if test='timeid != null'>"+
            "and timeid = #{timeid}"+
            "</if>"+
            "<if test='venuesid != null'>"+
            "and venuesid = #{venuesid}"+
            "</if>"+
            "<if test='date != null'>"+
            "and date = #{date}"+
            "</if>"+
            "<if test='offset != null and pageSize != null'>"+
            "limit #{offset},#{pageSize}"+
            "</if>"+
            "</script>"
    })

    List<Order> findListbyuserid(Map<String, Object> queryMap);

    @Delete("delete from `order` where id in (${value})")

    int delete(String ids);

    @Select({"<script>"+
            "SELECT * from `order` where venuesid in (select venues.id from venues,cashier where venues.stadiumid=cashier.stadiumid and cashier.id =${cashierid}) "+
            "<if test='date != null'>"+
            "and date = #{date}"+
            "</if>"+
            "<if test='ordertypeid != null'>"+
            "and ordertypeid = #{ordertypeid}"+
            "</if>"+
            "<if test='timeid != null'>"+
            "and timeid = #{timeid}"+
            "</if>"+
            "<if test='venuesid != null'>"+
            "and venuesid = #{venuesid}"+
            "</if>"+
            "<if test='date != null'>"+
            "and date = #{date}"+
            "</if>"+
            "<if test='memberid != null'>"+
            "and memberid = #{memberid}"+
            "</if>"+
            "<if test='offset != null and pageSize != null'>"+
            "limit #{offset},#{pageSize}"+
            "</if>"+
            "</script>"
    })

    List<Order> findListbycashierid(Map<String, Object> queryMap);

    @Select({"<script>"+
            "SELECT count(*) from `order` where venuesid in (select venues.id from venues,cashier where venues.stadiumid=cashier.stadiumid and cashier.id =${cashierid}) "+
            "<if test='ordertypeid != null'>"+
            "and ordertypeid = #{ordertypeid}"+
            "</if>"+
            "<if test='timeid != null'>"+
            "and timeid = #{timeid}"+
            "</if>"+
            "<if test='venuesid != null'>"+
            "and venuesid = #{venuesid}"+
            "</if>"+
            "<if test='date != null'>"+
            "and date = #{date}"+
            "</if>"+
            "<if test='memberid != null'>"+
            "and memberid = #{memberid}"+
            "</if>"+
            "</script>"
    })

    int getTotalbycashierid(Map<String, Object> queryMap);


}
