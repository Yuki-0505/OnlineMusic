package com.entity;

/*
 * 歌曲评论表
 */
public class UserSongComment {
	private int uCommentId;//用户评论编号
	private int userId;//用户编号
	private int songId;//歌曲编号
	private String commentText;//评论内容
	private String commentDate;//评论时间
	public int getuCommentId() {
		return uCommentId;
	}
	public void setuCommentId(int uCommentId) {
		this.uCommentId = uCommentId;
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
	public String getCommentText() {
		return commentText;
	}
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	public String getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(String commentDate) {
		this.commentDate = commentDate;
	}
}
