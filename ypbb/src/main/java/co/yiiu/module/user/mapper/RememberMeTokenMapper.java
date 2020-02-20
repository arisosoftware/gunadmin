package co.yiiu.module.user.mapper;

import java.util.List;

import co.yiiu.module.user.pojo.RememberMeToken;

public interface RememberMeTokenMapper {
	int deleteByPrimaryKey(Integer id);

	void deleteByUsername(String username);

	List<RememberMeToken> findAllByUsername(String username);

	// 自定义方法
	RememberMeToken findBySeries(String series);

	int insert(RememberMeToken record);

	int insertSelective(RememberMeToken record);

	RememberMeToken selectByPrimaryKey(Integer id);

	int updateByPrimaryKey(RememberMeToken record);

	int updateByPrimaryKeySelective(RememberMeToken record);
}