package co.yiiu.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import co.yiiu.config.properties.SiteConfig;
import co.yiiu.core.base.BaseController;
import co.yiiu.core.bean.Result;
import co.yiiu.core.exception.ApiAssert;
import co.yiiu.module.user.pojo.User;
import co.yiiu.module.user.service.UserService;

/**
 * Created by tomoya at 2018/3/26
 */
@Controller
@RequestMapping("/admin/user")
public class UserAdminController extends BaseController {

	@Autowired
	private SiteConfig siteConfig;
	@Autowired
	private UserService userService;

	@GetMapping("/block")
	@ResponseBody
	public Result block(Integer id) {
		userService.blockUser(id);
		return Result.success();
	}

	@GetMapping("/delete")
	@ResponseBody
	public Result delete(Integer id) {
		userService.deleteById(id);
		return Result.success();
	}

	@GetMapping("/edit")
	public String edit(Integer id, Model model) {
		model.addAttribute("user", userService.findById(id));
		return "admin/user/edit";
	}

	@GetMapping("/list")
	public String list(@RequestParam(defaultValue = "1") Integer pageNo, Model model) {
		model.addAttribute("page", userService.pageUser(pageNo, siteConfig.getPageSize()));
		return "admin/user/list";
	}

	@PostMapping("/edit")
	@ResponseBody
	public Result update(Integer id, String username, String password, Integer reputation) {
		ApiAssert.notEmpty(username, "用户名不能为空");
		ApiAssert.notNull(reputation, "声望不能为空");
		User user = userService.findById(id);
		user.setUsername(username);
		// 如果密码字段存在，则修改
		if (!StringUtils.isEmpty(password)) {
			user.setPassword(new BCryptPasswordEncoder().encode(password));
		}
		user.setReputation(reputation);
		userService.update(user);
		return Result.success();
	}

}
