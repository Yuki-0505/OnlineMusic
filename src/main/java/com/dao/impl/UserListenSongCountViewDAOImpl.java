package com.dao.impl;
/*
 * 用户听歌记录统计视图
 */
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.dao.UserListenSongCountViewDAOInter;
import com.entity.UserListenSongCountView;
import com.mysql.jdbc.Connection;

public class UserListenSongCountViewDAOImpl extends BaseDAO implements UserListenSongCountViewDAOInter {//查询
	//查询该用户听歌次数最多的记录，并返回
	@SuppressWarnings("null")
	public ArrayList<UserListenSongCountView> selecteUserListenSongCountView(
			int userId) {
		Connection connection = getConnection();
		ArrayList<UserListenSongCountView> userListenSongCountViewArrayList = new ArrayList<UserListenSongCountView>();
		if (connection != null) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				String sql = "select * from userlistensongcount_view where userId = ? and listenSongCount = (select max(listenSongCount) from userlistensongcount_view where userId = ?)";
				pstmt = connection.prepareStatement(sql);
				pstmt.setInt(1, userId);
				pstmt.setInt(2, userId);
				rs = pstmt.executeQuery();
				if (rs != null) {
					while (rs.next()) {
						UserListenSongCountView userListenSongCountView = new UserListenSongCountView();
						userListenSongCountView.setUserId(rs.getInt(USERID));
						userListenSongCountView.setSongId(rs.getInt(SONGID));
						userListenSongCountView.setListenSongCount(rs.getInt(LISTENSONGCOUNT));
						userListenSongCountViewArrayList.add(userListenSongCountView);
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				userListenSongCountViewArrayList = null;
			}finally{
				close(connection, pstmt, rs);
			}
		}else {
			userListenSongCountViewArrayList = null;
		}
		return userListenSongCountViewArrayList;
	}
	//降序查询听歌记录视图
	public ArrayList<UserListenSongCountView> selectUserListenSongCountViewsOrderByListenCountDesc(int userId){
		Connection connection = getConnection();
		ArrayList<UserListenSongCountView> userListenSongCountViews = new ArrayList<UserListenSongCountView>();
		
		if (connection != null) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "select * from userlistensongcount_view where userId = ? ORDER BY listenSongCount DESC limit 0,20";
				pstmt = connection.prepareStatement(sql);
				pstmt.setInt(1, userId);
				rs = pstmt.executeQuery();
				if (rs != null) {
					while (rs.next()) {
						UserListenSongCountView userListenSongCountView = new UserListenSongCountView();
						userListenSongCountView.setUserId(rs.getInt(USERID));//用户编号
						userListenSongCountView.setSongId(rs.getInt(SONGID));//歌曲编号
						userListenSongCountView.setListenSongCount(rs.getInt(LISTENSONGCOUNT));//歌曲播放次数
						userListenSongCountViews.add(userListenSongCountView);
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				userListenSongCountViews = null;
			}finally{
				close(connection, pstmt, rs);
			}
 		}else {
			userListenSongCountViews = null;
		}
		return userListenSongCountViews;
	}
}
