package guestdemo.action;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import guestdemo.dao.BoardDAO;
import guestdemo.dto.BoardDTO;

public class WriteAction {

	public MultipartRequest execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		MultipartRequest multi = null;

		String saveDirectory = req.getRealPath("/");
		File file = new File(saveDirectory + "/temp");
		if (!file.exists()) {
			file.mkdir();
		}
		saveDirectory += "/temp/";
		int maxPostSize = 1 * 1000 * 1000 * 1000; // 1GB
		String encoding = "UTF-8";
		multi = new MultipartRequest(req, saveDirectory, maxPostSize, encoding, new DefaultFileRenamePolicy());

		BoardDTO dto = new BoardDTO();
		dto.setWriter(multi.getParameter("writer"));
		dto.setEmail(multi.getParameter("email"));
		dto.setSubject(multi.getParameter("subject"));
		dto.setContent(multi.getParameter("content"));
		dto.setUpload(multi.getFilesystemName("upload"));

		BoardDAO dao = BoardDAO.getInstance();

		if (multi.getParameter("re_level") != null) {
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			map.put("ref", new Integer(multi.getParameter("ref")));
			map.put("re_step", new Integer(multi.getParameter("re_step")));
			dao.reStepMethod(map);

			dto.setRef(Integer.parseInt(multi.getParameter("ref")));
			dto.setRe_step(Integer.parseInt(multi.getParameter("re_step")) + 1);
			dto.setRe_level(Integer.parseInt(multi.getParameter("re_level")) + 1);

		}

		dao.insertMethod(dto);
		
		return multi;
	}
}
