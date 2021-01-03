package com.controller;

import com.entity.Song;
import com.service.impl.SongServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UploadSongServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        Song song = new Song();
        song.setSongName(request.getParameter("songName"));
        song.setSongUrl(request.getParameter("songUrl"));
        boolean updateOrNot = new SongServiceImpl().uploadSong(song);
        out.print(updateOrNot);
    }
}
