package com.dao.impl;
/*
 * 用户收藏歌单表userWithSongList
 */
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.dao.UserWithSongListDAOInter;
import com.entity.SongList;
import com.entity.UserWithSongList;
import com.mysql.jdbc.Connection;

public class UserWithSongListDAOImpl extends BaseDAO implements UserWithSongListDAOInter {
	@SuppressWarnings("null")
	public ArrayList<UserWithSongList> selectAllUserWithSongList(int userId) {//查询用户收藏的所有歌单（返回该表）
		Connection connection = getConnection();
		ArrayList<UserWithSongList> userWithSongListArrayList = new ArrayList<UserWithSongList>();
		if (connection != null) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "select * from userwithsonglist where userId = ?";
				pstmt = connection.prepareStatement(sql);
				pstmt.setInt(1, userId);
				rs = pstmt.executeQuery();
				if (rs != null) {
					while (rs.next()) {
						UserWithSongList userWithSongList = new UserWithSongList();
						userWithSongList.setuSongListId(rs.getInt(USONGLISTID));
						userWithSongList.setUserId(rs.getInt(USERID));
						userWithSongList.setSongListId(rs.getInt(SONGLISTID));
						userWithSongList.setCollectionDate(rs.getString(COLLECTIONDATE));
						userWithSongListArrayList.add(userWithSongList);
					}
				}
			} catch (SQLException e) {
				userWithSongListArrayList = null;
			}finally{
				close(connection, pstmt, rs);
			}
		}else {
			userWithSongListArrayList = null;
		}
		return userWithSongListArrayList;
	}
	//根据歌单编号查询歌单信息
	public SongList selectSongListOfSongListId (int songListId){
		Connection connection = getConnection();
		SongList songList = new SongList();
		if (connection != null) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "select * from songlist where songListId = ?";
				pstmt = connection.prepareStatement(sql);
				pstmt.setInt(1, songListId);
				rs = pstmt.executeQuery();
				if (rs != null && rs.next()) {
					songList.setSongListId(rs.getInt(SONGLISTID));
					songList.setUserId(rs.getInt(USERID));
					songList.setSongListName(rs.getString(SONGLISTNAME));
					songList.setTypeId(rs.getInt(TYPEID));
					songList.setAccessCount(rs.getInt(ACCESSCOUNT));
					songList.setIntroduce(rs.getString(INTRODUCE));
					songList.setCollectionCount(rs.getInt(COLLECTIONCOUNT));
					songList.setTags(rs.getString(TAGS));
					songList.setSongListStateId(rs.getInt(SONGLISTSTATEID));
					songList.setSongListImg(rs.getString(SONGLISTIMG));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				songList = null;
			}finally{
				close(connection, pstmt, rs);
			}
		}else {
			songList = null;
		}
		
		return songList;
	}
	//根据用户歌单收藏表，查询对应的歌单信息
	public ArrayList<SongList> selectSongListOfUserWithSongList(int userId){
		//调用查询收藏歌单的方法
		ArrayList<UserWithSongList> userWithSongListArrayList= selectAllUserWithSongList(userId);//主要或去收藏的歌单编号
		System.out.println("用户收藏歌单是否为空：" + userWithSongListArrayList);
		//存放歌单信息对象（用户收藏的歌单信息）
		ArrayList<SongList> songListOfUserWithSongList = new ArrayList<SongList>();
		//遍历集合，根据songListId（歌单编号）来查询相应的歌单信息，并返回歌单信息集合
		for (int i = 0; i < userWithSongListArrayList.size(); i++) {
			int songListId = userWithSongListArrayList.get(i).getSongListId();
			SongList songList = new SongList();
			songList = selectSongListOfSongListId(songListId);
			songListOfUserWithSongList.add(songList);
		}
		return songListOfUserWithSongList; 
	}
	//用户收藏歌单
	public boolean userCollectionSongList(UserWithSongList userWithSongList) {
		Connection connection = getConnection();
		int row = 0;//记录添加数据时返回的行数
		boolean addOrNot = false;
		if (connection != null) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "insert into userwithsonglist(userId, songListId, collectionDate) values(?, ?, ?)";
				pstmt = connection.prepareStatement(sql);
				pstmt.setInt(1, userWithSongList.getUserId());
				pstmt.setInt(2, userWithSongList.getSongListId());
				pstmt.setString(3, userWithSongList.getCollectionDate());
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
	//删除用户收藏歌单
	public boolean deleteUserWithSongList(int userId, int songListId) {
		Connection connection = getConnection();
		int row = 0;
		boolean deleteOrNot = false;
		if (connection != null) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "delete from userwithsonglist where userId = ? and songListId = ?";
				pstmt = connection.prepareStatement(sql);
				pstmt.setInt(1, userId);
				pstmt.setInt(2, songListId);
				row = pstmt.executeUpdate();
				if (row > 0) {
					deleteOrNot = true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				deleteOrNot = false;
			}finally{
				close(connection, pstmt, rs);
			}
		}
		return deleteOrNot;
	}
	
	//根据用户编号，歌单编号查询用户是否已经收藏该歌单
	public boolean userCollectionSongListOrNot(int userId, int songListId){
		//如果查到数据，表示已经收藏，返回false
		Connection connection = getConnection();
		boolean collectionOrNot = true;
		if (connection != null) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				String sql = "select * from userwithsonglist where userId = ? and songListId = ?";
				pstmt = connection.prepareStatement(sql);
				pstmt.setInt(1, userId);
				pstmt.setInt(2, songListId);
				rs = pstmt.executeQuery();
				if (rs != null && rs.next()) {
					collectionOrNot = false;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				collectionOrNot = false;
			}finally{
				close(connection, pstmt, rs);
			}
			
		}else {
			collectionOrNot = false;
		}
		return collectionOrNot;
	}
}
