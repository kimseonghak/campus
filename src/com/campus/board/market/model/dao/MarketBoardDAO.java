package com.campus.board.market.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.campus.board.common.BoardCommon;
import com.campus.board.market.model.vo.MarketBoard;
import com.campus.common.JDBCTemplate;

public class MarketBoardDAO {

	//중고장터 목록 전체 불러오기
	public ArrayList<MarketBoard> marketboardListAll(Connection conn, int currentPage, int perPage) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<MarketBoard> list = new ArrayList<MarketBoard>();
		
		int start=currentPage*perPage-(perPage-1);
		int end=currentPage*perPage;
		
		try {
			String query="select * from (select row_number() over (order by market_no desc) as num, marketboard.* from marketboard)"
					+ " where num between ? and ?";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			rset=pstmt.executeQuery();
			
			while(rset.next()) {
				MarketBoard marketBoard=new MarketBoard();
				marketBoard.setMarketNo(rset.getInt("market_no"));
				marketBoard.setMarketTitle(rset.getString("market_title"));
				marketBoard.setMarketDate(rset.getDate("market_date"));
				marketBoard.setUserId(rset.getString("user_id"));
				marketBoard.setImgNo(rset.getInt("img_no"));
				marketBoard.setMarketProduct(rset.getString("market_product"));
				marketBoard.setMarketPrice(rset.getInt("market_price"));
				marketBoard.setMarketCondition(rset.getString("market_condition").charAt(0));
				marketBoard.setMarketLocation(rset.getString("market_location"));
				marketBoard.setMarketTrade(rset.getString("market_trade"));
				marketBoard.setMarketContent(rset.getString("market_content"));
				marketBoard.setMarketHit(rset.getInt("market_hit"));
				marketBoard.setMarketWithdrawal(rset.getString("market_withdrawal").charAt(0));
				
				list.add(marketBoard);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	//중고장터 목록 페이징 처리
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
			sb.append("<a href='/board/market/listAll.do?currentPage=" + (startNavi - 1) + "'>< </a>");
		}
		for(int i=startNavi;i<=endNavi;i++) {
			if (i == currentPage) {
				sb.append("<a href='/board/market/listAll.do?currentPage=" + i + "'><B>" + i + "</B></a> ");
			} else {
				sb.append("<a href='/board/market/listAll.do?currentPage=" + i + "'>" + i + "</a> ");
			}
		}
		if (next) {
			sb.append(" <a href='/board/market/listAll.do?currentPage=" + (endNavi + 1) + "'>></a>");
		}
		return sb.toString();
	}
	
