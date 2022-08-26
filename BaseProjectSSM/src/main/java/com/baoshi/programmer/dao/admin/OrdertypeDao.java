package com.baoshi.programmer.dao.admin;

import com.baoshi.programmer.entity.admin.Ordertype;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface OrdertypeDao {
    @Select("select * from ordertype where 1=1")
    public List<Ordertype> findall();
}
