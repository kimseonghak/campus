package com.campus.board.free.controller;

import java.io.IOException;
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
 * Servlet implementation class FreeBoardUpdate
 */
@WebServlet("/board/free/postUpdate.do")
public class FreeBoardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FreeBoardUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String freeContent = request.getParameter("content");
		int freeNo = (Integer.parseInt(request.getParameter("boardNo")));
		int currentPage = (Integer.parseInt(request.getParameter("currentPage")));
		
		String userId = ((Member)request.getSession().getAttribute("member")).getUserId();
		
		FreeBoard freeBoard=new FreeBoard();
		freeBoard.setFreeContent(freeContent);
		freeBoard.setFreeNo(freeNo);
		freeBoard.setUserId(userId);
		
		FreeBoardService freebService=new FreeBoardServiceImpl();
		int result=freebService.update(freeBoard);
		
		if(result>0) {
			response.sendRedirect("/board/free/selectOne.do?freeNo="+freeNo+"&currentPage="+currentPage);
		}else
		{
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
