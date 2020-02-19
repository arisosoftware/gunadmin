package cn.enilu.guns.admin.core.base.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.View;

@Component("error")
public class GunsErrorView implements View {

	@Override
	public String getContentType() {
		return "text/html";
	}

	@Override
	public void render(Map<String, ?> map, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws Exception {
		httpServletRequest.getRequestDispatcher("/global/error").forward(httpServletRequest, httpServletResponse);
	}
}
