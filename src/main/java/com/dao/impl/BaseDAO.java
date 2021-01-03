package com.dao.impl;

import com.mysql.jdbc.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//实现数据连接和资源关闭操作
public class BaseDAO {
    private static Connection connection = null;
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://127.0.0.1:3306/mcmusic";
    private static final String user = "root";
    private static final String password = "root";//本地数据库密码
    static {
        try {
            Class.forName(driver);
            connection = (Connection) DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static Connection getConnection(){
        return connection;
    }
    //资源关闭操作
    public static void close(java.sql.Connection con, PreparedStatement preparedStatement, ResultSet rs){
        Thread threadClose = new BaseThreadClose(con, preparedStatement, rs);
    }
}

//关闭数据库资源线程类
class BaseThreadClose extends Thread{
    private Connection connection;
    private PreparedStatement pstmt;
    private ResultSet resultSet;
    public BaseThreadClose(java.sql.Connection con, PreparedStatement pstmt, ResultSet resultSet){//构造函数
        this.connection = (Connection) con;
        this.pstmt = pstmt;
        this.resultSet = resultSet;
    }
    @Override
    public void run() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
