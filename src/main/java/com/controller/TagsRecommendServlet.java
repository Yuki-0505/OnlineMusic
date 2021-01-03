package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.entity.SongList;
import com.entity.SongListWithSong;
import com.service.impl.SongListServiceImpl;
import com.service.SongListServiceInter;
import com.service.impl.SongListWithSongServiceImpl;
import com.service.SongListWithSongServiceInter;

/**
 * 标签推荐
 * @author 29207
 *
 */
public class TagsRecommendServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, Integer> songListTagsNameMap = new HashMap<String, Integer>();//存储歌单标签名字和出现次数
		java.util.List<Map.Entry<String, Integer>> songListTagsNameListClassement = new ArrayList<Map.Entry<String,Integer>>();//存储排序后歌单标签名字和出现次数
		
		PrintWriter out = response.getWriter();
		//获取歌单编号
		String songListIdString = request.getParameter("songListId");
		int songListId = Integer.parseInt(songListIdString);//歌单编号
		
		//声明查询歌单的服务
		SongListServiceInter songListService = new SongListServiceImpl();
		
		//用于存放所有歌曲存在的所有歌单
		ArrayList<SongList> allSongLists = new ArrayList<SongList>();
		
		//查询该歌单中存在的歌曲
		//声明songListWithSong(歌曲歌单表服务对象)
		SongListWithSongServiceInter songListWithSongService = new SongListWithSongServiceImpl();
		//调用方法
		ArrayList<SongListWithSong> songListWithSongs = songListWithSongService.selectSongListWithSongOfSongLIstId(songListId);
		//遍历，获取其中的歌曲编号
		for (SongListWithSong songListWithSong : songListWithSongs) {
			int songId = songListWithSong.getSongId();//获取每一个歌曲编号
			//根据歌曲编号，查询该歌曲存在在哪些歌单中
			ArrayList<SongListWithSong> songFromSongLists = songListWithSongService.selectSongListIdFromSongListWithSongOfSongId(songId);
			//遍历，取出每个歌单的歌单编号，根据歌单编号查找歌单
			for (SongListWithSong songListWithSong2 : songFromSongLists) {
				int oneSongFromsongListId = songListWithSong2.getSongListId();
				//根据歌单编号查询歌单信息
				SongList oneSongList = songListService.selectSongListOfSongListId(oneSongFromsongListId);
				//把该歌单存入allSongLists
				allSongLists.add(oneSongList);
			}
		}
		System.out.println("==========我是标签推荐服务=========");
		//一个歌单中所有歌曲涉及到的所有歌单为allSongLists
		int allSongListsLen = allSongLists.size();
		UserSongListRecommendServlet userSongListRecommendServlet = new UserSongListRecommendServlet();
		songListTagsNameMap = userSongListRecommendServlet.getSongListTagsNameMap(allSongListsLen, allSongLists, songListTagsNameMap);
		songListTagsNameListClassement = userSongListRecommendServlet.songListTagsNameComm(songListTagsNameMap);//标签排序后
		int songListTagsNameListClassementLen =  songListTagsNameListClassement.size();
		JSONObject tagsRecommJsonObject = new JSONObject();
		System.out.println("排序后长度：" + songListTagsNameListClassementLen);
		if (songListTagsNameListClassementLen == 0) {
			out.print("null");
		}else {
			if (songListTagsNameListClassementLen <= 5) {
				for (int i = 0; i < songListTagsNameListClassementLen; i++) {
					String tagsRecomm = songListTagsNameListClassement.get(i).getKey();
					tagsRecommJsonObject.put(i, tagsRecomm);
				}
			}else {
				for (int i = 0; i < 5; i++) {
					String tagsRecomm = songListTagsNameListClassement.get(i).getKey();
					tagsRecommJsonObject.put(i, tagsRecomm);
				}
			}
			System.out.println("推荐标签JSON===" + tagsRecommJsonObject);
			out.print(tagsRecommJsonObject);
		}
	}
}
