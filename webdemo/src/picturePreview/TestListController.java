package picturePreview;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/previewList")
public class TestListController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setAttribute("aList", TestDAO.getInstance().selectMethod());
		RequestDispatcher dis = req.getRequestDispatcher("/picturePerview/list.jsp");
		dis.forward(req, resp);
		
	}
	
}
