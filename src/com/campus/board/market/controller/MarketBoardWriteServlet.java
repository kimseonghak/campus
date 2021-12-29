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
import com.campus.community.upload.model.service.ImgUploadService;
import com.campus.community.upload.model.service.ImgUploadServiceImpl;
import com.campus.community.upload.model.vo.Image;
import com.campus.member.model.vo.Member;

/**
 * Servlet implementation class MarketBoardWriteServlet
 */
@WebServlet("/board/market/write.do")
public class MarketBoardWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MarketBoardWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String currentPage=request.getParameter("currentPage");
		String imgPath=request.getParameter("imgPath");
		String marketTitle = request.getParameter("marketTitle");
		String marketContent = request.getParameter("marketContent");
		// TODO: DEBUG CHECK
		System.out.println(request.getSession().getAttribute("member"));
		String userId = ((Member)request.getSession().getAttribute("member")).getUserId();
		
		MarketBoard marketBoard = new MarketBoard();
		marketBoard.setMarketTitle(marketTitle);
		marketBoard.setMarketContent(marketContent);
		marketBoard.setUserId(userId);
		marketBoard.setImgNo(21);
		marketBoard.setMarketProduct(request.getParameter("marketProduct"));
		marketBoard.setMarketPrice(Integer.parseInt(request.getParameter("marketPrice")));
		marketBoard.setMarketCondition(request.getParameter("marketCondition"));
		marketBoard.setMarketLocation(request.getParameter("marketLocation"));
		String[] tradeValues=request.getParameterValues("marketTrade");
		String trade = "";
		if(tradeValues==null) {
			trade="";
		}
		else {
			for (int i = 1; i < tradeValues.length; i++) {
				trade = trade + ", " + tradeValues[i];
			}
		}
		marketBoard.setMarketTrade(trade);
		
		MarketBoardService marketbService = new MarketBoardServiceImpl();
		int result=marketbService.insert(marketBoard);
		
		RequestDispatcher view = request.getRequestDispatcher("/board/market/listAll.do");
		
		if(result>0)
		{
			request.setAttribute("writeResult", true);
			request.setAttribute("currentPage", currentPage);
		}else
		{
			request.setAttribute("writeResult", false);
			request.setAttribute("currentPage", currentPage);
		}
		view.forward(request, response);
	}
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
