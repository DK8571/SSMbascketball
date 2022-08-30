package com.baoshi.programmer.service.admin.impl;

import com.baoshi.programmer.dao.admin.TurnoverDao;
import com.baoshi.programmer.entity.admin.Turnover;
import com.baoshi.programmer.service.admin.TurnoverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TurnoverServiceImpl implements TurnoverService{
    @Autowired
    private TurnoverDao turnoverDao;

    @Override
    public List<Turnover> findturnover(Long id) {
        return turnoverDao.findturnover(id);
    }
}
