package com.campus.board.market.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.campus.board.market.model.service.MarketBoardService;
import com.campus.board.market.model.service.MarketBoardServiceImpl;
import com.campus.board.market.model.vo.MarketBoard;

/**
 * Servlet implementation class MarketBoardSelectOneServlet
 */
@WebServlet("/board/market/selectOne.do")
public class MarketBoardSelectOneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MarketBoardSelectOneServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		int marketNo = Integer.parseInt(request.getParameter("marketNo"));
		System.out.println(marketNo);
		
		MarketBoardService marketbService = new MarketBoardServiceImpl();
		MarketBoard marketBoard = marketbService.marketboardSelectOne(marketNo);
		
		if (marketBoard != null) {
			RequestDispatcher view = request.getRequestDispatcher("/community/market/marketpost.jsp");
			request.setAttribute("marketBoard", marketBoard);
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
