package com.campus.board.notice.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.campus.board.common.BoardCommon;
import com.campus.board.notice.model.vo.NoticeBoard;
import com.campus.common.JDBCTemplate;

public class NoticeBoardDAO {

	//공지게시판 목록 전체 불러오기
	public ArrayList<NoticeBoard> noticeboardListAll(Connection conn, int currentPage, int perPage) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<NoticeBoard> list = new ArrayList<NoticeBoard>();
		
		int start=currentPage*perPage-(perPage-1);
		int end=currentPage*perPage;
		
		try {
			String query="select * from (select row_number() over (order by notice_no desc) as num, noticeboard.* from noticeboard"
					+ " where notice_withdrawal='N')"
					+ " where num between ? and ?";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			rset=pstmt.executeQuery();
			
			while(rset.next()) {
				NoticeBoard noticeBoard=new NoticeBoard();
				noticeBoard.setNoticeNo(rset.getInt("notice_no"));
				noticeBoard.setBusinessId(rset.getString("business_id"));
				noticeBoard.setBusinessName(rset.getString("business_name"));
				noticeBoard.setNoticeTitle(rset.getString("notice_title"));
				noticeBoard.setNoticeHit(rset.getInt("notice_hit"));
				noticeBoard.setNoticeLike(rset.getInt("notice_like"));
				noticeBoard.setNoticeHate(rset.getInt("notice_hate"));
				noticeBoard.setNoticeDate(rset.getDate("notice_date"));
				noticeBoard.setNoticeContent(rset.getString("notice_content"));
				
				list.add(noticeBoard);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	//공지게시판 목록 페이징 처리
	public String getPageNavi(Connection conn, int currentPage, int perPage, int naviPage) {
		int totalPost=totalPost(conn);
		int totalPage=BoardCommon.totalPage(perPage, totalPost);
		int startNavi=BoardCommon.startNavi(currentPage, perPage, naviPage, totalPost);
		int endNavi=startNavi+naviPage-1;
		if (endNavi > totalPage) {
			endNavi = totalPage;
		}
		boolean prev = true;
		boolean next = true;
		if (startNavi == 1) {
			prev = false;
		}
		if(endNavi==totalPage) {
			next = false;
		}
		
		StringBuilder sb = new StringBuilder();
		if(prev) {
			sb.append("<a href='/board/notice/listAll.do?currentPage=" + (startNavi - 1) + "'>< </a>");
		}
		for(int i=startNavi;i<=endNavi;i++) {
			if (i == currentPage) {
				sb.append("<a href='/board/notice/listAll.do?currentPage=" + i + "'><B>" + i + "</B></a> ");
			} else {
				sb.append("<a href='/board/notice/listAll.do?currentPage=" + i + "'>" + i + "</a> ");
			}
		}
		if (next) {
			sb.append(" <a href='/board/notice/listAll.do?currentPage=" + (endNavi + 1) + "'>></a>");
		}
		return sb.toString();
	}
	
	//공지게시판 전체 게시글 수
	public int totalPost(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		int count=0;
		
		try {
			String query = "select count(*) as count from noticeboard where notice_withdrawal='N'";
			
			pstmt = conn.prepareStatement(query);
			
			rset = pstmt.executeQuery();
			rset.next();
			
			count=rset.getInt("count");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return count;
	}
	
	//공지게시판에서 게시글 하나 선택
	public NoticeBoard noticeboardSelectOne(Connection conn, int noticeNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		NoticeBoard noticeBoard=null;
		
		try {
			String query="select * from noticeboard where notice_no=? and notice_withdrawal='N'";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				noticeBoard=new NoticeBoard();
				noticeBoard.setNoticeNo(rset.getInt("notice_no"));
				noticeBoard.setBusinessId(rset.getString("business_id"));
				noticeBoard.setNoticeTitle(rset.getString("notice_title"));
				noticeBoard.setNoticeHit(rset.getInt("notice_hit"));
				noticeBoard.setNoticeLike(rset.getInt("notice_like"));
				noticeBoard.setNoticeHate(rset.getInt("notice_hate"));
				noticeBoard.setNoticeDate(rset.getDate("notice_date"));
				noticeBoard.setNoticeContent(rset.getString("notice_content"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return noticeBoard;
	}

	//공지게시판 게시글 수정
	public int update(NoticeBoard noticeBoard, Connection conn) {
		PreparedStatement pstmt = null;
		
		int result = 0;
		
		try {
			String query = "update noticeboard set notice_content=? where notice_no=? and user_id=?";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, noticeBoard.getNoticeContent());
			pstmt.setInt(2, noticeBoard.getNoticeNo());
			pstmt.setString(3, noticeBoard.getBusinessId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	//공지게시판 게시글 삭제
	public int delete(int noticeNo, String userId, Connection conn) {
		PreparedStatement pstmt = null;
		
		int result = 0;
		
		try {
			String query="update board set notice_withdrawal='Y' where notice_no=? and buiness_id=?";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, noticeNo);
			pstmt.setString(2, userId);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	//공지게시판 게시글 작성
	public int insert(NoticeBoard noticeBoard, Connection conn) {
		PreparedStatement pstmt = null;
		
		int result = 0;
		
		try {
			String query = "insert into noticeboard values(notice_bbs.nextval,?,default,?,?,?,0,0,0,default)";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, noticeBoard.getNoticeTitle());
			pstmt.setString(2, noticeBoard.getBusinessId());
			pstmt.setString(3, noticeBoard.getBusinessName());
			pstmt.setString(4, noticeBoard.getNoticeContent());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	//공지게시판 게시글 검색
	public ArrayList<NoticeBoard> search(Connection conn, int currentPage, int perPage, String type, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<NoticeBoard> list = new ArrayList<NoticeBoard>();
		
		int start = currentPage * perPage - (perPage - 1);
		int end = currentPage * perPage;
		
		try {
			String query="";
			switch(type) {
			case "noticeTitle":
				query="select * from (select row_number() over(order by notice_no desc) as num, noticeboard.* from noticeboard"
						+ " where notice_withdrawal='N' and notice_title like ?)"
						+ " where num between ? and ?";
				break;
			default:
				query="select * from (select row_number() over(order by notice_no desc) as num, noticeboard.* from noticeboard"
						+ " where notice_withdrawal='N' and notice_title like ?)"
						+ " where num between ? and ?";
				break;
			}
			pstmt = conn.prepareStatement(query);
			if(!type.equals("all")) {
				pstmt.setString(1, "%"+keyword+"%");
				pstmt.setInt(2, start);
				pstmt.setInt(3, end);
			}
			
			rset = pstmt.executeQuery();
			while (rset.next()) {
				NoticeBoard noticeBoard = new NoticeBoard();
				noticeBoard.setNoticeNo(rset.getInt("notice_no"));
				noticeBoard.setNoticeTitle(rset.getString("notice_title"));
				noticeBoard.setNoticeDate(rset.getDate("notice_date"));
				noticeBoard.setBusinessId(rset.getString("business_id"));
				noticeBoard.setBusinessName(rset.getString("business_name"));
				noticeBoard.setNoticeContent(rset.getString("notice_content"));
				noticeBoard.setNoticeHit(rset.getInt("notice_hit"));
				noticeBoard.setNoticeLike(rset.getInt("notice_like"));
				noticeBoard.setNoticeHate(rset.getInt("notice_hate"));
				
				list.add(noticeBoard);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	//공지게시판 검색 페이징 처리
	public String searchNavi(Connection conn, int currentPage, int perPage, int naviPage, String keyword, String type) {
		int totalPost=totalPost(conn,type,keyword);
		int totalPage=BoardCommon.totalPage(perPage, totalPost);
		int startNavi=BoardCommon.startNavi(currentPage, perPage, naviPage, totalPost);
		int endNavi=startNavi+naviPage-1;

		if (endNavi > totalPage) {
			endNavi = totalPage;
		}
		
		boolean prev = true;
		boolean next = true;
		if (startNavi == 1) {
			prev = false;
		}
		if (endNavi == totalPage) {
			next = false;
		}
		
		StringBuilder sb = new StringBuilder();
		if (prev) {
			sb.append("<a href='/board/notice/search.do?currentPage=" + (startNavi - 1) + "&type="+type+"&keyword="+keyword+"'>< </a>");
		}
		for (int i = startNavi; i <= endNavi; i++) {
			if (i == currentPage) {
				sb.append("<a href='/board/notice/search.do?currentPage=" + i + "&type="+type+"&keyword="+keyword+"'><B>" + i + "</B></a> ");
			} else {
				sb.append("<a href='/board/notice/search.do?currentPage=" + i + "&type="+type+"&keyword="+keyword+"'>" + i + "</a> ");
			}
		}
		if (next) {
			sb.append(" <a href='/board/notice/search.do?currentPage=" + (endNavi + 1) + "&type="+type+"&keyword="+keyword+"'>></a>");
		}
		return sb.toString();
	}
	
	//공지게시판 검색 전체 게시글 수
	private int totalPost(Connection conn, String type, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		int totalPost = 0;
		
		try {
			String query="";
			switch(type) {
			case "noticeTitle":
				query="select count(*) as totalcount from noticeboard"
						+ " where notice_withdrawal='N' and notice_title like ?;";
				break;
			}
			
			pstmt = conn.prepareStatement(query);
			if(type.equals("noticeTitle")) {
				pstmt.setString(1, "%"+keyword+"%");
			}
			
			rset = pstmt.executeQuery();
			rset.next();
			totalPost=rset.getInt("totalPost");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return totalPost;
	}
}
