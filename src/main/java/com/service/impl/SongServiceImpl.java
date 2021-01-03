package com.service.impl;

import java.util.ArrayList;

import com.dao.impl.SongDAOImpl;
import com.dao.SongDAOInter;
import com.entity.Song;
import com.service.SongServiceInter;

public class SongServiceImpl implements SongServiceInter {
	private SongDAOInter songDAO =  new SongDAOImpl();
 	//根据歌曲编号，查询对应的歌曲
	public Song selectSongOfSongId(int songId) {
		Song song = songDAO.selectSongOfSongId(songId);
		return song;
	}
	
	//根据歌曲名称模糊查询
	public ArrayList<Song> selectSongOfSongName(String songName){
		ArrayList<Song> songArrayList = songDAO.selectSongOfSongName(songName);
		return songArrayList;
	}
	
	//增加歌曲收藏次数（用户添加歌曲到歌单，就把歌曲的收藏次数+1）
	public boolean upSongCollectionCount(int songId){
		boolean upSongCollCountOrNot = songDAO.upSongCollectionCount(songId);
		return upSongCollCountOrNot;
	}
	
	//减少歌曲收藏次数
	public boolean cutDownSongCollCount(int songId){
		boolean cutDowmSongCollCountOrNot = songDAO.cutDownSongCollCount(songId);
		return cutDowmSongCollCountOrNot;
	}
	//增加歌曲播放次数
	public boolean upSongPlayCount(int songId){
		boolean upSongPlayCount = songDAO.upSongPlayCount(songId);
		return upSongPlayCount;
	}
	//歌曲热度查询（播放次数降序查询）50首
	public ArrayList<Song> songArrayListPlayCount(){
		ArrayList<Song> songArrayList = songDAO.songArrayListPlayCount();
		return songArrayList;
	}
	//歌曲下载榜
	public ArrayList<Song> songArrayListDownloadCount() {
		ArrayList<Song> songArrayList = songDAO.songArrayListDownloadCount();
		return songArrayList;
	}
	//歌曲收藏榜单
	public ArrayList<Song> songArrayListCollectionCount() {
		ArrayList<Song> songArrayList = songDAO.songArrayListCollectionCount();
		return songArrayList;
	}
	//分页查询所有歌曲
	public ArrayList<Song> selectSongLimit(int startRowsNum, int onePageRows){
		ArrayList<Song> songLimitArrayList = songDAO.selectSongLimit(startRowsNum, onePageRows);
		return songLimitArrayList;
	}
	//查询所有歌曲行数，用于分页
	public int selectSongRowsNum(){
		int songRowsNum = songDAO.selectSongRowsNum();
		return songRowsNum;
	}
	
	//修改歌曲链接
	public boolean updateSongSongUrl(int songId, String songUrl){
		boolean updateOrNot = songDAO.updateSongSongUrl(songId, songUrl);
		return updateOrNot;
	}
	//修改歌曲（暂只限修改歌曲名和语种）
	public boolean updateSongInfo(Song song){
		boolean updateOrNot = songDAO.updateSongInfo(song);
		return updateOrNot;
	}
	//上传歌曲
	public boolean uploadSong(Song song) {
		return songDAO.uploadSong(song);
	}
}
