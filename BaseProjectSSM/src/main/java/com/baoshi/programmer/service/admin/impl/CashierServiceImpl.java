package com.baoshi.programmer.service.admin.impl;

import com.baoshi.programmer.dao.admin.CashierDao;
import com.baoshi.programmer.entity.admin.User;
import com.baoshi.programmer.service.admin.CashierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CashierServiceImpl implements CashierService {
    @Autowired
    private CashierDao cashierDao;
    @Override
    public User findByCashiername(String username) {
        return cashierDao.findByCashiername(username);
    }

    @Override
    public int add(User user) {
        return cashierDao.add(user);
    }

    @Override
    public int edit(User user) {
        return  cashierDao.edit(user);
    }

    @Override
    public int delete(String ids) {
        return cashierDao.delete(ids);
    }

    @Override
    public List<User> findList(Map<String, Object> queryMap) {
        System.out.println(queryMap);
        List<User> a  = cashierDao.findList(queryMap);
        System.out.println(a);
        return a;
    }

    @Override
    public int getTotal(Map<String, Object> queryMap) {
        return cashierDao.getTotal(queryMap);
    }
}

