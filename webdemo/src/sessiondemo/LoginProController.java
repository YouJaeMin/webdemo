package sessiondemo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logPro")
public class LoginProController extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String id = req.getParameter("id");
		String pass = req.getParameter("pass");
		
		MemDTO dto = new MemDTO();
		dto.setId(id);
		dto.setPass(pass);
		
		
		int check = MemDAO.getInstance().memCheck(dto);
		
		if(check == 1){
			HttpSession session = req.getSession();
			session.setAttribute("logOk", id);
			// 시간지정
			session.setMaxInactiveInterval(30*60); //30분
			resp.sendRedirect("login?ck="+1);
		}else{
			resp.sendRedirect("login?ck="+2);
		}
		
	}
	
	
}
