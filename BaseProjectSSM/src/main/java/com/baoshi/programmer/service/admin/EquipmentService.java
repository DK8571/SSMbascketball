package com.baoshi.programmer.service.admin;


import com.baoshi.programmer.entity.admin.Equipment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface EquipmentService {
    public Equipment findByequipmentname(String equipmentname);
    public int add(Equipment venues);
    public int edit(Equipment venues);
    public int delete(String ids);
    public List<Equipment> findList(Map<String, Object> queryMap);
    public int getTotal(Map<String, Object> queryMap);
}
