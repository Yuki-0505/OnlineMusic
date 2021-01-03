package com.entity;
/*
 * 用户听歌记录表
 */
public class UserListenSong {
	private int uListenSongId;
	private int userId;
	private int songId;
	private String listenSongDate;
	public int getuListenSongId() {
		return uListenSongId;
	}
	public void setuListenSongId(int uListenSongId) {
		this.uListenSongId = uListenSongId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getSongId() {
		return songId;
	}
	public void setSongId(int songId) {
		this.songId = songId;
	}
	public String getListenSongDate() {
		return listenSongDate;
	}
	public void setListenSongDate(String listenSongDate) {
		this.listenSongDate = listenSongDate;
	}
}
