
package com.dao;
/*
 * 用户收藏歌单表userWithSongList
 */
import java.util.ArrayList;

import com.entity.SongList;
import com.entity.UserWithSongList;

public interface UserWithSongListDAOInter {
	final String USONGLISTID = "uSongListId";
	final String USERID = "userId";
	final String SONGLISTID = "songListId";
	final String COLLECTIONDATE = "collectionDate";
	final String SONGLISTNAME = "songListName";
	final String TYPEID = "typeId";
	final String ACCESSCOUNT = "accessCount";
	final String INTRODUCE = "introduce";
	final String COLLECTIONCOUNT = "collectionCount";
	final String TAGS = "tags";
	final String SONGLISTSTATEID = "songListStateId";
	final String SONGLISTIMG = "songListImg";
	//查询用户收藏的所有歌单
	public ArrayList<UserWithSongList> selectAllUserWithSongList(int userId);
	//根据用户歌单收藏表，查询对应的歌单信息
	public ArrayList<SongList> selectSongListOfUserWithSongList(int userId);
	//添加用户收藏歌单
	public boolean userCollectionSongList(UserWithSongList userWithSongList);//返回值boolean类型，传入参数，用户收藏歌单实体类对象
	//删除用户收藏歌单
	public boolean deleteUserWithSongList(int userId, int songListId);
	//根据用户编号，歌单编号查询用户是否已经收藏该歌单
	public boolean userCollectionSongListOrNot(int userId, int songListId);
}