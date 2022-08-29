package com.baoshi.programmer.service.admin.impl;

import com.baoshi.programmer.dao.admin.OrderDao;
import com.baoshi.programmer.entity.admin.Order;
import com.baoshi.programmer.service.admin.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;


    @Override
    public int add(Order order) {
        return 0;
    }


    @Override
    public int delete(String ids) {
        return orderDao.delete(ids);
    }

    @Override
    public List<Order> findList(Map<String, Object> queryMap) {
        return orderDao.findlist(queryMap);
    }

    @Override
    public int getTotal(Map<String, Object> queryMap) {
        return orderDao.gettoal(queryMap);
    }

    @Override
    public List<Order> findListbyuserid(Map<String, Object> queryMap) {
        return orderDao.findListbyuserid(queryMap);
    }

    @Override
    public int getTotalbyuserid(Map<String, Object> queryMap) {
        return orderDao.getTotalbyuserid(queryMap);
    }

    @Override
    public List<Order> findListbycashierid(Map<String, Object> queryMap) {
        return orderDao.findListbycashierid(queryMap);
    }

    @Override
    public int getTotalbycashierid(Map<String, Object> queryMap) {
        return orderDao.getTotalbycashierid(queryMap);
    }
}
