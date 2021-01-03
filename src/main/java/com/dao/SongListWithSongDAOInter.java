package com.dao;
/*
 * 歌曲歌单表，
 */
import java.util.ArrayList;

import com.entity.SongListWithSong;

public interface SongListWithSongDAOInter {
	final String SLSONGID = "slSongId";//歌单歌曲编号
	final String SONGLISTID = "songListId";//歌单编号
	final String SONGID = "songId";//歌曲编号
	final String COLLECTIONDATE = "collectionDate";//收藏日期
	
	//根据歌曲编号查询歌曲歌单表，返回的应该是一个该表的对象集合（因为每首歌可以属于多个歌单）
	public ArrayList<SongListWithSong> selectSongListWithSongOfSongId(int userId);
	//根据歌单编号，查询歌曲歌单表，返回对应歌单表中的歌曲编号
	public ArrayList<SongListWithSong> selectSongListWithSongOfSongLIstId(int songListId);
	//添加信息，歌曲添加到歌单
	public boolean addSongListWithSong(SongListWithSong songListWithSong);
	//根据歌单编号，歌曲编号，查询表中是否已经存在数据，false表示已经存在，true表示不存在
	public boolean selectSongListWithSongToSongListIdAndSongId(SongListWithSong songListWithSong);
	//从歌单中删除歌曲
	public boolean deleteSongFromSongListWithSong(SongListWithSong songListWithSong);
	//根据歌曲编号，查询歌单编号
	public ArrayList<SongListWithSong> selectSongListIdFromSongListWithSongOfSongId(int songId);
}
