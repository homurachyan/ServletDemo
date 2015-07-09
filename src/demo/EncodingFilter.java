package demo;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;

public class EncodingFilter implements Filter{
	private FilterConfig filterConfig = null;
	private String encoding = null; 
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("EncodingFilter 初始化");
		this.filterConfig = filterConfig;
		System.out.println(filterConfig.getFilterName());
		this.encoding = filterConfig.getInitParameter("encoding"); 
	}
    private String getEncoding() {   
        return this.encoding;   
     } 
    
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		this.encoding = null;   
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		  // 获取ServletContext 对象，用于记录日志 
        ServletContext context = this.filterConfig.getServletContext(); 
        System.out.println("开始设置编码格式");
		context.log("开始设置编码格式");
        String encoding = getEncoding();   
        if (encoding == null){   
            encoding = "gb2312";   
        }
        // 在请求里设置上指定的编码
        req.setCharacterEncoding(encoding);

        
        chain.doFilter(req, res);  
        context.log("成功设置了编码格式");
	}
  
}
