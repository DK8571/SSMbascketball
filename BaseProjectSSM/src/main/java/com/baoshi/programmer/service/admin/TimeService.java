package com.baoshi.programmer.service.admin;


import com.baoshi.programmer.entity.admin.Time;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface TimeService {
    public Time findBytimename(String time);
    public int add(Time time);
    public int edit(Time time);
    public int delete(String ids);
    public List<Time> findList(Map<String, Object> queryMap);
    public int getTotal(Map<String, Object> queryMap);
}
