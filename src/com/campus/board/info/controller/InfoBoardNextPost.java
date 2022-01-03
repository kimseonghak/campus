package com.campus.board.info.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.campus.board.info.model.service.InfoBoardService;
import com.campus.board.info.model.service.InfoBoardServiceImpl;

/**
 * Servlet implementation class InfoBoardNextPost
 */
@WebServlet("/InfoBoardNextPost.do")
public class InfoBoardNextPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InfoBoardNextPost() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int infoNo = Integer.parseInt(request.getParameter("infoNo"));
		
		InfoBoardService nbService = new InfoBoardServiceImpl();
		int nextNo = nbService.nextInfoBoard(infoNo);
		
		PrintWriter out = response.getWriter();
		if(nextNo==infoNo) {
			out.println("<script>alert('마지막 글입니다.');</script>");
		}
		
		response.sendRedirect("/board/info/selectOne.do?infoNo="+nextNo);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
