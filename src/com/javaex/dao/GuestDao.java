package com.javaex.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.GuestVo;

public class GuestDao {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "webdb";
	
	private void getConnection() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pw);
		} catch(ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch(SQLException e) {
			System.out.println("error: " + e);
		}
	}
	
	
	private void close() {
		try {
			if(rs!=null) {
				rs.close();
			}
			if(pstmt!=null) {
				pstmt.close();
			}
			if(conn!=null) {
				conn.close();
			}
		} catch(SQLException e) {
			System.out.println("error: " + e);
		}
	}
	
	//내용 입력하기 
	public int guestInsert(GuestVo guestVo) {
		int count = -1;
		getConnection();
		try {
			String query = "";
			query += " insert into guestbook ";
			query += " values (SEQ_GUESTBOOK_NO.nextval, ?, ?, ?, sysdate) ";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, guestVo.getName());
			pstmt.setString(2, guestVo.getPassword());
			pstmt.setString(3, guestVo.getContent());
			
			count = pstmt.executeUpdate();
			
			// 4.결과처리
			System.out.println("[" + count + "건 추가되었습니다.]");
			
		} catch(SQLException e) {
			System.out.println("error: " + e);
		}
		close();
		return count;
	}
	
	//게스트리스트 불러오기
	public List<GuestVo> getGuestList() {
		List<GuestVo> guestList = new ArrayList<>();
		getConnection();
		try {
			String query = "";
			query += " select	no,";
			query += " 			name,";
			query += " 			password,";
			query += " 			content,";
			query += " 			to_char(reg_date, ?) ";
			query += " from guestbook";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "YYYY-MM-DD HH:MM:SS");
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int no = rs.getInt(1);
				String name = rs.getString(2);
				String password = rs.getString(3);
				String content = rs.getString(4);
				String regDate = rs.getString(5);
				
				GuestVo guestVo = new GuestVo(no, name, password, content, regDate);
				guestList.add(guestVo);
			}
		} catch(SQLException e) {
			System.out.println("error: " + e);
		}
		close();
		return guestList;
	}
	
	
	//게스트리스트 삭제
	public int guestDelete(int no) {
		int count = -1;
		getConnection();
		try {
			String query = ""; 
			query += " delete from guestbook";
			query += " where no = ?";
			
			pstmt = conn.prepareStatement(query); 
			pstmt.setInt(1, no);
			count = pstmt.executeUpdate(); 

			System.out.println(count + "건 삭제되었습니다.");
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		close();
		return count;
	}
	
	public GuestVo getGuest(int no) {
		GuestVo guestVo = new GuestVo();
		getConnection();
		try {
			String query = "";
			query += " select  no, ";
			query += " 		   name, ";
			query += " 		   password, ";
			query += " 		   content, ";
			query += " 		   to_char(reg_date, ?) ";
			query += " from guestbook ";
			query += " where no = ? ";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "YYYY-MM-DD HH:MM:SS");
			pstmt.setInt(2, no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				String name = rs.getString(2);
				String password = rs.getString(3);
				String content = rs.getString(4);
				String regDate = rs.getString(5);

				guestVo = new GuestVo(no, name, password, content, regDate);
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		close();
		return guestVo;
	}
	
}