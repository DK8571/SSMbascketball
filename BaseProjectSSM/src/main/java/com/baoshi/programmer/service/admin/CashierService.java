package com.baoshi.programmer.service.admin;

import com.baoshi.programmer.entity.admin.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public interface CashierService {
    public User findByCashiername(String username);
    public int add(User user);
    public int edit(User user);
    public int delete(String ids);
    public List<User> findList(Map<String, Object> queryMap);
    public int getTotal(Map<String, Object> queryMap);
}
