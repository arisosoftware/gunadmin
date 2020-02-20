package co.yiiu.module.log.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import co.yiiu.module.log.pojo.Log;
import co.yiiu.module.log.pojo.LogWithBLOBs;

public interface LogMapper {
	int countAllForAdmin();

	int countByUserId(Integer userId);

	int deleteByPrimaryKey(Integer id);

	void deleteByUserId(Integer userId);

	List<Map> findAllForAdmin(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize,
			@Param("orderBy") String orderBy);

	// 自定义方法
	List<LogWithBLOBs> findByUserId(@Param("userId") Integer userId, @Param("pageNo") Integer pageNo,
			@Param("pageSize") Integer pageSize, @Param("orderBy") String orderBy);

	int insert(LogWithBLOBs record);

	int insertSelective(LogWithBLOBs record);

	LogWithBLOBs selectByPrimaryKey(Integer id);

	int updateByPrimaryKey(Log record);

	int updateByPrimaryKeySelective(LogWithBLOBs record);

	int updateByPrimaryKeyWithBLOBs(LogWithBLOBs record);
}