package cn.enilu.guns.admin.core.intercept;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import cn.enilu.guns.admin.core.base.controller.BaseController;
import cn.enilu.guns.admin.core.util.HttpSessionHolder;

@Aspect
@Component
public class SessionInterceptor extends BaseController {

	@Pointcut("execution(* cn.enilu.guns.admin.*..controller.*.*(..))")
	public void cutService() {
	}

	@Around("cutService()")
	public Object sessionKit(ProceedingJoinPoint point) throws Throwable {

		HttpSessionHolder.put(super.getHttpServletRequest().getSession());
		try {
			return point.proceed();
		} finally {
			HttpSessionHolder.remove();
		}
	}
}
