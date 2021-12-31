package com.campus.main.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.campus.common.JDBCTemplate;
import com.campus.main.model.vo.Free;
import com.campus.main.model.vo.Info;
import com.campus.main.model.vo.Market;
import com.campus.main.model.vo.Notice;

public class MainDAO {

	public ArrayList<Free> freeData(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Free> freeList = new ArrayList<Free>();
		String query = "select * from (select row_number() over (order by free_no desc) as num, freeboard.* from freeboard where free_withdrawal='N') " + 
				"where num between 1 and 8";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Free f = new Free();
				f.setFreeNo(rset.getInt("free_no"));
				f.setFreeTitle(rset.getString("free_title"));
				freeList.add(f);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return freeList;
	}

	public int freeCount(int freeNo,Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int freeCount = 0;
		String query = "select freeboard.free_no,freeboard.free_title,count(freecomment.free_no) as count from freeboard " + 
				"left join freecomment on (freeboard.free_no=freecomment.free_no) group by freeboard.free_no,freeboard.free_title having freeboard.free_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, freeNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				freeCount = rset.getInt("count");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return freeCount;
	}

	public ArrayList<Notice> noticeData(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Notice> noticeList = new ArrayList<Notice>();
		String query = "select * from (select row_number() over (order by notice_no desc) as num, noticeboard.* from noticeboard where notice_withdrawal='N') " + 
				"where num between 1 and 8";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Notice n = new Notice();
				n.setNoticeNo(rset.getInt("notice_no"));
				n.setNoticeTitle(rset.getString("notice_title"));
				noticeList.add(n);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return noticeList;
	}

	public boolean countCheck(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from countpp where TO_DATE(COUNT_DATE)=TO_DATE(SYSDATE)";
		boolean result = false;
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if(rset.next()) result=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}

		return result;
	}

	public void countCreate(Connection conn) {
		PreparedStatement pstmt = null;
		String query = "INSERT INTO COUNTPP VALUES(COUNTPP_SEQ.NEXTVAL,1,SYSDATE)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
	}

	public int count(Connection conn) {
		PreparedStatement pstmt = null;
		int result= 0;
		String query = "UPDATE COUNTPP SET COUNT_PP=COUNT_PP+1 WHERE TO_DATE(COUNT_DATE)=TO_DATE(SYSDATE)";
		try {
			pstmt = conn.prepareStatement(query);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<Info> infoData(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Info> infoList = new ArrayList<Info>();
		String query = "select * from (select row_number() over (order by info_no desc) as num, infoboard.* from infoboard where info_withdrawal='N') " + 
				"where num between 1 and 3";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Info i = new Info();
				i.setInfoNo(rset.getInt("info_no"));
				i.setInfoTitle(rset.getString("info_title"));
				infoList.add(i);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return infoList;
	}

	public int infoCount(int infoNo, Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int infoCount = 0;
		String query = "select infoboard.info_no,infoboard.info_title,count(infocomment.info_no) as count from infoboard " + 
				"left join infocomment on (infoboard.info_no=infocomment.info_no) group by infoboard.info_no,infoboard.info_title having infoboard.info_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, infoNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				infoCount = rset.getInt("count");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return infoCount;
	}

	public ArrayList<Market> marketData(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Market> marketList = new ArrayList<Market>();
		String query = "select * from (select row_number() over (order by market_no desc) as num, marketboard.* from marketboard where market_withdrawal='N') " + 
				"where num between 1 and 3";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Market m = new Market();
				m.setMarketNo(rset.getInt("market_no"));
				m.setMarketTitle(rset.getString("market_title"));
				m.setImgNo(rset.getInt("img_no"));
				marketList.add(m);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return marketList;
	}

	public int marketCount(int marketNo, Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int marketCount = 0;
		String query = "select marketboard.market_no,marketboard.market_title,count(marketcomment.market_no) as count from marketboard " + 
				"left join marketcomment on (marketboard.market_no=marketcomment.market_no) group by marketboard.market_no,marketboard.market_title having marketboard.market_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, marketNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				marketCount = rset.getInt("count");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return marketCount;
	}

	public String marketImg(int imgNo, Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String imgPath = null;
		String query = "select img_path from imgupload where img_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, imgNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				imgPath = rset.getString("img_path");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return imgPath;
	}

}
