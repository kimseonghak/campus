package com.campus.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.campus.member.model.service.MemberService;
import com.campus.member.model.service.MemberServiceImpl;
import com.campus.member.model.vo.Member;

/**
 * Servlet implementation class M_login
 */
@WebServlet("/main/login.do")
public class M_login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public M_login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
		MemberService mService = new MemberServiceImpl();
		Member m = mService.loginMember(userId,userPwd);
		
		if(m!=null) {
			HttpSession session =  request.getSession();
			session.setAttribute("member", m);
			
			response.sendRedirect("/");
		}else {
			RequestDispatcher view = request.getRequestDispatcher("/main/login/M_LoginFail.jsp");
			view.forward(request, response);
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
