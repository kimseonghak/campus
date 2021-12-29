package com.campus.reservation.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import com.campus.common.JDBCTemplate;
import com.campus.reservation.model.dao.CampingAreaDAO;
import com.campus.reservation.model.dao.reservationDAO;
import com.campus.reservation.model.vo.CampingArea;

public class CampingAreaServiceImpl implements CampingAreaService{
	private CampingAreaDAO campingAreaDAO = new CampingAreaDAO();
	private reservationDAO reservationDAO = new reservationDAO();
	
	@Override
	public HashMap<String, Object> selectAllList(int bsnNo, int currentPage) {

		int recordCountPerPage = 5;
		int naviCountPerPage = 5;
		
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<CampingArea> list = campingAreaDAO.selectAllList(conn, bsnNo, currentPage, recordCountPerPage);
		
		String pageNavi = campingAreaDAO.getPageNavi(conn, bsnNo, naviCountPerPage, recordCountPerPage, currentPage);
		
		JDBCTemplate.close(conn);
		
		HashMap<String, Object> hm = new HashMap<String, Object>();

		hm.put("list", list);
		hm.put("pageNavi", pageNavi);
		
		return hm;		

	}

	@Override
	public HashMap<String, Object> selectMainList(int currentPage, String location) {
		int recordCountPerPage = 5;
		int naviCountPerPage = 5;
		
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<CampingArea> list = campingAreaDAO.selectMainList(conn,currentPage, recordCountPerPage, location);
		
		String pageNavi = campingAreaDAO.getMainPageNavi(conn, naviCountPerPage, recordCountPerPage, currentPage, location);
		
		JDBCTemplate.close(conn);
		
		HashMap<String, Object> hm = new HashMap<String, Object>();

		hm.put("list", list);
		hm.put("pageNavi", pageNavi);
		
		return hm;		

	}

	@Override
	public ArrayList<CampingArea> selectArea(int rsvSeq){
		
		Connection conn = JDBCTemplate.getConnection();

		ArrayList<CampingArea> list = campingAreaDAO.selectArea(conn, rsvSeq);
		
		JDBCTemplate.close(conn);
		
		return list;
	}

	
	@Override
	public int reservation(CampingArea list,String userId,String reservSta, String reservEnd) {
		Connection conn = JDBCTemplate.getConnection();
		int result = reservationDAO.insertReservation(conn, list, userId, reservSta,reservEnd);
		if(result>0) JDBCTemplate.commit(conn);
		else JDBCTemplate.rollback(conn);
		
		return result;
	}
}
