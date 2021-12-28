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
import com.campus.board.notice.model.vo.NoticeBoard;

/**
 * Servlet implementation class NoticeBoardSelectOneServlet
 */
@WebServlet("/board/notice/selectOne.do")
public class NoticeBoardSelectOneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeBoardSelectOneServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		System.out.println(noticeNo);
		
		NoticeBoardService noticebService = new NoticeBoardServiceImpl();
		NoticeBoard noticeBoard = noticebService.noticeboardSelectOne(noticeNo);
		
		if (noticeBoard != null) {
			RequestDispatcher view = request.getRequestDispatcher("/community/notice/noticepost.jsp");
			request.setAttribute("noticeBoard", noticeBoard);
			request.setAttribute("currentPage", currentPage);
			view.forward(request, response);

		} else {
			response.sendRedirect("/main/error/error.jspp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
