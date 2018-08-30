package auth;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFilter implements Filter{
	
	@Inject
	private LoginBean loginBean;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println(loginBean.getUser());
		
		if(loginBean==null||!loginBean.isLoggedIn()){
			String contextPath = ((HttpServletRequest)request).getContextPath();
			System.out.println("Filter loginbean null vagy !isLoggedIn");
			((HttpServletResponse)response).sendRedirect(contextPath + "/Sorrend.jpg");
		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
