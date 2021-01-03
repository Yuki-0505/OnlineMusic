package com.service;

import java.util.ArrayList;

import com.entity.UserListenSongCountView;

public interface UserListenSongCountViewServiceInter {
	//用户听歌记录视图查询
	public ArrayList<UserListenSongCountView> selectUserListenSongCountViewArrayList(int userId);
	//降序查询听歌记录视图
	public ArrayList<UserListenSongCountView> selectUserListenSongCountViewsOrderByListenCountDesc(int userId);
}
