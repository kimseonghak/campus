package com.campus.userPage.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.campus.member.model.vo.Member;
import com.campus.userPage.model.service.UserService;
import com.campus.userPage.model.service.UserServiceImpl;
import com.campus.userPage.model.vo.WishT;

/**
 * Servlet implementation class AddWishServlet
 */
@WebServlet("/userPage/addWish.do")
public class AddWishServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddWishServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		int businessNo = Integer.parseInt(request.getParameter("businessNo"));
		String campNo = request.getParameter("campNo");
		String userId = ((Member)request.getSession().getAttribute("member")).getUserId();
		
		//System.out.println(businessNo);
		//System.out.println(campNo);
		//System.out.println(userId);
		
		WishT wish = new WishT(businessNo,campNo,userId );
		
		UserService uService = new UserServiceImpl();
		int result = uService.addWish(wish);
		
		boolean wishResult  = false;
		if(result>0) 
		{
			wishResult = true;
		}
		else
		{
			wishResult = false;
		}

		PrintWriter out = response.getWriter();
		out.print(result);
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
