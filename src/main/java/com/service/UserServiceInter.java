package com.service;

import java.util.ArrayList;

import com.entity.User;

public interface UserServiceInter {
	//增加（注册）
		public boolean addUser(User user);
		//修改（用户修改自己信息）
		public boolean updateUser(User user);
		//查询（根据用户账号查找）用于Ajax判断账号是否已经被注册
		public boolean selectUserOfLoginId(String loginId);
		//查询（根据用户名称查找）用于Ajax判断此名称是否已经被使用
		public boolean selectUserOfUserName(String userName);
		//查询（根据手机号查询）用于Ajax判断此手机号是否被绑定
		public boolean selectUserOfPhone(String phone);
		//查询（根据邮箱）用于Ajax查询
		public boolean selectUserOfEmail(String email);
		//查询（根据账号、密码查找）用于账号密码登录
		public User selectUserOfLoginIdAndPasswordToLogin(String loginId, String password);
		//查询（根据手机号、密码查找）用于手机号密码登录
		public User selectUserOfPhoneAndPasswordToLogin(String phone, String password);
		//查询，根据用户编号查询用户信息
		public User selectUserInfoOfUserId(int userId);
		//修改/上传用户头像
		public boolean updateUserHeaderImg(String headSculptureUrl, int userId);
		//无条件查询用户（用于计算用户个数）
		public ArrayList<User> selectAllUserInfo();
		//分页查询所有用户账号信息
		public ArrayList<User> selectAllUserInfoLimit(int startRowNum,
				int showRowsNum);
		
		//修改用户userStateId值
		public boolean updateUserStateId(int userStateId, int userId);
}
