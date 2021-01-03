package com.controller;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.impl.SongServiceImpl;
import com.service.SongServiceInter;

import sun.misc.BASE64Decoder;

/*修改歌曲文件，地址*/
public class UpdateSongSongUrlServlet extends HttpServlet {
	private static Random random;
	static {
		random = new Random();
	}
	public static String getRandom(){
		int randomInt = random.nextInt(100000);
		String randomString = randomInt + "";
		return randomString;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(super.getServletContext().getRealPath("/musicFile/"));
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String songIdString = request.getParameter("songId");
		System.out.println("歌曲编号" + songIdString);
		int songId = Integer.parseInt(songIdString);//歌曲编号
		String musicFileBase64String = request.getParameter("file");
		
		//获取时间戳
		Date date = new Date();
		String timestamp = String.valueOf(date.getTime());
		System.out.println("歌曲文件上传当期时间：" + timestamp);
		
		String randomString = getRandom();
		 
		String musicFileName = songIdString + "_" + timestamp + "_" + randomString + ".mp3";
		
		String songUrl = "/musicFile/" + musicFileName;
		
		SongServiceInter songService = new SongServiceImpl();
		boolean updateFileOrNot = false;
		boolean updateOrNot = songService.updateSongSongUrl(songId, songUrl);
		if (updateOrNot) {
			BASE64Decoder decoder = new BASE64Decoder();
			System.out.println("哈哈");
			String musicFileString = musicFileBase64String.substring(21);
			System.out.println("耶耶耶");
			//Base64解码
	        byte[] b = decoder.decodeBuffer(musicFileString);
	        System.out.println("长度：" + b.length);
	        for(int i=0;i<b.length;++i)
	        {
	            if(b[i]<0)
	            {//调整异常数据
	                b[i]+=256;
	            }
	        }
	        System.out.println("=========开始写入文件=========");

//	        OutputStream outImg = new FileOutputStream("/musicFile/" + musicFileName);
	        OutputStream outImg = new FileOutputStream(super.getServletContext().getRealPath("/musicFile/"+musicFileName));
//	        OutputStream outImg = new FileOutputStream("C:\\musicFile\\" + musicFileName);
	        BufferedOutputStream buf = new BufferedOutputStream(outImg);
//	        outImg.write(b);
	        buf.write(b);
//	        outImg.flush();
	        buf.flush();
	        buf.close();
	        outImg.close();
	        System.out.println("=========写入文件成功=========");
	        updateFileOrNot = true;
		}
		
		out.print(updateFileOrNot);
	}
}
