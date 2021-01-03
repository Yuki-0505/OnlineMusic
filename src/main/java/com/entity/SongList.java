package com.entity;
/*
 * 歌单表实体类
 */
public class SongList {
	private int songListId;//歌单编号
	private int userId;//用户编号
	private String songListName;//歌单名称
	private int typeId;//类型编号
	private int accessCount;//热度
	private String introduce;//歌单介绍
	private int collectionCount;//收藏次数
	private String tags;//歌单标签
	private int songListStateId;//歌单状态信息
	private String songListImg;//歌单图片
	public String getSongListImg() {
		return songListImg;
	}
	public void setSongListImg(String songListImg) {
		this.songListImg = songListImg;
	}
	public int getSongListId() {
		return songListId;
	}
	public void setSongListId(int songListId) {
		this.songListId = songListId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getSongListName() {
		return songListName;
	}
	public void setSongListName(String songListName) {
		this.songListName = songListName;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public int getAccessCount() {
		return accessCount;
	}
	public void setAccessCount(int accessCount) {
		this.accessCount = accessCount;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public int getCollectionCount() {
		return collectionCount;
	}
	public void setCollectionCount(int collectionCount) {
		this.collectionCount = collectionCount;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public int getSongListStateId() {
		return songListStateId;
	}
	public void setSongListStateId(int songListStateId) {
		this.songListStateId = songListStateId;
	}
}
