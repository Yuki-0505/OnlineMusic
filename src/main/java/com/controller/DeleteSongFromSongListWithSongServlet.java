package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.Song;
import com.entity.SongListWithSong;
import com.service.impl.SongListWithSongServiceImpl;
import com.service.SongListWithSongServiceInter;
import com.service.impl.SongServiceImpl;
import com.service.SongServiceInter;

/**
 * 把歌单中的歌曲从歌单中移除
 * @author 29207
 *
 */
public class DeleteSongFromSongListWithSongServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String songIdString = request.getParameter("songId");
		String songListIdString = request.getParameter("songListId");
		int songId = Integer.parseInt(songIdString);//歌曲编号
		int songListId = Integer.parseInt(songListIdString);//歌单编号
		SongListWithSong songListWithSong = new SongListWithSong();
		songListWithSong.setSongId(songId);
		songListWithSong.setSongListId(songListId);
		//歌曲收藏次数-1
		SongServiceInter songService = new SongServiceImpl();
		Song song = songService.selectSongOfSongId(songId);
		int collectionCount = song.getCollectionCount();
		boolean cutDownSongCollCount = true;
		if (collectionCount > 0) {
			cutDownSongCollCount = songService.cutDownSongCollCount(songId);
		}
		if (cutDownSongCollCount) {
			//创建移除歌曲的服务对象
			SongListWithSongServiceInter songListWithSongService = new SongListWithSongServiceImpl();
			//调用删除方法
			boolean deleteOrNot = songListWithSongService.deleteSongFromSongListWithSong(songListWithSong);//返回true表示删除成功
			out.print(deleteOrNot);
		}else {
			out.print(cutDownSongCollCount);
		}
		
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}
}
