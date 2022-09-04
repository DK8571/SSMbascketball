package com.baoshi.programmer.service.admin;

import com.baoshi.programmer.entity.admin.Member;
import com.baoshi.programmer.entity.admin.User;

import java.util.List;
import java.util.Map;

public interface MemberService {
    public User findByUsername(String username);
    public int add(User user);
    public int edit(User user);
    public int editPassword(User user);
    public int delete(String ids);
    public List<Member> findList(Map<String, Object> queryMap);
    public int getTotal(Map<String, Object> queryMap);
    public void addmember(long id);
    public int deletemember(String ids);
    public void editmember(double balance, Long id);
    public Member findbyuserid(long userid);
    public Member findbymemberid(long memberid);
    public Double findblance(String ids);
    public Integer findorder(String ids);
}
