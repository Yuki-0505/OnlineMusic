package com.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dao.UserListenSongDAOInter;
import com.entity.UserListenSong;
import com.mysql.jdbc.Connection;

public class UserListenSongDAOImpl extends BaseDAO implements UserListenSongDAOInter {
	public boolean addUserListenSongRecore(UserListenSong userListenSong) {
		Connection connection = getConnection();
		boolean addOrNot = false;//记录是否添加成功
		int row = 0;//记录插入的行数，
		if (connection != null) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				String sql = "insert into userlistensong(userId, songId, listenSongDate) values(?,?,?)";
				pstmt = connection.prepareStatement(sql);
				pstmt.setInt(1, userListenSong.getUserId());
				pstmt.setInt(2, userListenSong.getSongId());
				pstmt.setString(3, userListenSong.getListenSongDate());
				row = pstmt.executeUpdate();
				if (row > 0) {
					addOrNot = true;
				}else {
					addOrNot = false;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				addOrNot = false;
			}finally{
				close(connection, pstmt, rs);
			}
		}else {
			addOrNot = false;
		}
		return addOrNot;
	}
}
