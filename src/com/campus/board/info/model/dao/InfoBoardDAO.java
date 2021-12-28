package com.campus.board.info.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.campus.board.common.BoardCommon;
import com.campus.board.info.model.vo.InfoBoard;
import com.campus.common.JDBCTemplate;

public class InfoBoardDAO {

	//정보공유게시판 전체 목록
	public ArrayList<InfoBoard> infoboardListAll(Connection conn, int currentPage, int perPage) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<InfoBoard> list = new ArrayList<InfoBoard>();
		
		int start=currentPage*perPage-(perPage-1);
		int end=currentPage*perPage;
		
		try {
			String query="select * from (select row_number() over (order by info_no desc) as num, infoboard.* from infoboard"
					+ " where info_withdrawal='N')"
					+ " where num between ? and ?";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			rset=pstmt.executeQuery();
			
			while(rset.next()) {
				InfoBoard infoBoard=new InfoBoard();
				infoBoard.setInfoNo(rset.getInt("info_no"));
				infoBoard.setUserId(rset.getString("user_id"));
				infoBoard.setInfoTitle(rset.getString("info_title"));
				infoBoard.setInfoHit(rset.getInt("info_hit"));
				infoBoard.setInfoLike(rset.getInt("info_like"));
				infoBoard.setInfoHate(rset.getInt("info_hate"));
				infoBoard.setInfoDate(rset.getDate("info_date"));
				infoBoard.setInfoContent(rset.getString("info_content"));
				
				list.add(infoBoard);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	//정보공유게시판 목록 페이징 처리
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
			sb.append("<a href='/board/info/listAll.do?currentPage=" + (startNavi - 1) + "'>< </a>");
		}
		for(int i=startNavi;i<=endNavi;i++) {
			if (i == currentPage) {
				sb.append("<a href='/board/info/listAll.do?currentPage=" + i + "'><B>" + i + "</B></a> ");
			} else {
				sb.append("<a href='/board/info/listAll.do?currentPage=" + i + "'>" + i + "</a> ");
			}
		}
		if (next) {
			sb.append(" <a href='/board/info/listAll.do?currentPage=" + (endNavi + 1) + "'>></a>");
		}
		return sb.toString();
	}
	
