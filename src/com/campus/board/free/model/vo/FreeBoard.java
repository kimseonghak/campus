package com.campus.board.free.model.vo;

import java.sql.Date;

public class FreeBoard {
	private int freeNo;
	private String freeTitle;
	private Date freeDate;
	private String userId;
	private String freeContent;
	private int freeHit;
	private int freeLike;
	private int freeHate;
	private char freeWithdrawal;
	private int freeCommentCount;
	
	public FreeBoard() {
		super();
	}
	public FreeBoard(int freeNo, String freeTitle, Date freeDate, String userId, String freeContent, int freeHit,
			int freeLike, int freeHate) {
		super();
		this.freeNo = freeNo;
		this.freeTitle = freeTitle;
		this.freeDate = freeDate;
		this.userId = userId;
		this.freeContent = freeContent;
		this.freeHit = freeHit;
		this.freeLike = freeLike;
		this.freeHate = freeHate;
	}
	
	
	public int getFreeCommentCount() {
		return freeCommentCount;
	}
	public void setFreeCommentCount(int freeCommentCount) {
		this.freeCommentCount = freeCommentCount;
	}
	public int getFreeNo() {
		return freeNo;
	}
	public void setFreeNo(int freeNo) {
		this.freeNo = freeNo;
	}
	public String getFreeTitle() {
		return freeTitle;
	}
	public void setFreeTitle(String freeTitle) {
		this.freeTitle = freeTitle;
	}
	public Date getFreeDate() {
		return freeDate;
	}
	public void setFreeDate(Date freeDate) {
		this.freeDate = freeDate;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFreeContent() {
		return freeContent;
	}
	public void setFreeContent(String freeContent) {
		this.freeContent = freeContent;
	}
	public int getFreeHit() {
		return freeHit;
	}
	public void setFreeHit(int freeHit) {
		this.freeHit = freeHit;
	}
	public int getFreeLike() {
		return freeLike;
	}
	public void setFreeLike(int freeLike) {
		this.freeLike = freeLike;
	}
	public int getFreeHate() {
		return freeHate;
	}
	public void setFreeHate(int freeHate) {
		this.freeHate = freeHate;
	}
	public char getFreeWithdrawal() {
		return freeWithdrawal;
	}
	public void setFreeWithdrawal(char freeWithdrawal) {
		this.freeWithdrawal = freeWithdrawal;
	}
}
