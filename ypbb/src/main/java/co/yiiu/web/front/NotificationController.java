package co.yiiu.web.front;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import co.yiiu.core.base.BaseController;

/**
 * Created by tomoya. Copyright (c) 2016, All Rights Reserved. https://yiiu.co
 */
@Controller
@RequestMapping("/notification")
public class NotificationController extends BaseController {

	/**
	 * 通知列表
	 *
	 * @param p
	 * @param model
	 * @return
	 */
	@GetMapping("/list")
	public String list(Integer p, Model model) {
		model.addAttribute("p", p);
		return "/front/notification/list";
	}

}
