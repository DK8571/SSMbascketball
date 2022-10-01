package com.baoshi.programmer.service.admin;

import com.baoshi.programmer.entity.admin.Instuctor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface InstructororderService {
    public List<Instuctor> findList(Map<String, Object> queryMap);
    public int getTotal(Map<String, Object> queryMap);
    public int add(Long instructorid, Long orderid);
    public int find(Long instructorid, Long orderid);
    public int delete(Long instructorid, Long orderid);
    public int  deletebyorderid(int orderid);
}
