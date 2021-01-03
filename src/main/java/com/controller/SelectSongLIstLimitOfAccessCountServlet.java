package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.entity.SongList;
import com.entity.User;
import com.service.impl.SongListServiceImpl;
import com.service.SongListServiceInter;
import com.service.impl.UserServiceImpl;
import com.service.UserServiceInter;

/*
 * 分页查询歌单（热度升序）
 */
public class SelectSongLIstLimitOfAccessCountServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
 		PrintWriter out = response.getWriter();
 		//获取当前页数
 		String currentPageString = request.getParameter("currentPage");//点击页面上的页数按钮
 		int currentPage = 1;//设置初始页码为1
 		if (currentPageString != null) {
 			//如果接收到的页码不为空，就按接收到的页码
 			currentPage = Integer.parseInt(currentPageString);
 		}
 		int selectRowNum = 25;//每页显示行数
 		int fromRowNum = (currentPage - 1) * 25;//从第几行开始
 		//前端获取要查询的歌单标签
 		String tagsString = request.getParameter("tags");
 		String tags = "";
 		if (tagsString != null) {
 			tags = "%" + tagsString + "%";
 		}else {
 			tags = "%" + "" + "%";
 		}
 		System.out.println("==========歌单标签：" + tags);
		//查询该标签歌单，判断页数
		SongListServiceInter songListService = new SongListServiceImpl();
		ArrayList<SongList> songLists = songListService.selectAllSongListOfTags(tags);
		
		//歌单显示总页数
		int allPageNums = 0;
		System.out.println("集合长度:=+++++++++" + songLists.size());
		if (songLists.size() <= 25 && songLists.size() > 0) {
			allPageNums = 1;
		}else {
			allPageNums = songLists.size() % 25 == 0 ? songLists.size()/25 : songLists.size()/25 + 1;
		}
		
		UserServiceInter userService = new UserServiceImpl();
		ArrayList<SongList> songListLimit = songListService.songListLimitToAccessCount(tags, fromRowNum, selectRowNum);
		JSONObject allSongListInfo = new JSONObject();
		for (int i = 0; i < songListLimit.size(); i++) {//遍历查询到的歌单集合
			SongList songList = songListLimit.get(i);
			int userId = songList.getUserId();//创建该歌单的用户编号
			//根据用户编号，查询用户信息
			User user = userService.selectUserInfoOfUserId(userId);
			String sign = user.getSign();//签名
			String userName = user.getUserName();//用户名
			int userStateId = user.getUserStateId();
			int userType = user.getUserType();
			
			User canSeeUser = new User();
			canSeeUser.setUserId(userId);
			canSeeUser.setUserName(userName);
			canSeeUser.setSign(sign);
			canSeeUser.setUserStateId(userStateId);
			canSeeUser.setUserType(userType);
			
			JSONObject songListInfoAndCreateUserObj = new JSONObject();
			songListInfoAndCreateUserObj.put("songListInfo", songList);//歌单信息
			songListInfoAndCreateUserObj.put("createTheSongListInfo", canSeeUser);//创建当前歌单的用户信息
			songListInfoAndCreateUserObj.put("currentPage", currentPage);//当前页码
			songListInfoAndCreateUserObj.put("allPageNums", allPageNums);//总页数
			System.out.println("总页数：=========" + allPageNums);
			allSongListInfo.put(i, songListInfoAndCreateUserObj);
		}
		JSONObject outSongListInfoAndCreateUser = JSONObject.fromObject(allSongListInfo);
		System.out.println("分页歌单：" + outSongListInfoAndCreateUser);
		out.print(outSongListInfoAndCreateUser);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
