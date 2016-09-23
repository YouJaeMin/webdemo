package cookiedemo;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/useCookie")
public class UseCookie extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String str = "";
		Cookie[] cookies = req.getCookies();
		if (cookies != null && cookies.length > 0) {
			for (int i = 0; i < cookies.length; i++) {
				str += cookies[i].getName()+"/"+cookies[i].getValue()+"<br/>";
			}
		}else{
			str = "쿠기가 없습니다.";
		}
		
		req.setAttribute("str", str);
		
		RequestDispatcher dis = req.getRequestDispatcher("/cookieview/usecookie.jsp");
		dis.forward(req, resp);
	}

}
