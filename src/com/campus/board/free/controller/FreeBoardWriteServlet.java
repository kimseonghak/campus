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
import com.campus.board.free.model.vo.FreeBoard;
import com.campus.member.model.vo.Member;

/**
 * Servlet implementation class FreeBoardWriteServlet
 */
@WebServlet("/board/free/write.do")
public class FreeBoardWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FreeBoardWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String freeTitle = request.getParameter("freeTitle");
		String freeContent = request.getParameter("freeContent");
		
		String userId = ((Member)request.getSession().getAttribute("member")).getUserId();
		String userName = ((Member)request.getSession().getAttribute("member")).getUserName();
		
		FreeBoard freeBoard = new FreeBoard();
		freeBoard.setFreeTitle(freeTitle);
		freeBoard.setFreeContent(freeContent);
		freeBoard.setUserId(userId);
		
		FreeBoardService freebService = new FreeBoardServiceImpl();
		int result=freebService.insert(freeBoard,userName);
		
		RequestDispatcher view = request.getRequestDispatcher("/community/free/write.jsp");
		
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
