package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.entity.SongList;
import com.service.impl.SongListServiceImpl;
import com.service.SongListServiceInter;
import com.service.impl.UserWithSongListServiceImpl;
import com.service.UserWithSongListServiceInter;

public class SelectUserSongListOfUserId extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//Ajax传过来用户编号，根据用户编号查询用户创建的歌单
				System.out.println("我的名字");
				PrintWriter out = response.getWriter();
				HttpSession session = request.getSession();
				String userIdString = request.getParameter("userId");
				int userId = Integer.parseInt(userIdString);//用户编号
				System.out.println("用户编号：" + userId);
				//声明查询用户创建的歌单的服务
				SongListServiceInter songListService = new SongListServiceImpl();
				//调用查询用户创建的歌单的方法
				ArrayList<SongList> songListArrayList = songListService.selectAllSongList(userId);
				//把用户创建歌单对象集合存储到json中
				JSONArray songListArrayListJSONArr = new JSONArray();
				for (SongList songList : songListArrayList) {
					int songListId = songList.getSongListId();
					String songListName = songList.getSongListName();
					String songListImg = songList.getSongListImg();
					String tags = songList.getTags();
					int createTheListUserId = songList.getUserId();
					JSONObject userCollectionSongListInfoToHtml = new JSONObject();
					userCollectionSongListInfoToHtml.put("songListId", songListId);
					userCollectionSongListInfoToHtml.put("songListName", songListName);
					userCollectionSongListInfoToHtml.put("songListImg", songListImg);
					userCollectionSongListInfoToHtml.put("tags", tags);
					userCollectionSongListInfoToHtml.put("userId", createTheListUserId);
					songListArrayListJSONArr.add(userCollectionSongListInfoToHtml);
				}
				
				
				System.out.println("========================================");
				System.out.println("用户创建歌单集合JSON对象：");
				System.out.println(songListArrayListJSONArr);
				System.out.println("========================================");
//				out.println(songListArrayListJSONArr);
				session.setAttribute("songListArrayList", songListArrayList);
				//查询用户收藏的歌单信息
				//调用查询用户收藏的歌单的服务
				UserWithSongListServiceInter userWithSongListService = new UserWithSongListServiceImpl();
				//调用查询用户收藏歌单的方法
				ArrayList<SongList> userCollectionSongList = userWithSongListService.selectSongListOfUserWithSongList(userId);
				//把用户收藏歌单对象集合存储到json中
				JSONArray userCollectionSongListJSONArr = new JSONArray();
				for (SongList songList : userCollectionSongList) {
					int songListId = songList.getSongListId();
					String songListName = songList.getSongListName();
					String songListImg = songList.getSongListImg();
					String tags = songList.getTags();
					int createTheListUserId = songList.getUserId();
					JSONObject userCollectionSongListInfoToHtml = new JSONObject();
					userCollectionSongListInfoToHtml.put("songListId", songListId);
					userCollectionSongListInfoToHtml.put("songListName", songListName);
					userCollectionSongListInfoToHtml.put("songListImg", songListImg);
					userCollectionSongListInfoToHtml.put("tags", tags);
					userCollectionSongListInfoToHtml.put("userId", createTheListUserId);
					userCollectionSongListJSONArr.add(userCollectionSongListInfoToHtml);
				}
				
				System.out.println("==================================================");
				System.out.println("打印用户收藏歌单集合JSON：");
				System.out.println(userCollectionSongListJSONArr);
				System.out.println("==================================================");
				session.setAttribute("userCollectionSongList", userCollectionSongList);
				//把多个JSON集合放入一个JSON二维数组中
//				ArrayList<ArrayList<SongList>> allSongListArr = new ArrayList<ArrayList<SongList>>();
//				allSongListArr.add(songListArrayList);
//				allSongListArr.add(userCollectionSongList);
//				
//				JSONArray allSongListJSON = new JSONArray().fromObject(allSongListArr);
//				System.out.println(allSongListJSON);
//				out.println(allSongListJSON);
				JSONObject allSongListObject = new JSONObject();
				allSongListObject.put("userCreateSongList", songListArrayListJSONArr);
				allSongListObject.put("userCollection", userCollectionSongListJSONArr);
				System.out.println("*******************");
				System.out.println("用户所有歌单：");
				System.out.println(allSongListObject);
				System.out.println("*******************");
				JSONArray allSongListArray = JSONArray.fromObject(allSongListObject);
				System.out.println("*******************");
				System.out.println("用户所有歌单JSON：");
				System.out.println(allSongListArray);
				System.out.println("*******************");
				out.print(allSongListArray);
//				request.getRequestDispatcher("MyMusicIframe.jsp").forward(request, response);
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
			doPost(request, response);
		
	}
}
