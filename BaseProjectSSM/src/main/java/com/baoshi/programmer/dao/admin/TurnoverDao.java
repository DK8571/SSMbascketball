package com.baoshi.programmer.dao.admin;

import com.baoshi.programmer.entity.admin.Turnover;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TurnoverDao {
    @Select({"<script>"+
            "<if test='id == 1'>"+
            "SELECT sum(`order`.price) price,date from `order` LEFT JOIN venues on `order`.venuesid = venues.id " +
            "GROUP BY  date  HAVING DATEDIFF(date,NOW())&lt;=0 and DATEDIFF(date,NOW())&gt;=-30 order by date"+
            "</if>"+
            "</script>"
            })
    List<Turnover> findturnover(@Param("id") Long id);
}
