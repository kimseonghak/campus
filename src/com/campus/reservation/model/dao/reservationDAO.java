package com.campus.reservation.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.campus.reservation.model.vo.CampingArea;

public class reservationDAO {
	
	public int insertReservation(Connection conn, CampingArea rsvInfo, String userId, String reservSta, String reservEnd) {
		
		int result =0;
		String phone = "";
		
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rset = null;
		
		String sql1 = "SELECT USER_PHONE FROM MEMBER WHERE USER_ID=?";
		
		try {
			pstmt1 = conn.prepareStatement(sql1);
			pstmt1.setString(1, userId);
			
			rset = pstmt1.executeQuery();
			
			if(rset.next()) {
				phone = rset.getString("USER_PHONE");
			}

			String sql2 = "INSERT INTO RESERVATION "
						+ "VALUES (TO_CHAR(SYSDATE, 'yyyymmddhhmi')|| LPAD(CAMP_SEQ.NEXTVAL,3,'0'), ?, ?, ?"
						+ ", ?, ?, ?, ?, ?, ?, sysdate, default,default)";
			
			pstmt2 = conn.prepareStatement(sql2);
			
			pstmt2.setInt(1, rsvInfo.getCampSeq());
			pstmt2.setInt(2, rsvInfo.getBusinessNo());
			pstmt2.setString(3, rsvInfo.getCampNo());
			pstmt2.setString(4, userId);
			pstmt2.setString(5, phone);
			pstmt2.setString(6, reservSta);
			pstmt2.setString(7, reservEnd);
			pstmt2.setInt(8, 2);
			pstmt2.setInt(9, rsvInfo.getCampPrice());
			
			result = pstmt2.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
		
	}
}
