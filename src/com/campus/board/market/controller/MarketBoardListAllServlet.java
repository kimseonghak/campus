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
import com.campus.board.market.model.vo.MarketPage;

/**
 * Servlet implementation class MarketBoardListAllServlet
 */
@WebServlet("/board/market/listAll.do")
public class MarketBoardListAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MarketBoardListAllServlet() {
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

		MarketBoardService marketbService = new MarketBoardServiceImpl();
		MarketPage page = marketbService.marketboardListAll(currentPage);
		
		RequestDispatcher view = request.getRequestDispatcher("/community/market/marketboard.jsp");
		request.setAttribute("marketpage", page);
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
