package com.service.impl;

import java.util.ArrayList;

import com.dao.impl.UserWithSongListDAOImpl;
import com.dao.UserWithSongListDAOInter;
import com.entity.SongList;
import com.entity.UserWithSongList;
import com.service.UserWithSongListServiceInter;

public class UserWithSongListServiceImpl implements UserWithSongListServiceInter {
	private UserWithSongListDAOInter userWithSongListDAO = new UserWithSongListDAOImpl();
	public ArrayList<UserWithSongList> selectUserWithSongList(int userId) {
		ArrayList<UserWithSongList> userWithSongListArrayList = userWithSongListDAO.selectAllUserWithSongList(userId);
		return userWithSongListArrayList;
	}
	//根据用户Id查询用户收藏的歌单，并返回歌单信息
	public ArrayList<SongList> selectSongListOfUserWithSongList(int userId) {
		ArrayList<SongList> songListOfUserWithSongList = userWithSongListDAO.selectSongListOfUserWithSongList(userId);
		return songListOfUserWithSongList;
	}
	//添加用户歌单收藏表
	public boolean userCollectionSongList(UserWithSongList userWithSongList) {
		boolean addOrNot = userWithSongListDAO.userCollectionSongList(userWithSongList);
		return addOrNot;
	}
	//删除用户收藏歌单
	public boolean deleteUserWithSongList(int userId, int songListId) {
		boolean deleteOrNot = userWithSongListDAO.deleteUserWithSongList(userId, songListId);
		return deleteOrNot;
	}
	
	//根据用户编号，歌单编号查询用户是否已经收藏该歌单
	public boolean userCollectionSongListOrNot(int userId, int songListId){
		//返回true，表示不存在数据，可以添加
		boolean collectionOrNot = userWithSongListDAO.userCollectionSongListOrNot(userId, songListId);
		return collectionOrNot;
	}
}
