package com.campus.board.msg.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.campus.board.msg.model.service.MsgBoardService;
import com.campus.board.msg.model.service.MsgBoardServiceImpl;
import com.campus.board.msg.model.vo.MsgBoard;

/**
 * Servlet implementation class MsgBoardSelectOneServlet
 */
@WebServlet("/board/msg/selectOne.do")
public class MsgBoardSelectOneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MsgBoardSelectOneServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		int msgNo = Integer.parseInt(request.getParameter("msgNo"));
		System.out.println(msgNo);
		
		MsgBoardService msgbService = new MsgBoardServiceImpl();
		MsgBoard msgBoard = msgbService.msgboardSelectOne(msgNo);
		
		if (msgBoard != null) {
			RequestDispatcher view = request.getRequestDispatcher("/community/msg/msgpost.jsp");
			request.setAttribute("msgBoard", msgBoard);
			request.setAttribute("currentPage", currentPage);
			view.forward(request, response);

		} else {
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
