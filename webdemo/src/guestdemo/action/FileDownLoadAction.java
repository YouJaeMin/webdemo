package guestdemo.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import guestdemo.dao.BoardDAO;

public class FileDownLoadAction implements ActionImp {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		int num = Integer.parseInt(req.getParameter("num"));
		BoardDAO dao = BoardDAO.getInstance();
		String filename = dao.fileMethod(num);
		try {
			// 파일명 한글처리
			String convName = URLEncoder.encode(filename, "UTF-8");
			convName = convName.replace("+", " "); // 공백 +로 나옴

			// 컨텐트 타입
			resp.setContentType("application/octet-stream");
			// 다운로드 파일명
			resp.setHeader("Content-Disposition", "attachment;filename=" + convName + ";");

			String saveDirectory = req.getRealPath("/");
			File file = new File(saveDirectory + "/temp/" + filename);

			// 서버의 파일첨부 읽어오기
			FileInputStream is = new FileInputStream(file);
			BufferedInputStream bs = new BufferedInputStream(is);

			// 클라이언트에 파일내용보내기
			BufferedOutputStream bo = new BufferedOutputStream(resp.getOutputStream());

			byte buffer[] = new byte[1024];
			int len = 0;
			while ((len = bs.read(buffer)) > 0) {
				bo.write(buffer, 0, len);
				bo.flush();
			}

			bo.close();
			bs.close();
			is.close();

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public MultipartRequest execute(HttpServletRequest req) {

		return null;
	}

}
