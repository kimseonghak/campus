package com.campus.reservation.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.campus.member.model.vo.Member;
import com.campus.reservation.model.service.CampingAreaService;
import com.campus.reservation.model.service.CampingAreaServiceImpl;
import com.campus.userPage.model.service.UserService;
import com.campus.userPage.model.service.UserServiceImpl;
import com.campus.userPage.model.vo.WishT;

/**
 * Servlet implementation class selectCampingList
 */
@WebServlet("/reservation/SelectCampingList.do")
public class selectCampingList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public selectCampingList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println(1);
		
		int currentPage;
		String location;
		
		if(request.getParameter("currentPage")==null)
		{
			currentPage=1;
		}else {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		

		if(request.getParameter("location")==null)
		{
			location="";
		}else {
			location = request.getParameter("location");
		}
		
		
		//System.out.println("location:" +location);
		//System.out.println("currentPage::"+currentPage);
		
		CampingAreaService campingAreaService = new CampingAreaServiceImpl();
		HashMap<String, Object> pageDataMap = campingAreaService.selectMainList(currentPage, location);
		
		//찜 리스트 가져오기
		String userId = ((Member)request.getSession().getAttribute("member")).getUserId();
		UserService uService = new UserServiceImpl();
		ArrayList<WishT> wlist = uService.selectUserWishList(userId);
		System.out.println("wlist : "+wlist);
		
		RequestDispatcher view = request.getRequestDispatcher("/reservation/views/reservationMain.jsp");
		
		request.setAttribute("pageDataMap", pageDataMap);
		request.setAttribute("currentPage", currentPage);
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
