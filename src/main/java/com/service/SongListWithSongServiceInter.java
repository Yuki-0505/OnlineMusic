package com.service;

import java.util.ArrayList;

import com.entity.SongListWithSong;

public interface SongListWithSongServiceInter {
	//根据歌单编号，查询歌曲歌单表，返回对应歌单表中的歌曲编号
	public ArrayList<SongListWithSong> selectSongListWithSongOfSongLIstId(int songListId);
	//添加信息，歌曲添加到歌单
	public boolean addSongListWithSong(SongListWithSong songListWithSong);
	//根据歌单编号，歌曲编号，查询表中是否已经存在数据，false表示已经存在，true表示不存在
	public boolean selectSongListWithSongToSongListIdAndSongId(SongListWithSong songListWithSong);
	//从歌单中删除歌曲(需要对应歌单编号，歌曲编号)
	public boolean deleteSongFromSongListWithSong(SongListWithSong songListWithSong);
	
	//根据歌曲编号，查询歌单编号
	public ArrayList<SongListWithSong> selectSongListIdFromSongListWithSongOfSongId(int songId);
}
