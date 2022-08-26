package com.baoshi.programmer.service.admin;

import com.baoshi.programmer.entity.admin.Authority;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * 权限service接口
 * @author drg
 *
 */
@Service
public interface AuthorityService {
	public int add(Authority authority);
	public int deleteByRoleId(Long roleId);
	public List<Authority> findListByRoleId(Long roleId);
}
