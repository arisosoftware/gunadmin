package cn.enilu.guns.admin.core.beetl;

import org.beetl.ext.spring.BeetlGroupUtilConfiguration;

import cn.enilu.guns.admin.core.util.KaptchaUtil;
import cn.enilu.guns.service.system.impl.ConstantFactory;
import cn.enilu.guns.utils.ToolUtil;

public class BeetlConfiguration extends BeetlGroupUtilConfiguration {

	@Override
	public void initOther() {

		groupTemplate.registerFunctionPackage("shiro", new ShiroExt());
		groupTemplate.registerFunctionPackage("tool", new ToolUtil());
		groupTemplate.registerFunctionPackage("kaptcha", new KaptchaUtil());
		groupTemplate.registerFunctionPackage("constant", ConstantFactory.me());

	}

}
