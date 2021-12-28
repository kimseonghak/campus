package com.campus.userPage.model.vo;

public class WishT {
	
	private int wishNo;
	private int businessNo;
	private String campNo;
	private String userId;
	
	
	
	
	
	public WishT(int businessNo, String campNo, String userId) {
		super();
		this.businessNo = businessNo;
		this.campNo = campNo;
		this.userId = userId;
	}
	public WishT() {
		super();
		// TODO Auto-generated constructor stub
	}
	public WishT(int wishNo, int businessNo, String campNo, String userId) {
		super();
		this.wishNo = wishNo;
		this.businessNo = businessNo;
		this.campNo = campNo;
		this.userId = userId;
	}
	public int getWishNo() {
		return wishNo;
	}
	public void setWishNo(int wishNo) {
		this.wishNo = wishNo;
	}
	public int getBusinessNo() {
		return businessNo;
	}
	public void setBusinessNo(int businessNo) {
		this.businessNo = businessNo;
	}
	public String getCampNo() {
		return campNo;
	}
	public void setCampNo(String campNo) {
		this.campNo = campNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "WishT [wishNo=" + wishNo + ", businessNo=" + businessNo + ", campNo=" + campNo + ", userId=" + userId
				+ "]";
	}
	
	
	
	
	
}