	//정보공유게시판 전체 게시글 수
	public int totalPost(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		int count=0;
		
		try {
			String query = "select count(*) as count from infoboard where info_withdrawal='N'";
			
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

	//정보공유게시판에서 게시글 하나 선택
	public InfoBoard infoboardSelectOne(Connection conn, int infoNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		InfoBoard infoBoard=null;
		
		try {
			String query="select * from infoboard where info_no=? and info_withdrawal='N'";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, infoNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				infoBoard=new InfoBoard();
				infoBoard.setInfoNo(rset.getInt("info_no"));
				infoBoard.setUserId(rset.getString("user_id"));
				infoBoard.setInfoTitle(rset.getString("info_title"));
				infoBoard.setInfoHit(rset.getInt("info_hit"));
				infoBoard.setInfoLike(rset.getInt("info_like"));
				infoBoard.setInfoHate(rset.getInt("info_hate"));
				infoBoard.setInfoDate(rset.getDate("info_date"));
				infoBoard.setInfoContent(rset.getString("info_content"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return infoBoard;
	}

	//정보공유게시판 게시글 수정
	public int update(InfoBoard infoBoard, Connection conn) {
		PreparedStatement pstmt = null;
		
		int result = 0;
		
		try {
			String query = "update infoboard set info_content=? where info_no=? and user_id=?";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, infoBoard.getInfoContent());
			pstmt.setInt(2, infoBoard.getInfoNo());
			pstmt.setString(3, infoBoard.getUserId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	//정보공유게시판 게시글 삭제
	public int delete(int infoNo, String userId, Connection conn) {
		PreparedStatement pstmt = null;
		
		int result = 0;
		
		try {
			String query="update board set info_withdrawal='Y' where info_no=? and user_id=?";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, infoNo);
			pstmt.setString(2, userId);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	//정보공유게시판 게시글 작성
	public int insert(InfoBoard infoBoard, Connection conn) {
		PreparedStatement pstmt = null;
		
		int result = 0;
		
		try {
			String query = "insert into infoboard values(info_bbs.nextval,?,default,?,?,0,0,0,default)";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, infoBoard.getInfoTitle());
			pstmt.setString(2, infoBoard.getUserId());
			pstmt.setString(3, infoBoard.getInfoContent());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	//정보공유게시판 게시글 검색
	public ArrayList<InfoBoard> search(Connection conn, int currentPage, int perPage, String type, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<InfoBoard> list = new ArrayList<InfoBoard>();
		
		int start = currentPage * perPage - (perPage - 1);
		int end = currentPage * perPage;
		
		try {
			String query="";
			switch(type) {
			case "infoTitle":
				query="select * from (select row_number() over(order by free_no desc) as num, infoboard.* from infoboard"
						+ " where info_withdrawal='N' and info_title like ?)"
						+ " where num between ? and ?";
				break;
			case "userId":
				query="select * from (select row_number() over(order by free_no desc) as num, infoboard.* from infoboard"
						+ " where info_withdrawal='N' and user_id like ?)"
						+ " where num between ? and ?";
				break;
			default:
				query="select * from (select row_number() over(order by free_no desc) as num, infoboard.* from infoboard"
						+ " where info_withdrawal='N' and (info_title like ? or user_id like ?)"
						+ " where num between ? and ?";
				break;
			}
			pstmt = conn.prepareStatement(query);
			if(!type.equals("all"))
			{
				pstmt.setString(1, "%"+keyword+"%");
				pstmt.setInt(2, start);
				pstmt.setInt(3, end);
			}else
			{
				pstmt.setString(1, "%"+keyword+"%");
				pstmt.setString(2, "%"+keyword+"%");
				pstmt.setInt(3, start);
				pstmt.setInt(4, end);
			}
			
			rset = pstmt.executeQuery();
			while (rset.next()) {
				InfoBoard infoBoard = new InfoBoard();
				infoBoard.setInfoNo(rset.getInt("info_no"));
				infoBoard.setInfoTitle(rset.getString("info_title"));
				infoBoard.setInfoDate(rset.getDate("info_date"));
				infoBoard.setUserId(rset.getString("user_id"));
				infoBoard.setInfoContent(rset.getString("info_content"));
				infoBoard.setInfoHit(rset.getInt("info_hit"));
				infoBoard.setInfoLike(rset.getInt("info_like"));
				infoBoard.setInfoHate(rset.getInt("info_hate"));
				
				list.add(infoBoard);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	//정보공유게시판 검색 페이징 처리
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
			sb.append("<a href='/board/info/search.do?currentPage=" + (startNavi - 1) + "&type="+type+"&keyword="+keyword+"'>< </a>");
		}
		for (int i = startNavi; i <= endNavi; i++) {
			if (i == currentPage) {
				sb.append("<a href='/board/info/search.do?currentPage=" + i + "&type="+type+"&keyword="+keyword+"'><B>" + i + "</B></a> ");
			} else {
				sb.append("<a href='/board/info/search.do?currentPage=" + i + "&type="+type+"&keyword="+keyword+"'>" + i + "</a> ");
			}
		}
		if (next) {
			sb.append(" <a href='/board/info/search.do?currentPage=" + (endNavi + 1) + "&type="+type+"&keyword="+keyword+"'>></a>");
		}
		return sb.toString();
	}
	
	//정보공유게시판 검색 전체 게시글 수
	private int totalPost(Connection conn, String type, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		int totalPost = 0;
		
		try {
			String query="";
			switch(type) {
			case "infoTitle":
				query="select count(*) as totalcount from infoboard"
						+ " where info_withdrawal='N' and info_title like ?;";
				break;
			case "userId":
				query="select count(*) as totalcount from infoboard"
						+ " where info_withdrawal='N' and user_id like ?;";
				break;
			default:
				query="select count(*) as totalcount from infoboard"
						+ " where info_withdrawal='N' and (info_title like ? or user_id like ?);";
				break;
			}
			
		pstmt = conn.prepareStatement(query);
		if(!type.equals("all")){
			pstmt.setString(1, "%"+keyword+"%");
		}else{
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setString(2, "%"+keyword+"%");
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