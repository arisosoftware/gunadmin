package co.yiiu.web.tag;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.yiiu.config.properties.SiteConfig;
import co.yiiu.core.bean.Page;
import co.yiiu.module.user.pojo.User;
import co.yiiu.module.user.service.UserService;
import freemarker.core.Environment;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * Created by tomoya. Copyright (c) 2016, All Rights Reserved. https://yiiu.co
 */
@Component
public class ReputationDirective implements TemplateDirectiveModel {

	@Autowired
	private SiteConfig siteConfig;
	@Autowired
	private UserService userService;

	@Override
	public void execute(Environment environment, Map map, TemplateModel[] templateModels,
			TemplateDirectiveBody templateDirectiveBody) throws TemplateException, IOException {
		DefaultObjectWrapperBuilder builder = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);

		int p = map.get("p") == null ? 1 : Integer.parseInt(map.get("p").toString());
		int limit = map.get("limit") == null ? siteConfig.getPageSize() : Integer.parseInt(map.get("limit").toString());

		Page<User> page = userService.findByReputation(p, limit);

		environment.setVariable("page", builder.build().wrap(page));

		templateDirectiveBody.render(environment.getOut());
	}
}