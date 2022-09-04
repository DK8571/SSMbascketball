package com.baoshi.programmer.service.admin;


import com.baoshi.programmer.entity.admin.Venues;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface VenuesService {

    public Venues findByVenuesname(String venuesname);
    public int add(Venues venues);
    public int edit(Venues venues);
    public int delete(String ids);
    public List<Venues> findList(Map<String, Object> queryMap);
    public int getTotal(Map<String, Object> queryMap);
    public Venues findbyVenusId(Long venuesid);
    public List<Venues> findListbycashierid(Map<String, Object> queryMap);
    public Integer findequipment(String ids);
}
