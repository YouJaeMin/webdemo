package filter;

import javax.servlet.Filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

//@WebFilter(
//        urlPatterns = "/*",
//        initParams = @WebInitParam(name = "test-param", value = "doc;xls;zip;txt;")        
//)
public class LogFilter implements Filter {

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;

		// Get the IP address of client machine.
		String ipAddress = request.getRemoteAddr();

		// Log the IP address and current timestamp.
		System.out.println("IP " + ipAddress + ", Time " + new Date().toString());

		chain.doFilter(req, res);
	}

	public void init(FilterConfig config) throws ServletException {		
		String testParam = config.getInitParameter("test-param");
		System.out.println("Test Param: " + testParam);
	}

	public void destroy() {
		// add code to release any resource
	}
}