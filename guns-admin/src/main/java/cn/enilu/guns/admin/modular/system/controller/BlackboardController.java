package cn.enilu.guns.admin.modular.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.enilu.guns.admin.core.base.controller.BaseController;
import cn.enilu.guns.dao.system.SysNoticeRepository;

@Controller
@RequestMapping("/blackboard")
public class BlackboardController extends BaseController {

	@Autowired
	SysNoticeRepository sysNoticeRepository;

	/**
	 * 跳转到黑板
	 */
	@RequestMapping("")
	public String blackboard(Model model) {
		List notices = sysNoticeRepository.findAll();
		model.addAttribute("noticeList", notices);
		return "/blackboard.html";
	}
}
