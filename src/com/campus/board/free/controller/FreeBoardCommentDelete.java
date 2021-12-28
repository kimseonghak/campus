package com.campus.board.free.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.campus.board.free.model.service.FreeBoardService;
import com.campus.board.free.model.service.FreeBoardServiceImpl;

/**
 * Servlet implementation class FreeBoardCommentDelete
 */
@WebServlet("/board/free/commentDelete.do")
public class FreeBoardCommentDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FreeBoardCommentDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cFreeNo = Integer.parseInt(request.getParameter("cFreeNo"));
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		int freeNo = Integer.parseInt(request.getParameter("freeNo"));
		int commentPage = Integer.parseInt(request.getParameter("commentPage"));
		
		FreeBoardService fbService = new FreeBoardServiceImpl();
		int result = fbService.commentDelete(cFreeNo);
		
		if(result>0) {
			response.sendRedirect("/board/free/selectOne.do?currentPage="+currentPage+"&freeNo="+freeNo+"&commentPage="+commentPage);
		}else {
			response.sendRedirect("/main/error/writeError.jsp");
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
