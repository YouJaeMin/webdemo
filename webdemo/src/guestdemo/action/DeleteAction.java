package guestdemo.action;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import guestdemo.dao.BoardDAO;

public class DeleteAction implements ActionImp {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		int num = Integer.parseInt(req.getParameter("num"));
		BoardDAO dao = BoardDAO.getInstance();

		String filename = dao.fileMethod(num);
		String saveDirectory = req.getRealPath("/");
		saveDirectory += "/temp/";

		if (filename != null) {
			File file = new File(saveDirectory, filename);
			file.delete();
		}

		dao.deleteMethod(num);
	}

	@Override
	public MultipartRequest execute(HttpServletRequest req) {
		return null;
	}

}
