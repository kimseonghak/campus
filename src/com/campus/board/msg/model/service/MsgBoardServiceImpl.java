package com.campus.board.msg.model.service;

import java.sql.Connection;

import com.campus.board.msg.model.dao.MsgBoardDAO;
import com.campus.board.msg.model.vo.MsgBoard;
import com.campus.board.msg.model.vo.MsgPage;
import com.campus.common.JDBCTemplate;

public class MsgBoardServiceImpl implements MsgBoardService{

	private MsgBoardDAO mbDAO = new MsgBoardDAO();
	
	@Override
	public MsgPage msgboardListAll(int currentPage, String userId) {
		Connection conn = JDBCTemplate.getConnection();
		
		MsgPage page=new MsgPage();
		
		int perPage=7;
		page.setPageList(mbDAO.msgboardListAll(conn,currentPage, userId, perPage));
		
		int naviPage=5;
		page.setPageNavi(mbDAO.getPageNavi(conn,currentPage,perPage,naviPage));
		
		JDBCTemplate.close(conn);
		
		return page;
	}

	@Override
	public MsgBoard msgboardSelectOne(int msgNo) {
		Connection conn = JDBCTemplate.getConnection();
		MsgBoard msgBoard = mbDAO.msgboardSelectOne(conn, msgNo);
		JDBCTemplate.close(conn);
		return msgBoard;
	}

	@Override
	public int delete(int msgNo, String userId) {
		Connection conn = JDBCTemplate.getConnection();
		int result = mbDAO.delete(msgNo,userId, conn);
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
	public int insert(MsgBoard msgBoard) {
		Connection conn = JDBCTemplate.getConnection();
		int result = mbDAO.insert(msgBoard, conn);
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
	public MsgPage search(String type, String keyword, int currentPage, String userId) {
		Connection conn = JDBCTemplate.getConnection();
		
		MsgPage page = new MsgPage();
		
		int perPage = 7;
		page.setPageList(mbDAO.search(conn,currentPage, userId, perPage, type, keyword));
		
		int naviPage = 5;
		page.setPageNavi(mbDAO.searchNavi(conn,currentPage,userId,perPage,naviPage, keyword, type));
		
		JDBCTemplate.close(conn);
		return page;
	}

}
