package com.campus.board.free.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.campus.board.free.model.service.FreeBoardService;
import com.campus.board.free.model.service.FreeBoardServiceImpl;

/**
 * Servlet implementation class FreeBoardPrevPost
 */
@WebServlet("/FreeBoardPrevPost.do")
public class FreeBoardPrevPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FreeBoardPrevPost() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int freeNo = Integer.parseInt(request.getParameter("freeNo"));
		FreeBoardService fbService = new FreeBoardServiceImpl();
		int prevNo = fbService.prvFreeBoard(freeNo);
		
		PrintWriter out = response.getWriter();
		if(prevNo==freeNo) {
			out.println("<script>alert('첫 글입니다.');</script>");
		}
		
		response.sendRedirect("/board/free/selectOne.do?freeNo="+prevNo);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
