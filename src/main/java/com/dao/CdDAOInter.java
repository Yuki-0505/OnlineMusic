package com.dao;

import com.entity.Cd;
/*
 * 专辑表
 */
public interface CdDAOInter {
	final String CDID = "CDId";//专辑编号
	final String CDNAME = "CDName";//专辑名字
	final String COVERURL = "coverUrl";//专辑封面图片
	final String SONGCOUNT = "songCount";//歌曲数量
	final String PUBLISHDATE = "publishDate";//发行日期
	final String SINGERID = "singerId";//歌手编号
	final String INTRODUCE = "introduce";//专辑简介
	final String COLLECTIONCOUNT = "collectionCount";//收藏次数
	//根据cdId查询专辑表
	public Cd selectCdOfCdId(int cdId);
}
