package com.baoshi.programmer.service.admin;


import com.baoshi.programmer.entity.admin.Order;
import com.baoshi.programmer.entity.admin.Turnover;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public interface OrderService {
    public int add(Order order);
    public int delete(String ids);
    public List<Order> findList(Map<String, Object> queryMap);
    public int getTotal(Map<String, Object> queryMap);
    public List<Order> findListbyuserid(Map<String, Object> queryMap);
    public int getTotalbyuserid(Map<String, Object> queryMap);
    public List<Order> findListbycashierid(Map<String, Object> queryMap);
    public int  getTotalbycashierid(Map<String, Object> queryMap);

    Order findbyorderid(Map<String, Object> queryMap);
}
