package com.campus.board.notice.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.campus.board.free.model.service.FreeBoardService;
import com.campus.board.free.model.service.FreeBoardServiceImpl;
import com.campus.board.notice.model.service.NoticeBoardService;
import com.campus.board.notice.model.service.NoticeBoardServiceImpl;

/**
 * Servlet implementation class NoticeBoardPrevPost
 */
@WebServlet("/NoticeBoardPrevPost.do")
public class NoticeBoardPrevPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeBoardPrevPost() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		
		NoticeBoardService nbService = new NoticeBoardServiceImpl();
		int prevNo = nbService.prevNoticeBoard(noticeNo);
		
		PrintWriter out = response.getWriter();
		if(prevNo==noticeNo) {
			out.println("<script>alert('첫 글입니다.');</script>");
		}
		
		response.sendRedirect("/board/notice/selectOne.do?noticeNo="+prevNo);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
