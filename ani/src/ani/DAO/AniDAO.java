package ani.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import ani.domain.AniVO;

// 추가 cre, 수정 upd, 삭제 del, 조회 red

public class AniDAO {
	DataSource ds;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public AniDAO() {
		try{
			Context init = new InitialContext();
	  	    ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		}catch(Exception ex){
			System.out.println("DB 연결 실패");
			return;
		}
	}
	
	//	회원 추가
	public boolean NewMem(AniVO vo) {
		String sql="insert into tem values(?, ?, ?, sysdate)";
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getMem_id());
			pstmt.setString(2, vo.getMem_pwd());
			pstmt.setString(3, vo.getMem_nick());
			pstmt.executeUpdate();
			return true;
		}catch(Exception ex){
			System.out.println("회원 등록 실패: " + ex);
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
			}
		return false;
	}
	
	// 회원 삭제
	public boolean DelMem(String id) {
		String sql="delete from tem where mem_id = ?";
		int result =0;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			result = pstmt.executeUpdate();
			if (result == 0)
				return false;
			return true;
		}catch(Exception ex){
			System.out.println("회원 삭제 실패: " + ex);
		}finally{
			try {
				if(pstmt!=null)
					pstmt.close();
				if(con!=null)
					con.close();
			}catch(Exception ex){}
		}
		return false;
	}

	// 회원 정보 수정
	public boolean UpdatMem(AniVO vo) throws Exception {

		String sql = "update tem set mem_pwd=? ,mem_nick=? where mem_id=?";

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getMem_pwd());
			pstmt.setString(2, vo.getMem_nick());
			pstmt.setString(3, vo.getMem_id());
			pstmt.executeUpdate();
			return true;
		} catch (Exception ex) {
			System.out.println("업데이트 실패 : " + ex);
		} finally {
			if (pstmt != null)
				try { pstmt.close(); } catch (SQLException ex) {}
			if (con != null)
				try { con.close(); } catch (SQLException ex) {}
		}
		return false;
	}
	
	// 모든 회원 정보 보기
	public boolean readMem() {
		String sql = "select * from tem";

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();

		} catch (SQLException ex) {
			System.out.println("회원 정보 조회 오류 : " + ex);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception ex) { }
		}
		return false;
	}
	
	// 회원 상세 정보 - 1명만
	public AniVO getBoardDetail(int num) {
		AniVO vo = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement("select * from tem where mem_id = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				vo = new AniVO();
				vo.setMem_id(rs.getString("mem_id"));
				vo.setMem_pwd(rs.getString("Mem_pwd"));
				vo.setMem_nick(rs.getString("mem_nick"));
				vo.setJoin_date(rs.getDate("join_date"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
		}
		return vo;
	}

	//
	public List<AniVO> getMemberList(int start, int end){
		// select
		List<AniVO> list = new ArrayList<AniVO>();
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement("select * from tem order by join_date desc");
//			pstmt.setInt(1, start);
//			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				AniVO vo = new AniVO();
				vo.setMem_id(rs.getString("mem_id"));
				vo.setMem_pwd(rs.getString("mem_pwd"));
				vo.setMem_nick(rs.getString("mem_nick"));
				vo.setJoin_date(rs.getDate("join_date"));
				list.add(vo);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
		}
		System.out.println(list.size() + "=====================");
		return list;
	}
	
	//
	public int getMemberCount() {
		int listCount = 0;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement("select count(*) as memberCount from tem");

			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				listCount = rs.getInt("memberCount");
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
		}
		return listCount;
	}
		
}
