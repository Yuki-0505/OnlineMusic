package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.service.impl.SongListServiceImpl;
import com.service.SongListServiceInter;
 
public class UserCreateTheSongListOrNotServlet extends HttpServlet {
	private SongListServiceInter songListService = new SongListServiceImpl();
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String userIdString = request.getParameter("userId");
		int userId = Integer.parseInt(userIdString);//用户编号
		String songListName = request.getParameter("songListName");
		boolean userCreateSongListOrNot = songListService.userCreateSongListOrNot(userId, songListName);
		if (userCreateSongListOrNot) {
			System.out.println("是否金星秀行间");
			//为true表示可以创建
			//跳转到添加歌单的servlet
			session.setAttribute("userId", userId);
			request.getRequestDispatcher("/WangYiYun/AddSongList").forward(request, response);
		}else {
			//为false表示不能创建
			out.print(userCreateSongListOrNot);
		}
	}
}
