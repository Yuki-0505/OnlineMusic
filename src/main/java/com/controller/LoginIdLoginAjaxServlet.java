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
 * 账号登录Ajax验证地址
 */
public class LoginIdLoginAjaxServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.print("<script language='javascript'>alert('嘤嘤嘤');</script>");
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		UserServiceInter userService = new UserServiceImpl();
		User user = userService.selectUserOfLoginIdAndPasswordToLogin(loginId, password);
		session.setAttribute("userInfo", user);
		JSONObject json = JSONObject.fromObject(user);
		String userJSON = json.toString();
		System.out.println("账号登录：" + userJSON);
		String errorString = "{\"shibai\":\"false\"}";
		if (user == null) {
			out.write(errorString);
		}else{
			System.out.println("账号登录==========");
			request.getRequestDispatcher("/UserSongListResommend").forward(request, response);
//				response.sendRedirect("/WangYiYun/UserSongListResommend");
			//			out.write(userJSON);
		}
	}
}
