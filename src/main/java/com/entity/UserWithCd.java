package com.entity;
/*
 * 用户专辑表
 */
public class UserWithCd {
	private int uCDId;//用户专辑编号
	private int userId;//用户编号
	private int CDId;//专辑编号
	private String collectDate;//收藏日期
	public int getuCDId() {
		return uCDId;
	}
	public void setuCDId(int uCDId) {
		this.uCDId = uCDId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getCDId() {
		return CDId;
	}
	public void setCDId(int cDId) {
		CDId = cDId;
	}
	public String getCollectDate() {
		return collectDate;
	}
	public void setCollectDate(String collectDate) {
		this.collectDate = collectDate;
	}
}
