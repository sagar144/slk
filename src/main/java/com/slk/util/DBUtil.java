package com.slk.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil{
public static Connection getConnection() throws Exception { 
    String uname="com.mysql.cj.jdbc.Driver"; 
    String url="jdbc:mysql://localhost:3306/mybank?useSSL=false"; 
    String username="root"; 
    String password="1234"; 
    Class.forName(uname); 
    Connection con = DriverManager.getConnection(url, username, password);  
	return con; 
} 
}