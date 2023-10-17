import java.sql.*;

public class OracleInsert {

	public static void main(String[] args) {

		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:orcdb";
		String user = "c##green";
		String password = "green1234";

		try {
			Class.forName(driver);
			System.out.println("jdbc driver loading sucess");
			Connection conn = DriverManager.getConnection(url, user, password);
			System.out.println("oracle connection success. \n");

			Statement stmt = conn.createStatement();

			String empLoad = "Select * from emp";
			ResultSet rs3 = stmt.executeQuery(empLoad);

			  while (rs3.next()) {
	                int i = 1;
	                while (i <= rs3.getMetaData().getColumnCount()) {
	                    String a = rs3.getString(i);
	                    System.out.print(a + " ");
	                    i++;
	                }
	                System.out.println(); 
	            }

			String maxDeptno = "Select MAX(deptno) from dept";
			ResultSet maxMaxDeptno = stmt.executeQuery(maxDeptno);
			maxMaxDeptno.next();
			int resultMaxDept = maxMaxDeptno.getInt(1) + 10;

			String ideptno = String.valueOf(resultMaxDept), sdname = "IT", sloc = "SEOUL";

			String sql = "INSERT INTO dept VALUES ('" + ideptno + "','" + sdname + "','" + sloc + "')";
			boolean b = stmt.execute(sql);
			if (!b) {
				System.out.println("Insert Success");
			} else {
				System.out.println("fail");
			}
//			String sql = "SELECT * FROM DEPT";
			String sql2 = "SELECT deptno, dname , loc from dept";
//			ResultSet rs = stmt.executeQuery(sql);
			ResultSet rs = stmt.executeQuery(sql2);
			while (rs.next()) {
				System.out.print(rs.getString("deptno") + " ");
				System.out.print(rs.getString("dname") + " ");
				System.out.println(rs.getString("loc") + " ");

			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();

		}

	}

}
