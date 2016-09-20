package servletdemo.part03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EmployeesDAO {

	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private EmployeesDAO() {
	}

	private static EmployeesDAO dao = new EmployeesDAO();

	public static EmployeesDAO getInstance() {
		return dao;
	}

	private Connection start() throws SQLException, ClassNotFoundException {

		Class.forName("oracle.jdbc.OracleDriver");
		String url = "jdbc:oracle:thin://@127.0.0.1:1521:xe";
		String username = "hr";
		String password = "a1234";

		return DriverManager.getConnection(url, username, password);
	}

	private void exit() {
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
	}

	public ArrayList<EmployeesDTO> search() {

		ArrayList<EmployeesDTO> aList = new ArrayList<EmployeesDTO>();

		try {
			conn = start();
			String sql = "select employee_id, first_name, salary from employees order by employee_id";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				EmployeesDTO dto = new EmployeesDTO();
				dto.setEmployee_id(rs.getInt("employee_id"));
				dto.setFirst_name(rs.getString("first_name"));
				dto.setSalary(rs.getInt("salary"));
				aList.add(dto);
			}

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			exit();
		}

		return aList;
	}
	
	public ArrayList<EmployeesDTO> shortList() {

		ArrayList<EmployeesDTO> aList = new ArrayList<EmployeesDTO>();

		try {
			conn = start();
			String sql = "select employee_id, first_name, salary from employees where rownum between 1 and 5 order by employee_id";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				EmployeesDTO dto = new EmployeesDTO();
				dto.setEmployee_id(rs.getInt("employee_id"));
				dto.setFirst_name(rs.getString("first_name"));
				dto.setSalary(rs.getInt("salary"));
				aList.add(dto);
			}

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			exit();
		}

		return aList;
	}

}
