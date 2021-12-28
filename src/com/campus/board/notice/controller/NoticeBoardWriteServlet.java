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
import com.campus.member.model.vo.Member;

/**
 * Servlet implementation class NoticeBoardWriteServlet
 */
@WebServlet("/board/notice/write.do")
public class NoticeBoardWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeBoardWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String noticeTitle = request.getParameter("noticeTitle");
		String noticeContent = request.getParameter("noticeContent");
		
		NoticeBoard noticeBoard = new NoticeBoard();
		noticeBoard.setNoticeTitle(noticeTitle);
		noticeBoard.setNoticeContent(noticeContent);
		NoticeBoardService noticebService = new NoticeBoardServiceImpl();
		int result=noticebService.insert(noticeBoard);
		
		RequestDispatcher view = request.getRequestDispatcher("/community/notice/write.jsp");
			
		if(result>0){
			request.setAttribute("writeResult", true);
		}
		else{
			request.setAttribute("writeResult", false);
		}
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
