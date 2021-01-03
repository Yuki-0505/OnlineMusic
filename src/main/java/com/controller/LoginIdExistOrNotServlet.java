package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.impl.UserServiceImpl;
import com.service.UserServiceInter;

public class LoginIdExistOrNotServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String loginId = request.getParameter("loginId");
		System.out.println(loginId);
		UserServiceInter userService = new UserServiceImpl();
		boolean loginExist = userService.selectUserOfLoginId(loginId);
		System.out.println(loginExist);
		out.print(loginExist);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
