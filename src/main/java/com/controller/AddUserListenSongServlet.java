package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.UserListenSong;
import com.service.impl.SongServiceImpl;
import com.service.SongServiceInter;
import com.service.impl.UserListenSongServiceImpl;
import com.service.UserListenSongServiceInter;

public class AddUserListenSongServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//使用Ajax技术，传过来用户编号和歌曲编号，来进行添加，返回boolean值
		PrintWriter out = response.getWriter();
		String userIdString = request.getParameter("userId");
		System.out.println("用户编号：" + userIdString);
		String songIdString = request.getParameter("songId");
		int songId = Integer.parseInt(songIdString);//获取歌曲编号
		if (userIdString != null && userIdString != "") {
			int userId = Integer.parseInt(userIdString);//获取用户编号
			//获取系统时间
			Date date = new Date();
			SimpleDateFormat temp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String listenSongDate = temp.format(date);//把获取到的系统时间转换成字符串格式
			UserListenSong userListenSong = new UserListenSong();
			userListenSong.setUserId(userId);
			userListenSong.setSongId(songId);
			userListenSong.setListenSongDate(listenSongDate);
			
			//定义增加歌曲播放量的服务
			SongServiceInter songService = new SongServiceImpl();
			boolean upSongPlayCountOrNot = songService.upSongPlayCount(songId);
			if (upSongPlayCountOrNot) {
				//调用添加记录的服务
				UserListenSongServiceInter userListenSongService = new UserListenSongServiceImpl();
				boolean addOrNot = ((UserListenSongServiceImpl) userListenSongService).addUserListenSongRecore(userListenSong);
				System.out.println("增加播放次数成功，增加播放历史成功");
				//返回值用于Ajax判断是否添加成功
				out.print(addOrNot);
			}else {
				out.print(upSongPlayCountOrNot);
			}
		}else{
			//定义增加歌曲播放量的服务
			SongServiceInter songService = new SongServiceImpl();
			boolean upSongPlayCountOrNot = songService.upSongPlayCount(songId);
			System.out.println("没有登录，只增加播放次数");
			out.print(upSongPlayCountOrNot);
		}
		
		
	}
 }
