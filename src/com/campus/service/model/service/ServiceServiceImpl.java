package com.campus.service.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import com.campus.common.JDBCTemplate;
import com.campus.service.dao.ServiceDAO;
import com.campus.service.model.vo.Answer;
import com.campus.service.model.vo.Service;

public class ServiceServiceImpl implements ServiceService{
	
	private ServiceDAO sDAO = new ServiceDAO();
	
	@Override
	public int writeService(Service s) {
		Connection conn = JDBCTemplate.getConnection();
		int result = sDAO.writeService(s,conn);
		if(result>0) JDBCTemplate.commit(conn);
		else JDBCTemplate.rollback(conn);
		JDBCTemplate.close(conn);
		
		return result;
	}

	@Override
	public HashMap<String, Object> serviceList(String userId, int currentPage) {
		Connection conn = JDBCTemplate.getConnection();
		int recordCountPerPage = 5;
		ArrayList<Service> list = sDAO.serviceList(conn,currentPage,recordCountPerPage,userId);
		int naviCountPerPage = 5;
		String pageNavi = sDAO.getServicePageNavi(conn,naviCountPerPage,currentPage,recordCountPerPage,userId);
		JDBCTemplate.close(conn);
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("pageNavi", pageNavi);
		
		return map;
	}

	@Override
	public Service serviceSelectContent(int serviceNo) {
		Connection conn = JDBCTemplate.getConnection();
		Service s = sDAO.serviceSelectContent(serviceNo,conn);
		JDBCTemplate.close(conn);
		
		return s;
	}

	@Override
	public int updateService(Service s, int serviceNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = sDAO.updateService(s,conn,serviceNo);
		if(result>0) JDBCTemplate.commit(conn);
		else JDBCTemplate.rollback(conn);
		JDBCTemplate.close(conn);
		
		return result;
	}

	@Override
	public HashMap<String, Object> serviceList(int currentPage) {
		Connection conn = JDBCTemplate.getConnection();
		int recordCountPerPage = 5;
		ArrayList<Service> list = sDAO.serviceList(conn,currentPage,recordCountPerPage);
		int naviCountPerPage = 5;
		String pageNavi = sDAO.getServicePageNavi(conn,naviCountPerPage,currentPage,recordCountPerPage);
		JDBCTemplate.close(conn);
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("pageNavi", pageNavi);
		
		return map;
	}

	@Override
	public int qnaAnswer(String answerContent, int serviceNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result1 = sDAO.qnaAnswer(answerContent,serviceNo,conn);
		int result2 = sDAO.serviceAnserYN(serviceNo,conn);
		if(result1>0 &&result2>0) JDBCTemplate.commit(conn);
		else JDBCTemplate.rollback(conn);
		JDBCTemplate.close(conn);
		
		int result = result1+result2;
		return result;
	}

	@Override
	public Answer qnaAnswerContent(int serviceNo) {
		Connection conn = JDBCTemplate.getConnection();
		Answer a = sDAO.qnaAnswerContent(serviceNo,conn);
		JDBCTemplate.close(conn);
		
		return a;
	}

}
