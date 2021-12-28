package com.campus.board.notice.controller;

import java.io.IOException;
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
 * Servlet implementation class NoticeBoardUpdateServlet
 */
@WebServlet("/board/notice/postUpdate.do")
public class NoticeBoardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeBoardUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String noticeContent = request.getParameter("content");
		int noticeNo = (Integer.parseInt(request.getParameter("noticeNo")));
		
		String userId = ((Member)request.getSession().getAttribute("member")).getUserId();
		
		NoticeBoard noticeBoard=new NoticeBoard();
		noticeBoard.setNoticeContent(noticeContent);
		noticeBoard.setNoticeNo(noticeNo);
		
		NoticeBoardService noticebService=new NoticeBoardServiceImpl();
		int result=noticebService.update(noticeBoard);
		
		if(result>0) {
			response.sendRedirect("/board/notice/selectOne.do?noticeNo="+noticeNo);
		}else
		{
			response.sendRedirect("/main/error/error.jsp");
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
