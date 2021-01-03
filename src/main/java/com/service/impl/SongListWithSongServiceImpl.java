package com.service.impl;

import java.util.ArrayList;

import com.dao.impl.SongListWithSongDAOImpl;
import com.dao.SongListWithSongDAOInter;
import com.entity.SongListWithSong;
import com.service.SongListWithSongServiceInter;

public class SongListWithSongServiceImpl implements SongListWithSongServiceInter {
	private SongListWithSongDAOInter songListWithSongDAO = new SongListWithSongDAOImpl();
	//根据歌单编号，查询歌曲歌单表，返回对应歌单表中的歌曲编号
	public ArrayList<SongListWithSong> selectSongListWithSongOfSongLIstId(
			int songListId) {
		ArrayList<SongListWithSong> songListWithSongArrList = songListWithSongDAO.selectSongListWithSongOfSongLIstId(songListId);
		return songListWithSongArrList;
	}
	//添加信息，歌曲添加到歌单
	public boolean addSongListWithSong(SongListWithSong songListWithSong){
		boolean addOrNot = songListWithSongDAO.addSongListWithSong(songListWithSong);
		return addOrNot;
	}
	//根据歌单编号，歌曲编号，查询表中是否已经存在数据，false表示已经存在，true表示不存在，可以进行其他操作
	public boolean selectSongListWithSongToSongListIdAndSongId(SongListWithSong songListWithSong){
		boolean existOrNot = songListWithSongDAO.selectSongListWithSongToSongListIdAndSongId(songListWithSong);
		return existOrNot;
	}
	//从歌单中删除歌曲(需要对应歌单编号，歌曲编号)
	public boolean deleteSongFromSongListWithSong(SongListWithSong songListWithSong){
		boolean deleteOrNot = songListWithSongDAO.deleteSongFromSongListWithSong(songListWithSong);
		return deleteOrNot;
	}
	
	//根据歌曲编号，查询歌单编号
	public ArrayList<SongListWithSong> selectSongListIdFromSongListWithSongOfSongId(int songId){
		ArrayList<SongListWithSong> songListWithSongs = songListWithSongDAO.selectSongListIdFromSongListWithSongOfSongId(songId);
		return songListWithSongs;
	}
}
