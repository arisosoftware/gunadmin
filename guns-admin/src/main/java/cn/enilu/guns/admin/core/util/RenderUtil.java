package cn.enilu.guns.admin.core.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import cn.enilu.guns.bean.exception.GunsException;
import cn.enilu.guns.bean.exception.GunsExceptionEnum;

public class RenderUtil {

	/**
	 * 渲染json对象
	 */
	public static void renderJson(HttpServletResponse response, Object jsonObject) {
		try {
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			PrintWriter writer = response.getWriter();
			writer.write(JSON.toJSONString(jsonObject));
		} catch (IOException e) {
			throw new GunsException(GunsExceptionEnum.WRITE_ERROR);
		}
	}
}
