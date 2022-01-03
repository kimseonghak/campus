package com.campus.community.upload.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.campus.common.JDBCTemplate;
import com.campus.community.upload.model.vo.Image;

public class ImgUploadDAO {

	public int insert(Connection conn, Image img, String userId) {
		int imgPost=0;

		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "insert into imgupload values (img_up.nextval,?,?,?,?,?,default,default,?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, img.getImgName());
			pstmt.setString(2, img.getImgPath());
			pstmt.setInt(3, img.getImgSize());
			pstmt.setString(4, img.getImgBoard().toUpperCase());
			pstmt.setInt(5, imgPost);
			pstmt.setString(6, userId);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public String imgPath(Connection conn, int imgNo) {
		
		PreparedStatement pstmt = null;
		ResultSet rset=null;

		String imgPath=null;
		
		String query="select img_path from imgupload where img_no=?";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, imgNo);
			
			rset=pstmt.executeQuery();
			if(rset.next()) {
				imgPath=rset.getString("img_path");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return imgPath;
	}
}
