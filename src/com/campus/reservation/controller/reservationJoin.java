package com.campus.reservation.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.campus.member.model.vo.Member;
import com.campus.reservation.model.service.CampingAreaService;
import com.campus.reservation.model.service.CampingAreaServiceImpl;
import com.campus.reservation.model.vo.CampingArea;

/**
 * Servlet implementation class reservationJoin
 */
@WebServlet("/reservation/reservationJoin.do")
public class reservationJoin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public reservationJoin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		if(request.getSession().getAttribute("member")==null)
		{
			response.sendRedirect("/");
			return;
		}
		
		int campSeq = Integer.parseInt(request.getParameter("campSeq"));
		String userId = ((Member)request.getSession().getAttribute("member")).getUserId();
		String reservSta = request.getParameter("reservSta");
		String reservEnd = request.getParameter("reservEnd");
		
		System.out.println("data : "+campSeq+","+userId+","+reservSta+","+reservEnd);
		
		CampingAreaService cpaService = new CampingAreaServiceImpl();
		ArrayList<CampingArea> list = cpaService.selectArea(campSeq);
		
		int result = cpaService.reservation(list.get(0),userId,reservSta,reservEnd);
		
		if(result>0)
		{
			response.sendRedirect("/reservation/views/reservationSuccess.jsp");
		}else
		{
			response.sendRedirect("/reservation/views/reservationFail.jsp");
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
