package com.campus.userPage.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.campus.common.JDBCTemplate;
import com.campus.member.model.vo.Member;
import com.campus.userPage.model.vo.UserReservation;
import com.campus.userPage.model.vo.UserWish;
import com.campus.userPage.model.vo.WishT;

public class UserDAO {

	public Member pwdCheck(String userId, String userPwd, Connection conn) {
		
		PreparedStatement pstmt = null;
		Member m = null;
		ResultSet rset = null;
		
		String query = "SELECT * FROM MEMBER WHERE USER_ID=? AND USER_PWD=? AND USER_WITHDRAWAL='N'";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			
			rset = pstmt.executeQuery();
			
			if(rset.next())
			{
				m = new Member();
				m.setUserNo(rset.getInt("user_no"));
				m.setUserId(rset.getString("user_id"));
				m.setUserPwd(rset.getString("user_pwd"));
				m.setUserName(rset.getString("user_name"));
				m.setUserBirth(rset.getDate("user_birth"));
				m.setUserPhone(rset.getString("user_phone"));
				m.setUserAddress(rset.getString("user_address"));
				m.setUserEmail(rset.getString("user_email"));
				m.setUserGender(rset.getString("user_gender").charAt(0));
				m.setUserJoined(rset.getDate("user_joined"));
				m.setUserWithdrawal(rset.getString("user_withdrawal").charAt(0));
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return m;
	}

	public int updateOneMember(Member m, Connection conn) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query ="UPDATE MEMBER " + 
						"SET USER_PWD=?, USER_PHONE=?, USER_ADDRESS=? " + 
						"WHERE USER_ID=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, m.getUserPwd());
			pstmt.setString(2, m.getUserPhone());
			pstmt.setString(3, m.getUserAddress());
			pstmt.setString(4, m.getUserId());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		
		return result;
	}

	public int deleteOneMember(int userNo, Connection conn) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query ="UPDATE MEMBER SET USER_WITHDRAWAL='Y' WHERE USER_NO=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,userNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public UserReservation selectReservation(String userId, Connection conn) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		UserReservation uReser = null;
		
