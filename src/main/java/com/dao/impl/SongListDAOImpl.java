package com.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.dao.SongListDAOInter;
import com.dao.SongListWithSongDAOInter;
import com.entity.SongList;
import com.entity.SongListWithSong;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class SongListDAOImpl extends BaseDAO implements SongListDAOInter {
	//无条件查询所有歌但
	public ArrayList<SongList> findAllSongList() {
		Connection connection = getConnection();
		ArrayList<SongList> songLists = new ArrayList<SongList>();
		if (connection != null) {
			Statement statement = null;
			ResultSet rs = null;
			
			try {
				String sql = "select songListId from songlist";
				statement = (Statement) connection.createStatement();
				rs = statement.executeQuery(sql);
				if (rs != null) {
					while (rs.next()) {
						SongList songList = new SongList();
						songList.setSongListId(rs.getInt(SONGLISTID));
						songLists.add(songList);
					}
				}else {
					songLists = null;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				songLists = null;
			}
		}else {
			songLists = null;
		}
		return songLists;
	}
	public ArrayList<SongList> selectAllSongList(int userId) {//根据用户编号查询歌单信息，获取用户创建的歌单
		Connection connection = getConnection();
		ArrayList<SongList> songListArrayList = new ArrayList<SongList>();
		if (connection != null) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				String sql = "select * from songlist where userId = ?";
				pstmt = connection.prepareStatement(sql);
				pstmt.setInt(1, userId);
				rs = pstmt.executeQuery();
				if (rs != null) {
					while (rs.next()) {
						SongList songList = new SongList();
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
						songListArrayList.add(songList);
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				songListArrayList = null;
			}finally{
				close(connection, pstmt, rs);
			}
		}else {
			songListArrayList = null;
		}
		return songListArrayList;
	}
	//按照歌单热度降序查询所有歌单信息
	public ArrayList<SongList> selectSongListOfAccessCount(int songListStateId) {
		
		Connection connection = getConnection();
		ArrayList<SongList> songListOfAccessCountList = new ArrayList<SongList>();
		if (connection != null) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
				try {
					String sql = "select * from songlist where songListStateId = ? order by accessCount desc";
					pstmt = connection.prepareStatement(sql);
					pstmt.setInt(1, songListStateId);
					rs = pstmt.executeQuery();
					if (rs != null) {
						while (rs.next()) {
							SongList songList = new SongList();
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
							songListOfAccessCountList.add(songList);
						}
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					songListOfAccessCountList = null;
				}finally{
					close(connection, pstmt, rs);
				}
		}
		return songListOfAccessCountList;
	}
	
	//根据用户听歌记录查询到的歌单编号，查询歌单信息，用于基于用户听歌记录的歌单推荐，
	//根据用户听歌历史，查询到对应的歌单信息，并返回
	public ArrayList<SongList> selectSongListOfListenCount(int userId){
		Connection connection = getConnection();
		ArrayList<SongList> songListArrayList = new ArrayList<SongList>();
		if (connection != null) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "select * from songlist where songListId = ?";
				pstmt = connection.prepareStatement(sql);
				SongListWithSongDAOInter songListWithSongDAO = new SongListWithSongDAOImpl();
				ArrayList<SongListWithSong> songListWithSongs = songListWithSongDAO.selectSongListWithSongOfSongId(userId);
				if (songListWithSongs != null) {
					for (int i = 0; i < songListWithSongs.size(); i++) {
						int songListId = songListWithSongs.get(i).getSongListId();
						pstmt.setInt(1, songListId);
						rs = pstmt.executeQuery();
						if (rs != null && rs.next()) {
							SongList songList = new SongList();
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
							songListArrayList.add(songList);
						}else {
							songListArrayList = null;
						}
					}
				}else {
					songListArrayList = null;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				songListArrayList = null;
			}
		}else {
			songListArrayList = null;
		}
		return songListArrayList;
	}
	//用户添加歌单
	public boolean addSongList(SongList songList) {
		Connection connection = getConnection();
		boolean addOrNot = false;
		int row = 0;
		if (connection != null) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "insert into songlist(userId, songListName) values(?, ?)";
				pstmt = connection.prepareStatement(sql);
				pstmt.setInt(1, songList.getUserId());
				pstmt.setString(2, songList.getSongListName());
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
	//根据歌单标签查询歌单信息（模糊查询）
	public ArrayList<SongList> selectSongListOfTags(String tags, int userId) {
		Connection connection = getConnection();
		ArrayList<SongList> songListOfTagsArr = new ArrayList<SongList>();
		if (connection != null) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "select * from songlist where tags like ? and userId != ? and songListStateId = 0 order by accessCount desc";
				pstmt = connection.prepareStatement(sql);
				pstmt.setString(1, tags);
				pstmt.setInt(2, userId);
				rs = pstmt.executeQuery();
				if (rs != null) {
					while (rs.next()) {
						SongList songList = new SongList();
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
						songListOfTagsArr.add(songList);
					}
				}else {
					songListOfTagsArr = null;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				songListOfTagsArr = null;
			}
		}else {
			songListOfTagsArr = null;
		}
		return songListOfTagsArr;
	}
	//根据歌单编号查询歌单信息
	public SongList selectSongListOfSongListId(int songListId) {
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
				}else {
					songList = null;
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
	//歌单表热度+1
	public boolean updateSongListAccessCount(int songListId) {
		Connection connection = getConnection();
		int row = 0;
		boolean updateOrNot = true;
		if (connection != null) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "update songlist set accessCount = (accessCount + 1) where songListId = ?";
				pstmt = connection.prepareStatement(sql);
				pstmt.setInt(1, songListId);
				row = pstmt.executeUpdate();
				if (row > 0) {
					updateOrNot = true;
				}else {
					updateOrNot = false;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				updateOrNot = false;
			}finally{
				close(connection, pstmt, rs);
			}
 		}else {
			updateOrNot = false;
		}
		return updateOrNot;
	}
	//判断用户是否已经创建了该歌单
	public boolean userCreateSongListOrNot(int userId, String songListName) {
		Connection connection = getConnection();
		boolean userCreateOrNot = false;
		if (connection != null) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "select * from songlist where userId = ? and songListName = ?";
				pstmt = connection.prepareStatement(sql);
				pstmt.setInt(1, userId);
				pstmt.setString(2, songListName);
				rs = pstmt.executeQuery();
				if (rs != null && rs.next()) {
					userCreateOrNot = false;//为false是表示不能创建
				}else {
					userCreateOrNot = true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				userCreateOrNot = false;
			}finally{
				close(connection, pstmt, rs);
			}
 		}else {
			userCreateOrNot = false;
		}
		return userCreateOrNot;
	}
	//删除歌单
	public boolean deleteSongList(int userId, int songListId) {
		Connection connection = getConnection();
		int row = 0;
		boolean deleteOrNot = false;
		if (connection != null) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "delete from songlist where userId = ? and songListId = ?";
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
	//修改歌单
	public boolean updateSongList(SongList songList) {
		Connection connection = getConnection();
		boolean updateOrNot = false;
		int row = 0;
		if (connection != null) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				String sql = "update songlist set songListName = ?, introduce = ?, tags = ? where songListId = ? and userId = ?";
				pstmt = connection.prepareStatement(sql);
				pstmt.setString(1, songList.getSongListName());
				pstmt.setString(2, songList.getIntroduce());
				pstmt.setString(3, songList.getTags());
				pstmt.setInt(4, songList.getSongListId());
				pstmt.setInt(5, songList.getUserId());
				
				row = pstmt.executeUpdate();
				if (row > 0) {
					updateOrNot = true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				updateOrNot = false;
			}finally{
				close(connection, pstmt, rs);
			}
		}
		return updateOrNot;
	}
	
	//分页查询所有歌单，并按照访问量（热度）降序排列，，，，传入参数，fromRowNum从第几行开始，selectRowNum每次查询几行
	public ArrayList<SongList> songListLimitToAccessCount(String tags, int fromRowNum, int selectRowNum){
		Connection connection = getConnection();
		ArrayList<SongList> songListArrLimit = new ArrayList<SongList>();
		if (connection != null) {
			PreparedStatement pstme = null;
			ResultSet rs = null;
			
			try {
				String sql = "select * from songlist where ifnull(tags, '') like ? ORDER BY accessCount desc limit ?,?";
				pstme = connection.prepareStatement(sql);
				pstme.setString(1, tags);
				pstme.setInt(2, fromRowNum);//从第几行开始
				pstme.setInt(3, selectRowNum);//每页显示数量
				
				rs = pstme.executeQuery();
				if (rs != null) {
					while (rs.next()) {
						SongList songList = new SongList();
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
						songListArrLimit.add(songList);
					}
				}else {
					songListArrLimit = null;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				songListArrLimit = null;
			}finally{
				close(connection, pstme, rs);
			}
 		}else {
 			songListArrLimit = null;
		}
		return songListArrLimit;
	}
	
	//查询含有某个标签的所有歌单
	public ArrayList<SongList> selectAllSongListOfTags(String tags){
		Connection connection = getConnection();
		ArrayList<SongList> songLists = new ArrayList<SongList>();
		System.out.println("传入的标签值：" + tags);
		if (connection != null) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "select * from songlist where ifnull(tags, '') like ?";
				pstmt = connection.prepareStatement(sql);
				pstmt.setString(1, tags);
				rs = pstmt.executeQuery();
				if (rs != null) {
					while (rs.next()) {
						SongList songList = new SongList();
						songList.setSongListId(rs.getInt(SONGLISTID));
						songLists.add(songList);
					}
				}else {
					songLists = null;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				songLists = null;
			}finally{
				close(connection, pstmt, rs);
			}
		}else {
			songLists = null;
		}
		
		
		return songLists;
	
	}
	
	//根据歌单名称模糊查询(分页)
	public ArrayList<SongList> selectSongListOfSongListName(String songListName, int startRow, int rowsNum){
		Connection connection = getConnection();
		ArrayList<SongList> songListArrayList = new ArrayList<SongList>();
		if (connection != null) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "select * from songlist where songListName like ? and songListStateId = 0 limit ?, ?";
				pstmt = connection.prepareStatement(sql);
				
				pstmt.setString(1, songListName);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, rowsNum);
				rs = pstmt.executeQuery();
				if (rs != null) {
					while (rs.next()) {
						SongList songList = new SongList();
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
						songListArrayList.add(songList);
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				songListArrayList = null;
			}finally{
				close(connection, pstmt, rs);
			}
			
		}else {
			songListArrayList = null;
		}
		return songListArrayList;
	}
	//根据歌单名称模糊查询（所有）
	public ArrayList<SongList> selectAllSongListOfSongListName(String songListName){
		Connection connection = getConnection();
		ArrayList<SongList> songListArrayList = new ArrayList<SongList>();
		if (connection != null) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "select * from songlist where songListName like ? and songListStateId = 0";
				pstmt = connection.prepareStatement(sql);
				pstmt.setString(1, songListName);
				rs = pstmt.executeQuery();
				if (rs != null) {
					while (rs.next()) {
						SongList songList = new SongList();
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
						songListArrayList.add(songList);
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				songListArrayList = null;
			}finally{
				close(connection, pstmt, rs);
			}
			
		}else {
			songListArrayList = null;
		}
		return songListArrayList;
	}
	//歌单收藏次数+1
	public boolean addSongListCollectionCount(int songListId){
		Connection connection = getConnection();
		int row = 0;
		boolean addOrNot = false;
		if (connection != null) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "update songlist set collectionCount = (collectionCount + 1) where songlistId = ?";
				pstmt = connection.prepareStatement(sql);
				pstmt.setInt(1, songListId);
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
	
	//用户删除收藏歌单时，歌单收藏次数-1
	public boolean cutDownCollectionCount(int songListId){
		Connection connection = getConnection();
		int row = 0;
		boolean cutDownOrNot = false;
		if (connection != null) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "update songlist set collectionCount = (collectionCount - 1) where songlistId = ?";
				pstmt = connection.prepareStatement(sql);
				pstmt.setInt(1, songListId);
				row = pstmt.executeUpdate();
				if (row > 0) {
					cutDownOrNot = true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				close(connection, pstmt, rs);
			}
			
		}
		return cutDownOrNot;
	}
	//上传/修改歌单封面图片
	public boolean updateSongListImg(String songListImg, int songListId){
		Connection connection = getConnection();
		int row = 0;
		boolean updateOrNot = false;
		if (connection != null) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "update songlist set songListImg = ? where songListId = ?";
				pstmt = connection.prepareStatement(sql);
				pstmt.setString(1, songListImg);
				pstmt.setInt(2, songListId);
				
				row = pstmt.executeUpdate();
				if (row > 0) {
					updateOrNot = true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				close(connection, pstmt, rs);
			}
		}
		return updateOrNot;
	}
}
