package com.campus.board.free.model.vo;

import java.sql.Date;

public class FreeComment {
	private int freeNo;
	private int cFreeNo;
	private String userId;
	private String cFreeConents;
	private Date cFreeDate;
	private char cFreeWithDrawal;
	
	public FreeComment(int freeNo, int cFreeNo, String userId, String cFreeConents, Date cFreeDate,
			char cFreeWithDrawal) {
		super();
		this.freeNo = freeNo;
		this.cFreeNo = cFreeNo;
		this.userId = userId;
		this.cFreeConents = cFreeConents;
		this.cFreeDate = cFreeDate;
		this.cFreeWithDrawal = cFreeWithDrawal;
	}
	public FreeComment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getFreeNo() {
		return freeNo;
	}
	public void setFreeNo(int freeNo) {
		this.freeNo = freeNo;
	}
	public int getcFreeNo() {
		return cFreeNo;
	}
	public void setcFreeNo(int cFreeNo) {
		this.cFreeNo = cFreeNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getcFreeConents() {
		return cFreeConents;
	}
	public void setcFreeConents(String cFreeConents) {
		this.cFreeConents = cFreeConents;
	}
	public Date getcFreeDate() {
		return cFreeDate;
	}
	public void setcFreeDate(Date cFreeDate) {
		this.cFreeDate = cFreeDate;
	}
	public char getcFreeWithDrawal() {
		return cFreeWithDrawal;
	}
	public void setcFreeWithDrawal(char cFreeWithDrawal) {
		this.cFreeWithDrawal = cFreeWithDrawal;
	}
}
