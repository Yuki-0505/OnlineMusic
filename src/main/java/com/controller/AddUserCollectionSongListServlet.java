package com.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.UserWithSongList;
import com.service.impl.SongListServiceImpl;
import com.service.SongListServiceInter;
import com.service.impl.UserWithSongListServiceImpl;
import com.service.UserWithSongListServiceInter;

public class AddUserCollectionSongListServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//用户收藏歌单
		//前端通过Ajax传过来数据，接收用户编号，歌单编号
		String userCollection = "0";
		PrintWriter out = response.getWriter();
		String userIdString = request.getParameter("userId");
		int userId = Integer.parseInt(userIdString);//用户编号
		String songListIdString  = request.getParameter("songListId");
		int songListId = Integer.parseInt(songListIdString);//歌单编号
		//获取系统时间
		Date date = new Date();
		SimpleDateFormat temp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String collectionDate = temp.format(date);//把获取到的系统时间转换成字符串格式
		UserWithSongListServiceInter userWithSongListService = new UserWithSongListServiceImpl();
		//调用查询用户是否已经收藏该歌单方法
		boolean collectionOrNot = userWithSongListService.userCollectionSongListOrNot(userId, songListId);
		if (collectionOrNot) {
			//可以添加
			UserWithSongList userWithSongList = new UserWithSongList();
			userWithSongList.setUserId(userId);
			userWithSongList.setSongListId(songListId);
			userWithSongList.setCollectionDate(collectionDate);
			//调用添加收藏表的方法
			
			boolean addOrNot = userWithSongListService.userCollectionSongList(userWithSongList);
			//Ajax返回值，判断是否添加成功
			if (addOrNot) {
				//调用收藏次数+1的方法
				SongListServiceInter songListService = new SongListServiceImpl();
				boolean addOrNot1 = songListService.addSongListCollectionCount(songListId);
				if (addOrNot1) {
					//添加成功
					userCollection = "200";//添加成功
				}else {
					out.print("400");//歌单收藏成功，但是收藏次数+1失败
				}
				
			}else {
				userCollection = "300";//添加失败
			}
		}else {
			userCollection = "500";//该歌单用户已经添加
		}
		
		out.print(userCollection);
	}
}
