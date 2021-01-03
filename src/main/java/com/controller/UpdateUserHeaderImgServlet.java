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

import com.service.impl.UserServiceImpl;
import com.service.UserServiceInter;

import sun.misc.BASE64Decoder;

//获取前端传递的头像数据
public class UpdateUserHeaderImgServlet extends HttpServlet {
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
		String userHeaderImgString = request.getParameter("updateImgString");
		String userIdString = request.getParameter("userId");
		int userId = Integer.parseInt(userIdString);
		System.out.println("用户编号：" + userIdString);
		//获取时间戳
		Date date = new Date();
		String timestamp = String.valueOf(date.getTime());
		System.out.println("头像上传，当期时间时间戳：" + timestamp);
		if (userHeaderImgString == null){
			System.out.println("没有图片");
		}else {
			
			BASE64Decoder decoder = new BASE64Decoder();
			String headerImgStrings = userHeaderImgString.substring(23);
			//Base64解码
            byte[] b = decoder.decodeBuffer(headerImgStrings);
            for(int i=0;i<b.length;++i)
            {
                if(b[i]<0)
                {//调整异常数据
                    b[i]+=256;
                }
            }
            String randomString = getRandom();
            
            String userHeaderImgName = userIdString + "_" + timestamp + "_" + randomString + ".jpg";
            
            //生成的要保存到数据库中的用户头像文件目录
            String headSculptureUrl = "/userHeaderImgs/" + userHeaderImgName;
            
            //把用户头像数据保存到数据库
            UserServiceInter userService = new UserServiceImpl();
            boolean updateOrNot = userService.updateUserHeaderImg(headSculptureUrl, userId);
            if (updateOrNot) {
            	//生成jpeg图片
                String imgFilePath = "D:\\userHeaderImgs\\" + userHeaderImgName;//新生成的图片
//            	String imgFilePath = "C:\\userHeaderImgs\\" + userHeaderImgName;//新生成的图片
                OutputStream outImg = new FileOutputStream(imgFilePath);
                BufferedOutputStream buf = new BufferedOutputStream(outImg);
                buf.write(b);
                buf.flush();
                buf.close();
//                outImg.write(b);
//                outImg.flush();
                outImg.close();
				out.print("200");//成功
			}else {
				out.print("400");//失败
			}
		}
	}
}
