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
import java.util.Map;
import java.util.Properties;

import com.firstjava.model.vo.ClassVO;
import com.firstjava.model.vo.MentorVO;
import com.firstjava.view.SearchForm;


public class ClassDAO {

	Connection conn;
	PreparedStatement stmt;
	ResultSet rs;
	SearchForm searchForm;
	

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
	
	public MentorVO search_mInfo(int classid) {
		System.out.println(classid);
		connect();
		try {
			String sql = "select job, major, license, plan from mentor natural join class where classid = ?";
			stmt = conn.prepareStatement(sql);
				stmt.setInt(1, classid);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				MentorVO vo = new MentorVO();
					vo.setJob(rs.getString("job"));
					vo.setMajor(rs.getString("major"));
					vo.setLicense(rs.getString("license"));	
					vo.setPlan(rs.getString("plan"));
				return vo;
			}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				disconnect();
			}
		return null;
	}//search_mInfo
	
	public int classLimit(String userid) {
		connect();
		int cnt = 0;
		try {
			String sql="select count(userid) cnt from class where userid = ?";
			stmt = conn.prepareStatement(sql);
				stmt.setString(1, userid);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				cnt = rs.getInt("cnt");
				return cnt;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return cnt;
	}//classLimit
	
	public boolean updateReview(String userid, int classid, int rate) {

		connect();
		try {

			String sql = "update register set rate = ? "
					+ "where classid = ? and userid = ?";

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, rate);
			stmt.setInt(2, classid);
			stmt.setString(3, userid);

			stmt.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return false;
	}
	
	

	public boolean cancelClass(String userid, int classid) {

		connect();
		try {

			String sql = "delete from register "
					+ "where classid = ? and userid = ?";

			stmt = conn.prepareStatement(sql);
		
			stmt.setInt(1, classid);
			stmt.setString(2, userid);

			stmt.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return false;
	}
	
	
	public void updateStudent(int classid) {
		connect();
		try {
			String sql = "update class set student = (select count(*) from register where classid = ?) where classid = ?";
			stmt = conn.prepareStatement(sql);
				stmt.setInt(1, classid);
				stmt.setInt(2, classid);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
			
	}//updateStudent
	
	public String checkMy(int no) {//자신의 강의 수강 금지
		connect();
		try {
			String sql = "select userid from class where classid = ?";
			stmt = conn.prepareStatement(sql);
				stmt.setInt(1, no);
			rs = stmt.executeQuery();
			if(rs.next()) {
				String id = rs.getString("userid");
				return id;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return null;	
	}//checkMy
	
	public int registerCheck(int classid, String userid) {//같은 id로 동일 강의 수강 금지
		connect();
		int count = 0;
		try {
			String sql = "select count(*) as cnt from register where classid = ? and userid = ?";
			stmt = conn.prepareStatement(sql);
				stmt.setInt(1, classid);
				stmt.setString(2, userid);
			rs = stmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return count;
	}//registerCheck
	
	public boolean registerClass(int classid, String userid) {
		connect();
		try {
			String sql = "insert into register (classid, userid) values (?, ?)";
			stmt = conn.prepareStatement(sql);
				stmt.setInt(1,classid);
				stmt.setString(2,userid);
			stmt.executeUpdate();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return false;
	}//registerClass
	
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
				String opendate = rs.getDate("opendate").toString();
				String closedate = rs.getDate("closedate").toString();
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
	}//searchByNo
	
	
	public ArrayList<ClassVO> searchById(String id) {//강의의 id값으로 테이블에서 선택된 강의 선택
		connect();
		ArrayList<ClassVO> list = new ArrayList<ClassVO>();

		try {
			String sql = "select classid, cname from class "
					+ "where userid = ?";
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				ClassVO vo = new ClassVO();
				vo.setClassno(rs.getInt("classid"));
				vo.setCname(rs.getString("cname"));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
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
	public boolean updateClass(ClassVO vo) {
		connect();
		try {

			String sql = "update class set classinfo=?,cateno=?,cname=?,opendate=?,closedate=?,limit=?"
					+ "where classid = ?";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, vo.getClassinfo());
			stmt.setInt(2,vo.getCateno());
			stmt.setString(3,vo.getCname());
			stmt.setString(4,vo.getOpenDate());
			stmt.setString(5,vo.getCloseDate());
			stmt.setInt(6,vo.getLimit());
			stmt.setInt(7,vo.getClassno());

			stmt.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return false;
	}
	
	
	//강의 테이블 삭제 
	public boolean delete(int id) {
		connect();
		try {
			String sql = "delete from class where classid = ?";
			stmt = conn.prepareStatement(sql);
				stmt.setInt(1, id);
			int t = stmt.executeUpdate();

			if (t == 1)
				return true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return false;
	}//delete	
	
	public int searchClassRate(int classid){ //검색
		connect();	
		
		try {
			String sql = "select classid, avg(rate) rate from register "
					+ "where classid = ? group by classid ";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, classid);
			rs = stmt.executeQuery();
			if(rs.next()){
				int rate = rs.getInt("rate");
				return rate;
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return 0;
	}
	
	
	
	public ArrayList<ClassVO> search(int no){ //검색
		connect();	
		ArrayList<ClassVO> list = new ArrayList<ClassVO>();
		
		try {
			String sql = "select classid, cname, classinfo, opendate, closedate from class where cateno = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,no);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				ClassVO vo = new ClassVO();
				vo.setClassno(rs.getInt("classid"));
				vo.setClassinfo(rs.getString("classinfo"));
				vo.setCname(rs.getString("cname"));
				vo.setOpenDate(rs.getDate("opendate").toString());
				vo.setCloseDate(rs.getDate("closedate").toString());
				vo.setCateno(no);
				list.add(vo);
			}
			
		} catch (SQLException e) {
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
				vo.setOpenDate(rs.getDate("opendate").toString());
				vo.setCloseDate(rs.getDate("closedate").toString());
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

	
	
	public ArrayList<ClassVO> searchClass(Map<String, Object> map) {
		connect();
		ArrayList<ClassVO> list = new ArrayList<ClassVO>();
		
		int columnTitle_idx =  (int) map.get("columnTitle");
		String keyword = (String) map.get("keyword");
		int columnSort_idx =  (int) map.get("columnSort");
		String sort = (String) map.get("sort");
		
		//String[] categoryTitle = { "NO", "분류", "강의명", "개강일", "종강일", "멘토명", "수강생", "정원" }
		
		String[] column = {"목록", "classid", "cname", "classinfo", "opendate", "closedate",  "userid", "student", "limit"};
		

		try {
			
			String sql = "select * from class ";
			
			switch(column[columnTitle_idx]) {
			
			case "classid" : sql += "where classid=?"; break;
			case "cname" :  sql += "where upper(cname) like upper(?)"; break;
			case "classinfo" :  sql += "where classinfo like ?"; break;
			case "opendate" :  sql += "where opendate=?"; break;
			case "closedate" : sql += "where closedate=?"; break; 
			case "userid" :  sql += "where userid like ?"; break;
			case "student" :  sql += "where student=?"; break;
			case "limit" : sql += "where limit=?"; break;
			}
			
			if (sort.equals("오름차순"))
				sql +=" order by " + column[columnSort_idx+1];
			else if (sort.equals("내림차순"))
				sql += " order by " + column[columnSort_idx+1] + " desc";
			
			stmt = conn.prepareStatement(sql);// sql문 
			
			if(column[columnTitle_idx] == "목록") {
					
			}else if(column[columnTitle_idx] == "classinfo" ||
			   column[columnTitle_idx] == "cname" ||
			   column[columnTitle_idx] == "userid" 	) {
				
				stmt.setObject(1,  "%" + keyword + "%" );// '%홍%'
				
			}else {
				stmt.setObject(1,  keyword );// '%홍%'
				
			}
			
			rs = stmt.executeQuery();// sql문 실행요청(실행시점!!)
			
			while (rs.next()) {// 행얻기
				// 열데이터 얻기
				ClassVO vo = new ClassVO();
				// 7개의 관련있는 속성데이터를 묶어주기 위해 사용.
				vo.setClassno(rs.getInt("classid"));
				vo.setClassinfo(rs.getString("classinfo"));
				vo.setUserid(rs.getString("userid"));
				vo.setCateno(rs.getInt("cateno"));
				vo.setCname(rs.getString("cname"));
				vo.setOpenDate(rs.getDate("opendate").toString());
				vo.setCloseDate(rs.getDate("closedate").toString());
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
	
	
	public boolean limitCheck(int classId) {//같은 id로 동일 강의 수강 금지
		connect();
		try {
			String sql = "select count(*) as cnt from class where classid = ? and student=limit";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, classId);
			
			rs = stmt.executeQuery();
			if(rs.next()) {
				if(rs.getInt("cnt")>0){
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return false;
	}//registerCheck
	
	public String dateCheck(int classId) {//같은 id로 동일 강의 수강 금지
		connect();
		try {
			String sql = "select opendate from class where classid = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, classId);
			
			rs = stmt.executeQuery();
			if(rs.next()) {
				return rs.getDate("opendate").toString();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return null;
	}//registerCheck
	
	
	
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
