package com.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProfilDAO {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	String userid = "scott";
	String passwd = "123456";
	
	public ProfilDAO() {
		try{
			Class.forName(driver);
		}catch(Exception e){e.printStackTrace();}
	}
	public boolean isExist(String name){
		boolean result = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = DriverManager.getConnection(url, userid, passwd);
			String sql = "SELECT * FROM MEMBER2 WHERE NAME = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,name);
			rs = pstmt.executeQuery();
			while(rs.next()){
				result = true;
			}
		}catch(Exception e){e.printStackTrace();
		}finally{
			try{
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			}catch(Exception e){e.printStackTrace();}
		}
		return result;
	}// isExist() ��
		
	public ArrayList<ProfilDTO> select(){
		// select()�� �����ϸ�  ArrayList��� �ڷᱸ���� ����ϴµ�
		// MemberDTO�� ����ִ� ������ ������ ����ִ� 
		ArrayList<ProfilDTO> list = new ArrayList<ProfilDTO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = DriverManager.getConnection(url, userid, passwd);
			String query = "SELECT * FROM MEMBER2";
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			while(rs.next()){
				ProfilDTO dto = new ProfilDTO();
				dto.setName(rs.getString("NAME"));
				dto.setAge(rs.getInt("AGE"));
				dto.setGender(rs.getString("GENDER").charAt(0));
				dto.setAddr(rs.getString("ADDR"));
				dto.setPhone(rs.getString("PHONE"));
				//charAt(0); 0��° �μ��� �д´�.
				list.add(dto);
			}//while ��
		}catch(Exception e){e.printStackTrace();
		}finally{
			try{
				if(rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			}catch(Exception e){e.printStackTrace();}
		}
		return list;
	}//ArrayList<MemberDTO> select()�� ��
	public void Insert(String name, String age, String addr, 
			String phone, String gender){
	//�ܺ� ȭ�鿡�� �Է��ϴ� ���� ����, ���� ������� ��� String Type���� �νĵ�	
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = DriverManager.getConnection(url, userid, passwd);
			String sql = "INSERT INTO MEMBER2(NAME, AGE, GENDER, ADDR, PHONE)" +
					"VALUES(?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setInt(2,Integer.parseInt(age));
			pstmt.setString(3, addr);
			pstmt.setString(4, phone);
			pstmt.setString(5, gender);
			pstmt.executeUpdate();
		}catch(Exception e){e.printStackTrace();			
		}finally{
			try{
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			}catch(Exception e){e.printStackTrace();}
		}
	}// insert() ��
	public void delete(String name){
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = DriverManager.getConnection(url, userid, passwd);
			String sql = "DELETE FROM MEMBER2 WHERE NAME = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,name);
			pstmt.executeUpdate();
		}catch(Exception e){e.printStackTrace();
		}finally{
			try{
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			}catch(Exception e){e.printStackTrace();}
		}
	}//delete() ��
	public void update(String name, String age, String addr, 
			String phone, String gender){	
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = DriverManager.getConnection(url, userid, passwd);
			String sql = "UPDATE MEMBER2 SET age = ?, gender = ?," +
					"addr = ?, phone = ? where name = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,Integer.parseInt(age));
			pstmt.setString(2,addr);
			pstmt.setString(3,phone);
			pstmt.setString(4, gender);
			pstmt.setString(5, name);
			pstmt.executeUpdate();
		}catch(Exception e){e.printStackTrace();
		}finally{
			try{
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			}catch(Exception e){e.printStackTrace();}
		} 
	}//Update() ��	
	public ProfilDTO search(String name) throws RecordNotFoundException{
		if (! isExist(name)){
			throw new RecordNotFoundException("ã�� ���ڵ尡 �����ϴ�.");}
		
		ProfilDTO dto = new ProfilDTO();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = DriverManager.getConnection(url, userid, passwd);
			String sql = "SELECT * FROM MEMBER2 WHERE NAME = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,name);
			rs = pstmt.executeQuery();
			while(rs.next()){
				dto.setName(rs.getString("NAME"));
				dto.setAge(rs.getInt("AGE"));
				dto.setGender(rs.getString("GENDER").charAt(0));
				dto.setAddr(rs.getString("ADDR"));
				dto.setPhone(rs.getString("PHONE"));
			}
		}catch(Exception e){e.printStackTrace();
		}finally{
			try{
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			}catch(Exception e){e.printStackTrace();}
		} return dto;
	}//search() ��
}//MemberDAO�� ��
