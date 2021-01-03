package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.entity.User;
import com.entity.UserSongListComment;
import com.service.impl.UserServiceImpl;
import com.service.UserServiceInter;
import com.service.impl.UserSongListCommentServiceImpl;
import com.service.UserSongListCommentServiceInter;

public class AddSongListCommentServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String userIdString = request.getParameter("userId");
		int userId = Integer.parseInt(userIdString);//用户编号
		
		String songListIdString = request.getParameter("songListId");
		int songListId = Integer.parseInt(songListIdString);//歌单编号
		
		String songListCommText = request.getParameter("songListCommText");//评论内容
		
		Date date = new Date();
		SimpleDateFormat temp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String songListCommDate = temp.format(date);//评论时间
		
		UserSongListComment userSongListComment = new UserSongListComment();
		userSongListComment.setUserId(userId);
		userSongListComment.setSongListId(songListId);
		userSongListComment.setSongListCommText(songListCommText);
		userSongListComment.setSongListCommDate(songListCommDate);
		
		//声明添加用户评论的服务对象
		UserSongListCommentServiceInter userSongListCommentService = new UserSongListCommentServiceImpl();
		//调用添加评论的方法
		boolean addOrNot = userSongListCommentService.addSongListComm(userSongListComment);
		if (addOrNot) {
			UserServiceInter userService = new UserServiceImpl();
			//添加成功，重新查询评论信息，并返回给前端
			ArrayList<UserSongListComment> songListComments = userSongListCommentService.selectSongListCommOfSongListId(songListId);
			JSONArray songListCommJsonArray = new JSONArray();
			for (UserSongListComment userSongListComment2 : songListComments) {
				int commUserId = userSongListComment2.getUserId();
				//查询用户信息
				User user = userService.selectUserInfoOfUserId(commUserId);
				//可以查看用户信息
				User commCanSeeUserInfo = new User();
				commCanSeeUserInfo.setUserId(user.getUserId());
				commCanSeeUserInfo.setUserName(user.getUserName());
				commCanSeeUserInfo.setHeadSculptureUrl(user.getHeadSculptureUrl());
				
				JSONObject oneCommJsonObject = new JSONObject();
				oneCommJsonObject.put("commentUserInfo", commCanSeeUserInfo);//评论人信息
				oneCommJsonObject.put("commentContent", userSongListComment2);//评论信息
				songListCommJsonArray.add(oneCommJsonObject);
			}
			out.print(songListCommJsonArray);
		}else{
			out.print("300");//添加失败
		}
	}
}
