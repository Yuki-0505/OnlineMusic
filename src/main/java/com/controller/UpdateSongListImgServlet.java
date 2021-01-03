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

import com.service.impl.SongListServiceImpl;
import com.service.SongListServiceInter;

import sun.misc.BASE64Decoder;

//获取前端传递的歌单封面图片数据
public class UpdateSongListImgServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String songListImgString = request.getParameter("updateImgString");
		String songListIdString = request.getParameter("songListId");
		int songListId = Integer.parseInt(songListIdString);
		System.out.println("歌单编号：" + songListIdString);
		//获取时间戳
		Date date = new Date();
		String timestamp = String.valueOf(date.getTime());
		System.out.println("歌单图片上传，当期时间戳：" + timestamp);
		if (songListImgString == null){
			System.out.println("没有图片");
		}else {
			
			BASE64Decoder decoder = new BASE64Decoder();
			String songListImgStrings = songListImgString.substring(23);
			//Base64解码
            byte[] b = decoder.decodeBuffer(songListImgStrings);
            for(int i=0;i<b.length;++i)
            {
                if(b[i]<0)
                {//调整异常数据
                    b[i]+=256;
                }
            }
            String randomString = getRandom();
            
            String songListImgName = songListIdString + "_" + timestamp + "_" + randomString + ".jpg";
            
            //生成的要保存到数据库中的用户头像文件目录
            String songListImg = "/songListImgs/" + songListImgName;
            
            //把歌单封面数据保存到数据库
            SongListServiceInter songListService = new SongListServiceImpl();
            boolean updateOrNot = songListService.updateSongListImg(songListImg, songListId);
            if (updateOrNot) {
            	//生成jpeg图片
                String imgFilePath = "D:\\songListImgs\\" + songListImgName;//新生成的图片
//            	String imgFilePath = "C:\\songListImgs\\" + songListImgName;//新生成的图片
                OutputStream outImg = new FileOutputStream(imgFilePath);
                BufferedOutputStream buf = new BufferedOutputStream(outImg);
                buf.write(b);
//                outImg.write(b);
//                outImg.flush();
                buf.flush();
                buf.close();
                outImg.close();
				out.print("200");//成功
			}else {
				out.print("400");//失败
			}
		}
	}
}
