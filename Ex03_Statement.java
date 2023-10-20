package com.test.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;


public class Ex03_Statement {
	
	public static void main(String[] args) {
		
		/*
		Ex03_Statement.java
		
		1. Connection - 연결 / 종료
		2. Statement - 모든 SQL을 실행하는 역할
		
		Statement 종류
		1. Statement
			- 기본형
			- 매개 변수 처리가 번거로움
			- 안정성 및 가독성 낮음
		
		2. PreparedStatement
			- 개량형
			- 매개 변수 처리에 특화
			- 안정성 및 가독성 높음
		
		3. CallableStatement
			- 개량형
			- 프로시저 호출 전용
		 */

		//m1(); //insert
		//m2(); //upadte
		//m3(); //delete
		//m4(); //create
		
		//m5();
		
		/*
			select
			1. 단일값 반환 > 1행 1열
			2. 다중값 반환 > 1행 N열
			3. 다중값 반환 > N행 1열
			4. 다중값 반환 > N행 N열
		*/
		//m6();
		//m7();
		//m8();
		m9();
		
	
	}//main
	

	/*
	private static void m6() {
		
		//1. 단일값 반환 > 1행 1열
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			
			rs.close();
			stat.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	*/
	
	private static void m9() {
		
		//4. 다중값 반환 > N행 N열
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			String sql = "select * from tblAddress";
			
			rs = stat.executeQuery(sql);
			
			System.out.println("[번호]\t[이름]\t[나이]\t[성별]\t[주소]\t\t\t\t[날짜]");
			
			
			while (rs.next()) {
				System.out.printf("%s\t%s\t%s\t%s\t%s\t%s\t\n"
								, rs.getString("seq")
								, rs.getString("name")
								, rs.getString("age")
								, rs.getString("gender")
								, rs.getString("address")
								, rs.getString("regdate"));
			}
			
			rs.close();
			stat.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void m8() {
		
		//3. 다중값 반환 > N행 1열
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			String sql = "select name from tblAddress";
			
			rs = stat.executeQuery(sql);
			
			/*		
			rs.next();			
			System.out.println(rs.getString("name"));
			rs.next();
			System.out.println(rs.getString("name"));
			*/
			
			while (rs.next()) {
				System.out.println(rs.getString("name"));
			}
			
			rs.close();
			stat.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
	private static void m7() {
		
		//2. 다중값 반환 > 1행 N열
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("번호: ");
		String seq = scan.nextLine();
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			String sql = "select * from tblAddress where seq = " + seq;
			
			rs = stat.executeQuery(sql);
			
			if (rs.next()) {
				System.out.println("번호: " + rs.getString("seq"));
				System.out.println("이름: " + rs.getString("name"));
				System.out.println("나이: " + rs.getString("age"));
				System.out.println("성별: " + rs.getString("gender"));
				System.out.println("주소: " + rs.getString("address"));
				System.out.println("날짜: " + rs.getString("regdate"));
			} else {
				System.out.printf("입력한 %s번의 데이터가 없습니다\n", seq);
			}
			
			rs.close();
			stat.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	private static void m6() {
		
		//1. 단일값 반환 > 1행 1열
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.open();
			stat = conn.createStatement();
	
			String sql = "select count(*) as cnt from tblAddress";
			
			rs = stat.executeQuery(sql);
			
			//System.out.println(rs);
			
			//ResultSet > Iterator, 향상된 for, 스트림, 커서
			
			rs.next(); //커서 1줄 전진
			
			//현재 커서가 가르키고 있는 레코드의 워하는 컬럼을 접근해서 데이터를 가져오기
			//rs.getXXX();
			//int count = rs.getInt(1); //컬럼 위치(index), 1부터 시작
			int count = rs.getInt("cnt"); //컬럼명
			
			System.out.println(count);
			
			//자원 해제 - 만들 때의 역순으로 해제
			rs.close();
			stat.close();
			conn.close();		
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private static void m5() {
		
		//tblAddress > 새로운 사람 등록하기

		Scanner scan = new Scanner(System.in);
		
		String name = "";
		String age = "";
		String gender = "";
		String address = "";
		
		Connection conn = null;
		Statement stat = null;
		
		try {
			
			System.out.print("이름: ");
			name = scan.nextLine();
			
			System.out.print("나이: ");
			age = scan.nextLine();
			
			System.out.print("성별(m,f): ");
			gender = scan.nextLine();
			
			System.out.print("주소: ");
			address = scan.nextLine();
			
			conn = DBUtil.open();
			
			if(!conn.isClosed()) {
				System.out.println("DB 접속 성공");
				
				String sql = String.format("insert into tblAddress (seq, name, age, gender, address, regdate) values (seqAddress.nextval, '%s', %s, '%s', '%s', default)", name, age, gender, address);
				
				stat = conn.createStatement();

				int result = stat.executeUpdate(sql);
				
				if (result == 1) {
					System.out.println("삽입 성공");
				} else {
					System.out.println("삽입 실패");
				}
				
				stat.close();
				conn.close();
				
			} else {
				System.out.println("DB 접속 실패");				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void m4() {
		
		//반환값 X > create 실행
		Connection conn = null;
		Statement stat = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			String sql = "create table tblAddress (\r\n"
					+ "    seq number primary key,\r\n"
					+ "    name varchar2(30) not null,\r\n"
					+ "    age number not null,\r\n"
					+ "    gender char(1) not null,\r\n"
					+ "    address varchar2(300) not null,\r\n"
					+ "    regdate date default sysdate not null\r\n"
					+ ")";
			
			
			//create일 땐 아래 코드는 신경 안써도 됨, 실행 후 SQL로 확인하면 만들어져 있음
			int result = stat.executeUpdate(sql);
			
			if (result > 0) {
				System.out.println("삭제 성공");
			} else {
				System.out.println("삭제 실패");				
			}
			
			stat.close();			
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private static void m3() {
		
		//반환값 X > delete 실행
		Connection conn = null;
		Statement stat = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			String sql = "delete from tblAddress where seq = 1";
			
			int result = stat.executeUpdate(sql);
			
			if (result > 0) {
				System.out.println("삭제 성공");
			} else {
				System.out.println("삭제 실패");				
			}
					
			stat.close();			
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private static void m2() {
		
		//반환값 X > update 실행
		Connection conn = null;
		Statement stat = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			String sql = "update tblAddress set age = age + 1";
			
			int result = stat.executeUpdate(sql);
			
			if (result > 0) {
				System.out.println("수정 성공");
			} else {
				System.out.println("수정 실패");				
			}
					
			
			stat.close();			
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private static void m1() {
		
		//반환값 X > insert 실행
		
		Connection conn = null;
		Statement stat = null;
		
		try {
			
			conn = DBUtil.open();
			
			if(!conn.isClosed()) {
				System.out.println("DB 접속 성공");
				
				//SQL 실행해야하지만 자바는 SQL을 모름, SQL로 문자열로 취급해야함
				
				//Servlet(자바)때 HTML,CSS,JavaScript를 몰라서 "<html>"로 표기한 거랑 같은 상황
				
				//ORA-00911: invalid character 오류 > 한번에 하나의 문장만 넣기(문장 끝 ; 삭제)
				String sql = "insert into tblAddress (seq, name, age, gender, address, regdate) values (seqAddress.nextval, '아무개', 22, 'm', '서울시 강남구 대치동', default)";
				
				//stat > SQL 실행하는 역할
				stat = conn.createStatement();
				
				
				/*
					1. 반환값이 없는 쿼리 > select 빼고 나머지 SQL
						- int stat.executeUpdate(String sql)
					
					2. 반환값이 있는 쿼리
						- ResultSet stat.executeQuery(String sql)
				*/
				
				
				//SQL Developer 상 Ctrl + Enter와 동일
				//적용된 행의 개수(피드백 값)
				int result = stat.executeUpdate(sql);
				
				if (result == 1) {
					System.out.println("삽입 성공");
				} else {
					System.out.println("삽입 실패");
				}
				
				
				//사용 후 자원 해제(정리)
				stat.close();
				conn.close();
				
			} else {
				System.out.println("DB 접속 실패");				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}//m1
	
	
}
