package guestdemo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

public interface ActionImp {
	public void execute(HttpServletRequest req, HttpServletResponse resp);
	public MultipartRequest execute(HttpServletRequest req);
}
