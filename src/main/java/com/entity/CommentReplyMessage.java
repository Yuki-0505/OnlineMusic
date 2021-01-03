package com.entity;

/*
 * 评论信息回复表实体类
 */
public class CommentReplyMessage {
	private int replyMsgId;//回复编号
	private int uCommentId;//用户评论编号
	private int userId;//用户编号
	private String replyMessage;//回复内容
	private String replyDate;//回复时间
	public int getReplyMsgId() {
		return replyMsgId;
	}
	public void setReplyMsgId(int replyMsgId) {
		this.replyMsgId = replyMsgId;
	}
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
	public String getReplyMessage() {
		return replyMessage;
	}
	public void setReplyMessage(String replyMessage) {
		this.replyMessage = replyMessage;
	}
	public String getReplyDate() {
		return replyDate;
	}
	public void setReplyDate(String replyDate) {
		this.replyDate = replyDate;
	}
}
