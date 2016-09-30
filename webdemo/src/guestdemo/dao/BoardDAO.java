package guestdemo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import guestdemo.dto.BoardDTO;
import guestdemo.dto.PageDTO;

public class BoardDAO {
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private BoardDAO() {
	}

	private static BoardDAO dao = new BoardDAO();

	public static BoardDAO getInstance() {
		return dao;
	}

	private Connection start() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.OracleDriver");

		String url = "jdbc:oracle:thin://@127.0.0.1:1521:xe";
		String user = "hr";
		String password = "a1234";
		return DriverManager.getConnection(url, user, password);
	}// end start()

	private void stop() {
		try {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}// end end()

	public void deleteMethod(int num) {
		try {
			conn = start();
			String sql = "delete from board where num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			stop();
		}

	}// end deleteMethod()

	public int rowTotalCount(HashMap<String, String> map) {
		int cnt = -1;
		try {
			conn = start();
			String sql = "select count(*) from board";

			if (map.get("searchKey") != null) {
				if (map.get("searchKey").equals("subject") || map.get("searchKey").equals("writer")) {
					sql += " where " + map.get("searchKey") + " like '%" + map.get("searchWord") + "%'";
				}
			}

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				cnt = rs.getInt(1);
			}

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			stop();
		}

		return cnt;
	}

	public ArrayList<BoardDTO> listMethod(PageDTO pdto) {
		ArrayList<BoardDTO> aList = new ArrayList<BoardDTO>();

		try {
			conn = start();
			/*
			 * String sql =
			 * "select * from board order by ref desc, re_step asc";
			 */
			String sql = "select b.* from(select rownum as rm, a.* from(select * from board";
			if (pdto.getSearchKey() != null) {
				if (pdto.getSearchKey().equals("subject") || pdto.getSearchKey().equals("writer")) {
					sql += " where " + pdto.getSearchKey() + " like '%" + pdto.getSearchWord() + "%'";
				}
			}
			sql += " order by ref desc, re_step asc)a)b where b.rm >=? and b.rm <= ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pdto.getStartRow());
			pstmt.setInt(2, pdto.getEndRow());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardDTO dto = new BoardDTO();

				dto.setNum(rs.getInt("num"));
				dto.setWriter(rs.getString("writer"));
				dto.setSubject(rs.getString("subject"));
				dto.setReadcount(rs.getInt("readcount"));
				dto.setRe_level(rs.getInt("re_level"));
				dto.setUpload(rs.getString("upload"));

				aList.add(dto);
			}

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			stop();
		}

		return aList;
	}

	public BoardDTO viewMethod(int num) {
		BoardDTO dto = null;
		try {
			conn = start();
			String sql = "select * from board where num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto = new BoardDTO();
				dto.setNum(rs.getInt("num"));
				dto.setWriter(rs.getString("writer"));
				dto.setReadcount(rs.getInt("readcount"));
				dto.setSubject(rs.getString("subject"));
				dto.setEmail(rs.getString("email"));
				dto.setContent(rs.getString("content"));
				dto.setUpload(rs.getString("upload"));
				dto.setRef(rs.getInt("ref"));
				dto.setRe_step(rs.getInt("re_step"));
				dto.setRe_level(rs.getInt("re_level"));
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			stop();
		}

		return dto;
	}// end viewMethod()

	public void updateMethod(BoardDTO dto) {

		try {
			conn = start();
			String sql = "";

			// if (dto.getUpload() != null) {
			sql = "update board set subject=?, email=?, content=?, upload=? where num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getSubject());
			pstmt.setString(2, dto.getEmail());
			pstmt.setString(3, dto.getContent());
			pstmt.setString(4, dto.getUpload());
			pstmt.setInt(5, dto.getNum());
			// } else {
			// sql = "update board set subject=?, email=?, content=? where
			// num=?";
			// pstmt = conn.prepareStatement(sql);
			// pstmt.setString(1, dto.getSubject());
			// pstmt.setString(2, dto.getEmail());
			// pstmt.setString(3, dto.getContent());
			// pstmt.setInt(4, dto.getNum());
			// }
			pstmt.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			stop();
		}

	}

	public String fileMethod(int num) {
		String file = null;
		try {
			conn = start();
			String sql = "select upload from board where num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				
				file = rs.getString("upload");
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			stop();
		}
		return file;
	}

	public void readCountMethod(int num) {
		try {
			conn = start();
			String sql = "update board set readcount=readcount+1 where num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			stop();
		}
	}

	public void readCountMethod2(int num) {
		int readcount = 0;

		try {
			conn = start();
			String sql = "select * from board where num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				readcount = rs.getInt("readcount");
				readcount++;
				sql = "update board set readcount=? where num=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, readcount);
				pstmt.setInt(2, num);
				pstmt.executeUpdate();
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			stop();
		}

	}

	public void insertMethod(BoardDTO dto) {
		try {
			conn = start();

			if (dto.getRe_level() == 0) {
				String sql = "insert into board(num,reg_date,writer,email,subject,content,upload,ref,re_step,re_level) values"
						+ "(board_seq.nextval,sysdate,?,?,?,?,?,board_seq.nextval,0,0)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, dto.getWriter());
				pstmt.setString(2, dto.getEmail());
				pstmt.setString(3, dto.getSubject());
				pstmt.setString(4, dto.getContent());
				pstmt.setString(5, dto.getUpload());
				pstmt.executeUpdate();
			} else {
				String sql = "insert into board(num,reg_date,writer,email,subject,content,upload,ref,re_step,re_level) values"
						+ "(board_seq.nextval,sysdate,?,?,?,?,?,?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, dto.getWriter());
				pstmt.setString(2, dto.getEmail());
				pstmt.setString(3, dto.getSubject());
				pstmt.setString(4, dto.getContent());
				pstmt.setString(5, dto.getUpload());
				pstmt.setInt(6, dto.getRef());
				pstmt.setInt(7, dto.getRe_step());
				pstmt.setInt(8, dto.getRe_level());
				pstmt.executeUpdate();
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			stop();
		}

	}

	public void reStepMethod(HashMap<String, Integer> map) {

		try {
			conn = start();
			String sql = "update board set re_step=re_step+1 where ref=? and re_step>?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, map.get("ref"));
			pstmt.setInt(2, map.get("re_step"));
			pstmt.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			stop();
		}

	}

}// end class
