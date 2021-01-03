package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.SongListWithSong;
import com.service.impl.SongListWithSongServiceImpl;
import com.service.SongListWithSongServiceInter;
import com.service.impl.SongServiceImpl;
import com.service.SongServiceInter;
/*
 * 用户添加歌曲到歌单
 */
public class AddSongListWithSongServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String songListIdString = request.getParameter("songListId");
		int songListId = Integer.parseInt(songListIdString);//歌单编号
		String songIdString = request.getParameter("songId");
		int songId = Integer.parseInt(songIdString);//歌曲编号
		System.out.println("歌单编号：" + songListId + ";歌曲编号：" + songId);
		Date date = new Date();
		SimpleDateFormat temp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String collectionDate = temp.format(date);//把获取到的系统时间转换成字符串格式
		SongListWithSong songListWithSong = new SongListWithSong();
		songListWithSong.setSongListId(songListId);
		songListWithSong.setSongId(songId);
		songListWithSong.setCollectionDate(collectionDate);
		//判断该歌曲是否已经存在在该歌单中
		SongListWithSongServiceInter songListWithSongService = new SongListWithSongServiceImpl();
		boolean existOrNot = songListWithSongService.selectSongListWithSongToSongListIdAndSongId(songListWithSong);
		System.out.println("歌曲存在吗：true不能存在：：：：" + existOrNot);
		if (existOrNot) {
			//为true，表示没有该数据，可以进行添加
			//先进行收藏次数+1
			SongServiceInter songService = new SongServiceImpl();
			boolean upSongCollCount = songService.upSongCollectionCount(songId);
			if (upSongCollCount) {
				//调用添加的方法
				boolean addOrNot = songListWithSongService.addSongListWithSong(songListWithSong);
				if (addOrNot) {
					//返回true，表示成功，使用200
					out.print("200");
				}else {
					//返回false，表示添加失败，使用300
					out.print("300");
				}
			}else{
				out.print("400");//添加失败
			}
		}else {
			//返回数字编码，100，表示该数据已经存在
			out.print("100");
		}
		
	}
}
