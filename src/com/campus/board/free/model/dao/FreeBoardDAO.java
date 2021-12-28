package com.campus.board.free.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.campus.board.common.BoardCommon;
import com.campus.board.free.model.vo.FreeBoard;
import com.campus.board.free.model.vo.FreeComment;
import com.campus.common.JDBCTemplate;
import com.campus.service.model.vo.Service;

public class FreeBoardDAO {

	//자유게시판 목록 전체 불러오기
	public ArrayList<FreeBoard> freeboardListAll(Connection conn, int currentPage, int perPage) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<FreeBoard> list = new ArrayList<FreeBoard>();
		
		int start=currentPage*perPage-(perPage-1);
		int end=currentPage*perPage;
		
		try {
			String query="select * from (select row_number() over (order by free_no desc) as num, freeboard.* from freeboard "
					+ "where free_withdrawal='N') "
					+ "where num between ? and ?";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			rset=pstmt.executeQuery();
			
			while(rset.next()) {
				FreeBoard freeBoard=new FreeBoard();
				freeBoard.setFreeNo(rset.getInt("free_no"));
				freeBoard.setUserId(rset.getString("user_id"));
				freeBoard.setFreeTitle(rset.getString("free_title"));
				freeBoard.setFreeHit(rset.getInt("free_hit"));
				freeBoard.setFreeLike(rset.getInt("free_like"));
				freeBoard.setFreeHate(rset.getInt("free_hate"));
				freeBoard.setFreeDate(rset.getDate("free_date"));
				freeBoard.setFreeContent(rset.getString("free_content"));
				
				list.add(freeBoard);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}
	
	//자유게시판 목록 페이징 처리
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
			sb.append("<a href='/board/free/listAll.do?currentPage=" + (startNavi - 1) + "'>< </a>");
		}
		for(int i=startNavi;i<=endNavi;i++) {
			if (i == currentPage) {
				sb.append("<a href='/board/free/listAll.do?currentPage=" + i + "'><B>" + i + "</B></a> ");
			} else {
				sb.append("<a href='/board/free/listAll.do?currentPage=" + i + "'>" + i + "</a> ");
			}
		}
		if (next) {
			sb.append(" <a href='/board/free/listAll.do?currentPage=" + (endNavi + 1) + "'>></a>");
		}
		return sb.toString();
	}
	
	//자유게시판 전체 게시글 수
	public int totalPost(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		int count=0;
		
		try {
			String query = "select count(*) as count from freeboard where free_withdrawal='N'";
			
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

	//자유게시판에서 게시글 하나 선택
	public FreeBoard freeboardSelectOne(Connection conn, int freeNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		FreeBoard freeBoard=null;
		
		try {
			String query="select * from freeboard where free_no=? and free_withdrawal='N'";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, freeNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				freeBoard=new FreeBoard();
				freeBoard.setFreeNo(rset.getInt("free_no"));
				freeBoard.setUserId(rset.getString("user_id"));
				freeBoard.setFreeTitle(rset.getString("free_title"));
				freeBoard.setFreeHit(rset.getInt("free_hit"));
				freeBoard.setFreeLike(rset.getInt("free_like"));
				freeBoard.setFreeHate(rset.getInt("free_hate"));
				freeBoard.setFreeDate(rset.getDate("free_date"));
				freeBoard.setFreeContent(rset.getString("free_content"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return freeBoard;
	}

	//자유게시판 게시글 수정
	public int update(FreeBoard freeBoard, Connection conn) {
		PreparedStatement pstmt = null;
		
		int result = 0;
		
		try {
			String query = "update freeboard set free_content=? where free_no=? and user_id=?";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, freeBoard.getFreeContent());
			pstmt.setInt(2, freeBoard.getFreeNo());
			pstmt.setString(3, freeBoard.getUserId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	//자유게시판 게시글 삭제
	public int delete(int freeNo, String userId, Connection conn) {
		PreparedStatement pstmt = null;
		
		int result = 0;
		
		try {
			String query="update board set free_withdrawal='Y' where free_no=? and user_id=?";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, freeNo);
			pstmt.setString(2, userId);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	//자유게시판 게시글 작성
	public int insert(FreeBoard freeBoard, Connection conn) {
		PreparedStatement pstmt = null;
		
		int result = 0;
		
		try {
			String query = "insert into freeboard values(free_bbs.nextval,?,default,?,?,0,0,0,default)";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, freeBoard.getFreeTitle());
			pstmt.setString(2, freeBoard.getUserId());
			pstmt.setString(3, freeBoard.getFreeContent());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	//자유게시판 게시글 검색
	public ArrayList<FreeBoard> search(Connection conn, int currentPage, int perPage, String type, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<FreeBoard> list = new ArrayList<FreeBoard>();
		
		int start = currentPage * perPage - (perPage - 1);
		int end = currentPage * perPage;
		
		try {
			String query="";
			switch(type) {
			case "freeTitle":
				query="select * from (select row_number() over(order by free_no desc) as num, freeboard.* from freeboard"
						+ " where free_withdrawal='N' and free_title like ?)"
						+ " where num between ? and ?";
				break;
			case "userId":
				query="select * from (select row_number() over(order by free_no desc) as num, freeboard.* from freeboard"
						+ " where free_withdrawal='N' and user_id like ?)"
						+ " where num between ? and ?";
				break;
			default:
				query="select * from (select row_number() over(order by free_no desc) as num, freeboard.* from freeboard"
						+ " where free_withdrawal='N' and (free_title like ? or user_id like ?)"
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
				FreeBoard freeBoard = new FreeBoard();
				freeBoard.setFreeNo(rset.getInt("free_no"));
				freeBoard.setFreeTitle(rset.getString("free_title"));
				freeBoard.setFreeDate(rset.getDate("free_date"));
				freeBoard.setUserId(rset.getString("user_id"));
				freeBoard.setFreeContent(rset.getString("free_content"));
				freeBoard.setFreeHit(rset.getInt("free_hit"));
				freeBoard.setFreeLike(rset.getInt("free_like"));
				freeBoard.setFreeHate(rset.getInt("free_hate"));
				
				list.add(freeBoard);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	//자유게시판 검색 페이징 처리
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
			sb.append("<a href='/board/free/search.do?currentPage=" + (startNavi - 1) + "&type="+type+"&keyword="+keyword+"'>< </a>");
		}
		for (int i = startNavi; i <= endNavi; i++) {
			if (i == currentPage) {
				sb.append("<a href='/board/free/search.do?currentPage=" + i + "&type="+type+"&keyword="+keyword+"'><B>" + i + "</B></a> ");
			} else {
				sb.append("<a href='/board/free/search.do?currentPage=" + i + "&type="+type+"&keyword="+keyword+"'>" + i + "</a> ");
			}
		}
		if (next) {
			sb.append(" <a href='/board/free/search.do?currentPage=" + (endNavi + 1) + "&type="+type+"&keyword="+keyword+"'>></a>");
		}
		return sb.toString();
	}

	//자유게시판 검색 전체 게시글 수
	private int totalPost(Connection conn, String type, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		int totalPost = 0;
		
		try {
			String query="";
			switch(type) {
			case "freeTitle":
				query="select count(*) as totalcount from freeboard"
						+ " where free_withdrawal='N' and free_title like ?;";
				break;
			case "userId":
				query="select count(*) as totalcount from freeboard"
						+ " where free_withdrawal='N' and user_id like ?;";
				break;
			default:
				query="select count(*) as totalcount from freeboard"
						+ " where free_withdrawal='N' and (free_title like ? or user_id like ?);";
				break;
			}
			
			pstmt = conn.prepareStatement(query);
			if(!type.equals("all"))
			{
				pstmt.setString(1, "%"+keyword+"%");
			}else
			{
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

	public ArrayList<FreeComment> commentList(int freeNo, Connection conn, int commentPage, int recordCountPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<FreeComment> list = new ArrayList<FreeComment>();
		
		int start = commentPage * recordCountPerPage - (recordCountPerPage-1);
		int end = commentPage * recordCountPerPage;
		
		String query="SELECT * FROM (SELECT ROW_NUMBER() OVER(ORDER BY CFREE_NO DESC) AS NUM, FREECOMMENT.* "
				+ "FROM FREECOMMENT WHERE CFREE_WITHDRAWAL='N' AND FREE_NO=?) WHERE NUM BETWEEN ? AND ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, freeNo);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				FreeComment cf = new FreeComment();
				cf.setFreeNo(rset.getInt("free_no"));
				cf.setcFreeNo(rset.getInt("cfree_no"));
				cf.setUserId(rset.getString("user_id"));
				cf.setcFreeConents(rset.getString("cfree_contents"));
				cf.setcFreeDate(rset.getDate("cfree_date"));
				
				list.add(cf);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return list;
	}

	public String getCommentPageNavi(Connection conn, int naviCountPerPage, int currentPage, int recordCountPerPage,
			int commentPage, int freeNo) {
		int recordTotalCount = totalCount(conn,freeNo);
		
		int pageTotalCount = (int)Math.ceil(recordTotalCount/(double)recordCountPerPage);
		
		int startNavi = (((currentPage-1) / naviCountPerPage) * naviCountPerPage) + 1;
		int endNavi = startNavi + (naviCountPerPage-1);
		
		if(endNavi>pageTotalCount) {
			endNavi = pageTotalCount;
		}
		
		StringBuilder sb = new StringBuilder();
		if(startNavi!=1) {
			sb.append("<a href='/board/free/selectOne.do?currentPage="+currentPage+"&freeNo="+freeNo+"&commentPage="+(startNavi-1)+"'>< prev</a> ");
		}
		for(int i = startNavi; i<=endNavi; i++) {
			if(i==currentPage) {
				sb.append("<a class='navi' id='focusNavi' href='/board/free/selectOne.do?currentPage="+currentPage+"&freeNo="+freeNo+"&commentPage="+i+"'><B style='font-siez:1.4em; color:#ff5000;'>"+i+"</B></a> ");
			}else {
				sb.append("<a class='navi' href='/board/free/selectOne.do?currentPage="+currentPage+"&freeNo="+freeNo+"&commentPage="+i+"'>"+i+"</a> ");
			}
		}
		if(endNavi!=pageTotalCount) {
			sb.append(" <a href='/board/free/selectOne.do?currentPage="+currentPage+"&freeNo="+freeNo+"&commentPage="+(endNavi+1)+"'>next ></a>");
		}
		
		return sb.toString();
	}

	private int totalCount(Connection conn, int freeNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int count = 0;
		String query = "SELECT COUNT(*) AS count FROM FREECOMMENT WHERE FREE_NO=? AND CFREE_WITHDRAWAL='N'";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, freeNo );
			rset = pstmt.executeQuery();
			if(rset.next()) {
				count = rset.getInt("count");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return count;
	}

	public int commentWrite(int freeNo, String userId, String commentContent, Connection conn) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT INTO FREECOMMENT VALUES (?,FREE_CMT.NEXTVAL,?,?,SYSDATE,'N')";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, freeNo);
			pstmt.setString(2, userId);
			pstmt.setString(3, commentContent);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int commentDelete(int cFreeNo, Connection conn) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "UPDATE FREECOMMENT SET CFREE_WITHDRAWAL='Y' WHERE CFREE_NO=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, cFreeNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
}