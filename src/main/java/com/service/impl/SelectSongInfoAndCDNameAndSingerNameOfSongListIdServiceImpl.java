package com.service.impl;

import java.util.ArrayList;

import com.dao.impl.SongInfoAndSingerInfoAndCDInfoImpl;
import com.dao.SongInfoAndSingerInfoAndCDInfoInter;
import com.entity.Song;
import com.service.SelectSongInfoAndCDNameAndSingerNameOfSongListIdServiceInter;

public class SelectSongInfoAndCDNameAndSingerNameOfSongListIdServiceImpl implements SelectSongInfoAndCDNameAndSingerNameOfSongListIdServiceInter {
	private SongInfoAndSingerInfoAndCDInfoInter songInfoAndSingerInfoAndCDInfo =  new SongInfoAndSingerInfoAndCDInfoImpl();
	//根据歌单编号。查询歌单中所有歌曲信息，（全部歌曲信息（song（根据songListId））、歌曲对应专辑名称（cd（根据cdId））、歌曲对应歌手姓名（singer（根据singerId）））
	public ArrayList<Song> selectSongAndCdNameAndSingerName(int songListId) {
		ArrayList<Song> songs = songInfoAndSingerInfoAndCDInfo.selectSongAndCdNameAndSingerName(songListId);
		return songs;
	}
	
	//根据歌曲名模糊查询歌曲，用于搜索
	public ArrayList<Song> selectSongOfSongName(String songName, int startNum, int rowNum){
		ArrayList<Song> songsOfSongName = songInfoAndSingerInfoAndCDInfo.selectSongOfSongName(songName, startNum, rowNum);
		return songsOfSongName;
	}
}
