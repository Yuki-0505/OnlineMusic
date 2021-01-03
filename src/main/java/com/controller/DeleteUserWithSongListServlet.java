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
import com.service.impl.UserWithSongListServiceImpl;
import com.service.UserWithSongListServiceInter;

public class DeleteUserWithSongListServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String userIdString = request.getParameter("userId");
		int userId = Integer.parseInt(userIdString);
		String songListIdString = request.getParameter("songListId");
		int songListId = Integer.parseInt(songListIdString);
		System.out.println("要删除的用户Id和歌单编号：" + userId + "======" + songListId);
		//声明歌单表服务
		SongListServiceInter songListService = new SongListServiceImpl();
		//查询
		SongList songList = songListService.selectSongListOfSongListId(songListId);
		int collectionCount = songList.getCollectionCount();
		if (collectionCount > 0) {
			//调用歌单收藏次数-1的方法
			boolean cutDownOrNot = songListService.cutDownCollectionCount(songListId);
		}
		
		//声明删除用户收藏歌单的服务
		UserWithSongListServiceInter userWithSongListService = new UserWithSongListServiceImpl();
		//调用删除用户收藏歌单的方法
		boolean deleteOrNot = userWithSongListService.deleteUserWithSongList(userId, songListId);
		out.print(deleteOrNot);//返回true表示删除成功，返回false表示删除成功
	}
}
