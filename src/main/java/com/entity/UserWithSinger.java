package com.entity;
/*
 * 用户歌手表
 */
public class UserWithSinger {
	private int uSingerId;//用户歌手编号
	private int userId;//用户编号
	private int singerId;//歌手编号
	private String collectionDate;//收藏日期
	public int getuSingerId() {
		return uSingerId;
	}
	public void setuSingerId(int uSingerId) {
		this.uSingerId = uSingerId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getSingerId() {
		return singerId;
	}
	public void setSingerId(int singerId) {
		this.singerId = singerId;
	}
	public String getCollectionDate() {
		return collectionDate;
	}
	public void setCollectionDate(String collectionDate) {
		this.collectionDate = collectionDate;
	}
}
