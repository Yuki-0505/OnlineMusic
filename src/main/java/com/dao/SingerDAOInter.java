package com.dao;

import com.entity.Singer;

public interface SingerDAOInter {
	final String SINGERID = "singerId";//歌手编号
	final String SINGERNAME = "singerName";//歌手名字
	final String ACCESSCOUNT = "accessCount";//热度
	final String COLLECTIONCOUNT = "collectionCount";//收藏次数
	final String AREAID = "areaId";//地区编号
	final String INTRODUCE = "introduce";//歌手简介
	final String BIRTHDAY = "birthday";//歌手生日
	final String SINGERSEX = "singerSex";//歌手性别
	final String PHOTOURL = "photoUrl";//歌手图片链接
	final String DEBUTDATE = "debutDate";//出道时间
	
	//根据歌手编号查询歌手信息
	public Singer selectSingerOfSingerId(int SingerId);
}
