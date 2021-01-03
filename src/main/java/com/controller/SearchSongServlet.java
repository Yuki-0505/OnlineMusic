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

import com.entity.Song;
import com.service.impl.SelectSongInfoAndCDNameAndSingerNameOfSongListIdServiceImpl;
import com.service.SelectSongInfoAndCDNameAndSingerNameOfSongListIdServiceInter;
import com.service.impl.SongServiceImpl;
import com.service.SongServiceInter;

public class SearchSongServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String searchSongName = request.getParameter("searchSongName");
		System.out.println("搜索内容：" + searchSongName);
		String songName = "%" + searchSongName + "%";
		System.out.println("搜索内容：" + songName);
		
		//计算搜索到的总行数
		SongServiceInter songService = new SongServiceImpl();
		ArrayList<Song> AllSongOfSongName = songService.selectSongOfSongName(songName);
		int allSongRowsNum = AllSongOfSongName.size();//查询到的总行数
		
		String startPageString = request.getParameter("startPage");//开始页
		int startPage = 1;
		if (startPageString != null && !startPageString.equals("")) {
			startPage = Integer.parseInt(startPageString);
		}
		
		//开始行
		int startRow = (startPage - 1) * 25;
		
		//每页显示行数
		int rowNum = 25;
		
		//计算共分为几页
		int PageNum = allSongRowsNum % 25 == 0 ? allSongRowsNum / rowNum : (allSongRowsNum / rowNum) + 1;
		
		//创建搜索歌曲的服务对象
		SelectSongInfoAndCDNameAndSingerNameOfSongListIdServiceInter selectSongInfoAndCDNameAndSingerNameOfSongListIdService = new SelectSongInfoAndCDNameAndSingerNameOfSongListIdServiceImpl();
		//调用搜索歌曲的方法
		ArrayList<Song> searchSongArr = selectSongInfoAndCDNameAndSingerNameOfSongListIdService.selectSongOfSongName(songName, startRow, rowNum);
		int searchSongArrLen = searchSongArr.size();
		System.out.println("搜索到歌曲对象：" + searchSongArrLen);
		JSONArray songJsonArray = new JSONArray();
		for (Song song : searchSongArr) {
			JSONObject songJSONAndPage = new JSONObject();
			songJSONAndPage.put("songInfo", song);
			songJSONAndPage.put("PageNum", PageNum);//共几页
			songJSONAndPage.put("startPage", startPage);//当前页
			songJSONAndPage.put("searchSongName", searchSongName);
			songJSONAndPage.put("allSongRowsNum", allSongRowsNum);//总行数
			JSONObject songJsonObject = JSONObject.fromObject(songJSONAndPage);
			songJsonArray.add(songJsonObject);//推荐歌单JSON数组
		}
		System.out.println("搜索到的歌曲：" + songJsonArray.toString());
		if (searchSongArrLen > 0) {
			out.print(songJsonArray);
		}else {
			out.print("false");
		}
	}
}
