package com.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.dao.SongListWithSongDAOInter;
import com.dao.UserListenSongCountViewDAOInter;

import com.entity.SongListWithSong;
import com.entity.UserListenSongCountView;
import com.mysql.jdbc.Connection;

public class SongListWithSongDAOImpl extends BaseDAO implements SongListWithSongDAOInter {
	//根据歌曲编号，查询歌曲所属歌单编号（只用于推荐）返回的是用户听歌记录最多的歌曲所属的歌单
	public ArrayList<SongListWithSong> selectSongListWithSongOfSongId(int userId) {//主要是用里面的歌单编号，用于查询歌单信息
		Connection connection = getConnection();
		ArrayList<SongListWithSong> songListWithSongArrayList = new ArrayList<SongListWithSong>();
		if (connection != null) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				String sql = "select * from songlistwithsong where songId = ?";
				pstmt = connection.prepareStatement(sql);
				UserListenSongCountViewDAOInter UserListenSongCountViewDAO = new UserListenSongCountViewDAOImpl();
				ArrayList<UserListenSongCountView> userListenSongCountViews = UserListenSongCountViewDAO.selecteUserListenSongCountView(userId);
				if (userListenSongCountViews != null) {
					for (int i = 0; i < userListenSongCountViews.size(); i++) {
						int songId = userListenSongCountViews.get(i).getSongId();
						pstmt.setInt(1, songId);
						rs = pstmt.executeQuery();
						if (rs != null) {
							while (rs.next()) {
								SongListWithSong songListWithSong = new SongListWithSong();
								songListWithSong.setSlSongId(rs.getInt(SLSONGID));
								songListWithSong.setSongListId(rs.getInt(SONGLISTID));
								songListWithSong.setSongId(rs.getInt(SONGID));
								songListWithSong.setCollectionDate(rs.getString(COLLECTIONDATE));
								songListWithSongArrayList.add(songListWithSong);
							}
						}else {
							songListWithSongArrayList = null;
						}
					}
				}else {
					songListWithSongArrayList = null;
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				songListWithSongArrayList = null;
			}finally{
				close(connection, pstmt, rs);
			}
		}else {
			songListWithSongArrayList = null;
		}
		return songListWithSongArrayList;
	}
	//根据歌单编号，查询歌曲歌单表，返回对应歌单表中的歌曲编号
	public ArrayList<SongListWithSong> selectSongListWithSongOfSongLIstId(
			int songListId) {
		Connection connection = getConnection();
		ArrayList<SongListWithSong> songListWithSongArrList = new ArrayList<SongListWithSong>();
		if (connection != null) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				String sql= "select * from songlistwithsong where songListId = ?";
				pstmt = connection.prepareStatement(sql);
				pstmt.setInt(1, songListId);
				rs = pstmt.executeQuery();
				if (rs != null) {
					while (rs.next()) {
						SongListWithSong songListWithSong = new SongListWithSong();
						songListWithSong.setSlSongId(rs.getInt(SLSONGID));//歌曲歌单编号
						songListWithSong.setSongListId(rs.getInt(SONGLISTID));//歌单编号
						songListWithSong.setSongId(rs.getInt(SONGID));//歌曲编号，用此信息查询歌曲信息
						songListWithSong.setCollectionDate(rs.getString(COLLECTIONDATE));//收藏日期
						songListWithSongArrList.add(songListWithSong);
					}
				}else {
					songListWithSongArrList = null;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				songListWithSongArrList = null;
			}finally{
				close(connection, pstmt, rs);
			}
		}else {
			songListWithSongArrList = null;
		}
		return songListWithSongArrList;
	}
	//添加信息，歌曲添加到歌单
	public boolean addSongListWithSong(SongListWithSong songListWithSong){
		Connection connection = getConnection();
		int row = 0;
		boolean addOrNot = false;
		if (connection != null) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "insert into songlistwithsong(songListId, songId, collectionDate) values(?, ?, ?)";
				pstmt = connection.prepareStatement(sql);
				pstmt.setInt(1, songListWithSong.getSongListId());
				pstmt.setInt(2, songListWithSong.getSongId());
				pstmt.setString(3, songListWithSong.getCollectionDate());
				row = pstmt.executeUpdate();
				if (row > 0) {
					addOrNot = true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				addOrNot = false;
			}finally{
				close(connection, pstmt, rs);
			}
		}
		return addOrNot;
	}
	
	//根据歌单编号，歌曲编号，查询表中是否已经存在数据，false表示已经存在，true表示不存在
	public boolean selectSongListWithSongToSongListIdAndSongId(SongListWithSong songListWithSong){
		Connection connection = getConnection();
		boolean existOrNot = true;//初始值为true，表示不存在该数据，可以进行添加
		if (connection != null) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "select * from songlistwithsong where songListId = ? and songId = ?";
				pstmt = connection.prepareStatement(sql);
				pstmt.setInt(1, songListWithSong.getSongListId());
				pstmt.setInt(2, songListWithSong.getSongId());
				rs = pstmt.executeQuery();
				if (rs != null && rs.next()) {
					System.out.println("查询成功了吗？？？？？？？？？");
					existOrNot = false;//数据已经存在，设置值为false，表示不可以添加
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				existOrNot = false;
			}finally{
				close(connection, pstmt, rs);
			}
		}else {
			existOrNot = false;
		}
		return existOrNot;//返回true表示可以添加数据，返回false表示不可以添加数据
	}
	//从歌单中删除歌曲
	public boolean deleteSongFromSongListWithSong(SongListWithSong songListWithSong){
		Connection connection = getConnection();
		int row = 0;
		boolean deleteOrNot = false;
		if (connection != null) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "delete from songlistwithsong where songListId = ? and songId = ?";
				pstmt = connection.prepareStatement(sql);
				pstmt.setInt(1, songListWithSong.getSongListId());
				pstmt.setInt(2, songListWithSong.getSongId());
				
				row = pstmt.executeUpdate();
				if (row > 0) {
					deleteOrNot = true;//返回true，表示删除成功
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
	
	//根据歌曲编号，查询歌单编号
	public ArrayList<SongListWithSong> selectSongListIdFromSongListWithSongOfSongId(int songId){
		Connection connection = getConnection();
		ArrayList<SongListWithSong> songListWithSongs = new ArrayList<SongListWithSong>();
		if (connection != null) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "select songListId from songlistwithsong where songId = ?";
				pstmt = connection.prepareStatement(sql);
				pstmt.setInt(1, songId);
				rs = pstmt.executeQuery();
				if (rs != null) {
					while (rs.next()) {
						SongListWithSong songListWithSong = new SongListWithSong();
						songListWithSong.setSongListId(rs.getInt(SONGLISTID));
						songListWithSongs.add(songListWithSong);
					}
				}else {
					songListWithSongs = null;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				songListWithSongs = null;
			}finally{
				close(connection, pstmt, rs);
			}
		}else {
			songListWithSongs = null;
		}
		return songListWithSongs;
	}
}
