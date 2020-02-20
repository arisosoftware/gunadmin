package co.yiiu.core.base;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import co.yiiu.config.properties.SiteConfig;
import co.yiiu.core.util.CookieHelper;
import co.yiiu.core.util.JsonUtil;
import co.yiiu.core.util.StrUtil;
import co.yiiu.core.util.encrypt.Base64Helper;
import co.yiiu.module.security.pojo.AdminUser;
import co.yiiu.module.security.service.AdminUserService;
import co.yiiu.module.user.pojo.User;
import co.yiiu.module.user.service.UserService;

/**
 * Created by tomoya. Copyright (c) 2016, All Rights Reserved. https://yiiu.co
 */
@Component
public class BaseEntity {

	private static final long DAY = 24 * BaseEntity.HOUR;
	private static final long HOUR = 60 * BaseEntity.MINUTE;
	private static final long MINUTE = 60 * 1000;

	private static final long MONTH = 31 * BaseEntity.DAY;
	private static final long WEEK = 7 * BaseEntity.DAY;
	private static final long YEAR = 12 * BaseEntity.MONTH;
	@Autowired
	private AdminUserService adminUserService;

	private Logger log = LoggerFactory.getLogger(BaseEntity.class);
	@Autowired
	private SiteConfig siteConfig;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	@Autowired
	private UserService userService;

	public String formatContent(String content) {
		Document parse = Jsoup.parse(content);
		Elements tableElements = parse.select("table");
		tableElements.forEach(element -> element.addClass("table table-bordered"));
		Elements aElements = parse.select("p");
		if (aElements != null && aElements.size() > 0) {
			aElements.forEach(element -> {
				try {
					String href = element.text();
					if (href.contains("//www.youtube.com/watch")) {
						URL aUrl = new URL(href);
						String query = aUrl.getQuery();
						Map<String, Object> querys = StrUtil.formatParams(query);
						element.text("");
						element.addClass("embed-responsive embed-responsive-16by9");
						element.append("<iframe class='embedded_video' src='https://www.youtube.com/embed/"
								+ querys.get("v") + "' frameborder='0' allowfullscreen></iframe>");
					} else if (href.contains("//v.youku.com/v_show/")) {
						element.text("");
						URL aUrl = new URL(href);
						String _href = "https://player.youku.com/embed/"
								+ aUrl.getPath().replace("/v_show/id_", "").replace(".html", "");
						element.addClass("embedded_video_wrapper");
						element.append("<iframe class='embedded_video' src='" + _href
								+ "' frameborder='0' allowfullscreen></iframe>");
					}
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			});
		}
		return parse.outerHtml();
	}

	/**
	 * 格式化日期
	 *
	 * @param date
	 * @return
	 */
	public String formatDate(Date date) {
		if (date == null)
			return "";

		long offset = System.currentTimeMillis() - date.getTime();
		if (offset > BaseEntity.YEAR)
			return (offset / BaseEntity.YEAR) + "年前";
		else if (offset > BaseEntity.MONTH)
			return (offset / BaseEntity.MONTH) + "个月前";
		else if (offset > BaseEntity.WEEK)
			return (offset / BaseEntity.WEEK) + "周前";
		else if (offset > BaseEntity.DAY)
			return (offset / BaseEntity.DAY) + "天前";
		else if (offset > BaseEntity.HOUR)
			return (offset / BaseEntity.HOUR) + "小时前";
		else if (offset > BaseEntity.MINUTE)
			return (offset / BaseEntity.MINUTE) + "分钟前";
		else
			return "刚刚";
	}

	// 获取后台用户
	public AdminUser getAdminUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null)
			return null;
		Object obj = authentication.getPrincipal();

		if (obj instanceof org.springframework.security.core.userdetails.User) {
			String username = ((UserDetails) obj).getUsername();
			return adminUserService.findByUsername(username);
		} else
			return null;
	}

	// 获取前台用户
	public User getUser() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getResponse();
		String token = CookieHelper.getValue(request, siteConfig.getCookie().getUserName());
		if (StringUtils.isEmpty(token))
			return null;
		// token不为空，查redis
		try {
			token = new String(Base64Helper.decode(token));
			ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
			String redisUser = stringStringValueOperations.get(token);
			if (!StringUtils.isEmpty(redisUser))
				return JsonUtil.jsonToObject(redisUser, User.class);

			User user = userService.findByToken(token);
			if (user == null) {
				CookieHelper.clearCookieByName(response, siteConfig.getCookie().getUserName());
			} else {
				stringStringValueOperations.set(token, JsonUtil.objectToJson(user));
				return user;
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			CookieHelper.clearCookieByName(response, siteConfig.getCookie().getUserName());
		}
		return null;
	}

	public boolean isEmpty(String text) {
		return StringUtils.isEmpty(text);
	}
}
