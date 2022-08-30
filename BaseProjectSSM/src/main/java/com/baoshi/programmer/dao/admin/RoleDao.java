package com.baoshi.programmer.dao.admin;

import com.baoshi.programmer.entity.admin.Role;
import com.baoshi.programmer.entity.admin.Turnover;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 角色role dao
 * @author drg
 *
 */
@Repository
public interface RoleDao {
	public int add(Role role);
	public int edit(Role role);
	public int delete(Long id);

	@Select({"<script>"+
			"select * from role " +
			"<if test='name != null'>" +
			"where name like '%${name}%' " +
			"</if>" +
			"<if test='offset != null and pageSize != null'>" +
			"limit #{offset},#{pageSize}" +
			"</if>"+
			"</script>"
	})

	public List<Role> findList(Map<String, Object> queryMap);

	public int getTotal(Map<String, Object> queryMap);

	@Select("select * from role where id = #{id}")

	public Role find(Long id);


}
