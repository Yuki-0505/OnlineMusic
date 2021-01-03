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

import com.entity.Song;
import com.entity.SongList;
import com.entity.User;
import com.entity.UserSongListComment;
import com.service.impl.SelectSongInfoAndCDNameAndSingerNameOfSongListIdServiceImpl;
import com.service.SelectSongInfoAndCDNameAndSingerNameOfSongListIdServiceInter;
import com.service.impl.SongListServiceImpl;
import com.service.SongListServiceInter;
import com.service.impl.UserServiceImpl;
import com.service.UserServiceInter;
import com.service.impl.UserSongListCommentServiceImpl;
import com.service.UserSongListCommentServiceInter;

public class SelectSongInfoAndCDNameAndSingerNameOfSongListIdServlet extends HttpServlet {
	private SongListServiceInter songListService = new SongListServiceImpl();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("<script language='javascript'>alert('哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈')</script>");
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String songListIdString = request.getParameter("songListId");
		if (songListIdString != null && songListIdString.length() > 0) {
			int songListId = Integer.parseInt(songListIdString);
			//根据该歌单编号，查询歌单信息，用于获取创建该歌单的用户编号
			SongListServiceInter songListService = new SongListServiceImpl();
			SongList songList = songListService.selectSongListOfSongListId(songListId);
			//增加歌单访问量
			//创建增加访问量的方法
			updateAccessCount(songListId);
			SelectSongInfoAndCDNameAndSingerNameOfSongListIdServiceInter selectSongInfoAndCDNameAndSingerNameOfSongListIdService = new SelectSongInfoAndCDNameAndSingerNameOfSongListIdServiceImpl();
			//歌单中歌曲
			ArrayList<Song> songArrayList = ((SelectSongInfoAndCDNameAndSingerNameOfSongListIdServiceImpl) selectSongInfoAndCDNameAndSingerNameOfSongListIdService).selectSongAndCdNameAndSingerName(songListId);
			
			//全部歌曲JSON数组
			JSONArray allSongJSONArray = new JSONArray();
			for (Song song : songArrayList) {
				/*
				 * "CDName":"百鸟朝凤","singerName":"王明",
				 * "singerSex":false,"songId":1,
				 * "songName":"不说再见","songTime":0,
				 * "songUrl":"/musicFile/1_1559455196116_71121.mp3","typeId":1
				 */
				int songId = song.getSongId();
				int typeId = song.getTypeId();
				double songTime = song.getSongTime();
				String songName = song.getSongName();
				String cdName = song.getCDName();
				String singerName = song.getSingerName();
				String songUrl = song.getSongUrl();
				JSONObject oneSongJsonObject = new JSONObject();
				oneSongJsonObject.put("songId", songId);
				oneSongJsonObject.put("typeId", typeId);
				oneSongJsonObject.put("songTime", songTime);
				oneSongJsonObject.put("songName", songName);
				oneSongJsonObject.put("CDName", cdName);
				oneSongJsonObject.put("singerName", singerName);
				oneSongJsonObject.put("songUrl", songUrl);
				allSongJSONArray.add(oneSongJsonObject);
			}
			
			
			System.out.println("++++++++++++++++++++++");
			System.out.println("歌单中歌曲：");
			System.out.println(allSongJSONArray);
			System.out.println("++++++++++++++++++++++");
			/*新增加内容*/
			//查询创建人信息（先获取用户编号）
			int createUserId = songList.getUserId();
			//根据用户编号，查询用户信息
			UserServiceInter userService = new UserServiceImpl();
			User user = userService.selectUserInfoOfUserId(createUserId);
			
			JSONObject canSeeUserInfoJSON = new JSONObject();
			canSeeUserInfoJSON.put("headSculptureUrl", user.getHeadSculptureUrl());
			canSeeUserInfoJSON.put("userId", user.getUserId());
			canSeeUserInfoJSON.put("userName", user.getUserName());
			//根据歌单编号，查询该歌单评论信息
			UserSongListCommentServiceInter userSongListCommentService = new UserSongListCommentServiceImpl();
			ArrayList<UserSongListComment> userSongListCommentArr = userSongListCommentService.selectSongListCommOfSongListId(songListId);
			JSONArray allCommJsonArray = new JSONArray();
			for (UserSongListComment userSongListComment : userSongListCommentArr) {
				//评论人编号
				int commUserId = userSongListComment.getUserId();
				//查询评论人信息
				User commUserInfo = userService.selectUserInfoOfUserId(commUserId);
				
				JSONObject commCanSeeUserInfoJSON = new JSONObject();
				commCanSeeUserInfoJSON.put("userId", commUserId);//评论人编号
				commCanSeeUserInfoJSON.put("userName", commUserInfo.getUserName());//评论人姓名
				commCanSeeUserInfoJSON.put("headSculptureUrl", commUserInfo.getHeadSculptureUrl());//评论人头像地址
				
				JSONObject oneCommJsonObject = new JSONObject();
				oneCommJsonObject.put("commentUserInfo", commCanSeeUserInfoJSON);
				oneCommJsonObject.put("commentContent", userSongListComment);
				allCommJsonArray.add(oneCommJsonObject);
			}
			
			//一个歌单的全部信息
			JSONObject songListAllInfo = new JSONObject();
			songListAllInfo.put("songListInfo", songList);
			songListAllInfo.put("createUserInfo", canSeeUserInfoJSON);//创建该歌单的用户信息
			songListAllInfo.put("song", allSongJSONArray);//歌单中歌曲信息
			songListAllInfo.put("songListComm", allCommJsonArray);//歌单评论信息
			
			System.out.println("++++++++++++++++++++++");
			System.out.println("该歌单全部信息：");
			System.out.println(songListAllInfo);
			System.out.println("++++++++++++++++++++++");
			/*新增加内容*/
				out.print(songListAllInfo);
		}else {
			System.out.println("没有歌单编号");
		}
		
	}
	
	public void updateAccessCount(int songListId){
		boolean updateOrNot = songListService.updateSongListAccessCount(songListId);
		if (updateOrNot) {
			System.out.println("热度+1增加成功");
		}else {
			System.out.println("热度增加发生错误");
		}
	}
}
