package com.baoshi.programmer.dao.admin;

import com.baoshi.programmer.entity.admin.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * user用户dao
 * @author drg
 *
 */
@Repository
public interface UserDao {

	@Select("select * from user where username = #{username}")

	public User findByUsername(String username);
	public int add(User user);
	public int edit(User user);
	public int editPassword(User user);
	public int delete(String ids);
	public List<User> findList(Map<String, Object> queryMap);
	public int getTotal(Map<String, Object> queryMap);
}