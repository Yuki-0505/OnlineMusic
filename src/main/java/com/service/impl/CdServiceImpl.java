package com.service.impl;

import com.dao.impl.CdDAOImpl;
import com.dao.CdDAOInter;
import com.entity.Cd;
import com.service.CdServiceInter;

public class CdServiceImpl implements CdServiceInter {
	private CdDAOInter CdDAO = new CdDAOImpl();
	//根据cdId查询专辑表
	public Cd selectCdOfCdId(int cdId) {
		Cd cd = CdDAO.selectCdOfCdId(cdId);
		return cd;
	}
}
