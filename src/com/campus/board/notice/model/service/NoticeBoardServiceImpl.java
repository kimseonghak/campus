package com.campus.board.notice.model.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.campus.board.notice.model.vo.NoticeBoard;
import com.campus.board.notice.model.vo.NoticePage;
import com.campus.board.notice.model.dao.NoticeBoardDAO;
import com.campus.board.notice.model.vo.NoticeBoard;
import com.campus.board.notice.model.vo.NoticePage;
import com.campus.common.JDBCTemplate;

public class NoticeBoardServiceImpl implements NoticeBoardService{

	NoticeBoardDAO nbDAO=new NoticeBoardDAO();
	
	@Override
	public NoticePage noticeboardListAll(int currentPage) {
		Connection conn = JDBCTemplate.getConnection();
		
		NoticePage page=new NoticePage();
		
		int perPage=15;
		page.setPageList(nbDAO.noticeboardListAll(conn,currentPage,perPage));
		
		int naviPage=5;
		page.setPageNavi(nbDAO.getPageNavi(conn,currentPage,perPage,naviPage));
		
		JDBCTemplate.close(conn);
		
		return page;
	}

	@Override
	public NoticeBoard noticeboardSelectOne(int noticeNo) {
		Connection conn = JDBCTemplate.getConnection();
		NoticeBoard noticeBoard = nbDAO.noticeboardSelectOne(conn, noticeNo);
		JDBCTemplate.close(conn);
		return noticeBoard;
	}

	@Override
	public int update(NoticeBoard noticeBoard) {
		Connection conn = JDBCTemplate.getConnection();
		int result = nbDAO.update(noticeBoard, conn);
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
	public int delete(int noticeNo, String userId) {
		Connection conn = JDBCTemplate.getConnection();
		int result = nbDAO.delete(noticeNo,userId, conn);
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
	public int insert(NoticeBoard noticeBoard) {
		Connection conn = JDBCTemplate.getConnection();
		int result = nbDAO.insert(noticeBoard, conn);
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
	public NoticePage search(String type, String keyword, int currentPage) {
		Connection conn = JDBCTemplate.getConnection();
		
		NoticePage page = new NoticePage();
		
		int perPage = 15;
		page.setPageList(nbDAO.search(conn,currentPage,perPage, type, keyword));
		
		int naviPage = 5;
		page.setPageNavi(nbDAO.searchNavi(conn,currentPage,perPage,naviPage, keyword, type));
		
		JDBCTemplate.close(conn);
		return page;
	}

	@Override
	public int prevNoticeBoard(int noticeNo) {
		Connection conn = JDBCTemplate.getConnection();
		int prevNo = nbDAO.prevNoticeBoard(noticeNo,conn);
		return prevNo;
	}

	@Override
	public int nextNoticeBoard(int noticeNo) {
		Connection conn = JDBCTemplate.getConnection();
		int nextNo = nbDAO.nextNoticeBoard(noticeNo,conn);
		
		return nextNo;
	}

}
