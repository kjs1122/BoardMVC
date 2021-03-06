package com.saeyan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.saeyan.dto.BoardVO;
import com.sun.xml.internal.ws.api.addressing.AddressingVersion.EPR;

import util.DBManger;

public class BoardDAO {
	
	private BoardDAO() {
		
	}
	
	private static BoardDAO instance = new BoardDAO();
	
	public static BoardDAO getInstance() {
		return instance;
	}
	// 글의 개수 구하기
	
	public int getListCount() {
		int x = 0;
		String sql = "select count(*) from board";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBManger.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				x = rs.getInt(1);//그룹함수는 칼럼명을 못가져오니 칼럼인덱스명으로 접근
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManger.close(conn, pstmt, rs);
		}
		
		return x;
	}
	// 글 목록 보기
	
	public List<BoardVO> getBoardList(int page, int limit){
		String sql = "select * from(select rownum as rnum,num,name,"
				+ "email,pass,title,content,"
				+ "readcount,writedate from"
				+ "(select * from board order by writedate desc))"
				+ "where rnum >= ? and rnum <= ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<BoardVO> list = new ArrayList<BoardVO>();
		int startRow = (page - 1) * 10 + 1;
		int endRow = startRow + limit - 1;
		try {
			conn = DBManger.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardVO bVo = new BoardVO();
				bVo.setNum(rs.getInt("num"));
				bVo.setName(rs.getString("name"));
				bVo.setEmail(rs.getString("email"));
				bVo.setPass(rs.getString("pass"));
				bVo.setTitle(rs.getString("title"));
				bVo.setContent(rs.getString("content"));
				bVo.setReadcount(rs.getInt("readcount"));
				bVo.setWritedate(rs.getTimestamp("writedate"));
				list.add(bVo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManger.close(conn, pstmt, rs);
		}
	
		return list;
	}
	
	
	
	public List<BoardVO> selectAllBoards(){
		String sql = "select * from board";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BoardVO> list = new ArrayList<BoardVO>();
		try {
			conn = DBManger.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardVO bVo = new BoardVO();
				bVo.setNum(rs.getInt("num"));
				bVo.setName(rs.getString("name"));
				bVo.setEmail(rs.getString("email"));
				bVo.setPass(rs.getString("pass"));
				bVo.setTitle(rs.getString("title"));
				bVo.setContent(rs.getString("content"));
				bVo.setReadcount(rs.getInt("readcount"));
				bVo.setWritedate(rs.getTimestamp("writedate"));
				list.add(bVo);
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManger.close(conn, pstmt, rs);
		}
		
		return list;
	}
	
	public void insertBoard(BoardVO bVo) {
		String sql = "insert into board("
				+ "num, name, email, pass, title, content)"
				+ "values(board_seq.nextval, ?, ?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManger.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bVo.getName());
			pstmt.setString(2, bVo.getEmail());
			pstmt.setString(3, bVo.getPass());
			pstmt.setString(4, bVo.getTitle());
			pstmt.setString(5, bVo.getContent());
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManger.close(conn, pstmt);
		}
		
	}
	
	public void updateReadCount(String num) {
		int number = Integer.parseInt(num);
		String sql = "update board set readcount = readcount + 1 where num = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManger.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, number);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManger.close(conn, pstmt);
		}
	}
	
	public BoardVO selectOneBoardByNum(String num) {
		int number = Integer.parseInt(num);
		String sql = "select * from board where num = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVO bVo = null;
		try {
			conn = DBManger.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, number);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				bVo = new BoardVO();
				bVo.setNum(rs.getInt("num"));
				bVo.setName(rs.getString("name"));
				bVo.setEmail(rs.getString("email"));
				bVo.setPass(rs.getString("pass"));
				bVo.setTitle(rs.getString("title"));
				bVo.setContent(rs.getString("content"));
				bVo.setReadcount(rs.getInt("readcount"));
				bVo.setWritedate(rs.getTimestamp("writedate"));
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManger.close(conn, pstmt, rs);
		}
		
		
		return bVo;
	}
	
	public void updateBoard(BoardVO bVo) {
		String sql = "update board set "
				+ "name=?, pass=?, email=?, title=?, content=? where num=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBManger.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bVo.getName());
			pstmt.setString(2, bVo.getEmail());
			pstmt.setString(3, bVo.getTitle());
			pstmt.setString(4, bVo.getTitle());
			pstmt.setString(5, bVo.getContent());
			pstmt.setInt(6, bVo.getNum());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManger.close(conn, pstmt);
		}
	}
	
	public void deleteBoard(String num) {
		String sql = "delete board where num=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBManger.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(num));
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManger.close(conn, pstmt);
		}
	}
	
}
