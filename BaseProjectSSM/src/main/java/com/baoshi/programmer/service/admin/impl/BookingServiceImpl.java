package com.baoshi.programmer.service.admin.impl;

import com.baoshi.programmer.dao.admin.BookingDao;
import com.baoshi.programmer.entity.admin.Booking;
import com.baoshi.programmer.service.admin.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingDao bookingDao;

    @Override
    public int add(Booking booking) {
        return bookingDao.add(booking);
    }

    @Override
    public List<Booking> findList(Map<String, Object> queryMap) {
        return bookingDao.findlist(queryMap);
    }

    @Override
    public int getTotal(Map<String, Object> queryMap) {
        return bookingDao.gettoal(queryMap);
    }
}
