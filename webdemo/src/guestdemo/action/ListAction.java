package guestdemo.action;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import guestdemo.dao.BoardDAO;
import guestdemo.dto.BoardDTO;
import guestdemo.dto.PageDTO;

public class ListAction {

	public void execute(HttpServletRequest req, HttpServletResponse resp) {

		String pageNum = req.getParameter("pageNum");
		if (pageNum == null || pageNum.equals("null") || pageNum.equals("")) {
			pageNum = "1";
		}
		int currentPage = Integer.parseInt(pageNum);
		
		String searchKey = req.getParameter("searchKey");
		String searchWord = req.getParameter("searchWord");
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("searchKey", searchKey);
		map.put("searchWord", searchWord);
		
		BoardDAO dao = BoardDAO.getInstance();
		int totalCount = dao.rowTotalCount(map);
		if (totalCount > 0) {
			PageDTO pdto = new PageDTO(currentPage, totalCount, searchKey, searchWord);
			List<BoardDTO> aList = dao.listMethod(pdto);
			req.setAttribute("aList", aList);
			req.setAttribute("pdto", pdto);
		}

	}

}
