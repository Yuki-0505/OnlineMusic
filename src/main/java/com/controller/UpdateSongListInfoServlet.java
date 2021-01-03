package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.SongList;
import com.service.impl.SongListServiceImpl;
import com.service.SongListServiceInter;

public class UpdateSongListInfoServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String songListIdString = request.getParameter("songListId");
		int songListId = Integer.parseInt(songListIdString);//歌单编号
		String userIdString = request.getParameter("userId");
		int userId = Integer.parseInt(userIdString);//用户编号
		String songListName = request.getParameter("songListName");//新修改的歌单名
		String tags = request.getParameter("tags");//标签
		String introduce = request.getParameter("introduce");//歌单简介
		SongList songList = new SongList();
		songList.setSongListId(songListId);//设置歌单编号
		songList.setUserId(userId);//设置用户编号
		songList.setSongListName(songListName);//设置歌单名
		songList.setTags(tags);//设置歌单标签
		songList.setIntroduce(introduce);//设置歌单简介
		SongListServiceInter songListService = new SongListServiceImpl();
		//调用修改歌单信息的方法
		boolean updateOrNot = songListService.updateSongList(songList);
		out.print(updateOrNot);//返回true表示修改成功，返回false表示修改失败
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
