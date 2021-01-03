package com.service;

import java.util.ArrayList;

import com.entity.Song;

public interface SelectSongInfoAndCDNameAndSingerNameOfSongListIdServiceInter {
	//根据歌单编号。查询歌单中所有歌曲信息，（全部歌曲信息（song（根据songListId））、歌曲对应专辑名称（cd（根据cdId））、歌曲对应歌手姓名（singer（根据singerId）））
	public ArrayList<Song> selectSongAndCdNameAndSingerName(int songListId);
	//根据歌曲名模糊查询歌曲，用于搜索
	public ArrayList<Song> selectSongOfSongName(String songName, int startNum, int rowNum);
}
