package com.entity;
/*
 * 用户歌单表
 */
public class UserWithSongList {
	private int uSongListId;//用户歌单编号
	private int userId;//用户编号
	private int songListId;//歌单编号
	private String collectionDate;//收藏日期
	public int getuSongListId() {
		return uSongListId;
	}
	public void setuSongListId(int uSongListId) {
		this.uSongListId = uSongListId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getSongListId() {
		return songListId;
	}
	public void setSongListId(int songListId) {
		this.songListId = songListId;
	}
	public String getCollectionDate() {
		return collectionDate;
	}
	public void setCollectionDate(String collectionDate) {
		this.collectionDate = collectionDate;
	}
}
