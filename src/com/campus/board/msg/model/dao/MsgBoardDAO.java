package com.campus.board.msg.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.campus.board.common.BoardCommon;
import com.campus.board.msg.model.vo.MsgBoard;
import com.campus.common.JDBCTemplate;

public class MsgBoardDAO {

	//쪽지함 목록 전체 불러오기
	public ArrayList<MsgBoard> msgboardListAll(Connection conn, int currentPage, String userId, int perPage) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<MsgBoard> list = new ArrayList<MsgBoard>();
		
		int start=currentPage*perPage-(perPage-1);
		int end=currentPage*perPage;
		
		try {
			String query="select * from (select row_number() over (order by msg_no desc) as num, msg.* from msg"
					+ " where msg_withdrawal='N' and (send_id=? or receive_id=?))"
					+ " where num between ? and ?";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, userId);
			pstmt.setInt(3, start);
			pstmt.setInt(4, end);
			
			rset=pstmt.executeQuery();
			
			while(rset.next()) {
				MsgBoard msgBoard=new MsgBoard();
				msgBoard.setMsgNo(rset.getInt("msg_no"));
				msgBoard.setSendId(rset.getString("send_id"));
				msgBoard.setReceiveId(rset.getString("receive_id"));
				msgBoard.setMsgTitle(rset.getString("msg_title"));
				msgBoard.setMsgDate(rset.getDate("msg_date"));
				msgBoard.setMsgContent(rset.getString("msg_content"));
				
				list.add(msgBoard);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}
	
