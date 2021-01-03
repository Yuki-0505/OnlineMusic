package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.entity.SongList;
import com.service.impl.SongListServiceImpl;
import com.service.SongListServiceInter;

public class AddSongListServlet extends HttpServlet {
	private SongListServiceInter songListService = new SongListServiceImpl();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("进来添加了嘛");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String userIdString = request.getParameter("userId");
		int userId = Integer.parseInt(userIdString);//用户编号
		String songListName = request.getParameter("songListName");//歌单名字
		System.out.println("用户编号：" + userId + "；" + "歌单名：" + songListName);
		boolean userCreateSongListOrNot = songListService.userCreateSongListOrNot(userId, songListName);
		System.out.println("是否已经创建： " + userCreateSongListOrNot);
		if (userCreateSongListOrNot) {
			System.out.println("是否金星秀行间");
			//为true表示可以创建
			SongList songList = new SongList();
			songList.setUserId(userId);//用户编号
			songList.setSongListName(songListName);//歌单名
			//调用创建歌单的服务
			
			boolean addOrNot = songListService.addSongList(songList);
			//返回boolean值，用于Ajax判断是否添加成功
			out.print(addOrNot);//返回true表示创建成功
		}else {
			//为false表示不能创建
			out.print(userCreateSongListOrNot);
		}
		
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
