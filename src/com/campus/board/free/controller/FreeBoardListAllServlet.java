package com.campus.board.free.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.campus.board.free.model.service.FreeBoardService;
import com.campus.board.free.model.service.FreeBoardServiceImpl;
import com.campus.board.free.model.vo.FreePage;

/**
 * Servlet implementation class FreeBoardListAllServlet
 */
@WebServlet("/board/free/listAll.do")
public class FreeBoardListAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FreeBoardListAllServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int currentPage;
		if (request.getParameter("currentPage") == null) {
			currentPage = 1;
			
		} else {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}

		FreeBoardService freebService = new FreeBoardServiceImpl();
		FreePage page = freebService.freeboardListAll(currentPage);
		
		RequestDispatcher view = request.getRequestDispatcher("/community/free/freeboard.jsp");
		request.setAttribute("freepage", page);
		request.setAttribute("currentPage", currentPage);
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