	//쪽지함 목록 페이징 처리
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
			sb.append("<a href='/board/msg/listAll.do?currentPage=" + (startNavi - 1) + "'>< </a>");
		}
		for(int i=startNavi;i<=endNavi;i++) {
			if (i == currentPage) {
				sb.append("<a href='/board/msg/listAll.do?currentPage=" + i + "'><B>" + i + "</B></a> ");
			} else {
				sb.append("<a href='/board/msg/listAll.do?currentPage=" + i + "'>" + i + "</a> ");
			}
		}
		if (next) {
			sb.append(" <a href='/board/msg/listAll.do?currentPage=" + (endNavi + 1) + "'>></a>");
		}
		return sb.toString();
	}
	
	//쪽지함 전체 쪽지 수
	public int totalPost(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		int count=0;
		
		try {
			String query = "select count(*) as count from msg where msg_withdrawal='N'";
			
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

	//쪽지함에서 게시글 하나 선택
	public MsgBoard msgboardSelectOne(Connection conn, int msgNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		MsgBoard msgBoard=null;
		
		try {
			String query="select * from msg where msg_no=? and msg_withdrawal='N'";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, msgNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				msgBoard=new MsgBoard();
				msgBoard.setMsgNo(rset.getInt("msg_no"));
				msgBoard.setSendId(rset.getString("send_id"));
				msgBoard.setReceiveId(rset.getString("receive_id"));
				msgBoard.setMsgTitle(rset.getString("msg_title"));
				msgBoard.setMsgDate(rset.getDate("msg_date"));
				msgBoard.setMsgContent(rset.getString("msg_content"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return msgBoard;
	}

	//쪽지함 쪽지 삭제
	public int delete(int msgNo, String userId, Connection conn) {
		PreparedStatement pstmt = null;
		
		int result = 0;
		
		try {
			String query="update msg set msg_withdrawal='Y' where msg_no=? and (send_id=? or receive_id=?)";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, msgNo);
			pstmt.setString(2, userId);
			pstmt.setString(3, userId);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	//쪽지함 쪽지 작성
	public int insert(MsgBoard msgBoard, Connection conn) {
		PreparedStatement pstmt = null;
		
		int result = 0;
		
		try {
			String query = "insert into msg values(msg_bbs.nextval,?,default,?,?,?,default)";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, msgBoard.getMsgTitle());
			pstmt.setString(2, msgBoard.getSendId());
			pstmt.setString(3, msgBoard.getReceiveId());
			pstmt.setString(4, msgBoard.getMsgContent());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	//쪽지함 쪽지 검색
	public ArrayList<MsgBoard> search(Connection conn, int currentPage, String userId, int perPage, String type, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<MsgBoard> list = new ArrayList<MsgBoard>();
		
		int start = currentPage * perPage - (perPage - 1);
		int end = currentPage * perPage;
		
		try {
			String query="";
			switch(type) {
			case "msgTitle":
				query="select * from (select row_number() over(order by msg_no desc) as num, msg.* from msg"
						+ " where msg_withdrawal='N' and (send_id=? or receive_id=?))"
						+ " where msg_title like ? and (num between ? and ?)";
				break;
			case "userId":
				query="select * from (select row_number() over(order by msg_no desc) as num, msg.* from msg"
						+ " where msg_withdrawal='N' and (send_id=? or receive_id=?))"
						+ " where (send_id like ? or receive_id like ?) and num between ? and ?";
				break;
			default:
				query="select * from (select row_number() over(order by msg_no desc) as num, msg.* from msg"
						+ " where msg_withdrawal='N' and (send_id=? or receive_id=?))"
						+ " where (msg_title like ? or send_id like ? or receive_id like ?) and num between ? and ?";
				break;
			}
			pstmt = conn.prepareStatement(query);
			if(type.equals("msgTitle")){
				pstmt.setString(1, userId);
				pstmt.setString(2, userId);
				pstmt.setString(3, "%"+keyword+"%");
				pstmt.setInt(4, start);
				pstmt.setInt(5, end);
			}
			else if(type.equals("userId")){
				pstmt.setString(1, userId);
				pstmt.setString(2, userId);
				pstmt.setString(3, "%"+keyword+"%");
				pstmt.setString(4, "%"+keyword+"%");
				pstmt.setInt(5, start);
				pstmt.setInt(6, end);
			}
			else{
				pstmt.setString(1, userId);
				pstmt.setString(2, userId);
				pstmt.setString(3, "%"+keyword+"%");
				pstmt.setString(4, "%"+keyword+"%");
				pstmt.setString(5, "%"+keyword+"%");
				pstmt.setInt(6, start);
				pstmt.setInt(7, end);
			}
			
			rset = pstmt.executeQuery();
			while (rset.next()) {
				MsgBoard msgBoard = new MsgBoard();
				msgBoard.setMsgNo(rset.getInt("msg_no"));
				msgBoard.setSendId(rset.getString("send_id"));
				msgBoard.setReceiveId(rset.getString("receive_id"));
				msgBoard.setMsgTitle(rset.getString("msg_title"));
				msgBoard.setMsgDate(rset.getDate("msg_date"));
				msgBoard.setMsgContent(rset.getString("msg_content"));
				
				list.add(msgBoard);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	//쪽지게시판 검색 페이징 처리
	public String searchNavi(Connection conn, int currentPage, String userId, int perPage, int naviPage, String keyword, String type) {
		int totalPost=totalPost(conn,userId,type,keyword);
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
			sb.append("<a href='/board/msg/search.do?currentPage=" + (startNavi - 1) + "&type="+type+"&keyword="+keyword+"'>< </a>");
		}
		for (int i = startNavi; i <= endNavi; i++) {
			if (i == currentPage) {
				sb.append("<a href='/board/msg/search.do?currentPage=" + i + "&type="+type+"&keyword="+keyword+"'><B>" + i + "</B></a> ");
			} else {
				sb.append("<a href='/board/msg/search.do?currentPage=" + i + "&type="+type+"&keyword="+keyword+"'>" + i + "</a> ");
			}
		}
		if (next) {
			sb.append(" <a href='/board/msg/search.do?currentPage=" + (endNavi + 1) + "&type="+type+"&keyword="+keyword+"'>></a>");
		}
		return sb.toString();
	}

	//쪽지게시판 검색 전체 게시글 수
	private int totalPost(Connection conn, String userId, String type, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		int totalPost = 0;
		
		try {
			String query="";
			switch(type) {
			case "msgTitle":
				query="select count(*) as totalcount from (select msg.* from msg where send_id=? or receive_id=?)"
						+ " where msg_withdrawal='N' and msg_title like ?;";
				break;
			case "userId":
				query="select count(*) as totalcount from (select msg.* from msg where send_id=? or receive_id=?)"
						+ " where msg_withdrawal='N' and user_id like ?;";
				break;
			default:
				query="select count(*) as totalcount from (select msg.* from msg where send_id=? or receive_id=?)"
						+ " where msg_withdrawal='N' and (msg_title like ? or user_id like ?);";
				break;
			}
			
			pstmt = conn.prepareStatement(query);
			if(!type.equals("all")) {
				pstmt.setString(1, userId);
				pstmt.setString(2, userId);
				pstmt.setString(3, "%"+keyword+"%");
			}
			else {
				pstmt.setString(1, userId);
				pstmt.setString(2, userId);
				pstmt.setString(3, "%"+keyword+"%");
				pstmt.setString(4, "%"+keyword+"%");
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