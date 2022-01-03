package com.campus.board.market.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.campus.board.market.model.service.MarketBoardService;
import com.campus.board.market.model.service.MarketBoardServiceImpl;

/**
 * Servlet implementation class MarketBoardNextPost
 */
@WebServlet("/MarketBoardNextPost.do")
public class MarketBoardNextPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MarketBoardNextPost() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int marketNo = Integer.parseInt(request.getParameter("marketNo"));
		
		MarketBoardService nbService = new MarketBoardServiceImpl();
		int nextNo = nbService.nextMarketBoard(marketNo);
		
		PrintWriter out = response.getWriter();
		if(nextNo==marketNo) {
			out.println("<script>alert('마지막 글입니다.');</script>");
		}
		
		response.sendRedirect("/board/market/selectOne.do?marketNo="+nextNo);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
