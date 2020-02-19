package cn.enilu.guns.admin.config;

import java.util.Optional;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import cn.enilu.guns.bean.core.ShiroUser;
import cn.enilu.guns.shiro.ShiroKit;
import cn.enilu.guns.utils.Constants;

@Configuration
public class UserIDAuditorConfig implements AuditorAware<Optional<Long>> {
	@Override
	public Optional<Long> getCurrentAuditor() {
		ShiroUser shiroUser = ShiroKit.getUser();
		if (shiroUser != null) {
			return Optional.of(shiroUser.getId());
		}
		return Optional.of(Constants.SYSTEM_USER_ID);
	}
}
