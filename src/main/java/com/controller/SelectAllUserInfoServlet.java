package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.entity.User;
import com.service.impl.UserServiceImpl;
import com.service.UserServiceInter;

public class SelectAllUserInfoServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		//点击页面上的页码或者上下页，接收到页码
		int clickPageNum = 1;
		String clickPageNumString = request.getParameter("clickPageNum");
		if (clickPageNumString != null && clickPageNumString != "") {
			clickPageNum = Integer.parseInt(clickPageNumString);
		}
		
		//每页显示行数
		int onePageRowsNum = 15;
		
		//获取用户个数
		UserServiceInter userService = new UserServiceImpl();
		ArrayList<User> allUsers = userService.selectAllUserInfo();
		int userNum = allUsers.size();
		System.out.println("用户数：" + userNum);
		
		//计算总页数
		int pagesNum = userNum % onePageRowsNum == 0 ? userNum / onePageRowsNum : (userNum /onePageRowsNum) + 1;
		System.out.println("总页数：" + pagesNum);
		//计算开始的行数
		int startRowNum = (clickPageNum - 1) * onePageRowsNum;
		System.out.println("开始行数：" + startRowNum);
		//查询所有用户信息的方法
		ArrayList<User> userArrayList = userService.selectAllUserInfoLimit(startRowNum, onePageRowsNum);
		System.out.println("本页用户数量：" + userArrayList.size());
		
		//存储用户信息与总页数，当前页数
		JSONObject userInfoPageNumAndCurrentPage = new JSONObject();
		userInfoPageNumAndCurrentPage.put("pagesNum", pagesNum);//存储总页数
		userInfoPageNumAndCurrentPage.put("currentPage", clickPageNum);//存储当前页
		
		JSONArray userJsonArray = new JSONArray();
		for (User user : userArrayList) {
			userJsonArray.add(user);
		}
		
		userInfoPageNumAndCurrentPage.put("userInfo", userJsonArray);
		
		System.out.println("用户信息：" + userInfoPageNumAndCurrentPage);
		out.print(userInfoPageNumAndCurrentPage);
		
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
