package com.dao;

import java.util.ArrayList;

import com.entity.UserSongListComment;
/*歌单评论表*/
public interface UserSongListCommentDAOInter {
	final String USONGLISTCOMMID = "uSongListCommId";
	final String USERID = "userId";
	final String SONGLISTID = "songListId";
	final String SONGLISTCOMMTEXT = "songListCommText";
	final String SONGLISTCOMMDATE = "songListCommDate";
	//查询歌单的评论信息
	public ArrayList<UserSongListComment> selectSongListCommOfSongListId(int songListId);
	//无条件查询所有评论信息
	public ArrayList<UserSongListComment> selectAllUserSongListComments();
	//添加评论信息
	public boolean addSongListComm(UserSongListComment userSongListComment);
	//分页查询所有歌单评论信息
	public ArrayList<UserSongListComment> selectUserSongListCommentLimit(int startRowsNum, int onePageRows);
	//根据评论编号删除评论信息
	public boolean deleteUserSongListComment(int uSongListCommId);
}
