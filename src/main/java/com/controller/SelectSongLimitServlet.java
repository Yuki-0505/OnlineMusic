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

import com.entity.Cd;
import com.entity.Singer;
import com.entity.Song;
import com.service.impl.CdServiceImpl;
import com.service.CdServiceInter;
import com.service.impl.SingerServiceImpl;
import com.service.SingerServiceInter;
import com.service.impl.SongServiceImpl;
import com.service.SongServiceInter;

/*管理员分页查询歌曲信息*/
public class SelectSongLimitServlet extends HttpServlet {
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
				int onePageRowsNum = 14;
				
				
				SongServiceInter songService = new SongServiceImpl();
				//计算总行数
				int allPageRowsNum  = songService.selectSongRowsNum();
				
				//计算总页数
				int pagesNum = allPageRowsNum % onePageRowsNum == 0 ? allPageRowsNum / onePageRowsNum : (allPageRowsNum /onePageRowsNum) + 1;
				System.out.println("总页数：" + pagesNum);
				//计算开始的行数
				int startRowsNum = (clickPageNum - 1) * onePageRowsNum;
				System.out.println("开始行数：" + startRowsNum);
				
				//分页查询
				ArrayList<Song> songLimitArrayList = songService.selectSongLimit(startRowsNum, onePageRowsNum);
				
				JSONArray songLimitJsonArray = new JSONArray();
				CdServiceInter cdService = new CdServiceImpl();
				SingerServiceInter singerService = new SingerServiceImpl();
				for (Song song : songLimitArrayList) {
					JSONObject oneSongJsonObject = new JSONObject();
					int cdId = song.getCDId();//专辑编号
					Cd cd = cdService.selectCdOfCdId(cdId);
					String cdName = cd.getCDName();//专辑名称
					
					int singerId = song.getSingerId();//歌手编号
					Singer singer = singerService.selectSingerOfSingerId(singerId);
					String singerName = singer.getSingerName();
					
					oneSongJsonObject.put("singerName", singerName);
					oneSongJsonObject.put("cdName", cdName);
					oneSongJsonObject.put("oneSongInfo", song);
					
					songLimitJsonArray.add(oneSongJsonObject);
				}
				JSONObject songInfoAndPageInfo = new JSONObject();
				songInfoAndPageInfo.put("currentPage", clickPageNum);
				songInfoAndPageInfo.put("pagesNum", pagesNum);
				songInfoAndPageInfo.put("songInfo", songLimitJsonArray);
				System.out.println("歌曲信息：" + songInfoAndPageInfo);
				out.print(songInfoAndPageInfo);
	}
}
