package com.service;

import java.util.ArrayList;

import com.entity.SongList;

public interface SongListServiceInter {
	//增加
	//删除歌单
	//修改歌单（主要是为歌单打上新的标签）
	//无条件查询所有歌但
	public ArrayList<SongList> findAllSongList();
	//查询（根据用户id，查询每个人创建的歌单）
	public ArrayList<SongList> selectAllSongList(int userId);
	//根据热降序度查询歌单信息
	public ArrayList<SongList> selectSongListOfAccessCount(int songListStateId);
	//根据用户听歌记录查询到的歌单编号，查询歌单信息，用于基于用户听歌记录的歌单推荐，
	public ArrayList<SongList> selectSongListOfListenCount(int userId);
	//用户创建歌单
	public boolean addSongList(SongList songList);//返回值boolean类型，传入参数歌单实体类对象
	//根据标签查询歌单(模糊查询)
	public ArrayList<SongList> selectSongListOfTags (String tags, int userId);
	//根据歌单编号查询歌单信息
	public SongList selectSongListOfSongListId(int songListId);
	//修改歌单表访问次数+1
	public boolean updateSongListAccessCount(int songListId);
	//判断用户是否已经创建了该歌单
	public boolean userCreateSongListOrNot(int userId, String songListName);
	//删除歌单
	public boolean deleteSongList(int userId, int songListId);
	//修改歌单
	public boolean updateSongList(SongList songList);
	
	//分页查询所有歌单，并按照访问量（热度）降序排列，，，，传入参数，fromRowNum从第几行开始，selectRowNum每次查询几行
	public ArrayList<SongList> songListLimitToAccessCount(String tags, int fromRowNum, int selectRowNum);

	//查询含有某个标签的所有歌单
	public ArrayList<SongList> selectAllSongListOfTags(String tags);
	
	//根据歌单名称模糊查询(分页)
	public ArrayList<SongList> selectSongListOfSongListName(String songListName, int startRow, int rowsNum);
	//根据歌单名称模糊查询（所有）
	public ArrayList<SongList> selectAllSongListOfSongListName(String songListName);
	
	//歌单收藏次数+1
	public boolean addSongListCollectionCount(int songListId);
	//用户删除收藏歌单时，歌单收藏次数-1
	public boolean cutDownCollectionCount(int songListId);
	//上传/修改歌单封面图片
	public boolean updateSongListImg(String songListImg, int songListId);
}
