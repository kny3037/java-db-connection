package com.dbexercise;


import com.dbexercise.domain.User;

import java.sql.*;
import java.util.Map;

public class UserDao {

    public void add() throws ClassNotFoundException, SQLException {
        Map<String, String> env = System.getenv();
        String dbhost = env.get("DB_HOST");
        String dbUser = env.get("DB_USER");
        String dbPassword = env.get("DB_PASSWORD");

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(dbhost,dbUser, dbPassword);
        PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO users(id, name, password) value (?,?,?)"
        );
        ps.setString(1,"0");
        ps.setString(2,"nayeong");
        ps.setString(3,"1123");

        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    public User select(String id) throws SQLException {
        Map<String, String> env = System.getenv();
        Connection connection = DriverManager.getConnection(
                env.get("DB_HOST"), env.get("DB_USER"), env.get("DB_PASSWORD")
        );
        PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM users where id = ?");
        pstmt.setString(1,id);
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        User user = new User(rs.getString("id"), rs.getString("name"),rs.getString("password"));
        rs.close();
        pstmt.close();
        connection.close();

        return user;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserDao userDao = new UserDao();
        User user = userDao.select("0");
        System.out.println(user.getName());

    }

}
