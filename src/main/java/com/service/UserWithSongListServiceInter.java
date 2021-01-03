package com.service;

import java.util.ArrayList;

import com.entity.SongList;
import com.entity.UserWithSongList;

public interface UserWithSongListServiceInter {
	//查询用户收藏的歌单标签
	public ArrayList<UserWithSongList> selectUserWithSongList(int userId);
	//用户收藏，添加数据
	public boolean userCollectionSongList(UserWithSongList userWithSongList);//返回值boolean类型，传入参数，用户收藏歌单实体类对象
	//根据用户歌单收藏表，查询对应的歌单信息
	public ArrayList<SongList> selectSongListOfUserWithSongList(int userId);
	//删除用户收藏歌单
	public boolean deleteUserWithSongList(int userId, int songListId);
	//根据用户编号，歌单编号查询用户是否已经收藏该歌单
	public boolean userCollectionSongListOrNot(int userId, int songListId);
}
