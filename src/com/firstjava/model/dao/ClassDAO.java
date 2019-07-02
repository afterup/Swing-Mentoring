package com.firstjava.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.firstjava.model.vo.ClassVO;


public class ClassDAO {

	Connection conn;
	PreparedStatement stmt;
	ResultSet rs;

	Properties pro;// DB접속관련 정보 저장 객체

	public ClassDAO() {
		try {
			pro = new Properties();
			pro.load(new FileReader("conn/conn.properties"));
			Class.forName(pro.getProperty("driver"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}// 생성자
	
	
	public ClassVO searchByNo(int no) {//강의의 no값으로 테이블에서 선택된 강의 선택
		connect();
		ClassVO vo = null;
		try {
			String sql = "select cname, cateno, limit, opendate, closedate, classinfo from class where classid = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, no);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				String cname = rs.getString("cname");
				int cateno = rs.getInt("cateno");
				int limit = rs.getInt("limit");
				String opendate = rs.getString("opendate");
				String closedate = rs.getString("closedate");
				String classinfo = rs.getString("classinfo");	
				
				vo = new ClassVO(no, null, classinfo, cateno, cname, opendate, closedate, 0, limit);
				return vo;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return null;
	}//searchById
	
	public boolean createClass(ClassVO c) {

		connect();
		try {
			//(class_seq.nextval, '자바 강의입니다','solbi94', 1, 'JAVA','19/07/01','19/07/08',0,10)
			String sql = "insert into class values (class_seq.nextval,?,?,?,?,?,?,0,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, c.getClassinfo());
			stmt.setString(2, c.getUserid());
			stmt.setInt(3, c.getCateno());
			stmt.setString(4, c.getCname());
			stmt.setString(5, c.getOpenDate());
			stmt.setString(6, c.getCloseDate());
			stmt.setInt(7, c.getLimit());
			
			stmt.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return false;
	}//createClass
	
	//강의 테이블 수정
	public void update() {}
	
	
	//강의 테이블 삭제 
	public boolean delete(int id) {

		connect();

		try {
			String sql = "delete from class " + "where classid=?";

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);

			int t = stmt.executeUpdate();

			if (t == 1)
				return true;

			System.out.println("삭제성공");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			disconnect();
		}
		return false;

	}	
	

	public ArrayList<ClassVO> search(String category) { // 검색
		connect();	
		ArrayList<ClassVO> list = new ArrayList<ClassVO>();
		
		try {
			String sql = "select  * from class " + "where lower(cname) like lower(?)";
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, "%" + category + "%");

			rs = stmt.executeQuery();

			while (rs.next()) {

				ClassVO vo = new ClassVO();
				vo.setClassno(rs.getInt("classid"));
				vo.setClassinfo(rs.getString("classinfo"));
				vo.setUserid(rs.getString("userid"));
				vo.setCateno(rs.getInt("cateno"));
				vo.setCname(rs.getString("cname"));
				vo.setOpenDate(rs.getString("opendate"));
				vo.setCloseDate(rs.getString("closedate"));
				vo.setStudent(rs.getInt("student"));
				vo.setLimit(rs.getInt("limit"));

				list.add(vo);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return list;
	}

	public ArrayList<ClassVO> findAll() { // 전체검색
		connect();
		ArrayList<ClassVO> list = new ArrayList<ClassVO>();
		try {
			String sql = "select * from class";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();//
			// 덩어리

			while (rs.next()) {// 행얻기
				// 열데이터 얻기
				ClassVO vo = new ClassVO();
				// 7개의 관련있는 속성데이터를 묶어주기 위해 사용.

				vo.setClassno(rs.getInt("classid"));
				vo.setClassinfo(rs.getString("classinfo"));
				vo.setUserid(rs.getString("userid"));
				vo.setCateno(rs.getInt("cateno"));
				vo.setCname(rs.getString("cname"));
				vo.setOpenDate(rs.getString("opendate"));
				vo.setCloseDate(rs.getString("closedate"));
				vo.setStudent(rs.getInt("student"));
				vo.setLimit(rs.getInt("limit"));

				list.add(vo);
			} // while
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return list;
	}// findAll

	private void connect() {// 연결객체생성
		try {
			conn = DriverManager.getConnection(pro.getProperty("url"), pro);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void disconnect() {// DB자원반환
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}// ClassDAO
