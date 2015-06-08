package commit;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;

public class InfJDBC {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

		try {

			Class.forName("com.mysql.jdbc.Driver");

			Connection con =
					DriverManager.getConnection("jdbc:mysql://localhost/sampledb","root","rontech");

			DatabaseMetaData dbmd = con.getMetaData();

			System.out.println("URL      :" + dbmd.getURL());
			System.out.println("Driver   :" + dbmd.getDriverName());
			System.out.println("Version  :" + dbmd.getDriverVersion());
			System.out.println("Database :" + dbmd.getDatabaseProductName());
			System.out.println("Version  :" + dbmd.getDatabaseProductVersion());

			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
