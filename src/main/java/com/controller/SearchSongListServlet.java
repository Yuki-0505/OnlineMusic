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

import com.entity.SongList;
import com.entity.User;
import com.service.impl.SongListServiceImpl;
import com.service.SongListServiceInter;
import com.service.impl.UserServiceImpl;
import com.service.UserServiceInter;

public class SearchSongListServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String searchSongListName = request.getParameter("searchSongListName");
		System.out.println("搜索内容：" + searchSongListName);
		String songListName = "%" + searchSongListName + "%";
		System.out.println("搜索内容：" + songListName);
		
		//计算搜索到的总行数
		SongListServiceInter songListService = new SongListServiceImpl();
		ArrayList<SongList> AllSongListOfSongListName = songListService.selectAllSongListOfSongListName(songListName);
		int allSongListRowNum = AllSongListOfSongListName.size();//查询到的总行数
		
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
		int PageNum = allSongListRowNum % 25 == 0 ? allSongListRowNum / rowNum : (allSongListRowNum / rowNum) + 1;
		
		//调用搜索歌曲的方法
		ArrayList<SongList> searchSongListArr = songListService.selectSongListOfSongListName(songListName, startRow, rowNum);
		int searchSongListArrLen = searchSongListArr.size();
		System.out.println("搜索到歌单对象：" + searchSongListArrLen);
		JSONArray songListJsonArray = new JSONArray();
		UserServiceInter userService = new UserServiceImpl();
		for (SongList songList : searchSongListArr) {
			JSONObject songListJSONAndPage = new JSONObject();
			//根据用户编号，查询到该歌单用户信息
			int thisSongListUserId = songList.getUserId();
			//根据用户编号查询用户信息
			User user = userService.selectUserInfoOfUserId(thisSongListUserId);
			//获取该用户用户名称
			String createTheSongListUserName = user.getUserName();
			
			
			songListJSONAndPage.put("songListInfo", songList);
			songListJSONAndPage.put("PageNum", PageNum);//共几页
			songListJSONAndPage.put("startPage", startPage);//当前页
			songListJSONAndPage.put("searchSongListName", searchSongListName);//搜索关键字
			songListJSONAndPage.put("allSongListRowsNum", allSongListRowNum);//总行数
			songListJSONAndPage.put("createTheSongListUserName", createTheSongListUserName);//创建该歌单的用户的名称
			JSONObject songListJsonObject = JSONObject.fromObject(songListJSONAndPage);
			songListJsonArray.add(songListJsonObject);//推荐歌单JSON数组
		}
		System.out.println("搜索到的歌单：" + songListJsonArray.toString());
		if (searchSongListArrLen > 0) {
			out.print(songListJsonArray);
		}else {
			out.print("false");
		}
		
		
	}
}
