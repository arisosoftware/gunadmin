package cn.enilu.guns.warpper;

import cn.enilu.guns.service.system.impl.ConstantFactory;
import cn.enilu.guns.utils.ToolUtil;

import java.util.Map;


public class DeptWarpper extends BaseControllerWarpper {

	public DeptWarpper(Object list) {
		super(list);
	}

	@Override
	public void warpTheMap(Map<String, Object> map) {

		Long pid = (Long) map.get("pid");

		if (ToolUtil.isEmpty(pid) || pid.intValue() == 0) {
			map.put("pName", "--");
		} else {
			map.put("pName", ConstantFactory.me().getDeptName(pid));
		}
	}

}
