package com.dao;

import com.entity.UserListenSong;

public interface UserListenSongDAOInter {
	final String ULISTENSONGID = "uListenSongId";
	final String USERID = "userId";
	final String SONGID = "songId";
	final String LISTENSONGDATE = "listenSongDate";
	//添加用户听歌记录
	public boolean addUserListenSongRecore(UserListenSong userListenSong);//添加听歌记录，返回值boolean类型，传入参数，用户听歌记录实体对象
}
