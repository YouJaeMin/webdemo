package guestdemo.action;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import guestdemo.dao.BoardDAO;
import guestdemo.dto.BoardDTO;

public class UpdateProAction implements ActionImp {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {

	}

	@Override
	public MultipartRequest execute(HttpServletRequest req) {
		MultipartRequest multi = null;

		String saveDirectory = req.getRealPath("/");
		saveDirectory += "/temp/";
		int maxPostSize = 1 * 1000 * 1000 * 1000; // 1GB
		String encoding = "UTF-8";
		try {
			multi = new MultipartRequest(req, saveDirectory, maxPostSize, encoding, new DefaultFileRenamePolicy());
		} catch (IOException e) {
			e.printStackTrace();
		}

		BoardDAO dao = BoardDAO.getInstance();
		BoardDTO dto = new BoardDTO();

		int num = Integer.parseInt(multi.getParameter("num"));
		// 수정 첨부파일이 있으면
		if (multi.getFilesystemName("upload") != null) {
			String filename = dao.fileMethod(num);
			// 있던 첨부파일을 삭제 한 후
			if (filename != null) {
				File file = new File(saveDirectory, filename);
				file.delete();
			}
			dto.setUpload(multi.getFilesystemName("upload"));
		} else { // 없으면 기존꺼 그대로
			String filename = dao.fileMethod(num);
			if (filename != null) {
				dto.setUpload(filename);
			}
		}

		dto.setEmail(multi.getParameter("email"));
		dto.setSubject(multi.getParameter("subject"));
		dto.setContent(multi.getParameter("content"));
		dto.setNum(Integer.parseInt(multi.getParameter("num")));
		dao.updateMethod(dto);

		return multi;
	}

}
