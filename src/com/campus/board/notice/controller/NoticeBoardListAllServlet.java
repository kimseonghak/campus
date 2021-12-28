package com.campus.board.notice.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.campus.board.notice.model.service.NoticeBoardService;
import com.campus.board.notice.model.service.NoticeBoardServiceImpl;
import com.campus.board.notice.model.vo.NoticePage;

/**
 * Servlet implementation class NoticeBoardListAllServlet
 */
@WebServlet("/board/notice/listAll.do")
public class NoticeBoardListAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeBoardListAllServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int currentPage;
		if (request.getParameter("currentPage") == null) {
			currentPage = 1;
		} else {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}

		NoticeBoardService noticebService = new NoticeBoardServiceImpl();
		NoticePage page = noticebService.noticeboardListAll(currentPage);
		RequestDispatcher view = request.getRequestDispatcher("/community/notice/noticeboard.jsp");
		request.setAttribute("noticepage", page);
		request.setAttribute("currentPage", currentPage);

		view.forward(request,response);;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
