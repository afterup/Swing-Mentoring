package com.firstjava.model.dao;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.firstjava.model.vo.MemberVO;

public class MemberDAO {

	Connection conn;
	PreparedStatement stmt;
	ResultSet rs;

	Properties pro;

	public MemberDAO() {
		try {
			pro = new Properties();
			pro.load(new FileReader("conn/conn.properties"));
			Class.forName(pro.getProperty("driver"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}// 생성자

	public MemberVO findById(String id) {// 회원정보 수정(폼)에 필요한 데이터 조회(검색)

		connect();
		MemberVO vo = null;// 조회된 결과행이 없음을 표현
		try {
			String sql = "select userid,uname,email,phone from member where id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);
			rs = stmt.executeQuery();

			if (rs.next()) {

				String name = rs.getString("name");
				String phone = rs.getString("phone");
				String email = rs.getString("email");

				vo = new MemberVO(id, null, name, email, phone);
				return vo;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return null;

	}// findById

	public int findExistId(String id) {
		connect();
		int count = 0;
		try {
			String sql = "select count(*) as count from member where userid= ?";

			stmt = conn.prepareStatement(sql);// sql문 DB에 전송
			// stmt.set자료형(물음표인덱스1~, 설정데이터);//?(바인드변수)에 대한 데이터 설정
			stmt.setString(1, id);
			rs = stmt.executeQuery();// 전송한 sql문 실행요청
			if (rs.next()) {
				count = rs.getInt("count");// rs.getInt(인덱스1,2,3..또는 "컬럼명" 또는 "별명")
				System.out.println(count);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return count;
	}// findExistId

	public boolean findLogin(String id, String pass) {

		connect();
		try {
			String sql = "select count(*) cnt from member where userid = ? and password = ?";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);
			stmt.setString(2, pass);

			rs = stmt.executeQuery();

			if (rs.next()) {
				if (rs.getInt("cnt") == 1) {
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return false;
	}// findLogin

	public boolean memberJoin(MemberVO m) {

		connect();
		try {

			String sql = "insert into member values (?,?,?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, m.getUserId());
			stmt.setString(2, m.getPassword());
			stmt.setString(3, m.getUname());
			stmt.setString(4, m.getEmail());
			stmt.setString(5, m.getPhone());

			stmt.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return false;
	}// insert

	public String findId(String name, String email) {
		connect();
		String id="";
		try {
			String sql = "select userid  FROM member " + "where uname= ? and email = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, name);
			stmt.setString(2, email);

			rs = stmt.executeQuery();

			if (rs.next()) {
				id = rs.getString("userid");
				System.out.println(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return id;

	}

	public String memberDelete(String name) {
		connect();

		try {

			String sql = "DELETE FROM member WHERE userid=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, name);

			stmt.executeUpdate();
			return "강퇴되었습니다.";
		} catch (SQLException e) {
			disconnect();
		}
		return "강퇴에 실패하였습니다.";

	}

	public ArrayList<MemberVO> MemberTable() { // 회원정보 전체조회
		connect();
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		try {
			String sql = "SELECT userid, uname, email, phone FROM member";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				MemberVO vo = new MemberVO();
				vo.setUserId(rs.getString("userid"));
				vo.setUname(rs.getString("uname"));
				vo.setEmail(rs.getString("email"));
				vo.setPhone(rs.getString("phone"));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}// MemberTable

	private void connect() {
		try {
			conn = DriverManager.getConnection(pro.getProperty("url"), pro);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}// connect

	private void disconnect() {
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}// disconnect

}
