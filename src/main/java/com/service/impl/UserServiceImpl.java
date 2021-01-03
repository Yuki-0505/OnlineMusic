package com.service.impl;

import java.util.ArrayList;

import com.dao.impl.UserDAOImpl;
import com.dao.UserDAOInter;
import com.entity.User;
import com.service.UserServiceInter;

public class UserServiceImpl implements UserServiceInter {
	private UserDAOInter userDAO = new UserDAOImpl();
	//注册
	public boolean addUser(User user) {
		return userDAO.addUser(user);
	}
	//修改
	public boolean updateUser(User user) {
		return userDAO.updateUser(user);
	}
	//账号查询
	public boolean selectUserOfLoginId(String loginId) {
		return userDAO.selectUserOfLoginId(loginId);
	}
	//用户名称名称查询
	public boolean selectUserOfUserName(String userName) {
		return userDAO.selectUserOfUserName(userName);
	}
	//手机号查询
	public boolean selectUserOfPhone(String phone) {
		return userDAO.selectUserOfPhone(phone);
	}
	//邮箱查询
	public boolean selectUserOfEmail(String email) {
		return userDAO.selectUserOfEmail(email);
	}
	//账号、密码登录
	public User selectUserOfLoginIdAndPasswordToLogin(String loginId,
			String password) {
		return userDAO.selectUserOfLoginIdAndPasswordToLogin(loginId, password);
	}
	//手机号、密码登录
	public User selectUserOfPhoneAndPasswordToLogin(String phone,
			String password) {
		return userDAO.selectUserOfPhoneAndPasswordToLogin(phone, password);
	}
	//查询，根据用户编号查询用户信息
	public User selectUserInfoOfUserId(int userId) {
		User user = userDAO.selectUserInfoOfUserId(userId);
		return user;
	}
	//修改/上传用户头像
	public boolean updateUserHeaderImg(String headSculptureUrl, int userId){
		boolean updateOrNot = userDAO.updateUserHeaderImg(headSculptureUrl, userId);
		return updateOrNot;
	}
	
	//无条件查询用户（用于计算用户个数）
	public ArrayList<User> selectAllUserInfo(){
		ArrayList<User> users = userDAO.selectAllUserInfo();
		return users;
	}
	
	//分页查询所有用户账号信息
	public ArrayList<User> selectAllUserInfoLimit(int startRowNum,
			int showRowsNum){
		ArrayList<User> userList = userDAO.selectAllUserInfoLimit(startRowNum, showRowsNum);
		return userList;
	}
	
	//修改用户userStateId值
	public boolean updateUserStateId(int userStateId, int userId){
		boolean updateOrNot = userDAO.updateUserStateId(userStateId, userId);
		return updateOrNot;
	}
}
