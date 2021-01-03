package com.service;
/*
 * 专辑服务
 */
import com.entity.Cd;

public interface CdServiceInter {
	//根据cdId查询专辑表
	public Cd selectCdOfCdId(int cdId);
}
