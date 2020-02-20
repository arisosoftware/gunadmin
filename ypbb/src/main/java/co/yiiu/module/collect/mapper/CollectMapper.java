package co.yiiu.module.collect.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import co.yiiu.module.collect.pojo.Collect;

public interface CollectMapper {
	int countByTopicId(Integer topicId);

	int countByUserId(Integer userId);

	int deleteByPrimaryKey(Integer id);

	void deleteByTopicId(Integer topicId);

	void deleteByUserId(Integer userId);

	// 自定义方法
	List<Map> findByUserId(@Param("userId") Integer userId, @Param("pageNo") Integer pageNo,
			@Param("pageSize") Integer pageSize, @Param("orderBy") String orderBy);

	Collect findByUserIdAndTopicId(@Param("userId") Integer userId, @Param("topicId") Integer topicId);

	int insert(Collect record);

	int insertSelective(Collect record);

	Collect selectByPrimaryKey(Integer id);

	int updateByPrimaryKey(Collect record);

	int updateByPrimaryKeySelective(Collect record);
}