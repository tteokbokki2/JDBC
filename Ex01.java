package com.test.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class Ex01 {
	
	public static void main(String[] args) {
		
		/*
			2. DB 서버 접속
			- JDBC > Connection 클래스 사용
			2.1 호스트명: 서버IP or 도메인주소 > localhost
			2.2 포트번호: 1521
			2.3 SID: xe
			2.4 드라이버: thin
			2.5 사용자: hr
			2.6 암호: java1234		
		*/
		
		Connection conn = null;
		
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
			
			//접속 상태 확인
			System.out.println(conn.isClosed()); //false
			
			//질의 + 업무..
			System.out.println("질의(SQL) 실행");
			
			//접속 종료
			conn.close();
			
			System.out.println(conn.isClosed()); //true
			
			/*
				오류
				
				1. 서버 주소 오류
				- IO 오류: The Network Adapter could not establish the connection
				- java.net.UnknownHostException: 알려진 호스트가 없습니다(localhost2)
				
				2. 포트번호 오류
				- IO 오류: The Network Adapter could not establish the connection
				- Connection refused: connect
				
				3.  연결 문자열 오류
				- 부적합한 Oracle URL이 지정되었습니다.
				
				4. SID 오류
				- TNS:listener does not currently know of SID given in connect descriptor
				
				5. 아이디 또는 암호 오류
				- ORA-01017: invalid username/password; logon denied
			
				6. 드라이버 오류
				- java.lang.ClassNotFoundException: oracle.jdbc.driver.oracleDriver
				
				7. 오라클 중지
				- Listener refused the connection with the following error:
				
				8. ojdbc.jar 미설치(6번과 동일)
				- java.lang.ClassNotFoundException: oracle.jdbc.driver.oracleDriver				
			*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
