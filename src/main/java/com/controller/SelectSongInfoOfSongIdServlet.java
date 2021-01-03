package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.entity.Cd;
import com.entity.Singer;
import com.entity.Song;
import com.service.impl.CdServiceImpl;
import com.service.CdServiceInter;
import com.service.impl.SingerServiceImpl;
import com.service.SingerServiceInter;
import com.service.impl.SongServiceImpl;
import com.service.SongServiceInter;
/*根据歌曲编号查询歌曲信息*/
public class SelectSongInfoOfSongIdServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String songIdString = request.getParameter("songId");
		int songId = Integer.parseInt(songIdString);
		
		SongServiceInter songService = new SongServiceImpl();
		Song song = songService.selectSongOfSongId(songId);
		
		CdServiceInter cdService = new CdServiceImpl();
		int cdId = song.getCDId();//专辑编号
		Cd cd = cdService.selectCdOfCdId(cdId);
		String cdName = cd.getCDName();//专辑名
		
		SingerServiceInter singerService = new SingerServiceImpl();
		int singerId = song.getSingerId();//歌手编号
		Singer singer = singerService.selectSingerOfSingerId(singerId);
		String singerName = singer.getSingerName();//歌手姓名
		
		JSONObject songInfoOfSongIdJSON = new JSONObject();
		songInfoOfSongIdJSON.put("cdName", cdName);
		songInfoOfSongIdJSON.put("singerName", singerName);
		songInfoOfSongIdJSON.put("songInfo", song);
		System.out.println("歌曲信息：" + songInfoOfSongIdJSON);
		out.print(songInfoOfSongIdJSON);
	}
}
