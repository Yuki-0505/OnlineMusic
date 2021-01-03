package com.dao;

import java.util.ArrayList;
/*
 * 用户听歌历史统计视图
 */

import com.entity.UserListenSongCountView;
public interface UserListenSongCountViewDAOInter {
	public final String USERID = "userId";
	public final String SONGID = "songId";
	public final String LISTENSONGCOUNT = "listenSongCount";
	//根据用户编号查询视图详情
	public ArrayList<UserListenSongCountView> selecteUserListenSongCountView (int userId);
	//根据用户听歌记录中的歌曲编号，来查询该歌曲对应的歌单编号（songlistwithsong歌曲歌单表）；根据歌单编号查询歌单详情
	
	//降序查询听歌记录视图
	public ArrayList<UserListenSongCountView> selectUserListenSongCountViewsOrderByListenCountDesc(int userId);
}
