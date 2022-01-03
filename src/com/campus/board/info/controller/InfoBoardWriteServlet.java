package com.campus.board.info.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.campus.board.info.model.service.InfoBoardService;
import com.campus.board.info.model.service.InfoBoardServiceImpl;
import com.campus.board.info.model.vo.InfoBoard;
import com.campus.member.model.vo.Member;

/**
 * Servlet implementation class InfoBoardWriteServlet
 */
@WebServlet("/board/info/write.do")
public class InfoBoardWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InfoBoardWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String infoTitle = request.getParameter("infoTitle");
		String infoContent = request.getParameter("infoContent");
		
		String userId = ((Member)request.getSession().getAttribute("member")).getUserId();
		String userName = ((Member)request.getSession().getAttribute("member")).getUserName();
		
		InfoBoard infoBoard = new InfoBoard();
		infoBoard.setInfoTitle(infoTitle);
		infoBoard.setInfoContent(infoContent);
		infoBoard.setUserId(userId);
		infoBoard.setUserName(userName);
		
		InfoBoardService infobService = new InfoBoardServiceImpl();
		int result=infobService.insert(infoBoard);
		
		RequestDispatcher view = request.getRequestDispatcher("/community/info/write.jsp");
		
		if(result>0)
		{
			request.setAttribute("writeResult", true);
		}else
		{
			request.setAttribute("writeResult", false);
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
