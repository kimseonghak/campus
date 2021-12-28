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
 * Servlet implementation class selectCampingAreaList
 */
@WebServlet("/reservation/SelectCampingAreaList.do")
public class selectCampingAreaList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public selectCampingAreaList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println(1);
		
		int currentPage;
		int bsnNo= Integer.parseInt(request.getParameter("bsnNo"));;
		//System.out.println("bsnNo::"+bsnNo);
		
		if(request.getParameter("currentPage")==null)
		{
			currentPage=1;
		}else {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		//System.out.println("currentPage::"+currentPage);
		
		CampingAreaService campingAreaService = new CampingAreaServiceImpl();
		HashMap<String, Object> pageDataMap = campingAreaService.selectAllList(bsnNo, currentPage);
		
		RequestDispatcher view = request.getRequestDispatcher("/reservation/views/campingArea.jsp");
		
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
