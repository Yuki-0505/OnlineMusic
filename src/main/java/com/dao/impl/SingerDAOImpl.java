package com.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dao.SingerDAOInter;
import com.entity.Singer;
import com.mysql.jdbc.Connection;

public class SingerDAOImpl extends BaseDAO implements SingerDAOInter {
	//根据歌手编号，查询歌手信息
	public Singer selectSingerOfSingerId(int singerId) {
		Connection connection = getConnection();
		Singer singer = new Singer();
		if (connection != null) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "select * from singer where singerId = ?";
				pstmt = connection.prepareStatement(sql);
				pstmt.setInt(1, singerId);
				rs = pstmt.executeQuery();
				if (rs != null && rs.next()) {
					singer.setSingerId(rs.getInt(SINGERID));
					singer.setSingerName(rs.getString(SINGERNAME));
					singer.setAccessCount(rs.getInt(ACCESSCOUNT));
					singer.setCollectionCount(rs.getInt(COLLECTIONCOUNT));
					singer.setAreaId(rs.getInt(AREAID));
					singer.setIntroduce(rs.getString(INTRODUCE));
					singer.setBirthday(rs.getString(BIRTHDAY));
					singer.setSingerSex(rs.getBoolean(SINGERSEX));
					singer.setPhotoUrl(rs.getString(PHOTOURL));
					singer.setDebutDate(rs.getString(DEBUTDATE));
				}else {
					singer = null;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				singer = null;
			}finally{
				close(connection, pstmt, rs);
			}
			
 		}else {
			singer = null;
		}
		return singer;
	}
}
