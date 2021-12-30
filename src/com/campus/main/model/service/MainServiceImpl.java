package com.campus.main.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.campus.common.JDBCTemplate;
import com.campus.main.model.dao.MainDAO;
import com.campus.main.model.vo.Free;
import com.campus.main.model.vo.Info;
import com.campus.main.model.vo.Market;
import com.campus.main.model.vo.Notice;

public class MainServiceImpl implements MainService{
	
	private MainDAO mDAO = new MainDAO();
	
	@Override
	public ArrayList<Free> freeData() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Free> freeList = mDAO.freeData(conn);
		for(Free f : freeList) {
			f.setCommentCount(mDAO.freeCount(f.getFreeNo(),conn));
		}
		JDBCTemplate.close(conn);
		return freeList;
	}

	@Override
	public ArrayList<Notice> noticeData() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Notice> noticeList = mDAO.noticeData(conn);
		JDBCTemplate.close(conn);
		return noticeList;
	}

	@Override
	public void count() {
		Connection conn = JDBCTemplate.getConnection();
		boolean result = mDAO.countCheck(conn);
		if(result) {
			int result2 = mDAO.count(conn);
			if(result2>0) JDBCTemplate.commit(conn);
			else JDBCTemplate.rollback(conn);
		}else {
			mDAO.countCreate(conn);
		}
		JDBCTemplate.close(conn);
	}

	@Override
	public ArrayList<Info> infoData() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Info> infoList = mDAO.infoData(conn);
		for(Info i : infoList) {
			i.setInfoCount(mDAO.infoCount(i.getInfoNo(),conn));
		}
		JDBCTemplate.close(conn);
		return infoList;
	}

	@Override
	public ArrayList<Market> marketData() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Market> marketList = mDAO.marketData(conn);
		for(Market m : marketList) {
			m.setMarketCount(mDAO.marketCount(m.getMarketNo(),conn));
			m.setImgPath(mDAO.marketImg(m.getImgNo(),conn));
		}
		JDBCTemplate.close(conn);
		return marketList;
	}

}
