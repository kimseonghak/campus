package com.campus.community.upload.model.vo;

import java.sql.Date;

public class Image {
	private int imgNo;
	private String imgName;
	private String imgPath;
	private int imgSize;
	private String imgBoard;
	private int imgPost;
	private Date imgTime;
	private char imgWithdrawal;
	
	public Image() {
		super();
	}
	public Image(int imgNo, String imgName, String imgPath, int imgSize, String imgBoard, int imgPost,
			Date imgTime) {
		super();
		this.imgNo = imgNo;
		this.imgName = imgName;
		this.imgPath = imgPath;
		this.imgSize = imgSize;
		this.imgBoard = imgBoard;
		this.imgPost = imgPost;
		this.imgTime = imgTime;
	}
	
	public int getImgNo() {
		return imgNo;
	}
	public void setImgNo(int imgNo) {
		this.imgNo = imgNo;
	}
	public String getImgName() {
		return imgName;
	}
	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public int getImgSize() {
		return imgSize;
	}
	public void setImgSize(int imgSize) {
		this.imgSize = imgSize;
	}
	public String getImgBoard() {
		return imgBoard;
	}
	public void setImgBoard(String imgBoard) {
		this.imgBoard = imgBoard;
	}
	public int getImgPost() {
		return imgPost;
	}
	public void setImgPost(int imgPost) {
		this.imgPost = imgPost;
	}
	public Date getImgTime() {
		return imgTime;
	}
	public void setImgTime(Date imgTime) {
		this.imgTime = imgTime;
	}
	public char getImgWithdrawal() {
		return imgWithdrawal;
	}
	public void setImgWithdrawal(char imgWithdrawal) {
		this.imgWithdrawal = imgWithdrawal;
	}
}
