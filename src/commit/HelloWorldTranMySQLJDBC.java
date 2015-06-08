package commit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class HelloWorldTranMySQLJDBC {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

		Connection con = null;
		Statement stmt = null;
		String name = "levis";
		String name1 = "lee";



		try {
			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection
			("jdbc:mysql://localhost/sampledb", "root", "rontech");

			System.out.println("comit=" + con.getAutoCommit());
			con.setAutoCommit(false);
			stmt = con.createStatement();

			String sql =
					"INSERT INTO sampledata VALUES(5,'" + name + "',' 555')";
			stmt.addBatch(sql);

			stmt.addBatch
			("INSERT INTO sampledata VALUES(12,'" + name1 + "',' 789')");

			stmt.executeBatch();

			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException ex) {
				e.printStackTrace();
			}
			  finally {
				try {
					if (stmt != null) {
						stmt.close();
					}
					if (con != null) {
						con.close();
					}
				} catch (SQLException e1) {
					e.printStackTrace();
				}
			}
		}

	}

}
