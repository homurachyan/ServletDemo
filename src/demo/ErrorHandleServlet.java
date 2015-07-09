package demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ErrorHandleServlet extends HttpServlet {
	public void init(ServletConfig config){     
	    System.out.println("ErrorHandleServlet 初始化 。。。"); 
	}  
/*	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		HttpSession s = req.getSession();
		res.getWriter().println("Hello World!"+s.getId());
	}*/
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// 分析 Servlet 异常       
	      Throwable throwable = (Throwable)
	      request.getAttribute("javax.servlet.error.exception");
	      Integer statusCode = (Integer)
	      request.getAttribute("javax.servlet.error.status_code");
	      String servletName = (String)
	      request.getAttribute("javax.servlet.error.servlet_name");
	      if (servletName == null){
	         servletName = "Unknown";
	      }
	      String requestUri = (String)
	      request.getAttribute("javax.servlet.error.request_uri");
	      if (requestUri == null){
	         requestUri = "Unknown";
	      }

	      // 设置响应内容类型
	      response.setContentType("text/html");
	 
	      PrintWriter out = response.getWriter();
		  String title = "Error/Exception Information";
	      String docType =
	      "<!doctype html public \"-//w3c//dtd html 4.0 " +
	      "transitional//en\">\n";
	      out.println(docType +
	        "<html>\n" +
	        "<head><title>" + title + "</title></head>\n" +
	        "<body bgcolor=\"#f0f0f0\">\n");

	      if (throwable == null && statusCode == null){
	         out.println("<h2>Error information is missing</h2>");
	         out.println("Please return to the <a href=\"" + 
	           response.encodeURL("http://localhost:8080/") + 
	           "\">Home Page</a>.");
	      }else if (statusCode != null){
	         out.println("The status code : " + statusCode);
	      }else{
	         out.println("<h2 class='tutheader'>Error information</h2>");
	         out.println("Servlet Name : " + servletName + 
	                             "</br></br>");
	         out.println("Exception Type : " + 
	                             throwable.getClass( ).getName( ) + 
	                             "</br></br>");
	         out.println("The request URI: " + requestUri + 
	                             "<br><br>");
	         out.println("The exception message: " + 
	                                 throwable.getMessage( ));
	      }
	      out.println("</body>");
	      out.println("</html>");
	}

	@Override
	public void destroy() {
		//destroy() 方法只会被调用一次，在 Servlet 生命周期结束时被调用。
		//destroy() 方法可以让您的 Servlet 关闭数据库连接、停止后台线程、把 Cookie 列表或点击计数器写入到磁盘，并执行其他类似的清理活动。
		System.out.println("ErrorHandleServlet 结束");
	}

}
