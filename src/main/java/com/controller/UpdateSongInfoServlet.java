package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.Song;
import com.service.impl.SongServiceImpl;
import com.service.SongServiceInter;

/*修改歌曲信息，只限歌曲名和语种*/
public class UpdateSongInfoServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String songIdString = request.getParameter("songId");
		int songId = Integer.parseInt(songIdString);
		
		String songName = request.getParameter("songName");
		String language = request.getParameter("language");
		
		Song song = new Song();
		song.setSongId(songId);
		song.setSongName(songName);
		song.setLanguage(language);
		SongServiceInter songService = new SongServiceImpl();
		boolean updateOrNot = songService.updateSongInfo(song);
		out.print(updateOrNot);
	}
}
