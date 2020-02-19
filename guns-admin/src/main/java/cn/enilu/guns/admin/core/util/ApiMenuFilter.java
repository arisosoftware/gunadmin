package cn.enilu.guns.admin.core.util;

import java.util.ArrayList;
import java.util.List;

import cn.enilu.guns.admin.config.properties.GunsProperties;
import cn.enilu.guns.bean.constant.Const;
import cn.enilu.guns.bean.vo.SpringContextHolder;
import cn.enilu.guns.bean.vo.node.MenuNode;

public class ApiMenuFilter extends MenuNode {

	public static List<MenuNode> build(List<MenuNode> nodes) {

		// 如果关闭了接口文档,则不显示接口文档菜单
		GunsProperties gunsProperties = SpringContextHolder.getBean(GunsProperties.class);
		if (!gunsProperties.getSwaggerOpen()) {
			List<MenuNode> menuNodesCopy = new ArrayList<>();
			for (MenuNode menuNode : nodes) {
				if (Const.API_MENU_NAME.equals(menuNode.getName())) {
					continue;
				} else {
					menuNodesCopy.add(menuNode);
				}
			}
			nodes = menuNodesCopy;
		}

		return nodes;
	}
}
