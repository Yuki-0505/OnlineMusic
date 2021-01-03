package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.impl.UserServiceImpl;
import com.service.UserServiceInter;
/*修改用户是否可以登录权限*/
public class UpdateUserStateIdServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String userIdString = request.getParameter("userId");
		String userStateIdString = request.getParameter("userStateId");
		
		int userId = Integer.parseInt(userIdString);
		int userStateId = Integer.parseInt(userStateIdString);
		
		UserServiceInter userService = new UserServiceImpl();
		boolean updateOrNot = userService.updateUserStateId(userStateId, userId);
		out.print(updateOrNot);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
