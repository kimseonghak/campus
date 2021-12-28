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
 * Servlet implementation class MsgBoardSearchServlet
 */
@WebServlet("/board/msg/search.do")
public class MsgBoardSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MsgBoardSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int currentPage;
		if (request.getParameter("currentPage") == null) {
			currentPage = 1;
		} else {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		String type = request.getParameter("type");
		String keyword = request.getParameter("keyword");
		String userId = request.getParameter("userId");
		
		MsgBoardService msgbService = new MsgBoardServiceImpl();
		
		MsgPage page=msgbService.search(type,keyword,currentPage,userId);
		
		RequestDispatcher view = request.getRequestDispatcher("/community/msg/msgboard.jsp");
		request.setAttribute("page", page);
		request.setAttribute("keyword", keyword);
		view.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
