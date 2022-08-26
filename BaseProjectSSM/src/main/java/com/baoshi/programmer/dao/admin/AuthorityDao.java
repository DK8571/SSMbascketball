package com.baoshi.programmer.dao.admin;

import com.baoshi.programmer.entity.admin.Authority;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 权限实现类dao
 * @author drg
 *
 */
@Repository
public interface AuthorityDao {
	public int add(Authority authority);
	public int deleteByRoleId(Long roleId);

	@Select({"<script>"+
			"select * from authority"+
			"<if test='_parameter != null'>"+
			"where roleId = #{roleId}"+
			"</if>"+
			"</script>"})

	public List<Authority> findListByRoleId(Long roleId);
}
