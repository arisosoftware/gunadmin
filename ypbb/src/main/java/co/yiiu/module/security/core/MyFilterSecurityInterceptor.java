package co.yiiu.module.security.core;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;

@Service
public class MyFilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {

	@Autowired
	private FilterInvocationSecurityMetadataSource securityMetadataSource;

	/**
	 *
	 */
	@Override
	public void destroy() {
	}

	/**
	 * @param request
	 * @param response
	 * @param chain
	 * @throws IOException
	 * @throws ServletException
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		invoke(new FilterInvocation(request, response, chain));
	}

	/**
	 * @return
	 */
	@Override
	public Class<? extends Object> getSecureObjectClass() {
		return FilterInvocation.class;
	}

	/**
	 * @param filterConfig
	 */
	@Override
	public void init(FilterConfig filterConfig) {

	}

	public void invoke(FilterInvocation fi) throws IOException, ServletException {
		// fi里面有一个被拦截的url
		// 里面调用MyInvocationSecurityMetadataSource的getAttributes(Object
		// object)这个方法获取fi对应的所有权限
		// 再调用MyAccessDecisionManager的decide方法来校验用户的权限是否足够
		InterceptorStatusToken token = super.beforeInvocation(fi);
		try {
			// 执行下一个拦截器
			fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
		} finally {
			super.afterInvocation(token, null);
		}
	}

	/**
	 * @return
	 */
	@Override
	public SecurityMetadataSource obtainSecurityMetadataSource() {
		return this.securityMetadataSource;
	}

	@Autowired
	public void setMyAccessDecisionManager(MyAccessDecisionManager myAccessDecisionManager) {
		super.setAccessDecisionManager(myAccessDecisionManager);
	}

}