
package cn.enilu.guns.shiro.check;

import cn.enilu.guns.bean.vo.SpringContextHolder;


public class PermissionCheckManager {
	private final static PermissionCheckManager me = new PermissionCheckManager();

	private ICheck defaultCheckFactory = SpringContextHolder.getBean(ICheck.class);

	public static PermissionCheckManager me() {
		return me;
	}

	private PermissionCheckManager() {
	}

	public PermissionCheckManager(ICheck checkFactory) {
		this.defaultCheckFactory = checkFactory;
	}

	public void setDefaultCheckFactory(ICheck defaultCheckFactory) {
		this.defaultCheckFactory = defaultCheckFactory;
	}

	public static boolean check(Object[] permissions) {
		return me.defaultCheckFactory.check(permissions);
	}

	public static boolean checkAll() {
		return me.defaultCheckFactory.checkAll();
	}
}
