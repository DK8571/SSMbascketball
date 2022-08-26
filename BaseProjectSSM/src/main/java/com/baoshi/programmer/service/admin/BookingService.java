package com.baoshi.programmer.service.admin;


import com.baoshi.programmer.entity.admin.Booking;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface BookingService {
    public int add(Booking booking);
    public List<Booking> findList(Map<String, Object> queryMap);
    public int getTotal(Map<String, Object> queryMap);
}
