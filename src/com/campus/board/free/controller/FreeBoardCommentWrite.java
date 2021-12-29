package com.campus.board.free.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.campus.board.free.model.service.FreeBoardService;
import com.campus.board.free.model.service.FreeBoardServiceImpl;
import com.campus.member.model.vo.Member;

/**
 * Servlet implementation class FreeBoardCommentWrite
 */
@WebServlet("/board/free/commentWrite.do")
public class FreeBoardCommentWrite extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FreeBoardCommentWrite() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		int freeNo = Integer.parseInt(request.getParameter("freeNo"));
		int commentPage;
		if(request.getParameter("commentPage")==null) {
			commentPage=1;
		}else {
			commentPage=Integer.parseInt(request.getParameter("commentPage"));
		}
		String commentContent = request.getParameter("commentContent");
		HttpSession session = request.getSession();
		String userId = ((Member)session.getAttribute("member")).getUserId();
		
		FreeBoardService fbService = new FreeBoardServiceImpl();
		int result = fbService.commentWrite(freeNo,userId,commentContent);
		
		if(result>0) {
			response.sendRedirect("/board/free/selectOne.do?currentPage="+currentPage+"&freeNo="+freeNo+"&commentPage="+commentPage);
		}else {
			response.sendRedirect("/main/error/writeError.jsp");
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
