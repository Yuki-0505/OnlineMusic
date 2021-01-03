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
import com.service.impl.SongListServiceImpl;
import com.service.SongListServiceInter;

/*
 * 根据用户编号查询用户创建的歌单信息
 * ()
 */
public class SelectUserCreateSongList extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String userIdString = request.getParameter("userId");
		int userId = Integer.parseInt(userIdString);
		SongListServiceInter songListService = new SongListServiceImpl();
		ArrayList<SongList> songLists = songListService.selectAllSongList(userId);
		int songListNum = songLists.size();
		if (songListNum > 0) {
			JSONArray songListJsonArray = new JSONArray();
			for (SongList songList : songLists) {
				JSONObject songListJsonObject = JSONObject.fromObject(songList);
				songListJsonArray.add(songListJsonObject);
			}
			out.print(songListJsonArray);
		}else {
			out.print("null");//返回null，用户没有创建歌单
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}
}
