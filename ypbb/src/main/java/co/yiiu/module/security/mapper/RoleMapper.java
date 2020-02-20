package co.yiiu.module.security.mapper;

import java.util.List;

import co.yiiu.module.security.pojo.Role;

public interface RoleMapper {
	int deleteByPrimaryKey(Integer id);

	// 自定义方法
	List<Role> findAll();

	int insert(Role record);

	int insertSelective(Role record);

	Role selectByPrimaryKey(Integer id);

	int updateByPrimaryKey(Role record);

	int updateByPrimaryKeySelective(Role record);
}