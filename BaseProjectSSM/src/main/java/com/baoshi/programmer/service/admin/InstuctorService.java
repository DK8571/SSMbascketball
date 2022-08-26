package com.baoshi.programmer.service.admin;


import com.baoshi.programmer.entity.admin.Instuctor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface InstuctorService {
    public Instuctor findByInstuctorname(String Instuctorname);
    public int add(Instuctor instuctor);
    public int edit(Instuctor instuctor);
    public int delete(String ids);
    public List<Instuctor> findList(Map<String, Object> queryMap);
    public int getTotal(Map<String, Object> queryMap);
}
