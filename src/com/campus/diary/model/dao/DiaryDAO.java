package com.campus.diary.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.campus.common.JDBCTemplate;
import com.campus.diary.model.vo.Background;
import com.campus.diary.model.vo.Frame;
import com.campus.diary.model.vo.Title;

public class DiaryDAO {

	public int insertFrameUpload(Connection conn, Frame fr) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "INSERT INTO FRAME VALUES(FRAME_SEQ.NEXTVAL,?,?,?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, fr.getUserId());
			pstmt.setString(2, fr.getImageName());
			pstmt.setString(3, fr.getImagePath());
			pstmt.setString(4, fr.getFrameTitle());
			pstmt.setDate(5, fr.getFrameDate());
			
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		
		return result;
	}

	public ArrayList<Frame> selectFrameList(Connection conn, String userId) {
		int imgpost=0;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Frame> list = new ArrayList<Frame>();
		
		String query = "SELECT * FROM FRAME WHERE USER_ID=? ORDER BY FRAME_DATE DESC";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Frame fr = new Frame();
				fr.setFrameNo(rset.getInt("frame_No"));
				fr.setUserId(rset.getString("user_id"));
				fr.setImageName(rset.getString("image_name"));
				fr.setImagePath(rset.getString("image_path"));
				fr.setFrameTitle(rset.getString("frame_title"));
				fr.setFrameDate(rset.getDate("frame_date"));
				
				list.add(fr);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
				
		return list;
	}

	public int deleteFrame(int frameNo, Connection conn) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "DELETE FROM FRAME WHERE FRAME_NO =?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, frameNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int updateTitle(Title t, Connection conn) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "MERGE INTO DIARY_TITLE " + 
						" USING DUAL ON (USER_ID=?) " + 
						" WHEN MATCHED THEN " + 
							" UPDATE SET DIARY_TITLE=? " + 
						" WHEN NOT MATCHED THEN " + 
							" INSERT(DIARY_TITLE_NO,USER_ID,DIARY_TITLE) " + 
							" VALUES(DIARY_TITLE_SEQ.NEXTVAL,?,?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1,t.getUserId());
			pstmt.setString(2,t.getDiaryTitle());
			pstmt.setString(3,t.getUserId());
			pstmt.setString(4,t.getDiaryTitle());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public Title selectTitle(String userId, Connection conn) {

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Title title = null;
		
		String query = "SELECT * FROM DIARY_TITLE WHERE USER_ID=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			
			rset=pstmt.executeQuery();
			
			if(rset.next())
			{
				title = new Title();
				title.setDiaryTitleNo(rset.getInt("diary_title_no"));
				title.setUserId(rset.getString("user_id"));
				title.setDiaryTitle(rset.getString("diary_title"));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		
		
		return title;
	}

	public int updateBackground(Background bg, Connection conn) {

		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "MERGE INTO DIARY_BACKGROUND " + 
						"	USING DUAL ON (USER_ID=?) " + 
						"	WHEN MATCHED THEN " + 
						"		UPDATE SET DIARY_BACK_NAME=?, DIARY_BACK_PATH=? " + 
						"	WHEN NOT MATCHED THEN" + 
						"        INSERT(DIARY_BACK_NO,USER_ID,DIARY_BACK_NAME,DIARY_BACK_PATH) " + 
						"		VALUES(DIARY_BACK_SEQ.NEXTVAL,?,?,?) ";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, bg.getUserId());
			pstmt.setString(2, bg.getDiaryBackName());
			pstmt.setString(3, bg.getDiaryBackPath());
			pstmt.setString(4, bg.getUserId());
			pstmt.setString(5, bg.getDiaryBackName());
			pstmt.setString(6, bg.getDiaryBackPath());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public Background selectBackground(String userId, Connection conn) {


		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Background back = null;
		
		String query = "SELECT * FROM DIARY_BACKGROUND WHERE USER_ID=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			
			rset=pstmt.executeQuery();
			
			if(rset.next())
			{
				back = new Background();
				back.setDiaryBackNo(rset.getInt("diary_back_no"));
				back.setUserId(rset.getString("user_id"));
				back.setDiaryBackName(rset.getString("diary_back_name"));
				back.setDiaryBackPath(rset.getString("diary_back_path"));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		
		
		return back;
	}

	

}
