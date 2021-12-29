package com.campus.board.market.model.service;

import java.sql.Connection;

import com.campus.board.market.model.dao.MarketBoardDAO;
import com.campus.board.market.model.vo.MarketBoard;
import com.campus.board.market.model.vo.MarketPage;
import com.campus.common.JDBCTemplate;

public class MarketBoardServiceImpl implements MarketBoardService{

	private MarketBoardDAO mbDAO = new MarketBoardDAO();
	
	@Override
	public MarketPage marketboardListAll(int currentPage) {
		Connection conn = JDBCTemplate.getConnection();
		
		MarketPage page=new MarketPage();
		
		int perPage=15;
		page.setPageList(mbDAO.marketboardListAll(conn,currentPage,perPage));
		
		int naviPage=5;
		page.setPageNavi(mbDAO.getPageNavi(conn,currentPage,perPage,naviPage));
		
		JDBCTemplate.close(conn);
		
		return page;
	}

	@Override
	public MarketBoard marketboardSelectOne(int marketNo) {
		Connection conn = JDBCTemplate.getConnection();
		MarketBoard marketBoard = mbDAO.marketboardSelectOne(conn, marketNo);
		JDBCTemplate.close(conn);
		return marketBoard;
	}

	@Override
	public int update(MarketBoard marketBoard) {
		Connection conn = JDBCTemplate.getConnection();
		int result = mbDAO.update(marketBoard, conn);
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
	public int delete(int marketNo, String userId) {
		Connection conn = JDBCTemplate.getConnection();
		int result = mbDAO.delete(marketNo,userId, conn);
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
	public int insert(MarketBoard marketBoard) {
		Connection conn = JDBCTemplate.getConnection();
		int newPost = mbDAO.insert(marketBoard, conn);
		int postNo=mbDAO.uploadPostNo(marketBoard, conn);
		int imgNo=mbDAO.uploadImgNo(marketBoard, conn);
		
		int result=0;
		System.out.println("8번"+postNo);
		System.out.println("9번"+imgNo);
		System.out.println("0번"+newPost);
		
		if(newPost>0&&postNo>0&&imgNo>0) {
			result=1;
			JDBCTemplate.commit(conn);
		}
		else{
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	@Override
	public MarketPage search(String type, String keyword, int currentPage) {
		Connection conn = JDBCTemplate.getConnection();
		
		MarketPage page = new MarketPage();
		
		int perPage = 15;
		page.setPageList(mbDAO.search(conn,currentPage,perPage, type, keyword));
		
		int naviPage = 5;
		page.setPageNavi(mbDAO.searchNavi(conn,currentPage,perPage,naviPage, keyword, type));
		
		JDBCTemplate.close(conn);
		return page;
	}

}
