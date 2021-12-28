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
import com.campus.board.msg.model.vo.MsgPage;

/**
 * Servlet implementation class MsgBoardListAllServlet
 */
@WebServlet("/board/msg/listAll.do")
public class MsgBoardListAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MsgBoardListAllServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId=request.getParameter("userId");
		
		int currentPage;
		if (request.getParameter("currentPage") == null) {
			currentPage = 1;
		} else {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}

		MsgBoardService msgbService = new MsgBoardServiceImpl();
		MsgPage page = msgbService.msgboardListAll(currentPage,userId);
		
		if (userId==null) {
			RequestDispatcher view = request.getRequestDispatcher("/main/error/error.jsp");
			request.setAttribute("currentPage", currentPage);
			view.forward(request,response);
		}
		else {
			RequestDispatcher view = request.getRequestDispatcher("/community/msg/msgboard.jsp");
			request.setAttribute("msgpage", page);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("userId", userId);
			view.forward(request,response);
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
