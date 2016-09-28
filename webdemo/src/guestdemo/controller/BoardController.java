package guestdemo.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import guestdemo.action.ListAction;
import guestdemo.action.ViewAction;
import guestdemo.action.WriteAction;

@WebServlet("/board/*")
public class BoardController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}

	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		String action = uri.substring(uri.lastIndexOf("/"));
		String path = "";
		if (action.equals("/*") || action.equals("/list.do")) {
			path = "/guestview/list.jsp";
			ListAction list = new ListAction();
			list.execute(req, resp);
		} else if (action.equals("/writeForm.do")) {
			path = "/guestview/write.jsp";
		} else if (action.equals("/view.do")) {
			path = "/guestview/view.jsp";
			ViewAction view = new ViewAction();
			view.execute(req, resp);
		} else if (action.equals("/write.do")) {
			WriteAction write = new WriteAction();
			write.execute(req, resp);
			resp.sendRedirect("list.do");
		}

		if (!path.equals("")) {
			RequestDispatcher dis = req.getRequestDispatcher(path);
			dis.forward(req, resp);
		}
	}

}
