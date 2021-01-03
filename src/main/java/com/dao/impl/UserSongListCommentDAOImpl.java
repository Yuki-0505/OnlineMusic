package com.dao.impl;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import com.dao.UserSongListCommentDAOInter;

import com.entity.UserSongListComment;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class UserSongListCommentDAOImpl extends BaseDAO implements UserSongListCommentDAOInter {
	//查询歌单的评论信息
	public ArrayList<UserSongListComment> selectSongListCommOfSongListId(int songListId){
		Connection connection = getConnection();
		ArrayList<UserSongListComment> userSongListCommentArrayList = new ArrayList<UserSongListComment>();
		if (connection != null) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "select * from usersonglistcomment where songListId = ?";
				pstmt = connection.prepareStatement(sql);
				
				pstmt.setInt(1, songListId);
				rs = pstmt.executeQuery();
				if (rs != null) {
					while (rs.next()) {
						UserSongListComment userSongListComment = new UserSongListComment();
						userSongListComment.setuSongListCommId(rs.getInt(USONGLISTCOMMID));
						userSongListComment.setUserId(rs.getInt(USERID));
						userSongListComment.setSongListId(rs.getInt(SONGLISTID));
						userSongListComment.setSongListCommText(rs.getString(SONGLISTCOMMTEXT));
						userSongListComment.setSongListCommDate(rs.getString(SONGLISTCOMMDATE));
						userSongListCommentArrayList.add(userSongListComment);
					}
				}else {
					userSongListCommentArrayList = null;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				userSongListCommentArrayList = null;
			}finally{
				close(connection, pstmt, rs);
			}
		}else {
			userSongListCommentArrayList = null;
		}
		return userSongListCommentArrayList;
	}
	//添加歌单评论信息
	public boolean addSongListComm(UserSongListComment userSongListComment){
		Connection connection = getConnection();
		int row = 0;
		boolean addOrNot = false;
		if (connection != null) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "insert into usersonglistcomment(userId, songListId, songListCommText, songListCommDate)  values(?, ?, ?, ?)";
				pstmt = connection.prepareStatement(sql);
				pstmt.setInt(1, userSongListComment.getUserId());
				pstmt.setInt(2, userSongListComment.getSongListId());
				pstmt.setString(3, userSongListComment.getSongListCommText());
				pstmt.setString(4, userSongListComment.getSongListCommDate());
				row = pstmt.executeUpdate();
				if (row > 0) {
					addOrNot = true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				close(connection, pstmt, rs);
			}
		}
		return addOrNot;
	}
	//分页查询所有歌单评论信息
	public ArrayList<UserSongListComment> selectUserSongListCommentLimit(int startRowsNum, int onePageRows){
		Connection connection = getConnection();
		ArrayList<UserSongListComment> userSongListCommentArrayList = new ArrayList<UserSongListComment>();
		
		if (connection != null) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "select * from usersonglistcomment limit ?, ?";
				pstmt = connection.prepareStatement(sql);
				pstmt.setInt(1, startRowsNum);
				pstmt.setInt(2, onePageRows);
				rs = pstmt.executeQuery();
				if (rs != null) {
					while (rs.next()) {
						UserSongListComment userSongListComment = new UserSongListComment();
						userSongListComment.setuSongListCommId(rs.getInt(USONGLISTCOMMID));
						userSongListComment.setUserId(rs.getInt(USERID));
						userSongListComment.setSongListId(rs.getInt(SONGLISTID));
						userSongListComment.setSongListCommText(rs.getString(SONGLISTCOMMTEXT));
						userSongListComment.setSongListCommDate(rs.getString(SONGLISTCOMMDATE));
						userSongListCommentArrayList.add(userSongListComment);
					}
				}else {
					userSongListCommentArrayList = null;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				userSongListCommentArrayList = null;
			}finally{
				close(connection, pstmt, rs);
			}
		}else {
			userSongListCommentArrayList = null;
		}
		return userSongListCommentArrayList;
	}
	//查询所有歌单评论
	public ArrayList<UserSongListComment> selectAllUserSongListComments() {
		Connection connection = getConnection();
		ArrayList<UserSongListComment> allUserSongListComments = new ArrayList<UserSongListComment>();
		if (connection != null) {
			Statement statement = null;
			ResultSet rs = null;
			
			try {
				String sql = "select uSongListCommId from usersonglistcomment";
				statement = (Statement) connection.createStatement();
				
				rs = statement.executeQuery(sql);
				if (rs != null) {
					while (rs.next()) {
						UserSongListComment userSongListComment = new UserSongListComment();
						userSongListComment.setuSongListCommId(rs.getInt(USONGLISTCOMMID));
						allUserSongListComments.add(userSongListComment);
					}
				}else {
					allUserSongListComments = null;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				allUserSongListComments = null;
			}finally{
				close(connection, null, rs);
			}
 		}else {
 			allUserSongListComments = null;
		}
		return allUserSongListComments;
	}
	//根据评论编号删除评论信息
	public boolean deleteUserSongListComment(int uSongListCommId){
		Connection connection = getConnection();
		int row = 0;
		boolean deleteOrNot = false;
		
		if (connection != null) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "delete from usersonglistcomment where uSongListCommId = ?";
				pstmt = connection.prepareStatement(sql);
				
				pstmt.setInt(1, uSongListCommId);
				row = pstmt.executeUpdate();
				if (row > 0) {
					deleteOrNot = true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				close(connection, pstmt, rs);
			}
		}
		return deleteOrNot;
	}
}
