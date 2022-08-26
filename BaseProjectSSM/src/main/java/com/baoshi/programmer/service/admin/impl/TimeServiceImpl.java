package com.baoshi.programmer.service.admin.impl;

import com.baoshi.programmer.dao.admin.TimeDao;
import com.baoshi.programmer.entity.admin.Time;
import com.baoshi.programmer.service.admin.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TimeServiceImpl implements TimeService {

    @Autowired
    private TimeDao timeDao;

    @Override
    public Time findBytimename(String time) {
        return timeDao.findBytimename(time);
    }

    @Override
    public int add(Time time) {
        return timeDao.add(time);
    }

    @Override
    public int edit(Time time) {
        return timeDao.edit(time);
    }

    @Override
    public int delete(String ids) {
        return timeDao.delete(ids);
    }

    @Override
    public List<Time> findList(Map<String, Object> queryMap) {
        return timeDao.findList(queryMap);
    }

    @Override
    public int getTotal(Map<String, Object> queryMap) {
        return timeDao.gettotal(queryMap);
    }
}

