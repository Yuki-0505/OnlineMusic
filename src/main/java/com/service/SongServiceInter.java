package com.service;

import java.util.ArrayList;

import com.entity.Song;

public interface SongServiceInter {
	//根据歌曲编号，查询对应的歌曲
	public Song selectSongOfSongId(int songId);
	//根据歌曲名称模糊查询
	public ArrayList<Song> selectSongOfSongName(String songName);
	//增加歌曲收藏次数（用户添加歌曲到歌单，就把歌曲的收藏次数+1）
	public boolean upSongCollectionCount(int songId);
	//减少歌曲收藏次数
	public boolean cutDownSongCollCount(int songId);
	//增加歌曲播放次数
	public boolean upSongPlayCount(int songId);
	//歌曲热度查询（播放次数降序查询）50首
	public ArrayList<Song> songArrayListPlayCount();
	//歌曲下载查询（下载量，降序）50首
	public ArrayList<Song> songArrayListDownloadCount();
	//歌曲收藏榜（歌曲收藏次数）50首
	public ArrayList<Song> songArrayListCollectionCount();
	
	//分页查询所有歌曲
	public ArrayList<Song> selectSongLimit(int startRowsNum, int onePageRows);
	//查询所有歌曲行数，用于分页
	public int selectSongRowsNum();
	
	//修改歌曲链接
	public boolean updateSongSongUrl(int songId, String songUrl);
	//修改歌曲（暂只限修改歌曲名和语种）
	public boolean updateSongInfo(Song song);
	//上传歌曲
	public boolean uploadSong(Song song);
}
