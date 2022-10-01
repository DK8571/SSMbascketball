package com.baoshi.programmer.service.admin.impl;

import com.baoshi.programmer.dao.admin.InstructororderDao;
import com.baoshi.programmer.entity.admin.Instuctor;
import com.baoshi.programmer.service.admin.InstructororderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class InstructororderServiceImpl implements InstructororderService {

    @Autowired
    private InstructororderDao instructororderDao;

    @Override
    public List<Instuctor> findList(Map<String, Object> queryMap) {
        return instructororderDao.findList(queryMap);
    }

    @Override
    public int getTotal(Map<String, Object> queryMap) {
        return instructororderDao.getTotal(queryMap);
    }

    @Override
    public int add(Long instructorid, Long orderid) {
        return instructororderDao.add(instructorid,orderid);
    }

    @Override
    public int find(Long instructorid, Long orderid) {
        return instructororderDao.find(instructorid,orderid);
    }

    @Override
    public int delete(Long instructorid, Long orderid) {
        return instructororderDao.delete(instructorid,orderid);
    }

    @Override
    public int deletebyorderid(int orderid) {
        return instructororderDao.deletebyorderid(orderid);
    }
}
