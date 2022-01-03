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
 * Servlet implementation class MarketBoardPrevPost
 */
@WebServlet("/MarketBoardPrevPost.do")
public class MarketBoardPrevPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MarketBoardPrevPost() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int marketNo = Integer.parseInt(request.getParameter("marketNo"));
		
		MarketBoardService nbService = new MarketBoardServiceImpl();
		int prevNo = nbService.prevMarketBoard(marketNo);
		
		PrintWriter out = response.getWriter();
		if(prevNo==marketNo) {
			out.println("<script>alert('첫 글입니다.');</script>");
		}
		
		response.sendRedirect("/board/market/selectOne.do?marketNo="+prevNo);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
