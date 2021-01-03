package com.entity;

/*
 * 歌曲歌单表
 */
public class SongListWithSong {
	private int slSongId;//歌曲歌单编号
	private int songListId;//歌单编号
	private int songId;//歌曲编号
	private String collectionDate;//收藏日期
	public int getSlSongId() {
		return slSongId;
	}
	public void setSlSongId(int slSongId) {
		this.slSongId = slSongId;
	}
	public int getSongListId() {
		return songListId;
	}
	public void setSongListId(int songListId) {
		this.songListId = songListId;
	}
	public int getSongId() {
		return songId;
	}
	public void setSongId(int songId) {
		this.songId = songId;
	}
	public String getCollectionDate() {
		return collectionDate;
	}
	public void setCollectionDate(String collectionDate) {
		this.collectionDate = collectionDate;
	}
}
