package com.campus.board.market.model.vo;

import java.sql.Date;

public class MarketBoard {
	private int marketNo;
	private String marketTitle;
	private Date marketDate;
	private String userId;
	private int imgNo;
	private String marketProduct;
	private int marketPrice;
	private String marketCondition;
	private String marketLocation;
	private String marketTrade;
	private String marketContent;
	private int marketHit;
	private char marketWithdrawal;

	public MarketBoard() {
		super();
	}
	public MarketBoard(int marketNo, String marketTitle, Date marketDate, String userId, int imgNo,
			String marketProduct, int marketPrice, String marketCondition, String marketLocation, String marketTrade,
			String marketContent, int marketHit) {
		super();
		this.marketNo = marketNo;
		this.marketTitle = marketTitle;
		this.marketDate = marketDate;
		this.userId = userId;
		this.imgNo = imgNo;
		this.marketProduct = marketProduct;
		this.marketPrice = marketPrice;
		this.marketCondition = marketCondition;
		this.marketLocation = marketLocation;
		this.marketTrade = marketTrade;
		this.marketContent = marketContent;
		this.marketHit = marketHit;
	}
	public MarketBoard(int marketNo, String marketTitle, Date marketDate, String userId, int imgNo,
			String marketProduct, int marketPrice, String marketCondition, String marketLocation, String marketTrade,
			String marketContent, int marketHit, char marketWithdrawal) {
		super();
		this.marketNo = marketNo;
		this.marketTitle = marketTitle;
		this.marketDate = marketDate;
		this.userId = userId;
		this.imgNo = imgNo;
		this.marketProduct = marketProduct;
		this.marketPrice = marketPrice;
		this.marketCondition = marketCondition;
		this.marketLocation = marketLocation;
		this.marketTrade = marketTrade;
		this.marketContent = marketContent;
		this.marketHit = marketHit;
		this.marketWithdrawal = marketWithdrawal;
	}

	public int getMarketNo() {
		return marketNo;
	}
	public void setMarketNo(int marketNo) {
		this.marketNo = marketNo;
	}
	public String getMarketTitle() {
		return marketTitle;
	}
	public void setMarketTitle(String marketTitle) {
		this.marketTitle = marketTitle;
	}
	public Date getMarketDate() {
		return marketDate;
	}
	public void setMarketDate(Date marketDate) {
		this.marketDate = marketDate;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getImgNo() {
		return imgNo;
	}
	public void setImgNo(int imgNo) {
		this.imgNo = imgNo;
	}
	public String getMarketProduct() {
		return marketProduct;
	}
	public void setMarketProduct(String marketProduct) {
		this.marketProduct = marketProduct;
	}
	public int getMarketPrice() {
		return marketPrice;
	}
	public void setMarketPrice(int marketPrice) {
		this.marketPrice = marketPrice;
	}
	public String getMarketCondition() {
		return marketCondition;
	}
	public void setMarketCondition(String marketCondition) {
		this.marketCondition = marketCondition;
	}
	public String getMarketLocation() {
		return marketLocation;
	}
	public void setMarketLocation(String marketLocation) {
		this.marketLocation = marketLocation;
	}
	public String getMarketTrade() {
		return marketTrade;
	}
	public void setMarketTrade(String marketTrade) {
		this.marketTrade = marketTrade;
	}
	public String getMarketContent() {
		return marketContent;
	}
	public void setMarketContent(String marketContent) {
		this.marketContent = marketContent;
	}
	public int getMarketHit() {
		return marketHit;
	}
	public void setMarketHit(int marketHit) {
		this.marketHit = marketHit;
	}
	public char getMarketWithdrawal() {
		return marketWithdrawal;
	}
	public void setMarketWithdrawal(char marketWithdrawal) {
		this.marketWithdrawal = marketWithdrawal;
	}
}
