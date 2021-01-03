package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.entity.SongList;
import com.service.impl.SongListServiceImpl;
import com.service.SongListServiceInter;
import com.service.impl.UserServiceImpl;
import com.service.UserServiceInter;

public class SelectSongListToAccessCount extends HttpServlet {
	private SongListServiceInter songListService = new SongListServiceImpl();
	private UserServiceInter userService = new UserServiceImpl();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		int songListStateId = 0;//歌单状态，0为公开
		ArrayList<SongList> songListToAccessCount = songListService.selectSongListOfAccessCount(songListStateId);
		int songListLength = songListToAccessCount.size();
		JSONObject allSongLIstObject = new JSONObject();
		int songListNum = 0;
		for (int i = 0; i < songListLength; i++) {//遍历歌单
			JSONObject oneSongList = new JSONObject();
			//查询创建歌单的用户信息
			int songListId = songListToAccessCount.get(i).getSongListId();//传到前端
			//浏览次数
			int accessCount = songListToAccessCount.get(i).getAccessCount();//传到前端
			//歌单名
			String songListName = songListToAccessCount.get(i).getSongListName();//传到前端
			//歌单图片URL
			String songListImg = songListToAccessCount.get(i).getSongListImg();//传到前端
			
			JSONObject songListInfoToHtml = new JSONObject();
			songListInfoToHtml.put("accessCount", accessCount);
			songListInfoToHtml.put("songListId", songListId);
			songListInfoToHtml.put("songListImg", songListImg);
			songListInfoToHtml.put("songListName", songListName);
			oneSongList.put("songListInfo", songListInfoToHtml);//添加一个歌单
			allSongLIstObject.put(songListNum, oneSongList);
			songListNum += 1;
		}
		System.out.println("============热门推荐==========");
		System.out.println(allSongLIstObject);
		out.print(allSongLIstObject);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
