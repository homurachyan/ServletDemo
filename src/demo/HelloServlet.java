package demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 最简单的Servlet
 * @author Winter Lau
 */
public class HelloServlet extends HttpServlet {
	private String message;
	
	public void init(ServletConfig config){     
	    System.out.println("Servlet 初始化 。。。"); 
	    message = "This World";
	}  
/*	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		HttpSession s = req.getSession();
		res.getWriter().println("Hello World!"+s.getId());
	}*/
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//获取request中的信息
		Enumeration<String> enu = request.getParameterNames();
		for (; enu.hasMoreElements();)
		       System.out.println(enu.nextElement());
		System.out.println(request.getQueryString()+" "+request.getProtocol());
/*		// 设置响应内容类型
		response.setContentType("text/html");
		
		// 实际的逻辑是在这里
		PrintWriter out = response.getWriter();
		out.println("<h1>" + message + "</h1>");*/
		
        //为名字和姓氏创建 Cookies      
        Cookie id = new Cookie("id", request.getParameter("id"));
        Cookie name = new Cookie("name", request.getParameter("name"));

        //为两个 Cookies设置过期日期为 24 小时后
        id.setMaxAge(60*60*24); 
        name.setMaxAge(60*60*24); 

        // 在响应头中添加两个 Cookies
        response.addCookie( id );
        response.addCookie( name );
        
     // 设置响应内容类型
        response.setContentType("text/html");
   
        PrintWriter out = response.getWriter();
        String title = "设置 Cookies 实例";
        String docType =
        "<!doctype html public \"-//w3c//dtd html 4.0 " +
        "transitional//en\">\n";
        out.println(docType +
                  "<html>\n" +
                  "<head><title>" + title + "</title></head>\n" +
                  "<body bgcolor=\"#f0f0f0\">\n" +
                  "<h1 align=\"center\">" + title + "</h1>\n" +
                  "<ul>\n" +
                  "  <li><b>名字</b>："
                  + request.getParameter("id") + "\n" +
                  "  <li><b>姓氏</b>："
                  + request.getParameter("name") + "\n" +
                  "</ul>\n" +
                  "</body></html>");
		
	}

	@Override
	public void destroy() {
		//destroy() 方法只会被调用一次，在 Servlet 生命周期结束时被调用。
		//destroy() 方法可以让您的 Servlet 关闭数据库连接、停止后台线程、把 Cookie 列表或点击计数器写入到磁盘，并执行其他类似的清理活动。
		System.out.println("Servlet 结束");
	}
}
