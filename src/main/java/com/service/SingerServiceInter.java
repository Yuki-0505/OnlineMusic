 package com.service;
/*
 * 歌手信息
 */
import com.entity.Singer;

public interface SingerServiceInter {
	//根据歌手编号查询歌手信息
	public Singer selectSingerOfSingerId(int SingerId);
}
