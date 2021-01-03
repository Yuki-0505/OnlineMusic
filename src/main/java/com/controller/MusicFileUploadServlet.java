package com.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.misc.BASE64Decoder;
/*音乐文件上传*/
public class MusicFileUploadServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String musicFileBase64String  = request.getParameter("file");
		String songIdString = request.getParameter("songId");
		System.out.println("歌曲编号" + songIdString);
//		System.out.println("文件：" + musicFileBase64String);
		
		BASE64Decoder decoder = new BASE64Decoder();
		System.out.println("哈哈");
		String musicFileString = musicFileBase64String.substring(21);
		System.out.println("耶耶耶");
		//Base64解码
        byte[] b = decoder.decodeBuffer(musicFileString);
//        System.out.println("长度：" + b.length);
        for(int i=0;i<b.length;++i)
        {
            if(b[i]<0)
            {//调整异常数据
                b[i]+=256;
            }
        }
        System.out.println("=========开始写入文件=========");
        OutputStream outImg = new FileOutputStream("D:\\musicFile\\好妹妹.mp3");
        
        outImg.write(b);
        outImg.flush();
        outImg.close();
        System.out.println("=========写入文件成功=========");
	}
}
