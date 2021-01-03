package com.service;

import java.util.ArrayList;

import com.entity.UserSongListComment;

public interface UserSongListCommentServiceInter {
	//查询歌单的评论信息
	public ArrayList<UserSongListComment> selectSongListCommOfSongListId(int songListId);
	
	//添加歌单评论信息
	public boolean addSongListComm(UserSongListComment userSongListComment);
	
	//分页查询所有歌单评论信息
	public ArrayList<UserSongListComment> selectUserSongListCommentLimit(int startRowsNum, int onePageRows);
	//查询所有歌单评论
	public ArrayList<UserSongListComment> selectAllUserSongListComments();
	//根据评论编号删除评论信息
	public boolean deleteUserSongListComment(int uSongListCommId);
}
