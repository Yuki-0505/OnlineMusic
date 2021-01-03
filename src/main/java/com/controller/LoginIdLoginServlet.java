package com.controller;
/*
 * LoginId登录表单提交地址，暂时未使用
 */
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

public class LoginIdLoginServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.print("<script language='javascript'>alert('哈哈哈哈哈哈哈哈哈哈哈');</script>");
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		System.out.println("是否进来啊啊啊啊啊啊啊啊啊啊啊啊");
		HttpSession session = request.getSession();
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		UserServiceInter userService = new UserServiceImpl();
		User user = userService.selectUserOfLoginIdAndPasswordToLogin(loginId, password);
		session.setAttribute("userInfo", user);
//		request.getRequestDispatcher("index.jsp").forward(request, response);
		request.getRequestDispatcher("/UserSongListResommend").forward(request, response);
		
	}
}