	//중고장터 전체 게시글 수
	public int totalPost(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		int count=0;
		
		try {
			String query = "select count(*) as count from marketboard";
			
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
	
	//중고장터에서 게시글 하나 선택
	public MarketBoard marketboardSelectOne(Connection conn, int marketNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		MarketBoard marketBoard=null;
		
		try {
			String query="select * from marketboard where market_no=? and market_withdrawal='N'";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, marketNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				marketBoard.setMarketNo(rset.getInt("market_no"));
				marketBoard.setMarketTitle(rset.getString("market_title"));
				marketBoard.setMarketDate(rset.getDate("market_date"));
				marketBoard.setUserId(rset.getString("user_id"));
				marketBoard.setImgNo(rset.getInt("img_no"));
				marketBoard.setMarketProduct(rset.getString("market_product"));
				marketBoard.setMarketPrice(rset.getInt("market_price"));
				marketBoard.setMarketCondition(rset.getString("market_condition").charAt(0));
				marketBoard.setMarketLocation(rset.getString("market_location"));
				marketBoard.setMarketTrade(rset.getString("market_trade"));
				marketBoard.setMarketContent(rset.getString("market_content"));
				marketBoard.setMarketHit(rset.getInt("market_hit"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return marketBoard;
	}

	//중고장터 게시글 수정
	public int update(MarketBoard marketBoard, Connection conn) {
		PreparedStatement pstmt = null;
		
		int result = 0;
		
		try {
			String query = "update marketboard set market_product=? market_price=? market_condition=? market_location=? market_trade=? market_content=? where market_no=? and user_id=?";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, marketBoard.getMarketProduct());
			pstmt.setInt(2, marketBoard.getMarketPrice());
			pstmt.setString(3, Character.toString(marketBoard.getMarketCondition()));
			pstmt.setString(4, marketBoard.getMarketLocation());
			pstmt.setString(5, marketBoard.getMarketTrade());
			pstmt.setString(6, marketBoard.getMarketContent());
			pstmt.setInt(7, marketBoard.getMarketNo());
			pstmt.setString(8, marketBoard.getUserId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	//중고장터 게시글 삭제
	public int delete(int marketNo, String userId, Connection conn) {
		PreparedStatement pstmt = null;
		
		int result = 0;
		
		try {
			String query="update board set market_withdrawal='Y' where market_no=? and user_id=?";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, marketNo);
			pstmt.setString(2, userId);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	//중고장터 게시글 작성
	public int insert(MarketBoard marketBoard, Connection conn) {
		PreparedStatement pstmt = null;
		
		int result = 0;
		
		try {
			String query = "insert into marketboard values(market_bbs.nextval,?,default,?,?,?,?,?,?,?,?,0,default)";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, marketBoard.getMarketTitle());
			pstmt.setString(2, marketBoard.getUserId());
			pstmt.setInt(3, marketBoard.getImgNo());
			pstmt.setString(4, marketBoard.getMarketProduct());
			pstmt.setInt(5, marketBoard.getMarketPrice());
			pstmt.setString(6, Character.toString(marketBoard.getMarketCondition()));
			pstmt.setString(7, marketBoard.getMarketLocation());
			pstmt.setString(8, marketBoard.getMarketTrade());
			pstmt.setString(9, marketBoard.getMarketContent());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	//중고장터 게시글 검색
	public ArrayList<MarketBoard> search(Connection conn, int currentPage, int perPage, String type, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<MarketBoard> list = new ArrayList<MarketBoard>();
		
		int start = currentPage * perPage - (perPage - 1);
		int end = currentPage * perPage;
		
		try {
			String query="";
			switch(type) {
			case "marketTitle":
				query="select * from (select row_number() over(order by market_no desc) as num, marketboard.* from marketboard"
						+ " where market_withdrawal='N' and market_title like ?)"
						+ " where num between ? and ?";
				break;
			case "userId":
				query="select * from (select row_number() over(order by market_no desc) as num, marketboard.* from marketboard"
						+ " where market_withdrawal='N' and user_id like ?)"
						+ " where num between ? and ?";
				break;
			default:
				query="select * from (select row_number() over(order by market_no desc) as num, marketboard.* from marketboard"
						+ " where market_withdrawal='N' and (market_title like ? or user_id like ?)"
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
				MarketBoard marketBoard = new MarketBoard();
				marketBoard.setMarketNo(rset.getInt("market_no"));
				marketBoard.setMarketTitle(rset.getString("market_title"));
				marketBoard.setMarketDate(rset.getDate("market_date"));
				marketBoard.setUserId(rset.getString("user_id"));
				marketBoard.setImgNo(rset.getInt("img_no"));
				marketBoard.setMarketProduct(rset.getString("market_product"));
				marketBoard.setMarketPrice(rset.getInt("market_price"));
				marketBoard.setMarketCondition(rset.getString("market_condition").charAt(0));
				marketBoard.setMarketLocation(rset.getString("market_location"));
				marketBoard.setMarketTrade(rset.getString("market_trade"));
				marketBoard.setMarketContent(rset.getString("market_content"));
				marketBoard.setMarketHit(rset.getInt("market_hit"));
				
				list.add(marketBoard);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	//중고장터 검색 페이징 처리
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
			sb.append("<a href='/board/market/search.do?currentPage=" + (startNavi - 1) + "&type="+type+"&keyword="+keyword+"'>< </a>");
		}
		for (int i = startNavi; i <= endNavi; i++) {
			if (i == currentPage) {
				sb.append("<a href='/board/market/search.do?currentPage=" + i + "&type="+type+"&keyword="+keyword+"'><B>" + i + "</B></a> ");
			} else {
				sb.append("<a href='/board/market/search.do?currentPage=" + i + "&type="+type+"&keyword="+keyword+"'>" + i + "</a> ");
			}
		}
		if (next) {
			sb.append(" <a href='/board/market/search.do?currentPage=" + (endNavi + 1) + "&type="+type+"&keyword="+keyword+"'>></a>");
		}
		return sb.toString();
	}
	
	//중고장터 검색 전체 게시글 수
	private int totalPost(Connection conn, String type, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		int totalPost = 0;
		
		try {
			String query="";
			switch(type) {
			case "marketTitle":
				query="select count(*) as totalcount from marketboard"
						+ " where market_withdrawal='N' and market_title like ?;";
				break;
			case "userId":
				query="select count(*) as totalcount from marketboard"
						+ " where market_withdrawal='N' and user_id like ?;";
				break;
			default:
				query="select count(*) as totalcount from marketboard"
						+ " where market_withdrawal='N' and (market_title like ? or user_id like ?);";
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
}
