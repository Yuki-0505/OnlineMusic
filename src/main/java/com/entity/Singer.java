package com.entity;

/*
 * 歌手表实体类
 */
public class Singer {
	private int singerId;//歌手编号
	private String singerName;//歌手名称
	private int accessCount;//热度
	private int collectionCount;//收藏次数
	private int areaId;//地区编号
	private String introduce;//歌手简介
	private String birthday;//歌手生日
	private boolean singerSex;//歌手性别，flase男，true女
	private String photoUrl;//歌手图片链接
	private String debutDate;//歌手出道时间
	public int getSingerId() {
		return singerId;
	}
	public void setSingerId(int singerId) {
		this.singerId = singerId;
	}
	public String getSingerName() {
		return singerName;
	}
	public void setSingerName(String singerName) {
		this.singerName = singerName;
	}
	public int getAccessCount() {
		return accessCount;
	}
	public void setAccessCount(int accessCount) {
		this.accessCount = accessCount;
	}
	public int getCollectionCount() {
		return collectionCount;
	}
	public void setCollectionCount(int collectionCount) {
		this.collectionCount = collectionCount;
	}
	public int getAreaId() {
		return areaId;
	}
	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public boolean isSingerSex() {
		return singerSex;
	}
	public void setSingerSex(boolean singerSex) {
		this.singerSex = singerSex;
	}
	public String getPhotoUrl() {
		return photoUrl;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	public String getDebutDate() {
		return debutDate;
	}
	public void setDebutDate(String debutDate) {
		this.debutDate = debutDate;
	}
}
