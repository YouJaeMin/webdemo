package guestdemo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import guestdemo.dao.BoardDAO;
import guestdemo.dto.BoardDTO;

public class UpdateFormAction {

	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		
		BoardDAO dao = BoardDAO.getInstance();
		
		int num = Integer.parseInt(req.getParameter("num"));
		
		BoardDTO dto = dao.viewMethod(num);
		
		req.setAttribute("dto", dto);
		
	}

}
