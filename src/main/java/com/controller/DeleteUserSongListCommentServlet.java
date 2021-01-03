package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.impl.UserSongListCommentServiceImpl;
import com.service.UserSongListCommentServiceInter;

/*删除评论信息*/
public class DeleteUserSongListCommentServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//需要先判断删除信息的管理员是否存在数据库中，是管理员才能删(这里没有判断)
		PrintWriter out = response.getWriter();
		String uSongListCommIdString = request.getParameter("uSongListCommId");
		int uSongListCommId = Integer.parseInt(uSongListCommIdString);
		System.out.println("接受到的");
		UserSongListCommentServiceInter userSongListCommentService = new UserSongListCommentServiceImpl();
		
		boolean deleteOrNot = userSongListCommentService.deleteUserSongListComment(uSongListCommId);
		out.print(deleteOrNot);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
