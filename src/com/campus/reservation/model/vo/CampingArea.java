package com.campus.reservation.model.vo;

import java.sql.Date;

public class CampingArea {
	private int campSeq;
	private int businessNo;
	private String businessName;
	private String businessAddress;
	private String campNo;
	private String campName;
	private String campType;
	private int campPo;
	private int campMaxpo;
	private int campPrice;
	private String checkin;
	private String checkout;
	private String reservNotice;
	private String reservInfo;
	private Date regDate;
	private String filename;
	
	public CampingArea() {
		super();
	}
	
	public CampingArea(int campSeq, String businessName, int businessNo, String businessAddress, String campNo, String campName, String campType, 
			int campPo, int campMaxpo, int campPrice, String checkin, String checkout, 
			String reservNotice, String reservInfo, Date regDate, String filename) {
		
		super();
		this.campSeq = campSeq;
		this.businessName = businessName;
		this.businessAddress = businessAddress;
		this.businessNo = businessNo;
		this.campNo = campNo;
		this.campName = campName;
		this.campType = campType;
		this.campPo = campPo;
		this.campMaxpo = campMaxpo;
		this.campPrice = campPrice;
		this.checkin = checkin;
		this.checkout = checkout;
		this.reservNotice = reservNotice;
		this.reservInfo = reservInfo;
		this.regDate = regDate;
		this.filename = filename;
	}



	public CampingArea(int campSeq, int businessNo, String campNo, String userId, String phone, String reservSta,
			String reservEnd, int price) {
		// TODO Auto-generated constructor stub
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getBusinessAddress() {
		return businessAddress;
	}

	public void setBusinessAddress(String businessAddress) {
		this.businessAddress = businessAddress;
	}

	public int getCampSeq() {
		return campSeq;
	}

	public void setCampSeq(int campSeq) {
		this.campSeq = campSeq;
	}
	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
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

	public String getCampName() {
		return campName;
	}

	public void setCampName(String campName) {
		this.campName = campName;
	}

	public String getCampType() {
		return campType;
	}

	public void setCampType(String campType) {
		this.campType = campType;
	}

	public int getCampPo() {
		return campPo;
	}

	public void setCampPo(int campPo) {
		this.campPo = campPo;
	}

	public int getCampMaxpo() {
		return campMaxpo;
	}

	public void setCampMaxpo(int campMaxpo) {
		this.campMaxpo = campMaxpo;
	}

	public int getCampPrice() {
		return campPrice;
	}

	public void setCampPrice(int campPrice) {
		this.campPrice = campPrice;
	}

	public String getCheckin() {
		return checkin;
	}

	public void setCheckin(String checkin) {
		this.checkin = checkin;
	}

	public String getCheckout() {
		return checkout;
	}

	public void setCheckout(String checkout) {
		this.checkout = checkout;
	}

	public String getReservNotice() {
		return reservNotice;
	}

	public void setReservNotice(String reservNotice) {
		this.reservNotice = reservNotice;
	}

	public String getReservInfo() {
		return reservInfo;
	}

	public void setReservInfo(String reservInfo) {
		this.reservInfo = reservInfo;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
}
