package com.campus.board.info.controller;

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
import com.campus.board.info.model.service.InfoBoardService;
import com.campus.board.info.model.service.InfoBoardServiceImpl;
import com.campus.board.info.model.vo.InfoBoard;

/**
 * Servlet implementation class InfoBoardSelectOneServlet
 */
@WebServlet("/board/info/selectOne.do")
public class InfoBoardSelectOneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InfoBoardSelectOneServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		int infoNo = Integer.parseInt(request.getParameter("infoNo"));
		System.out.println(infoNo);
		
		InfoBoardService infobService = new InfoBoardServiceImpl();
		InfoBoard infoBoard = infobService.infoboardSelectOne(infoNo);
		
		if (infoBoard != null) {
			RequestDispatcher view = request.getRequestDispatcher("/community/info/infopost.jsp");
			request.setAttribute("infoBoard", infoBoard);
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
