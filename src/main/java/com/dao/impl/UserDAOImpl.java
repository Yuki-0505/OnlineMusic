package com.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.dao.UserDAOInter;
import com.entity.User;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class UserDAOImpl extends BaseDAO implements UserDAOInter {
	//注册
	public boolean addUser(User user) {
		boolean addTf = false;
		Connection connection = getConnection();
		if (connection != null) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				String sql = "insert into user(" +
						LOGINID + "," + PASSWORD + "," +
						USERNAME + "," + USERSEX + "," +
						EMAIL + "," + PHONE + "," + SIGN + "," +
						HEADSCULPTUREURL + "," + REGISTATIONDATE  + ") values(?,?,?,?,?,?,?,?,?)";
				pstmt = connection.prepareStatement(sql);
				pstmt.setString(1, user.getLoginId());
				pstmt.setString(2, user.getPassword());
				pstmt.setString(3, user.getUserName());
				pstmt.setString(4, user.getUserSex());
				pstmt.setString(5, user.getEmail());
				pstmt.setString(6, user.getPhone());
				pstmt.setString(7, user.getSign());
				pstmt.setString(8, user.getHeadSculptureUrl());
				pstmt.setString(9, user.getRegistationDate());
				int row = pstmt.executeUpdate();
				if (row > 0) {
					addTf = true;
				}else {
					addTf = false;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				addTf = false;
			}finally{
				close(connection, pstmt, rs);
			}
		}else {
			addTf = false;
		}
		return addTf;
	}
	//修改
	public boolean updateUser(User user) {
		boolean updateTf = false;
		Connection connection = getConnection();
		if (connection != null) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				String sql = "update user set userName = ?, userSex = ?, email = ?, phone = ?, sign = ? where userId = ?";
				pstmt = connection.prepareStatement(sql);
				pstmt.setString(1, user.getUserName());
				pstmt.setString(2, user.getUserSex());
				pstmt.setString(3, user.getEmail());
				pstmt.setString(4, user.getPhone());
				pstmt.setString(5, user.getSign());
				pstmt.setInt(6, user.getUserId());
				int row = pstmt.executeUpdate();
				if (row > 0) {
					updateTf = true;
				}else {
					updateTf = false;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				updateTf = false;
			}finally{
				close(connection, pstmt, rs);
			}
		}else { 
			updateTf = false;
		}
		return updateTf;
	}
	//查询（根据用户账号查找）用于Ajax判断账号是否已经被注册
	public boolean selectUserOfLoginId(String loginId) {
		boolean selectTf = true;
		Connection connection = getConnection();
		if (connection != null) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "select userId from user where " + LOGINID + " = ?";
				pstmt = connection.prepareStatement(sql);
				pstmt.setString(1, loginId);
				rs = pstmt.executeQuery();
				if (rs != null && rs.next()) {
					selectTf = false;
				}else {
					selectTf = true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				selectTf = true;
			}finally{
				close(connection, pstmt, rs);
			}
		}else {
			selectTf = true;
		}
		return selectTf;//返回值为false说明该账号不可使用（查询值不为空）
	}
	//查询（根据用户名称查找）用于Ajax判断此名称是否已经被使用
	public boolean selectUserOfUserName(String userName) {
		boolean selectTf = true;
		Connection connection = getConnection();
		if (connection != null) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "select userId from user where " + USERNAME + " = ?";
				pstmt = connection.prepareStatement(sql);
				pstmt.setString(1, userName);
				rs = pstmt.executeQuery();
				if (rs != null && rs.next()) {
					selectTf = false;
				}else {
					selectTf = true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				selectTf = true;
			}finally{
				close(connection, pstmt, rs);
			}
		}else {
			selectTf = true;
		}
		return selectTf;
	}
	//查询（根据手机号查询）用于Ajax判断此手机号是否被绑定
	public boolean selectUserOfPhone(String phone) {
		boolean selectTf = true;
		Connection connection = getConnection();
		if (connection != null) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "select userId from user where " + PHONE + " = ?";
				pstmt = connection.prepareStatement(sql);
				pstmt.setString(1, phone);
				rs = pstmt.executeQuery();
				if (rs != null && rs.next()) {
					selectTf = false;
				}else {
					selectTf = true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				selectTf = true;
			}finally{
				close(connection, pstmt, rs);
			}
		}else {
			selectTf = true;
		}
		return selectTf;
	}
	//查询（根据邮箱查询）Ajax判断邮箱是否被使用
	public boolean selectUserOfEmail(String email) {
		boolean selectTf = true;
		Connection connection = getConnection();
		if (connection != null) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "select userId from user where " + EMAIL + " = ?";
				pstmt = connection.prepareStatement(sql);
				pstmt.setString(1, email);
				rs = pstmt.executeQuery();
				if (rs != null && rs.next()) {
					selectTf = false;
				}else {
					selectTf = true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				selectTf = true;
			}finally{
				close(connection, pstmt, rs);
			}
		}else {
			selectTf = true;
		}
		return selectTf;
	}
	//查询（根据账号、密码查找）用于账号密码登录
	public User selectUserOfLoginIdAndPasswordToLogin(String loginId,
			String password) {
		Connection connection = getConnection();
		User user = null;
		if (connection != null) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				String sql = "select * from user where " + LOGINID + " = ? and " + PASSWORD + " = ?";
				pstmt = connection.prepareStatement(sql);
				pstmt.setString(1, loginId);
				pstmt.setString(2, password);
				rs = pstmt.executeQuery();
				if (rs != null && rs.next()) {
					user = new User();
					user.setUserId(rs.getInt(USERID));
					user.setLoginId(rs.getString(LOGINID));
					user.setPassword(rs.getString(PASSWORD));
					user.setUserName(rs.getString(USERNAME));
					user.setUserSex(rs.getString(USERSEX));
					user.setEmail(rs.getString(EMAIL));
					user.setPhone(rs.getString(PHONE));
					user.setUserType(rs.getInt(USERTYPE));
					user.setSign(rs.getString(SIGN));
					user.setHeadSculptureUrl(rs.getString(HEADSCULPTUREURL));
					user.setRegistationDate(rs.getString(REGISTATIONDATE));
					user.setUserStateId(rs.getInt(USERSTATEID));
				}else {
					user = null;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				user = null;
			}finally{
				close(connection, pstmt, rs);
			}
		}else {
			user = null;
		}
		return user;
	}
	//查询（根据手机号、密码查找）用于手机号密码登录
	public User selectUserOfPhoneAndPasswordToLogin(String phone,
			String password) {
		Connection connection = getConnection();
		User user = null;
		if (connection != null) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				String sql = "select * from user where " + PHONE + " = ? and " + PASSWORD + " = ?";
				pstmt = connection.prepareStatement(sql);
				pstmt.setString(1, phone);
				pstmt.setString(2, password);
				rs = pstmt.executeQuery();
				if (rs != null && rs.next()) {
					user = new User();
					user.setUserId(rs.getInt(USERID));
					user.setLoginId(rs.getString(LOGINID));
					user.setPassword(rs.getString(PASSWORD));
					user.setUserName(rs.getString(USERNAME));
					user.setUserSex(rs.getString(USERSEX));
					user.setEmail(rs.getString(EMAIL));
					user.setPhone(rs.getString(PHONE));
					user.setUserType(rs.getInt(USERTYPE));
					user.setSign(rs.getString(SIGN));
					user.setHeadSculptureUrl(rs.getString(HEADSCULPTUREURL));
					user.setRegistationDate(rs.getString(REGISTATIONDATE));
					user.setUserStateId(rs.getInt(USERSTATEID));
				}else {
					user = null;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				user = null;
			}finally{
				close(connection, pstmt, rs);
			}
		}else {
			user = null;
		}
		return user;
	}
	//根据用户编号查询用户信息
	public User selectUserInfoOfUserId(int userId) {
		Connection connection = getConnection();
		User user = new User();
		if (connection != null) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "select * from user where userId = ?";
				pstmt = connection.prepareStatement(sql);
				pstmt.setInt(1, userId);
				rs = pstmt.executeQuery();
				if (rs != null && rs.next()) {
					user.setUserId(rs.getInt(USERID));
					user.setLoginId(rs.getString(LOGINID));
					user.setPassword(rs.getString(PASSWORD));
					user.setUserName(rs.getString(USERNAME));
					user.setUserSex(rs.getString(USERSEX));
					user.setEmail(rs.getString(EMAIL));
					user.setPhone(rs.getString(PHONE));
					user.setUserType(rs.getInt(USERTYPE));
					user.setSign(rs.getString(SIGN));
					user.setHeadSculptureUrl(rs.getString(HEADSCULPTUREURL));
					user.setRegistationDate(rs.getString(REGISTATIONDATE));
					user.setUserStateId(rs.getInt(USERSTATEID));
				}else {
					user = null;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				user = null;
			}finally{
				close(connection, pstmt, rs);
			}
			
		}else {
			user = null;
		}
		return user;
	}
	
	//修改/上传用户头像
	public boolean updateUserHeaderImg(String headSculptureUrl, int userId){
		Connection connection = getConnection();
		int row = 0;
		boolean updateOrNot = false;
		if (connection != null) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "update user set headSculptureUrl = ? where userId = ?";
				pstmt = connection.prepareStatement(sql);
				pstmt.setString(1, headSculptureUrl);
				pstmt.setInt(2, userId);
				
				row = pstmt.executeUpdate();
				if (row > 0) {
					updateOrNot = true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				close(connection, pstmt, rs);
			}
		}
		return updateOrNot;
	}
	
	//无条件查询用户（用于计算用户个数）
	public ArrayList<User> selectAllUserInfo(){
		Connection connection = getConnection();
		ArrayList<User> users = new ArrayList<User>();
		
		if (connection != null) {
			Statement statement = null;
			ResultSet rs = null;
			
			try {
				String sql = "select userId from user";
				statement = (Statement) connection.createStatement();
				rs = statement.executeQuery(sql);
				if (rs != null) {
					while ( rs.next() ) {
						User user = new User();
						user.setUserId(rs.getInt(USERID));
						users.add(user);
					}
				}else {
					users = null;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				users = null;
			}
		}else {
			users = null;
		}
		return users;
	}
	
	//分页查询所有用户账号信息
	public ArrayList<User> selectAllUserInfoLimit(int startRowNum,
			int showRowsNum) {
		Connection connection = getConnection();
		ArrayList<User> userList = new ArrayList<User>();
		
		if (connection != null) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "select * from user limit ?, ?";
				pstmt = connection.prepareStatement(sql);
				pstmt.setInt(1, startRowNum);
				pstmt.setInt(2, showRowsNum);
				
				rs = pstmt.executeQuery();
				if (rs != null) {
					while (rs.next()) {
						User user = new User();
						user.setUserId(rs.getInt(USERID));
						user.setLoginId(rs.getString(LOGINID));
						user.setPassword(rs.getString(PASSWORD));
						user.setUserName(rs.getString(USERNAME));
						user.setUserSex(rs.getString(USERSEX));
						user.setEmail(rs.getString(EMAIL));
						user.setPhone(rs.getString(PHONE));
						user.setUserType(rs.getInt(USERTYPE));
						user.setSign(rs.getString(SIGN));
						user.setHeadSculptureUrl(rs.getString(HEADSCULPTUREURL));
						user.setRegistationDate(rs.getString(REGISTATIONDATE));
						user.setUserStateId(rs.getInt(USERSTATEID));
						userList.add(user);
					}
				}else {
					userList = null;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				userList = null;
			}finally{
				close(connection, pstmt, rs);
			}
		}else {
			userList = null;
		}
		return userList;
	}
	//修改用户userStateId值
	public boolean updateUserStateId(int userStateId, int userId){
		Connection connection = getConnection();
		int row = 0;
		boolean updateOrNot = false;
		if (connection != null) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "update user set userStateId = ? where userId = ?";
				pstmt = connection.prepareStatement(sql);
				pstmt.setInt(1, userStateId);
				pstmt.setInt(2, userId);
				row = pstmt.executeUpdate();
				if (row > 0) {
					updateOrNot = true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				updateOrNot = false;
			}finally{
				close(connection, pstmt, rs);
			}
			
		}
		return updateOrNot;
	}
}
