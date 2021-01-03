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

/*根据用户编号，查询用户信息*/
public class SelectUserInfoServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String userIdString = request.getParameter("userId");
		System.out.println("接收到的用户信息编号：" + userIdString);
		int userId = Integer.parseInt(userIdString);
		UserServiceInter userService = new UserServiceImpl();
		User user = userService.selectUserInfoOfUserId(userId);
		JSONObject userInfoJSONObject = JSONObject.fromObject(user);
		System.out.println("用户信息：" + userInfoJSONObject);
		out.print(userInfoJSONObject);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}
}
