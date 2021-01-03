package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.impl.SongListServiceImpl;
import com.service.SongListServiceInter;

public class DeleteUserCreateSongListServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String userIdString  = request.getParameter("userId");
		int userId = Integer.parseInt(userIdString);
		String songListIdString = request.getParameter("songListId");
		int songListId = Integer.parseInt(songListIdString);
		//创建删除用户创建的歌单的服务对象
		SongListServiceInter songListService = new SongListServiceImpl();
		//调用删除方法
		boolean deleteOrNot = songListService.deleteSongList(userId, songListId);
		out.print(deleteOrNot);//返回true表示删除成功，返回false表示删除失败
	}
}
