package com.entity;

public class UserSongListComment {
	private int uSongListCommId;
	private int userId;
	private int songListId;
	private String songListCommText;
	private String songListCommDate;
	public int getuSongListCommId() {
		return uSongListCommId;
	}
	public void setuSongListCommId(int uSongListCommId) {
		this.uSongListCommId = uSongListCommId;
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
	public String getSongListCommText() {
		return songListCommText;
	}
	public void setSongListCommText(String songListCommText) {
		this.songListCommText = songListCommText;
	}
	public String getSongListCommDate() {
		return songListCommDate;
	}
	public void setSongListCommDate(String songListCommDate) {
		this.songListCommDate = songListCommDate;
	}
}
