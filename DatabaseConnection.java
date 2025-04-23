package com.movie.common;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DatabaseConnection {

	 	private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:XE";  // 예시: localhost, 1521 포트, SID = XE
	    private static final String DB_USER = "MOVIE";  // 오라클 사용자명
	    private static final String DB_PASSWORD = "movie";
	    
	    private Connection connection;
	    
	    public DatabaseConnection() {
	        try {
	            // 오라클 JDBC 드라이버 로딩
	            Class.forName("oracle.jdbc.OracleDriver");

	            // 연결에 사용할 Properties 객체 생성
	            Properties connectionProps = new Properties();
	            connectionProps.put("user", DB_USER);  // 사용자명 설정
	            connectionProps.put("password", DB_PASSWORD);  // 비밀번호 설정

	            // 데이터베이스 연결
	            connection = DriverManager.getConnection(DB_URL, connectionProps);

	            System.out.println("Database connection established successfully.");
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	            System.out.println("Oracle JDBC Driver not found.");
	        } catch (SQLException e) {
	            e.printStackTrace();
	            System.out.println("Failed to connect to the database.");
	        }
	    }
	
}
