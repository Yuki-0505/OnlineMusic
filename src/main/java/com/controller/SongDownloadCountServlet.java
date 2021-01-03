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
import com.service.impl.CdServiceImpl;
import com.service.CdServiceInter;
import com.service.impl.SingerServiceImpl;
import com.service.SingerServiceInter;
import com.service.impl.SongServiceImpl;
import com.service.SongServiceInter;
/*下载次数前50名歌曲，排行榜*/
public class SongDownloadCountServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		//调用查询歌曲的服务
		SongServiceInter songService = new SongServiceImpl();
		ArrayList<Song> songArrayList = songService.songArrayListDownloadCount();
		CdServiceInter cdService = new CdServiceImpl();
		SingerServiceInter singerService = new SingerServiceImpl();
		
		JSONArray songInfoArray = new JSONArray();
		for (Song song : songArrayList) {
			int songId = song.getSongId();
			String songName = song.getSongName();
			String songUrl = song.getSongUrl();
			double songTime = song.getSongTime();
			
			JSONObject songInfoJsonObject = new JSONObject();
			songInfoJsonObject.put("songId", songId);
			songInfoJsonObject.put("songName", songName);
			songInfoJsonObject.put("songUrl", songUrl);
			songInfoJsonObject.put("songTime", songTime);
			//获取专辑编号
			int cdId = song.getCDId();
			//获取专辑信息
			
			Cd cd = cdService.selectCdOfCdId(cdId);
			String cdName = cd.getCDName();
			
			JSONObject cdJsonObject = new JSONObject();
			cdJsonObject.put("CDId", cdId);
			cdJsonObject.put("CDName", cdName);
			//获取歌手编号
			int singerId = song.getSingerId();
			//获取歌手信息
			Singer singer = singerService.selectSingerOfSingerId(singerId);
			String singerName = singer.getSingerName();
			
			JSONObject singerJsonObject = new JSONObject();
			singerJsonObject.put("singerId", singerId);
			singerJsonObject.put("singerName", singerName);
			
			JSONObject oneSongAllInfo = new JSONObject();
			oneSongAllInfo.put("song", songInfoJsonObject);
			oneSongAllInfo.put("cd", cdJsonObject);
			oneSongAllInfo.put("singer", singerJsonObject);
			
			songInfoArray.add(oneSongAllInfo);
		}
		
		int songInfoArrLen = songInfoArray.size();
		if (songInfoArrLen > 0) {
			out.print(songInfoArray);
		}else {
			out.print("300");//没有数据
		}
	}
}
