package com.campus.board.info.model.vo;

import java.sql.Date;

public class InfoBoard {
	private int infoNo;
	private String infoTitle;
	private Date infoDate;
	private String userId;
	private String userName;
	private String infoContent;
	private int infoHit;
	private int infoLike;
	private int infoHate;
	private char infoWithdrawal;
	
	public InfoBoard() {
		super();
	}
	public InfoBoard(int infoNo, String infoTitle, Date infoDate, String userId, String userName, String infoContent, int infoHit,
			int infoLike, int infoHate) {
		super();
		this.infoNo = infoNo;
		this.infoTitle = infoTitle;
		this.infoDate = infoDate;
		this.userId = userId;
		this.userName = userName;
		this.infoContent = infoContent;
		this.infoHit = infoHit;
		this.infoLike = infoLike;
		this.infoHate = infoHate;
	}
	
	public int getInfoNo() {
		return infoNo;
	}
	public void setInfoNo(int infoNo) {
		this.infoNo = infoNo;
	}
	public String getInfoTitle() {
		return infoTitle;
	}
	public void setInfoTitle(String infoTitle) {
		this.infoTitle = infoTitle;
	}
	public Date getInfoDate() {
		return infoDate;
	}
	public void setInfoDate(Date infoDate) {
		this.infoDate = infoDate;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getInfoContent() {
		return infoContent;
	}
	public void setInfoContent(String infoContent) {
		this.infoContent = infoContent;
	}
	public int getInfoHit() {
		return infoHit;
	}
	public void setInfoHit(int infoHit) {
		this.infoHit = infoHit;
	}
	public int getInfoLike() {
		return infoLike;
	}
	public void setInfoLike(int infoLike) {
		this.infoLike = infoLike;
	}
	public int getInfoHate() {
		return infoHate;
	}
	public void setInfoHate(int infoHate) {
		this.infoHate = infoHate;
	}
	public char getInfoWithdrawal() {
		return infoWithdrawal;
	}
	public void setInfoWithdrawal(char infoWithdrawal) {
		this.infoWithdrawal = infoWithdrawal;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
