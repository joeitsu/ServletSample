<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%!
// サーブレットのinitメソッドに相当
public void jspInit() {
try {
	Class.forName("com.mysql.jdbc.Driver");
} catch (Exception e) {
  e.printStackTrace();
  }
}
  %>

 <html>
 <head>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>デーブル内容</title>
 </head>

 <body>
 <p>JSPによるデータベースのアクセス</p>
 <table border = '1'><tr><th>ID</th><th>NAME</th><th>PASSWORD</th></tr>

 <%
 		Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
        	con = DriverManager.getConnection(
        			"jdbc:mysql://localhost/sampledb",
        			"root", "rontech");

        	stmt = con.createStatement();

        	rs = stmt.executeQuery(
        			"select id, name, password from sampledata");

        	while (rs.next()) {
 %>
        		<tr>
                <%-- レコードのIDフィールドを表示 --%>
                <td><%= rs.getInt("id")%></td>
                <%-- レコードのNAMEフィールドを表示 --%>
                <td><%= rs.getString("name")%></td>
                <%-- レコードのPASSWORDフィールドを表示 --%>
                <td><%= rs.getString("password")%></td>
                </tr>
<%
        	}
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
        	try { rs.close(); } catch (Exception e) {}
        	try { stmt.close(); } catch (Exception e) {}
        	try { con.close(); } catch (Exception e) {}
        }
 %>

        </table>
 </body>
 </html>