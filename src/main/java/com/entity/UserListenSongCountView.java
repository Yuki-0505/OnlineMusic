package com.entity;
/*
 * 用户听歌记录统计视图
 */
public class UserListenSongCountView {
	private int userId;
	private int songId;
	private int listenSongCount;
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
	public int getListenSongCount() {
		return listenSongCount;
	}
	public void setListenSongCount(int listenSongCount) {
		this.listenSongCount = listenSongCount;
	}
}
