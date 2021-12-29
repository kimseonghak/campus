package com.campus.community.upload.model.service;

import java.sql.Connection;

import com.campus.common.JDBCTemplate;
import com.campus.community.upload.model.dao.ImgUploadDAO;
import com.campus.community.upload.model.vo.Image;

public class ImgUploadServiceImpl implements ImgUploadService{
	
	private ImgUploadDAO imgDAO= new ImgUploadDAO();

	@Override
	public int insert(Image img, String userId) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = imgDAO.insert(conn,img,userId);
		
		if(result>0) JDBCTemplate.commit(conn);
		else JDBCTemplate.rollback(conn);
		JDBCTemplate.close(conn);
		
		return result;
	}

}
