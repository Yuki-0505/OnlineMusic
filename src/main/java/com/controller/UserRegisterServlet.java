package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.User;
import com.service.impl.UserServiceImpl;
import com.service.UserServiceInter;

/*
 * 用户注册
 */
public class UserRegisterServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("是否进来");
		PrintWriter out = response.getWriter();
		String loginId = request.getParameter("loginid");
		String password = request.getParameter("psd1");
		String userName = request.getParameter("username");
		String usersex = request.getParameter("usersex");
		String email = request.getParameter("email");
		String phone = request.getParameter("phoneno");
		String sign = request.getParameter("sign");
		String headSculptureUrl = "";
		Date time = new Date();
		SimpleDateFormat day = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = day.format(time);
		System.out.println("账号：" + loginId);
		System.out.println("密码" + password);
		System.out.println("昵称：" + userName);
		System.out.println("性别：" + usersex);
		System.out.println("邮箱：" + email);
		System.out.println("手机号：" + phone);
		System.out.println("签名：" + sign);
		User user = new User();
		user.setLoginId(loginId);
		user.setPassword(password);
		user.setUserName(userName);
		user.setUserSex(usersex);
		user.setEmail(email);
		user.setPhone(phone);
		user.setSign(sign);
		user.setHeadSculptureUrl(headSculptureUrl);
		user.setRegistationDate(date);
		
		
		UserServiceInter userService = new UserServiceImpl();
		boolean addOrNot = userService.addUser(user);
		if (addOrNot) {
			System.out.println("response");
			response.sendRedirect("http://localhost:8060/WangYiYunHTML/index.html");
//			out.print("<script>window.location.href='http://localhost/WangYiYunHTML/index.html'</script>");
//			out.print("<script>window.location.href='http://192.144.181.175:8060/WangYiYunHTML/index.html'</script>");
		}else{
			out.print("<script>alert(注册失败！);window.location.href='http://localhost/WangYiYunHTML/Register.html'</script>");
//			out.print("<script>alert(注册失败！);window.location.href='http://192.144.181.175:8060/WangYiYunHTML/Register.html'</script>");
		}
		//返回true注册成功，返回false注册失败
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
