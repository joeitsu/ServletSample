package commit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TransactionSample{

	public static void main(String[] args) throws ClassNotFoundException,SQLException {

		Class.forName("com.mysql.jdbc.Driver");

		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/sampledb", "root", "rontech");

		con.setAutoCommit(false);

		Statement stmt = con.createStatement();

		String sql = "insert into sampledata values(10,'gd',100);";

		stmt.executeUpdate(sql);

		sql="update sampledata set password = 000 where name ='fukazawa';";

		stmt.executeUpdate(sql);

		sql="delete from sampledata where id = 13;";

		stmt.executeUpdate(sql);

		con.commit();


	}

}
