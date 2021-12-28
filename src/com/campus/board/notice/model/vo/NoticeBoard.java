package com.campus.board.notice.model.vo;

import java.sql.Date;

public class NoticeBoard {
	private int noticeNo;
	private String noticeTitle;
	private Date noticeDate;
	private String businessId;
	private String businessName;
	private String noticeContent;
	private int noticeHit;
	private int noticeLike;
	private int noticeHate;
	private char noticeWithdrawal;
	
	public NoticeBoard() {
		super();
	}
	public NoticeBoard(int noticeNo, String noticeTitle, Date noticeDate, String businessId, String businessName,
			String noticeContent, int noticeHit, int noticeLike, int noticeHate, char noticeWithdrawal) {
		super();
		this.noticeNo = noticeNo;
		this.noticeTitle = noticeTitle;
		this.noticeDate = noticeDate;
		this.businessId = businessId;
		this.businessName = businessName;
		this.noticeContent = noticeContent;
		this.noticeHit = noticeHit;
		this.noticeLike = noticeLike;
		this.noticeHate = noticeHate;
		this.noticeWithdrawal = noticeWithdrawal;
	}

	public int getNoticeNo() {
		return noticeNo;
	}
	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}
	public String getNoticeTitle() {
		return noticeTitle;
	}
	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}
	public Date getNoticeDate() {
		return noticeDate;
	}
	public void setNoticeDate(Date noticeDate) {
		this.noticeDate = noticeDate;
	}
	public String getBusinessId() {
		return businessId;
	}
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	public String getNoticeContent() {
		return noticeContent;
	}
	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}
	public int getNoticeHit() {
		return noticeHit;
	}
	public void setNoticeHit(int noticeHit) {
		this.noticeHit = noticeHit;
	}
	public int getNoticeLike() {
		return noticeLike;
	}
	public void setNoticeLike(int noticeLike) {
		this.noticeLike = noticeLike;
	}
	public int getNoticeHate() {
		return noticeHate;
	}
	public void setNoticeHate(int noticeHate) {
		this.noticeHate = noticeHate;
	}
	public char getNoticeWithdrawal() {
		return noticeWithdrawal;
	}
	public void setNoticeWithdrawal(char noticeWithdrawal) {
		this.noticeWithdrawal = noticeWithdrawal;
	}
}
