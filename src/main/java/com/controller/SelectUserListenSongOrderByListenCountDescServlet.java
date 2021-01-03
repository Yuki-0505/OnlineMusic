package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.entity.Cd;
import com.entity.Singer;
import com.entity.Song;
import com.entity.UserListenSongCountView;
import com.service.impl.CdServiceImpl;
import com.service.CdServiceInter;
import com.service.impl.SingerServiceImpl;
import com.service.SingerServiceInter;
import com.service.impl.SongServiceImpl;
import com.service.SongServiceInter;
import com.service.impl.UserListenSongCountViewServiceImpl;
import com.service.UserListenSongCountViewServiceInter;

/*降序查询歌曲播放次数视图*/
public class SelectUserListenSongOrderByListenCountDescServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String userIdString = request.getParameter("userId");
		
		int userId = Integer.parseInt(userIdString);
		
		SongServiceInter songService = new SongServiceImpl();
		SingerServiceInter singerService = new SingerServiceImpl();
		CdServiceInter cdService = new CdServiceImpl();
		//查询播放次数视图
		UserListenSongCountViewServiceInter userListenSongCountViewService = new UserListenSongCountViewServiceImpl();
		//查询方法
		ArrayList<UserListenSongCountView> userListenSongCountViewOrderByCountDesc = userListenSongCountViewService.selectUserListenSongCountViewsOrderByListenCountDesc(userId);
		JSONArray allSongInfo = new JSONArray();
		for (UserListenSongCountView userListenSongCountView : userListenSongCountViewOrderByCountDesc) {
			JSONObject oneSongJsonObject = new JSONObject();
			int songId = userListenSongCountView.getSongId();//获取歌曲编号
			//歌曲播放次数
			int listenSongCount = userListenSongCountView.getListenSongCount();
			//根据歌曲编号，查询歌曲信息
			Song song = songService.selectSongOfSongId(songId);//查询到的歌曲信息
			String songName = song.getSongName();//歌曲名
			String songUrl = song.getSongUrl();//歌曲地址
			
			JSONObject songJsonObject = new JSONObject();
			songJsonObject.put("songId", songId);
			songJsonObject.put("songName", songName);
			songJsonObject.put("songUrl", songUrl);
			//歌手编号
			int singerId = song.getSingerId();
			//专辑编号
			int cdId = song.getCDId();
			//查询歌手信息
			Singer singer = singerService.selectSingerOfSingerId(singerId);
			String singerName = singer.getSingerName();
			
			JSONObject singerJsonObject = new JSONObject();
			singerJsonObject.put("singerId", singerId);
			singerJsonObject.put("singerName", singerName);
			//查询专辑信息
			Cd cd = cdService.selectCdOfCdId(cdId);
			
			String cdName = cd.getCDName();
			JSONObject cdJsonObject = new JSONObject();
			cdJsonObject.put("CDId", cdId);
			cdJsonObject.put("CDName", cdName);
			oneSongJsonObject.put("song", songJsonObject);
			oneSongJsonObject.put("singer", singerJsonObject);
			oneSongJsonObject.put("cd", cdJsonObject);
			oneSongJsonObject.put("listenCount", listenSongCount);
			allSongInfo.add(oneSongJsonObject);
		}
		System.out.println("获取用户听歌记录排行：" + allSongInfo);
		out.print(allSongInfo);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
