package com.baoshi.programmer.service.admin.impl;

import com.baoshi.programmer.dao.admin.InstuctorDao;
import com.baoshi.programmer.entity.admin.Instuctor;
import com.baoshi.programmer.service.admin.InstuctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class InstuctorServiceImpl implements InstuctorService {

    @Autowired
    private InstuctorDao instuctorDao;

    @Override
    public Instuctor findByInstuctorname(String Instuctorname) {
        return instuctorDao.findByUsername(Instuctorname);
    }

    @Override
    public int add(Instuctor instuctor) {
        return instuctorDao.add(instuctor);
    }

    @Override
    public int edit(Instuctor instuctor) {
        return instuctorDao.edit(instuctor);
    }

    @Override
    public int delete(String ids) {
        return instuctorDao.delete(ids);
    }

    @Override
    public List<Instuctor> findList(Map<String, Object> queryMap) {
        return instuctorDao.getlist(queryMap);
    }

    @Override
    public int getTotal(Map<String, Object> queryMap) {
        return instuctorDao.getTotal(queryMap);
    }
}

