package com.baoshi.programmer.service.admin.impl;

import com.baoshi.programmer.dao.admin.MemberDao;
import com.baoshi.programmer.entity.admin.Member;
import com.baoshi.programmer.entity.admin.User;
import com.baoshi.programmer.service.admin.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberDao memberDao;

    @Override
    public User findByUsername(String username) {
        // TODO Auto-generated method stub
        return memberDao.findByUsername(username);
    }

    @Override
    public int add(User user) {
        // TODO Auto-generated method stub
        return memberDao.add(user);
    }

    @Override
    public int edit(User user) {
        // TODO Auto-generated method stub
        return memberDao.edit(user);
    }

    @Override
    public int editPassword(User user) {
        return 0;
    }

    @Override
    public int delete(String ids) {
        // TODO Auto-generated method stub
        return memberDao.delete(ids);
    }

    @Override
    public List<Member> findList(Map<String, Object> queryMap) {
        // TODO Auto-generated method stub
        return memberDao.findList(queryMap);
    }

    @Override
    public int getTotal(Map<String, Object> queryMap) {
        // TODO Auto-generated method stub
        return memberDao.getTotal(queryMap);
    }

    @Override
    public void addmember(long id) {
        // TODO Auto-generated method stub
        memberDao.addmember(id);
    }

    @Override
    public int deletemember(String ids) {
        return memberDao.deletemember(ids);
    }

    @Override
    public void editmember(double balance, Long id) {
        memberDao.editmember(balance, id);
    }

    @Override
    public Member findbyuserid(long userid) {
        return memberDao.findbyuserid(userid);
    }

}
