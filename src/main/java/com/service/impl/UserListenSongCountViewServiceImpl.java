package com.service.impl;

import java.util.ArrayList;

import com.dao.impl.UserListenSongCountViewDAOImpl;
import com.dao.UserListenSongCountViewDAOInter;
import com.entity.UserListenSongCountView;
import com.service.UserListenSongCountViewServiceInter;

public class UserListenSongCountViewServiceImpl implements UserListenSongCountViewServiceInter {
	public UserListenSongCountViewDAOInter userListenSongCountViewDAO = new UserListenSongCountViewDAOImpl();
	public ArrayList<UserListenSongCountView> selectUserListenSongCountViewArrayList(
			int userId) {
		ArrayList<UserListenSongCountView> userListenSongCountViewArrayList = userListenSongCountViewDAO.selecteUserListenSongCountView(userId);
		return userListenSongCountViewArrayList;
	}
	
	//降序查询听歌记录视图
	public ArrayList<UserListenSongCountView> selectUserListenSongCountViewsOrderByListenCountDesc(int userId){
		ArrayList<UserListenSongCountView> userListenSongCountViews = userListenSongCountViewDAO.selectUserListenSongCountViewsOrderByListenCountDesc(userId);
		return userListenSongCountViews;
	}
}
