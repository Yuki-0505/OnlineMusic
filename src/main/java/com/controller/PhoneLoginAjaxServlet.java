package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import com.entity.User;
import com.service.impl.UserServiceImpl;
import com.service.UserServiceInter;
/*
 * 手机号登录Ajax验证url代码
 */
public class PhoneLoginAjaxServlet extends HttpServlet {
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
		JSONObject json = JSONObject.fromObject(user);
		String userJSON = json.toString();
		System.out.println("手机号登录：" + userJSON);
		String errorString = "{\"shibai\":\"false\"}";
		System.out.println("打印User:" + user);
		if (user == null) {
			out.write(errorString);
		}else {
//			response.sendRedirect("/WangYiYun/UserSongListResommend");
			request.getRequestDispatcher("/UserSongListResommend").forward(request, response);
//			out.write(userJSON);
		}
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.print("<script language='javascript'>alert('哈哈哈哈哈哈哈哈哈哈哈');</script>");
	}
}
