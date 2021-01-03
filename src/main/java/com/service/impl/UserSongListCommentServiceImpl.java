package com.service.impl;

import java.util.ArrayList;

import com.dao.impl.UserSongListCommentDAOImpl;
import com.dao.UserSongListCommentDAOInter;
import com.entity.UserSongListComment;
import com.service.UserSongListCommentServiceInter;

public class UserSongListCommentServiceImpl implements UserSongListCommentServiceInter {
	private UserSongListCommentDAOInter userSongListCommentDAO = new UserSongListCommentDAOImpl();
	//查询歌单的评论信息
	public ArrayList<UserSongListComment> selectSongListCommOfSongListId(int songListId){
		ArrayList<UserSongListComment> userSongListCommentArrayList = userSongListCommentDAO.selectSongListCommOfSongListId(songListId);
		return userSongListCommentArrayList;
	}
	
	//添加歌单评论信息
	public boolean addSongListComm(UserSongListComment userSongListComment){
		boolean addOrNot = userSongListCommentDAO.addSongListComm(userSongListComment);
		return addOrNot;
	}
	
	//分页查询所有歌单评论信息
	public ArrayList<UserSongListComment> selectUserSongListCommentLimit(int startRowsNum, int onePageRows){
		ArrayList<UserSongListComment> userSongListCommentArrayList = userSongListCommentDAO.selectUserSongListCommentLimit(startRowsNum, onePageRows);
		return userSongListCommentArrayList;
	}
	
	//查询所有歌单评论
	public ArrayList<UserSongListComment> selectAllUserSongListComments(){
		ArrayList<UserSongListComment> allUserSongListComments = userSongListCommentDAO.selectAllUserSongListComments();
		return allUserSongListComments;
	}
	
	//根据评论编号删除评论信息
	public boolean deleteUserSongListComment(int uSongListCommId){
		boolean deleteOrNot = userSongListCommentDAO.deleteUserSongListComment(uSongListCommId);
		return deleteOrNot;
	}
}
