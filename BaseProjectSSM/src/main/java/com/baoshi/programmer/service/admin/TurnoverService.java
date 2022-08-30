package com.baoshi.programmer.service.admin;

import com.baoshi.programmer.entity.admin.Turnover;

import java.util.List;
import java.util.Map;

public interface TurnoverService {

    public List<Turnover> findturnover(Map<String,Object> querymap);
}
