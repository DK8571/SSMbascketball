package com.baoshi.programmer.service.admin.impl;

import com.baoshi.programmer.dao.admin.EquipmentDao;
import com.baoshi.programmer.entity.admin.Equipment;
import com.baoshi.programmer.service.admin.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EquipmentServiceImpl implements EquipmentService {

    @Autowired
    private EquipmentDao equipmentDao;

    @Override
    public Equipment findByequipmentname(String equipmentname) {
        return equipmentDao.findByequipmentname(equipmentname);
    }

    @Override
    public int add(Equipment equipment) {
        return equipmentDao.add(equipment);
    }

    @Override
    public int edit(Equipment equipment) {
        return equipmentDao.edit(equipment);
    }

    @Override
    public int delete(String ids) {
        return equipmentDao.delete(ids);
    }

    @Override
    public List<Equipment> findList(Map<String, Object> queryMap) {
        return equipmentDao.findList(queryMap);
    }

    @Override
    public int getTotal(Map<String, Object> queryMap) {
        return equipmentDao.gettotal(queryMap);
    }
}

