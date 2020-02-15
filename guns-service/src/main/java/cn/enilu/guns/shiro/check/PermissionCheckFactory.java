
package cn.enilu.guns.shiro.check;

import cn.enilu.guns.bean.core.ShiroUser;
import cn.enilu.guns.bean.vo.SpringContextHolder;
import cn.enilu.guns.shiro.ShiroKit;
import cn.enilu.guns.utils.CollectionKit;
import cn.enilu.guns.utils.HttpKit;
import cn.enilu.guns.web.listener.ConfigListener;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;


@Service
@DependsOn("springContextHolder")
@Transactional(readOnly = true)
public class PermissionCheckFactory implements ICheck {

	public static ICheck me() {
		return SpringContextHolder.getBean(ICheck.class);
	}

	@Override
	public boolean check(Object[] permissions) {
		ShiroUser user = ShiroKit.getUser();
		if (null == user) {
			return false;
		}
		String join = CollectionKit.join(permissions, ",");
		if (ShiroKit.hasAnyRoles(join)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean checkAll() {
		HttpServletRequest request = HttpKit.getRequest();
		ShiroUser user = ShiroKit.getUser();
		if (null == user) {
			return false;
		}
		String requestURI = request.getRequestURI().replaceFirst(ConfigListener.getConf().get("contextPath"), "");
		String[] str = requestURI.split("/");
		if (str.length > 3) {
			requestURI = "/" + str[1] + "/" + str[2];
		}
		if (ShiroKit.hasPermission(requestURI)) {
			return true;
		}
		return false;
	}

}
