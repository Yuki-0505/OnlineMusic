package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.impl.UserServiceImpl;

public class PhoneExistOrNotServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String phone = request.getParameter("phone");
		UserServiceImpl userServiceImpl = new UserServiceImpl();
		boolean cs = userServiceImpl.selectUserOfPhone(phone);
		String dataString = new Boolean(cs).toString();
		out.print(dataString);
		System.out.println(dataString);
	}
}
