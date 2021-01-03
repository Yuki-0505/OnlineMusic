package com.service.impl;

import com.dao.impl.SingerDAOImpl;
import com.dao.SingerDAOInter;
import com.entity.Singer;
import com.service.SingerServiceInter;

/*
 * 歌手信息
 */
public class SingerServiceImpl implements SingerServiceInter {
	SingerDAOInter singerDAO = new SingerDAOImpl();
	//根据歌手编号查询歌手信息
	public Singer selectSingerOfSingerId(int SingerId) {
		Singer singer = singerDAO.selectSingerOfSingerId(SingerId);
		return singer;
	}
	
}
