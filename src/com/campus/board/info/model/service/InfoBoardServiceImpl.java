package com.campus.board.info.model.service;

import java.sql.Connection;

import com.campus.board.info.model.dao.InfoBoardDAO;
import com.campus.board.info.model.vo.InfoBoard;
import com.campus.board.info.model.vo.InfoPage;
import com.campus.common.JDBCTemplate;

public class InfoBoardServiceImpl implements InfoBoardService{
	
	private InfoBoardDAO ibDAO = new InfoBoardDAO();
	
	@Override
	public InfoPage infoboardListAll(int currentPage) {
		Connection conn = JDBCTemplate.getConnection();
		
		InfoPage page=new InfoPage();
		
		int perPage=15;
		page.setPageList(ibDAO.infoboardListAll(conn,currentPage,perPage));
		
		int naviPage=5;
		page.setPageNavi(ibDAO.getPageNavi(conn,currentPage,perPage,naviPage));
		
		JDBCTemplate.close(conn);
		
		return page;
	}

	@Override
	public InfoBoard infoboardSelectOne(int infoNo) {
		Connection conn = JDBCTemplate.getConnection();
		InfoBoard infoBoard = ibDAO.infoboardSelectOne(conn, infoNo);
		JDBCTemplate.close(conn);
		return infoBoard;
	}
	
	@Override
	public int update(InfoBoard infoBoard) {
		Connection conn = JDBCTemplate.getConnection();
		int result = ibDAO.update(infoBoard, conn);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}
		else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	@Override
	public int delete(int infoNo, String userId) {
		Connection conn = JDBCTemplate.getConnection();
		int result = ibDAO.delete(infoNo,userId, conn);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}
		else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
	
	@Override
	public int insert(InfoBoard infoBoard) {
		Connection conn = JDBCTemplate.getConnection();
		int result = ibDAO.insert(infoBoard, conn);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}
		else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	@Override
	public InfoPage search(String type, String keyword, int currentPage) {
		Connection conn = JDBCTemplate.getConnection();
		
		InfoPage page = new InfoPage();
		
		int perPage = 15;
		page.setPageList(ibDAO.search(conn,currentPage,perPage, type, keyword));
		
		int naviPage = 5;
		page.setPageNavi(ibDAO.searchNavi(conn,currentPage,perPage,naviPage, keyword, type));
		
		JDBCTemplate.close(conn);
		return page;
	}

	@Override
	public int prevInfoBoard(int infoNo) {
		Connection conn = JDBCTemplate.getConnection();
		int prevNo = ibDAO.prevInfoBoard(infoNo,conn);
		return prevNo;
	}

	@Override
	public int nextInfoBoard(int infoNo) {
		Connection conn = JDBCTemplate.getConnection();
		int nextNo = ibDAO.nextInfoBoard(infoNo,conn);
		
		return nextNo;
	}
}
