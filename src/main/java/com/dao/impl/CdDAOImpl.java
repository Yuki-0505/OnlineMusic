package com.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dao.CdDAOInter;
import com.entity.Cd;
import com.mysql.jdbc.Connection;

public class CdDAOImpl extends BaseDAO implements CdDAOInter {
	//根据cdId查询专辑表
	public Cd selectCdOfCdId(int cdId) {
		Connection connection = getConnection();
		Cd cd = new Cd();
		if (connection != null) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "select * from cd where CDId = ?";
				pstmt = connection.prepareStatement(sql);
				pstmt.setInt(1, cdId);
				rs = pstmt.executeQuery();
				if (rs != null && rs.next()) {
					cd.setCDId(rs.getInt(CDID));
					cd.setCDName(rs.getString(CDNAME));
					cd.setCoverUrl(rs.getString(COVERURL));
					cd.setSongCount(rs.getInt(SONGCOUNT));
					cd.setPublishDate(rs.getString(PUBLISHDATE));
					cd.setSingerId(rs.getInt(SINGERID));
					cd.setIntroduce(rs.getString(INTRODUCE));
					cd.setCollectionCount(rs.getInt(COLLECTIONCOUNT));
				}else {
					cd = null;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				cd = null;
			}finally{
				close(connection, pstmt, rs);
			}
			
 		}else {
			cd = null;
 		}
		return cd;
	}
}
