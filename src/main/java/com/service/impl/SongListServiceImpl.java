package com.service.impl;

import java.util.ArrayList;

import com.dao.impl.SongListDAOImpl;
import com.dao.SongListDAOInter;
import com.entity.SongList;
import com.service.SongListServiceInter;

public class SongListServiceImpl implements SongListServiceInter {
	private SongListDAOInter songListDAO = new SongListDAOImpl();
	//无条件查询所有歌但
	public ArrayList<SongList> findAllSongList(){
		ArrayList<SongList> songLists = songListDAO.findAllSongList();
		return songLists;
	}
	public ArrayList<SongList> selectAllSongList(int userId) {//查询用户创建的歌单
		ArrayList<SongList> songListArrayList = songListDAO.selectAllSongList(userId);
 		return songListArrayList;
	}
	public ArrayList<SongList> selectSongListOfAccessCount(int songListStateId) {
		ArrayList<SongList> songListOfAccessCountList = songListDAO.selectSongListOfAccessCount(songListStateId);
		return songListOfAccessCountList;
	}
	//根据用户听歌记录查询到的歌单编号，查询歌单信息，用于基于用户听歌记录的歌单推荐，
	public ArrayList<SongList> selectSongListOfListenCount(int userId){
		ArrayList<SongList> songListOfUserListenSong = songListDAO.selectSongListOfListenCount(userId);
		return songListOfUserListenSong;
	}
	//用户创建歌单
	public boolean addSongList(SongList songList) {
		boolean addOrNot = songListDAO.addSongList(songList);
		return addOrNot;
	}
	
	//根据标签查询歌单(模糊查询)
	public ArrayList<SongList> selectSongListOfTags(String tags, int userId) {
		ArrayList<SongList> songListArrayList = songListDAO.selectSongListOfTags(tags, userId);
		return songListArrayList;
	}
	//根据歌单编号查询歌单信息
	public SongList selectSongListOfSongListId(int songListId) {
		SongList songList = songListDAO.selectSongListOfSongListId(songListId);
		return songList;
	}
	//修改歌单表访问次数+1
	public boolean updateSongListAccessCount(int songListId) {
		boolean updateOrNot = songListDAO.updateSongListAccessCount(songListId);
		return updateOrNot;
	}
	//判断用户是否已经创建了该歌单
	public boolean userCreateSongListOrNot(int userId, String songListName){
		boolean userCreateOrNot = songListDAO.userCreateSongListOrNot(userId, songListName);
		return userCreateOrNot;
	}
	//删除歌单
	public boolean deleteSongList(int userId, int songListId){
		boolean deleteOrNot = songListDAO.deleteSongList(userId, songListId);
		return deleteOrNot;
	}
	//修改歌单
	public boolean updateSongList(SongList songList){
		boolean updateOrNot = songListDAO.updateSongList(songList);
		return updateOrNot;
	}
	
	//分页查询所有歌单，并按照访问量（热度）降序排列，，，，传入参数，fromRowNum从第几行开始，selectRowNum每次查询几行
	public ArrayList<SongList> songListLimitToAccessCount(String tags, int fromRowNum, int selectRowNum){
		ArrayList<SongList> songListOfLimit = songListDAO.songListLimitToAccessCount(tags, fromRowNum, selectRowNum);
		return songListOfLimit;
	}
	
	//查询含有某个标签的所有歌单
	public ArrayList<SongList> selectAllSongListOfTags(String tags){
		ArrayList<SongList> songLists = songListDAO.selectAllSongListOfTags(tags);
		return songLists;
	}
	
	//根据歌单名称模糊查询(分页)
	public ArrayList<SongList> selectSongListOfSongListName(String songListName, int startRow, int rowsNum){
		ArrayList<SongList> songListOfSongListNameLimit = songListDAO.selectSongListOfSongListName(songListName, startRow, rowsNum);
		return songListOfSongListNameLimit;
	}
	//根据歌单名称模糊查询（所有）
	public ArrayList<SongList> selectAllSongListOfSongListName(String songListName){
		ArrayList<SongList> allSongListOfSongListName = songListDAO.selectAllSongListOfSongListName(songListName);
		return allSongListOfSongListName;
	}
	//歌单收藏次数+1
	public boolean addSongListCollectionCount(int songListId){
		boolean addOrNot = songListDAO.addSongListCollectionCount(songListId);
		return addOrNot;
	}
	
	//用户删除收藏歌单时，歌单收藏次数-1
	public boolean cutDownCollectionCount(int songListId){
		boolean cutDownOrNot = songListDAO.cutDownCollectionCount(songListId);
		return cutDownOrNot;
	}
	//上传/修改歌单封面图片
	public boolean updateSongListImg(String songListImg, int songListId){
		boolean updateOrNot = songListDAO.updateSongListImg(songListImg, songListId);
		return updateOrNot;
	}
}
