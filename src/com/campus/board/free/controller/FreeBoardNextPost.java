package com.campus.board.free.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.campus.board.free.model.service.FreeBoardService;
import com.campus.board.free.model.service.FreeBoardServiceImpl;

/**
 * Servlet implementation class FreeBoardNextPost
 */
@WebServlet("/FreeBoardNextPost.do")
public class FreeBoardNextPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FreeBoardNextPost() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int freeNo = Integer.parseInt(request.getParameter("freeNo"));
		
		FreeBoardService fbService = new FreeBoardServiceImpl();
		int nextNo = fbService.nextFreeBoard(freeNo);
		
		response.sendRedirect("/board/free/selectOne.do?freeNo="+nextNo);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
