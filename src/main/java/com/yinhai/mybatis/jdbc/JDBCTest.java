package com.yinhai.mybatis.jdbc;

import java.sql.*;

public class JDBCTest {
    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            //加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //获取连接
            String url = "jdbc:mysql://127.0.0.1:3306/ssmdemo?characterEncoding=utf-8&serverTimezone=UTC";
            String user = "root";
            String password = "root";
            connection = DriverManager.getConnection(url,user,password);
            //获取statement，preparedStatement
            String sql = "select * from tb_user where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            //设置参数
            preparedStatement.setLong(1,1L);
            //执行查询
            rs = preparedStatement.executeQuery();
            //处理结果集
            while (rs.next()){
                System.out.println(rs.getString("user_name"));
                System.out.println(rs.getString("name"));
                System.out.println(rs.getInt("age"));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(rs != null) {
                rs.close();
            }
            if(preparedStatement != null){
                preparedStatement.close();
            }
            if(connection != null) {
                connection.close();
            }
        }
    }
}
