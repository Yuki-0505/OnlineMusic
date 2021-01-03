package com.dao;

import java.util.ArrayList;

import com.entity.Song;
import com.entity.SongList;

/*
 * songlist歌单表(用户创建的歌单信息)
 */
public interface SongListDAOInter {
	public final String SONGLISTID = "songListId";//歌单编号
	public final String USERID = "userId";//用户编号
	public final String SONGLISTNAME = "songListName";//歌单名称
	public final String TYPEID = "typeId";//类型编号
	public final String ACCESSCOUNT = "accessCount";//热度，访问次数
	public final String INTRODUCE = "introduce";//歌单介绍
	public final String COLLECTIONCOUNT = "collectionCount";//收藏次数
	public final String TAGS = "tags";//歌单标签
	public final String SONGLISTSTATEID = "songListStateId";//歌单状态信息编号
	public final String SONGLISTIMG = "songListImg";//歌单图片
	//创建歌单
	//删除歌单
	//改歌单
	//无条件查询所有歌单
	public ArrayList<SongList> findAllSongList();
	//根据userId查询歌单(主要获取表中的标签信息)
	public ArrayList<SongList> selectAllSongList(int userId);
	//按照歌单热度降序查询歌单
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

	//查询含有某个标签的所有歌单,,用于查询页数
	public ArrayList<SongList> selectAllSongListOfTags(String tags);
	
	//根据歌单名称模糊查询(分页)
	public ArrayList<SongList> selectSongListOfSongListName(String songListName, int startRow, int rowsNum);
	//根据歌单名称模糊查询（所有）
	public ArrayList<SongList> selectAllSongListOfSongListName(String songListName);
	
	//用户收藏歌单时，歌单收藏次数+1
	public boolean addSongListCollectionCount(int songListId);
	//用户删除收藏歌单时，歌单收藏次数-1
	public boolean cutDownCollectionCount(int songListId);
	//上传/修改歌单封面图片
	public boolean updateSongListImg(String songListImg, int songListId);
}
