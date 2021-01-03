package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.entity.User;
import com.service.impl.UserServiceImpl;
import com.service.UserServiceInter;
/*
 * 手机号登录表单提交地址
 * 暂时未使用
 */
public class PhoneLoginServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
		UserServiceInter userService = new UserServiceImpl();
		User user = userService.selectUserOfPhoneAndPasswordToLogin(phone, password);
		session.setAttribute("userInfo", user);
//		request.getRequestDispatcher("index.jsp").forward(request, response);
		request.getRequestDispatcher("/UserSongListResommend").forward(request, response);
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.print("<script language='javascript'>alert('哈哈哈哈哈哈哈哈哈哈哈');</script>");
	}
}
