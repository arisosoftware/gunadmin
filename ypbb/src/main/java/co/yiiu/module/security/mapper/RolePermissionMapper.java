package co.yiiu.module.security.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import co.yiiu.module.security.pojo.RolePermission;

public interface RolePermissionMapper {
	int deleteByPrimaryKey(Long id);

	// 自定义方法
	void deleteRolePermission(@Param("roleId") Integer roleId, @Param("permissionId") Integer permissionId);

	List<RolePermission> findByRoleId(Integer roleId);

	int insert(RolePermission record);

	int insertSelective(RolePermission record);

	RolePermission selectByPrimaryKey(Long id);

	int updateByPrimaryKey(RolePermission record);

	int updateByPrimaryKeySelective(RolePermission record);
}