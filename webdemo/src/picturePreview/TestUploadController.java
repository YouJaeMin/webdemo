package picturePreview;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/uploadMain")
public class TestUploadController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dis = req.getRequestDispatcher("/picturePerview/insert.jsp");
		dis.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MultipartRequest multi = null;

		// String saveDirectory = "c:/temp/";
		String root = req.getRealPath("/");
		String saveDirectory = root + "/temp";
		File file = new File(saveDirectory);
		if (!file.isDirectory()) {
			file.mkdir();
		}
		int maxPostSize = 1 * 1000 * 1000 * 1000; // 1GB
		String encoding = "UTF-8";
		// DefaultFileRenamePolicy 중복 파일 이름 바꿔 올리기
		multi = new MultipartRequest(req, saveDirectory, maxPostSize, encoding, new DefaultFileRenamePolicy());

		TestDAO.getInstance().insertMethod(multi.getFilesystemName("filepath"));

		resp.sendRedirect("previewList");

	}

}
