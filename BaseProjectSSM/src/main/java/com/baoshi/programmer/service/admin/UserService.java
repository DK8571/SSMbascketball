package com.baoshi.programmer.service.admin;

import com.baoshi.programmer.entity.admin.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * user用户service
 * @author drg
 *
 */
public interface UserService {
	public User findByUsername(String username);
	public int add(User user);
	public int edit(User user);
	public int editPassword(User user);
	public int delete(String ids);
	public List<User> findList(Map<String, Object> queryMap);
	public int getTotal(Map<String, Object> queryMap);
    public User findbyuserid(Long userid);
	public List<User> findListbycashierid(Map<String, Object> queryMap);
    public Integer findcountmember(String ids);
}
