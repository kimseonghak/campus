package com.campus.userPage.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.campus.member.model.vo.Member;
import com.campus.userPage.model.service.UserService;
import com.campus.userPage.model.service.UserServiceImpl;
import com.campus.userPage.model.vo.UserReservation;
import com.campus.userPage.model.vo.UserWish;

/**
 * Servlet implementation class UserPageServlet
 */
@WebServlet("/userPage/userPage.do")
public class UserPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserPageServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getSession().getAttribute("member") == null) {
			response.sendRedirect("/main/login/M_Login.jsp");
		} else {
			
			
			// 1. 유효한 예약 정보  가져오기 (일단 한개니까 가져오는 거 해보기)
			String userId = ((Member) request.getSession().getAttribute("member")).getUserId();
			
			UserService uService = new UserServiceImpl();
			// 예약 정보 가져오기
			UserReservation uReser = uService.selectReservation(userId);
			ArrayList<UserWish> ulist = uService.selectWishList(userId);
			//System.out.println(ulist);
			
			
			
			RequestDispatcher view = request.getRequestDispatcher("/userPage/u_main.jsp");
			request.setAttribute("UserReservation", uReser);
			request.setAttribute("UserWishList", ulist);
			view.forward(request, response);
			
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
