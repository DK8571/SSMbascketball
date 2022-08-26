package com.baoshi.programmer.service.admin.impl;

import com.baoshi.programmer.dao.admin.OrdertypeDao;
import com.baoshi.programmer.entity.admin.Ordertype;
import com.baoshi.programmer.service.admin.OrdertypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdertypeServiceImpl implements OrdertypeService {

    @Autowired
    private OrdertypeDao ordertypeDao;

    public List<Ordertype> findList() {
        return ordertypeDao.findall();
    }

}
