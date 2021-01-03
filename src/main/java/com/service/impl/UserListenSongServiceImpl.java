package com.service.impl;

import com.dao.impl.UserListenSongDAOImpl;
import com.dao.UserListenSongDAOInter;
import com.entity.UserListenSong;
import com.service.UserListenSongServiceInter;

public class UserListenSongServiceImpl implements UserListenSongServiceInter {
	private UserListenSongDAOInter userListenSongDAO = new UserListenSongDAOImpl();
	//添加用户听歌记录
	public boolean addUserListenSongRecore(UserListenSong userListenSong) {
		// TODO Auto-generated method stub
		boolean addOrNot = userListenSongDAO.addUserListenSongRecore(userListenSong);
		return addOrNot;
	}
}
