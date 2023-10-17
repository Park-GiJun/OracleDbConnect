package DaoExercise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class MemberDAO {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:orcdb";
	String user = "c##green";
	String password = "green1234";

	private Connection con;
	private Statement stmt;
	private ResultSet rs;

	public ArrayList<MemberVo> list(String name) {
		ArrayList<MemberVo> list = new ArrayList<>();
		try {
			connDB();

			String query = "select * from emp ";
			if (name != null) {

				query += "where ename ='" + name.toUpperCase() + "'";
			}

			System.out.println("SQL : " + query);
			rs = stmt.executeQuery(query);
			rs.last();
			System.out.println("rs.getRow() :" + rs.getRow());

			if (rs.getRow() == 0) {
				System.out.println("0 row selected");
			} else {
				System.out.println(rs.getRow() + "row selected");
				rs.previous();
				while (rs.next()) {
					String empno = rs.getString("empno");
					String ename = rs.getString("ename");
					int sal = rs.getInt("sal");
					int comm = rs.getInt("comm");

					MemberVo data = new MemberVo(empno, ename, sal, comm);
					list.add(data);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	public void connDB() {
		try {
			Class.forName(driver);
			System.out.println("jdbc driver loading sucess");
			con = DriverManager.getConnection(url, user, password);
			System.out.println("oracle connection success. \n");
//			stmt = con.createStatement();
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			System.out.println("create statemnent");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