		String query="SELECT business_name, camp.camp_no, camp_type, reserv_sta, reserv_end,  " + 
					"		checkin, checkout, user_id, reserv_no, business_address, file_name, path  " + 
					"FROM CAMP " + 
					"	LEFT JOIN RESERVATION ON (RESERVATION.CAMP_NO = CAMP.CAMP_NO) " + 
					"	LEFT JOIN BUSINESS ON (BUSINESS.BUSINESS_NO = CAMP.BUSINESS_NO)  " + 
					"	LEFT JOIN CAMPIMG ON (CAMPIMG.CAMP_SEQ = CAMP.CAMP_SEQ) " + 
					"WHERE USER_ID = ? AND RESERVATION.DEL_YN='N' AND END_YN='N' " + 
					"        AND business_no IN (SELECT BUSINESS_NO FROM RESERVATION WHERE USER_ID= ? AND END_YN='N' ) "+
					"ORDER BY RESERV_STA";
				
				
				
				
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, userId);
			pstmt.setString(2, userId);
			rset = pstmt.executeQuery();
			//System.out.println(rset.next());
			
			
			if(rset.next())
			{
				uReser = new UserReservation();
				uReser.setBusinessName(rset.getString("business_name"));
				uReser.setCampNo(rset.getString("camp_no"));
				uReser.setCampType(rset.getString("camp_type"));
				uReser.setReservSta(rset.getInt("reserv_sta"));
				uReser.setReservEnd(rset.getInt("reserv_end"));
				uReser.setCheckin(rset.getString("checkin"));
				uReser.setCheckout(rset.getString("checkout"));
				uReser.setUserId(rset.getString("user_id"));
				uReser.setReservNo(rset.getString("reserv_no"));
				uReser.setBusinessAddress(rset.getString("business_address"));
				uReser.setFileName(rset.getString("file_name"));
				uReser.setPath(rset.getString("path"));
				
			}
			//System.out.println(uReser);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		
		
		return uReser;
	}

	public ArrayList<UserReservation> selectAllReservationN(String userId, Connection conn) {
			
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<UserReservation> list = new ArrayList<UserReservation>();
		
		String query="SELECT business_name, camp.camp_no, camp_type, reserv_sta, reserv_end,  " + 
				"		checkin, checkout, user_id, reserv_no, business_address, file_name, path  " + 
				"FROM CAMP " + 
				"	LEFT JOIN RESERVATION ON (RESERVATION.CAMP_NO = CAMP.CAMP_NO) " + 
				"	LEFT JOIN BUSINESS ON (BUSINESS.BUSINESS_NO = CAMP.BUSINESS_NO)  " + 
				"	LEFT JOIN CAMPIMG ON (CAMPIMG.CAMP_SEQ = CAMP.CAMP_SEQ) " + 
				"WHERE USER_ID = ? AND RESERVATION.DEL_YN='N' AND END_YN='N' " + 
				"        AND business_no IN (SELECT BUSINESS_NO FROM RESERVATION WHERE USER_ID= ? AND END_YN='N' ) "+
				"ORDER BY RESERV_STA";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, userId);
			pstmt.setString(2, userId);
			rset = pstmt.executeQuery();
			
			while(rset.next())
			{
				UserReservation uReser = new UserReservation();
				uReser.setBusinessName(rset.getString("business_name"));
				uReser.setCampNo(rset.getString("camp_no"));
				uReser.setCampType(rset.getString("camp_type"));
				uReser.setReservSta(rset.getInt("reserv_sta"));
				uReser.setReservEnd(rset.getInt("reserv_end"));
				uReser.setCheckin(rset.getString("checkin"));
				uReser.setCheckout(rset.getString("checkout"));
				uReser.setUserId(rset.getString("user_id"));
				uReser.setReservNo(rset.getString("reserv_no"));
				uReser.setBusinessAddress(rset.getString("business_address"));
				uReser.setFileName(rset.getString("file_name"));
				uReser.setPath(rset.getString("path"));
				
				list.add(uReser);
				
			}
			//System.out.println(uReser);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		
		
		return list;
		
	}

	public ArrayList<UserReservation> selectAllReservationY(String userId, Connection conn) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<UserReservation> list = new ArrayList<UserReservation>();
		
		String query="SELECT business_name, camp.camp_no, camp_type, reserv_sta, reserv_end,  " + 
				"		checkin, checkout, user_id, reserv_no, business_address, file_name, path  " + 
				"FROM CAMP " + 
				"	LEFT JOIN RESERVATION ON (RESERVATION.CAMP_NO = CAMP.CAMP_NO) " + 
				"	LEFT JOIN BUSINESS ON (BUSINESS.BUSINESS_NO = CAMP.BUSINESS_NO)  " + 
				"	LEFT JOIN CAMPIMG ON (CAMPIMG.CAMP_SEQ = CAMP.CAMP_SEQ) " + 
				"WHERE USER_ID = ? AND RESERVATION.DEL_YN='N' AND END_YN='Y' " + 
				"        AND business_no IN (SELECT BUSINESS_NO FROM RESERVATION WHERE USER_ID= ? AND END_YN='Y' ) "+
				"ORDER BY RESERV_STA desc";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, userId);
			pstmt.setString(2, userId);
			rset = pstmt.executeQuery();
			
			while(rset.next())
			{
				UserReservation uReser = new UserReservation();
				uReser.setBusinessName(rset.getString("business_name"));
				uReser.setCampNo(rset.getString("camp_no"));
				uReser.setCampType(rset.getString("camp_type"));
				uReser.setReservSta(rset.getInt("reserv_sta"));
				uReser.setReservEnd(rset.getInt("reserv_end"));
				uReser.setCheckin(rset.getString("checkin"));
				uReser.setCheckout(rset.getString("checkout"));
				uReser.setUserId(rset.getString("user_id"));
				uReser.setReservNo(rset.getString("reserv_no"));
				uReser.setBusinessAddress(rset.getString("business_address"));
				uReser.setFileName(rset.getString("file_name"));
				uReser.setPath(rset.getString("path"));
				
				list.add(uReser);
				
			}
			//System.out.println(uReser);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		
		
		return list;
	}

	public ArrayList<UserWish> selectWishList(String userId, Connection conn) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<UserWish> list = new ArrayList<UserWish>();
		
		String query = "SELECT business_name, substr(business_address, 0, instr(business_address, ' ', 1, 2)) as business_addr, " + 
						"	camp.camp_no, file_name, path, business_address, camp_price " + 
						"FROM WISH " + 
						"	LEFT JOIN CAMP ON (WISH.CAMP_NO = CAMP.CAMP_NO) " + 
						"	LEFT JOIN BUSINESS ON (BUSINESS.BUSINESS_NO = CAMP.BUSINESS_NO)  " + 
						"	LEFT JOIN CAMPIMG ON (CAMPIMG.CAMP_SEQ = CAMP.CAMP_SEQ) " + 
						"WHERE USER_ID = ? " + 
						"        AND business_no IN (SELECT BUSINESS_NO FROM WISH WHERE USER_ID= ?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, userId);
			
			rset = pstmt.executeQuery();
			
			while(rset.next())
			{
				UserWish uWish = new UserWish();
				uWish.setBusinessName(rset.getString("business_name"));
				uWish.setBusinessAddr(rset.getString("business_addr"));
				uWish.setCmapNo(rset.getString("camp_no"));
				uWish.setFileName(rset.getString("file_name"));
				uWish.setPath(rset.getString("path"));
				uWish.setBusinessAddress(rset.getString("business_address"));
				uWish.setCampPrice(rset.getInt("camp_price"));
				
				list.add(uWish);
				
			}
			//System.out.println(list);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		
		
		return list;
	}

	public int addWish(WishT wish, Connection conn) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "INSERT INTO wish VALUES(WISH_SEQ.NEXTVAL, ?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, wish.getBusinessNo());
			pstmt.setString(2, wish.getCampNo());
			pstmt.setString(3, wish.getUserId());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		
		return result;
	}

	public ArrayList<WishT> selectUserWishList(String userId, Connection conn) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<WishT> list = new ArrayList<WishT>();

		String query="SELECT * FROM WISH WHERE USER_ID=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery();
			
			while(rset.next())
			{
				WishT wish = new WishT();
				wish.setWishNo(rset.getInt("wish_no"));
				wish.setBusinessNo(rset.getInt("business_no"));
				wish.setCampNo(rset.getString("camp_no"));
				wish.setUserId(rset.getString("user_id"));
				list.add(wish);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		System.out.println("list : " +list);
		return list;
	}

}
