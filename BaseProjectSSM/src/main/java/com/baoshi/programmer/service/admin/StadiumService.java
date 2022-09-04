package com.baoshi.programmer.service.admin;

import com.baoshi.programmer.entity.admin.Stadium;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface StadiumService {
    public Stadium findByStadiumname(String stadiumname);
    public int add(Stadium stadium);
    public int edit(Stadium stadium);
    public int delete(String stadium);
    public List<Stadium> findList(Map<String, Object> queryMap);
    public int getTotal(Map<String, Object> queryMap);
    public Integer findvenues(String ids);
}
