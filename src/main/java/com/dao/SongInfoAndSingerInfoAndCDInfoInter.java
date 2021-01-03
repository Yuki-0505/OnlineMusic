package com.dao;

import java.util.ArrayList;

import com.entity.Singer;
import com.entity.Song;

//查询歌曲与对应歌手、专辑信息
//综合查询、单独查询
public interface SongInfoAndSingerInfoAndCDInfoInter {
	final String SONGID = "songId";//歌曲编号
	final String SONGNAME = "songName";//歌曲名字
	final String SINGERID = "singerId";//歌手编号
	final String CDID = "CDId";//专辑编号
	final String LANGUAGE = "language";//语言
	final String PLAYCOUNT = "playCount";//播放次数
	final String DOWNLOADCOUNT = "downloadCount";//下载次数
	final String COLLECTIONCOUNT = "collectionCount";//收藏次数
	final String PUBLISHDATE = "publishDate";//歌曲发行时间
	final String SONGURL = "songUrl";//歌曲链接
	final String CYRICURL = "cyricUrl";//歌词连接地址
	final String SONGTIME = "songTime";//歌曲时间
	final String TYPEID = "typeId";//类型编号
	
	final String SINGERNAME = "singerName";
	final String ACCESSCOUNT = "accessCount";//热度
	final String AREAID = "areaId";
	final String INTRODUCE = "introduce";
	final String BIRTHDAY = "birthday";
	final String SINGERSEX = "singerSex";
	final String PHONEURL = "photoUrl";
	final String DEBUTDATE = "debutDate";
	
	final String CDNAME = "CDName";
	final String COVERURL = "coverUrl";
	final String SONGCOUNT = "songCount";
	//根据歌单编号。查询歌单中所有歌曲信息，（全部歌曲信息（song（根据songListId））、歌曲对应专辑名称（cd（根据cdId））、歌曲对应歌手姓名（singer（根据singerId）））
	public ArrayList<Song> selectSongAndCdNameAndSingerName(int songListId);
	//模糊查询（根据歌曲名称）用于搜索,参数查询文字、开始行、每次行数
	public ArrayList<Song> selectSongOfSongName(String songName, int startRow, int rowNum);
	
}
