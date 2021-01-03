package com.service;

import com.entity.UserListenSong;

public interface UserListenSongServiceInter {
	//添加用户听歌记录
	public boolean addUserListenSongRecore(UserListenSong userListenSong);//添加听歌记录，返回值boolean类型，传入参数，用户听歌记录实体对象
}
