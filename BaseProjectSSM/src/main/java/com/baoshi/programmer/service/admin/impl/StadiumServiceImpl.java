package com.baoshi.programmer.service.admin.impl;

import com.baoshi.programmer.dao.admin.StadiumDao;
import com.baoshi.programmer.entity.admin.Stadium;
import com.baoshi.programmer.service.admin.StadiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StadiumServiceImpl implements StadiumService {

    @Autowired
    private StadiumDao stadiumDao;

    @Override
    public Stadium findByStadiumname(String stadiumname) {
        return stadiumDao.findByStadiumname(stadiumname);
    }

    @Override
    public int add(Stadium stadium) {
        return stadiumDao.add(stadium);
    }

    @Override
    public int edit(Stadium stadium) {
        return stadiumDao.edit(stadium);
    }

    @Override
    public int delete(String stadium) {
        return stadiumDao.delete(stadium);
    }

    @Override
    public List<Stadium> findList(Map<String, Object> queryMap) {
        return stadiumDao.findList(queryMap);
    }

    @Override
    public int getTotal(Map<String, Object> queryMap) {
        return stadiumDao.getTotal(queryMap);
    }

    @Override
    public Integer findvenues(String ids) {
        return stadiumDao.findvenues(ids);
    }
}
