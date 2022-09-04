package com.baoshi.programmer.service.admin.impl;

import com.baoshi.programmer.dao.admin.UserDao;
import com.baoshi.programmer.entity.admin.User;
import com.baoshi.programmer.service.admin.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * user用户serviceimpl
 * @author drg
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return userDao.findByUsername(username);
	}

	@Override
	public int add(User user) {
		// TODO Auto-generated method stub
		return userDao.add(user);
	}

	@Override
	public int edit(User user) {
		// TODO Auto-generated method stub
		return userDao.edit(user);
	}

	@Override
	public int delete(String ids) {
		// TODO Auto-generated method stub
		return userDao.delete(ids);
	}

	@Override
	public List<User> findList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return userDao.findList(queryMap);
	}

	@Override
	public int getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return userDao.getTotal(queryMap);
	}

	@Override
	public User findbyuserid(Long userid) {
		return userDao.findbyuserid(userid);
	}

	@Override
	public List<User> findListbycashierid(Map<String, Object> queryMap) {
		return userDao.findListbycashierid(queryMap);
	}

	@Override
	public Integer findcountmember(String ids) {
		return userDao.findcountmember(ids);
	}

	@Override
	public int editPassword(User user) {
		// TODO Auto-generated method stub
		return userDao.editPassword(user);
	}

}
