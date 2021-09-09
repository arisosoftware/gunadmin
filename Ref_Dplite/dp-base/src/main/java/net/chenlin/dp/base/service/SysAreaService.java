package net.chenlin.dp.base.service;

import java.util.List;
import java.util.Map;

import net.chenlin.dp.base.entity.SysAreaEntity;
import net.chenlin.dp.common.entity.R;

/**
 * 行政区域
 *
 * @author ZhouChenglin
 * @email yczclcn@163.com
 * @url www.chenlintech.com
 * @date 2017年8月18日 下午3:40:18
 */
public interface SysAreaService {

	List<SysAreaEntity> listAreaByParentCode(String areaCode);
	
	R listAreaByParentCode(Map<String, Object> params);
	
	R saveArea(SysAreaEntity area);
	
	R getAreaById(Long areaId);
	
	R updateArea(SysAreaEntity area);
	
	R batchRemoveArea(Long[] id);
	
}
