package com.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.dao.SongInfoAndSingerInfoAndCDInfoInter;
import com.entity.Cd;
import com.entity.Singer;
import com.entity.Song;
import com.entity.SongListWithSong;
import com.mysql.jdbc.Connection;
import com.service.impl.CdServiceImpl;
import com.service.CdServiceInter;
import com.service.impl.SingerServiceImpl;
import com.service.SingerServiceInter;
import com.service.impl.SongListWithSongServiceImpl;
import com.service.SongListWithSongServiceInter;

public class SongInfoAndSingerInfoAndCDInfoImpl extends BaseDAO implements SongInfoAndSingerInfoAndCDInfoInter {
	public ArrayList<Song> selectSongAndCdNameAndSingerName(int songListId) {
		Connection connection = getConnection();
		int row = 0;
		ArrayList<Song> songArrList = new ArrayList<Song>();
		if (connection != null) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				SongListWithSongServiceInter songListWithSongService = new SongListWithSongServiceImpl();
				ArrayList<SongListWithSong> songListWithSongArrayList = songListWithSongService.selectSongListWithSongOfSongLIstId(songListId);
				//查询了song表中的全部信息，cd表中的CDName信息，singer表中的singerName信息
				String sql = "select * from song,(select CDName from cd where CDId = (select CDId from song where songId = ?)) cdInfo,(select singerName from singer where singerId = (select singerId from song where songId = ?)) serInfo where songId = ?";
				for (int i = 0; i < songListWithSongArrayList.size(); i++) {
					int songId = songListWithSongArrayList.get(i).getSongId();
					pstmt = connection.prepareStatement(sql);
					pstmt.setInt(1, songId);
					pstmt.setInt(2, songId);
					pstmt.setInt(3, songId);
					rs = pstmt.executeQuery();
					if (rs != null && rs.next()) {
						Song song = new Song();
						song.setSongId(rs.getInt(SONGID));
						song.setSongName(rs.getString(SONGNAME));
						song.setSingerId(rs.getInt(SINGERID));
						song.setCDId(rs.getInt(CDID));
						song.setLanguage(rs.getString(LANGUAGE));
						song.setPlayCount(rs.getInt(PLAYCOUNT));
						song.setDownloadCount(rs.getInt(DOWNLOADCOUNT));
						song.setCollectionCount(rs.getInt(COLLECTIONCOUNT));
						song.setPublishDate(rs.getString(PUBLISHDATE));
						song.setSongUrl(rs.getString(SONGURL));
						song.setCyricUrl(rs.getString(CYRICURL));
						song.setSongTime(rs.getDouble(SONGTIME));
						song.setTypeId(rs.getInt(TYPEID));
						song.setSingerName(rs.getString(SINGERNAME));
						song.setCDName(rs.getString(CDNAME));
						songArrList.add(song);
					}
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				songArrList = null;
			}finally{
				close(connection, pstmt, rs);
			}
		}else {
			songArrList = null;
		}
		return songArrList;
	}
	//模糊查询（根据歌曲名称）用于搜索
	public ArrayList<Song> selectSongOfSongName(String songName, int startRow, int rowNum){
		Connection connection = getConnection();
		ArrayList<Song> songsOfSongName = new ArrayList<Song>();
		
		if (connection != null) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "select * from song where songName like ? limit ?, ?";
				pstmt = connection.prepareStatement(sql);
				pstmt.setString(1, songName);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, rowNum);
				rs = pstmt.executeQuery();
				if (rs != null) {
					//专辑查询服务类创建对象
					CdServiceInter cdService = new CdServiceImpl();
					//歌手查询服务类创建对象
					SingerServiceInter singerService = new SingerServiceImpl();
					while (rs.next()) {
						Song song = new Song();
						int cdId = rs.getInt(CDID);//该歌曲所属专辑编号
						//根据专辑编号查询专辑
						Cd cd = cdService.selectCdOfCdId(cdId);
						
						int singerId = rs.getInt(SINGERID);//该歌曲歌手编号
						//根据歌手编号查询歌手
						Singer singer = singerService.selectSingerOfSingerId(singerId);
						song.setSongId(rs.getInt(SONGID));
						song.setSongName(rs.getString(SONGNAME));
						song.setSingerId(rs.getInt(SINGERID));
						song.setCDId(rs.getInt(CDID));
						song.setLanguage(rs.getString(LANGUAGE));
						song.setPlayCount(rs.getInt(PLAYCOUNT));
						song.setDownloadCount(rs.getInt(DOWNLOADCOUNT));
						song.setCollectionCount(rs.getInt(COLLECTIONCOUNT));
						song.setPublishDate(rs.getString(PUBLISHDATE));
						song.setSongUrl(rs.getString(SONGURL));
						song.setCyricUrl(rs.getString(CYRICURL));
						song.setSongTime(rs.getDouble(SONGTIME));
						song.setTypeId(rs.getInt(TYPEID));
						song.setSingerName(singer.getSingerName());
						song.setCDName(cd.getCDName());
						songsOfSongName.add(song);
					}
				}else {
					songsOfSongName = null;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				songsOfSongName = null;
			}finally{
				close(connection, pstmt, rs);
			}
		}else {
			songsOfSongName = null;
		}
		return songsOfSongName;
	}
}
