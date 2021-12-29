package com.campus.userPage.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.campus.common.JDBCTemplate;
import com.campus.member.model.vo.Member;
import com.campus.userPage.model.dao.UserDAO;
import com.campus.userPage.model.vo.UserReservation;
import com.campus.userPage.model.vo.UserWish;
import com.campus.userPage.model.vo.WishT;

public class UserServiceImpl implements UserService{
	
	private UserDAO uDAO = new UserDAO();

	@Override
	public Member pwdCheck(String userId, String userPwd) {
		
		Connection conn = JDBCTemplate.getConnection();
		Member m = uDAO.pwdCheck(userId, userPwd, conn);
		JDBCTemplate.close(conn);
		
		return m;
	}

	@Override
	public int updateOneMember(Member m) {
		
		Connection conn = JDBCTemplate.getConnection();
		int result = uDAO.updateOneMember(m,conn);
		
		if(result>0) JDBCTemplate.commit(conn);
		else JDBCTemplate.commit(conn);
		JDBCTemplate.close(conn);
		
		return result;
	}

	@Override
	public int deleteOneMember(int userNo) {
		
		Connection conn = JDBCTemplate.getConnection();
		int result = uDAO.deleteOneMember(userNo,conn);
		

		if(result>0) JDBCTemplate.commit(conn);
		else JDBCTemplate.rollback(conn);
		
		JDBCTemplate.close(conn);
		
		return result;
	}

	@Override
	public UserReservation selectReservation(String userId) {
		
		Connection conn = JDBCTemplate.getConnection();
		UserReservation uReser = uDAO.selectReservation(userId,conn);
		JDBCTemplate.close(conn);
		
		return uReser;
	}

	@Override
	public ArrayList<UserReservation> selectAllReservationN(String userId) {
		
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<UserReservation> list = uDAO.selectAllReservationN(userId, conn);
		JDBCTemplate.close(conn);
		
		
		return list;
	}

	@Override
	public ArrayList<UserReservation> selectAllReservationY(String userId) {
		
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<UserReservation> list = uDAO.selectAllReservationY(userId, conn);
		JDBCTemplate.close(conn);
		
		
		return list;
	}

	@Override
	public ArrayList<UserWish> selectWishList(String userId) {

		Connection conn = JDBCTemplate.getConnection();
		ArrayList<UserWish> list = uDAO.selectWishList(userId, conn);
		JDBCTemplate.close(conn);
		
		
		return list;
	}

	@Override
	public int addWish(WishT wish) {
		
		Connection conn = JDBCTemplate.getConnection();
		int result = uDAO.addWish(wish, conn);

		if(result>0) JDBCTemplate.commit(conn);
		else JDBCTemplate.rollback(conn);
		
		JDBCTemplate.close(conn);
		
		return result;
	}

	@Override
	public ArrayList<WishT> selectUserWishList(String userId) {

		Connection conn = JDBCTemplate.getConnection();
		ArrayList<WishT> list = uDAO.selectUserWishList(userId, conn);
		JDBCTemplate.close(conn);
		
		return list;
	}

	@Override
	public int deleteWish(WishT wish) {

		Connection conn = JDBCTemplate.getConnection();
		int result = uDAO.deleteWish(wish, conn);

		if(result>0) JDBCTemplate.commit(conn);
		else JDBCTemplate.rollback(conn);
		
		JDBCTemplate.close(conn);
		
		return result;
	}

}
