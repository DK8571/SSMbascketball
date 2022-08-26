package com.baoshi.programmer.service.admin.impl;

import com.baoshi.programmer.dao.admin.VenuesDao;
import com.baoshi.programmer.entity.admin.Venues;
import com.baoshi.programmer.service.admin.VenuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class VenuesServiceImpl implements VenuesService {

    @Autowired
    private VenuesDao venuesDao;


    @Override
    public Venues findByVenuesname(String venuesname) {
        return venuesDao.findByVenuesname(venuesname);
    }

    @Override
    public int add(Venues venues) {
        return venuesDao.add(venues);
    }

    @Override
    public int edit(Venues venues) {
        return venuesDao.edit(venues);
    }

    @Override
    public int delete(String ids) {
        return venuesDao.delete(ids);
    }

    @Override
    public List<Venues> findList(Map<String, Object> queryMap) {
        return venuesDao.findList(queryMap);
    }

    @Override
    public int getTotal(Map<String, Object> queryMap) {
        return venuesDao.getTotal(queryMap);
    }

    @Override
    public Venues findbyVenusId(Long venuesid) {
        return venuesDao.findbyVenusId(venuesid);
    }
}
