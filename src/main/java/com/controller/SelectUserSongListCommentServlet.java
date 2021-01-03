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
import com.entity.User;
import com.entity.UserSongListComment;
import com.service.impl.SongListServiceImpl;
import com.service.SongListServiceInter;
import com.service.impl.UserServiceImpl;
import com.service.UserServiceInter;
import com.service.impl.UserSongListCommentServiceImpl;
import com.service.UserSongListCommentServiceInter;
//分页查询歌单评论
public class SelectUserSongListCommentServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		//点击页面上的页码或者上下页，接收到页码
		int clickPageNum = 1;
		String clickPageNumString = request.getParameter("clickPageNum");
		if (clickPageNumString != null && clickPageNumString != "") {
			clickPageNum = Integer.parseInt(clickPageNumString);
		}
		System.out.println("页码：" + clickPageNum);
		//每页显示行数
		int onePageRowsNum = 15;
		
		
		UserSongListCommentServiceInter userSongListCommentService = new UserSongListCommentServiceImpl();
		ArrayList<UserSongListComment> allUserSongListComments = userSongListCommentService.selectAllUserSongListComments();
		//计算总行数
		int allPageRowsNum  = allUserSongListComments.size();
		
		//计算总页数
		int pagesNum = allPageRowsNum % onePageRowsNum == 0 ? allPageRowsNum / onePageRowsNum : (allPageRowsNum /onePageRowsNum) + 1;
		System.out.println("总页数：" + pagesNum);
		//计算开始的行数
		int startRowNum = (clickPageNum - 1) * onePageRowsNum;
		System.out.println("开始行数：" + startRowNum);
		
		//该页显示的评论信息
		ArrayList<UserSongListComment> userSongListCommentLimit = userSongListCommentService.selectUserSongListCommentLimit(startRowNum, onePageRowsNum);
		UserServiceInter userService = new UserServiceImpl();
		SongListServiceInter songListService = new SongListServiceImpl();
		JSONArray userSongListCommentJsonArray = new JSONArray();
		for (UserSongListComment userSongListComment : userSongListCommentLimit) {
			JSONObject songListCommtAllInfoJsonObject = new JSONObject();
			int userId = userSongListComment.getUserId();
			User user = userService.selectUserInfoOfUserId(userId);
			String loginId = user.getLoginId();//用户账号
			int songListId = userSongListComment.getSongListId();
			SongList songList = songListService.selectSongListOfSongListId(songListId);
			String songListName = songList.getSongListName();//歌单名
			songListCommtAllInfoJsonObject.put("loginId", loginId);
			songListCommtAllInfoJsonObject.put("songListName", songListName);
			songListCommtAllInfoJsonObject.put("userSongListComm", userSongListComment);
			userSongListCommentJsonArray.add(songListCommtAllInfoJsonObject);
		}
		
		JSONObject userSongListCommentAndPageInfoJsonObject = new JSONObject();
		userSongListCommentAndPageInfoJsonObject.put("currentPage", clickPageNum);//当前页
		userSongListCommentAndPageInfoJsonObject.put("pagesNum", pagesNum);
		userSongListCommentAndPageInfoJsonObject.put("userSongListCommentInfoArr", userSongListCommentJsonArray);
		System.out.println("评论西悉尼");
		System.out.println("评论信息：" + userSongListCommentAndPageInfoJsonObject);
		out.print(userSongListCommentAndPageInfoJsonObject);
		
	}
}
