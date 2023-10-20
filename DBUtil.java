package com.test.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
	
	private static Connection conn;
	
	public static Connection open() {
				
		//연결 문자열, Connection String
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "hr";
		String pw = "java1234";
		
		try {
			
			//외부 입출력 작업 시 예외 처리 필수
			
			//JDBC 드라이버 로딩(관련 클래스 정보)
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//Connection 객체 생성 + 오라클 접속 완료
			conn = DriverManager.getConnection(url, id, pw);
			
			return conn;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}

	public static Connection open(String server, String id, String pw) {
		
		//프로젝트 시 사용
		String url = "jdbc:oracle:thin:@" + server + ":1521:xe";
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection(url, id, pw);
			
			return conn;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}

}
