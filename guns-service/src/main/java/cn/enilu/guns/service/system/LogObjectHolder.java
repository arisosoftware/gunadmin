package cn.enilu.guns.service.system;

import cn.enilu.guns.bean.vo.SpringContextHolder;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.io.Serializable;


@Component
@Scope(scopeName = WebApplicationContext.SCOPE_SESSION)
public class LogObjectHolder implements Serializable {

	private Object object = null;

	public void set(Object obj) {
		this.object = obj;
	}

	public Object get() {
		return object;
	}

	public static LogObjectHolder me() {
		LogObjectHolder bean = SpringContextHolder.getBean(LogObjectHolder.class);
		return bean;
	}
}
