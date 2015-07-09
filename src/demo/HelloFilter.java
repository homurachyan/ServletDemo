package demo;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HelloFilter implements Filter {
	private FilterConfig filterConfig = null;
	private String index = null; 
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("Filter 初始化");
		this.filterConfig = filterConfig;
		this.index = filterConfig.getInitParameter("indexPage");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		System.out.println("拦截 URI="+request.getRequestURI());
		System.out.println("Filter已经截获到用户的请求的地址: " + request.getServletPath());
		System.out.println(this.getClass().getClassLoader().getResource("/").getPath());
		System.out.println(request.getRemoteAddr());
		
        HttpSession session = request.getSession(false);
        if (session != null) {  
    		System.out.println("不存在用户名,跳转到欢迎页面");
    		//request.setAttribute("tip" , "您还没有登录");
    		//跳转到指定的URL地址，产生一个新的request，所以要传递参数只有在url后加参 数
    		//response.sendRedirect(index);
    		//是直接将请求转发到指定URL，所以该请求 能够直接获得上一个请求的数据，也就是说采用请求转发
    		//request.getRequestDispatcher(index).forward(request,response);
    		chain.doFilter(req, res);
        }else{
        	chain.doFilter(req, res);     	
        }  
	}

	@Override
	public void destroy() {
		System.out.println("Filter 结束");
	}
}
