package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.entity.User;
import com.service.impl.UserServiceImpl;
import com.service.UserServiceInter;

public class UpdateUserInfoServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String userIdString = request.getParameter("userId");
		int userId = Integer.parseInt(userIdString);
		String userName = request.getParameter("userName");//用户名
		String userSex = request.getParameter("userSex");//用户性别
		String email = request.getParameter("email");//用户邮箱
		String phone = request.getParameter("phone");//用户手机号
		String sign = request.getParameter("sign");//签名
		System.out.println("修改用户信息");
		System.out.println("用户名：" + userName);
		User userSetting = new User();
		userSetting.setUserId(userId);
		userSetting.setUserName(userName);
		userSetting.setUserSex(userSex);
		userSetting.setEmail(email);
		userSetting.setPhone(phone);
		userSetting.setSign(sign);
		
		UserServiceInter userService = new UserServiceImpl();
		//调用修改用户信息的方法
		boolean updateOrNot = userService.updateUser(userSetting);
		if (updateOrNot) {
			//重新查询用户信息
			User user = userService.selectUserInfoOfUserId(userId);
			if (user != null) {
				JSONObject userInfoJSONObject = JSONObject.fromObject(user);
				out.print(userInfoJSONObject);
			}else {
				out.print("200");//修改信息成功，重新查询数据失败
			}
		}else {
			out.print("400");//修改失败
		}
 	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("============================疑惑");
	}
}
