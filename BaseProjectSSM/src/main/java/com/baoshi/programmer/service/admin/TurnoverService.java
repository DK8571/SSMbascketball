package com.baoshi.programmer.service.admin;

import com.baoshi.programmer.entity.admin.Turnover;

import java.util.List;

public interface TurnoverService {

    public List<Turnover> findturnover(Long id);
}
