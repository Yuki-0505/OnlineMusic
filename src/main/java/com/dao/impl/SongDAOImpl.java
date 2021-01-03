package com.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.dao.SongDAOInter;
import com.entity.Song;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class SongDAOImpl extends BaseDAO implements SongDAOInter {
    public Song selectSongOfSongId(int songId) {
        Connection connection = getConnection();
        Song song = new Song();
        if (connection != null) {
            PreparedStatement pstmt = null;
            ResultSet rs = null;

            try {
                String sql = "select * from song where songId = ?";
                pstmt = connection.prepareStatement(sql);
                pstmt.setInt(1, songId);
                rs = pstmt.executeQuery();
                if (rs != null && rs.next()) {
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
                } else {
                    song = null;
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                song = null;
            } finally {
                close(connection, pstmt, rs);
            }
        } else {
            song = null;
        }
        return song;
    }

    //根据歌曲名称模糊查询
    public ArrayList<Song> selectSongOfSongName(String songName) {
        Connection connection = getConnection();
        ArrayList<Song> songsOfSongName = new ArrayList<Song>();

        if (connection != null) {
            PreparedStatement pstmt = null;
            ResultSet rs = null;

            try {
                String sql = "select songId from song where songName like ?";
                pstmt = connection.prepareStatement(sql);
                pstmt.setString(1, songName);
                rs = pstmt.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        Song song = new Song();
                        song.setSongId(rs.getInt(SONGID));
                        songsOfSongName.add(song);
                    }
                } else {
                    songsOfSongName = null;
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                songsOfSongName = null;
            } finally {
                close(connection, pstmt, rs);
            }
        } else {
            songsOfSongName = null;
        }
        return songsOfSongName;
    }

    //增加歌曲收藏次数（用户添加歌曲到歌单，就把歌曲的收藏次数+1）
    public boolean upSongCollectionCount(int songId) {
        Connection connection = getConnection();
        int row = 0;
        boolean upSongCollCountOrNot = false;
        if (connection != null) {
            PreparedStatement pstmt = null;
            ResultSet rs = null;

            try {
                String sql = "update song set collectionCount = (collectionCount + 1) where songId = ?";
                pstmt = connection.prepareStatement(sql);
                pstmt.setInt(1, songId);

                row = pstmt.executeUpdate();
                if (row > 0) {
                    upSongCollCountOrNot = true;
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                close(connection, pstmt, rs);
            }
        }
        return upSongCollCountOrNot;
    }

    //减少歌曲收藏次数
    public boolean cutDownSongCollCount(int songId) {
        Connection connection = getConnection();
        int row = 0;
        boolean cutDownSongCollCountOrNot = false;
        if (connection != null) {
            PreparedStatement pstmt = null;
            ResultSet rs = null;

            try {
                String sql = "update song set collectionCount = (collectionCount - 1) where songId = ?";
                pstmt = connection.prepareStatement(sql);
                pstmt.setInt(1, songId);

                row = pstmt.executeUpdate();
                if (row > 0) {
                    cutDownSongCollCountOrNot = true;
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                close(connection, pstmt, rs);
            }
        }
        return cutDownSongCollCountOrNot;
    }

    //增加歌曲播放次数
    public boolean upSongPlayCount(int songId) {
        Connection connection = getConnection();
        int row = 0;
        boolean upSongPlayCountOrNot = false;
        if (connection != null) {
            PreparedStatement pstmt = null;
            ResultSet rs = null;

            try {
                String sql = "update song set playCount = (playCount + 1) where songId = ?";
                pstmt = connection.prepareStatement(sql);
                pstmt.setInt(1, songId);

                row = pstmt.executeUpdate();
                if (row > 0) {
                    upSongPlayCountOrNot = true;
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                close(connection, pstmt, rs);
            }
        }
        return upSongPlayCountOrNot;
    }

    //歌曲热度查询（播放次数降序查询）50首
    public ArrayList<Song> songArrayListPlayCount() {
        Connection connection = getConnection();
        ArrayList<Song> songArrayList = new ArrayList<Song>();

        if (connection != null) {
            Statement statement = null;
            ResultSet rs = null;

            try {
                String sql = "select * from song ORDER BY playCount desc LIMIT 0,50";
                statement = (Statement) connection.createStatement();
                rs = statement.executeQuery(sql);
                if (rs != null) {
                    while (rs.next()) {
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
                        songArrayList.add(song);
                    }
                } else {
                    songArrayList = null;
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                songArrayList = null;
            } finally {
                close(connection, null, rs);
            }
        }
        return songArrayList;
    }

    //歌曲下载查询（下载量，降序）50首
    public ArrayList<Song> songArrayListDownloadCount() {
        Connection connection = getConnection();
        ArrayList<Song> songArrayList = new ArrayList<Song>();

        if (connection != null) {
            Statement statement = null;
            ResultSet rs = null;

            try {
                String sql = "select * from song ORDER BY downloadCount desc LIMIT 0,50";
                statement = (Statement) connection.createStatement();
                rs = statement.executeQuery(sql);
                if (rs != null) {
                    while (rs.next()) {
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
                        songArrayList.add(song);
                    }
                } else {
                    songArrayList = null;
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                songArrayList = null;
            } finally {
                close(connection, null, rs);
            }
        }
        return songArrayList;
    }

    //歌曲收藏榜单
    public ArrayList<Song> songArrayListCollectionCount() {
        Connection connection = getConnection();
        ArrayList<Song> songArrayList = new ArrayList<Song>();

        if (connection != null) {
            Statement statement = null;
            ResultSet rs = null;

            try {
                String sql = "select * from song ORDER BY collectionCount desc LIMIT 0,50";
                statement = (Statement) connection.createStatement();
                rs = statement.executeQuery(sql);
                if (rs != null) {
                    while (rs.next()) {
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
                        songArrayList.add(song);
                    }
                } else {
                    songArrayList = null;
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                songArrayList = null;
            } finally {
                close(connection, null, rs);
            }
        }
        return songArrayList;
    }

    //分页查询所有歌曲
    public ArrayList<Song> selectSongLimit(int startRowsNum, int onePageRows) {
        Connection connection = getConnection();
        ArrayList<Song> songLimitArrayList = new ArrayList<Song>();

        if (connection != null) {
            PreparedStatement pstmt = null;
            ResultSet rs = null;

            try {
                String sql = "select * from song limit ?, ?";
                pstmt = connection.prepareStatement(sql);
                pstmt.setInt(1, startRowsNum);
                pstmt.setInt(2, onePageRows);
                rs = pstmt.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
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
                        songLimitArrayList.add(song);
                    }
                } else {
                    songLimitArrayList = null;
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                songLimitArrayList = null;
            } finally {
                close(connection, pstmt, rs);
            }
        } else {
            songLimitArrayList = null;
        }
        return songLimitArrayList;
    }

    //查询所有歌曲行数，用于分页
    public int selectSongRowsNum() {
        Connection connection = getConnection();
        int songRowsNum = 0;
        if (connection != null) {
            Statement statement = null;
            ResultSet rs = null;

            try {
                String sql = "select songId from song";
                statement = (Statement) connection.createStatement();
                rs = statement.executeQuery(sql);
                if (rs != null) {
                    while (rs.next()) {
                        songRowsNum = rs.getRow();
                    }
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                close(connection, null, rs);
            }
        }
        return songRowsNum;
    }

    //修改歌曲链接
    public boolean updateSongSongUrl(int songId, String songUrl) {
        Connection connection = getConnection();
        boolean updateOrNot = false;
        int row = 0;
        if (connection != null) {
            PreparedStatement pstmt = null;
            ResultSet rs = null;

            try {
                String sql = "update song set songUrl = ? where songId = ?";
                pstmt = connection.prepareStatement(sql);
                pstmt.setString(1, songUrl);
                pstmt.setInt(2, songId);

                row = pstmt.executeUpdate();
                if (row > 0) {
                    updateOrNot = true;
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                close(connection, pstmt, rs);
            }

        }
        return updateOrNot;

    }

    //修改歌曲（暂只限修改歌曲名和语种）
    public boolean updateSongInfo(Song song) {
        Connection connection = getConnection();
        boolean updateOrNot = false;
        int row = 0;
        if (connection != null) {
            PreparedStatement pstmt = null;
            ResultSet rs = null;

            try {
                String sql = "update song set songName = ?, language = ? where songId = ?";
                pstmt = connection.prepareStatement(sql);
                pstmt.setString(1, song.getSongName());
                pstmt.setString(2, song.getLanguage());
                pstmt.setInt(3, song.getSongId());

                row = pstmt.executeUpdate();
                if (row > 0) {
                    updateOrNot = true;
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                close(connection, pstmt, rs);
            }

        }
        return updateOrNot;
    }

    //上传歌曲
    public boolean uploadSong(Song song) {
        Connection connection = getConnection();
        boolean updateOrNot = false;
        int row = 0;
        if (connection != null) {
            PreparedStatement pstmt = null;
            ResultSet rs = null;

            String sql = "insert into song (songName,singerId,CDId,songUrl) values(?,?,?,?)";
            try {
                pstmt = connection.prepareStatement(sql);
                pstmt.setString(1, song.getSongName());
                pstmt.setInt(2, song.getSingerId());
                pstmt.setInt(2, song.getCDId());
                pstmt.setString(2, song.getSongUrl());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                close(connection, pstmt, rs);
            }

//			try {
//				String sql = "update song set songUrl = ? where songId = ?";
//				pstmt = connection.prepareStatement(sql);
//				pstmt.setString(1, songUrl);
//				pstmt.setInt(2, songId);
//
//				row = pstmt.executeUpdate();
//				if (row > 0) {
//					updateOrNot = true;
//				}
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}finally{
//				close(connection, pstmt, rs);
//			}

        }
        return updateOrNot;
    }
}
