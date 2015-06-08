package pack;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class Select
 */
@WebServlet("/Select")
public class Select extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Select() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	protected void processRequest (HttpServletRequest request, HttpServletResponse response)
	throws ServletException, java.io.IOException {

		 response.setContentType("text/html; charset=UTF-8");
	        java.io.PrintWriter out = response.getWriter();
	        out.println("<html>");
	        out.println("<head>");
	        out.println("<title>ユーザー情報</title>");
	        out.println("</head>");
	        out.println("<body>");
	        out.println("<table border=\"1\"><tr><th>id<th>名前<th>パスワード");

	        Connection con = null;
	        Statement stmt = null;
	        ResultSet rs = null;
	        try {
	            con = DriverManager.getConnection(
	                "jdbc:mysql://localhost/sampledb",
	                "root", "rontech");

	            stmt = con.createStatement();

	            String name = request.getParameter("name");

                rs = stmt.executeQuery(
                //名前検索
	             /* "select * from sampledata where name = '" + name + "'");*/
                //曖昧検索
                  "select * from sampledata where name like '%" + name + "%'");




	            while (rs.next()) {
	                out.println("<tr>");
	                out.println("<td>" + rs.getInt("id") + "</td>" );
	                out.println("<td>" + rs.getString("name") + "</td>" );
	                out.println("<td>" + rs.getString("password") +  "</td>" );
	                out.println("</tr>");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            try { rs.close(); } catch (Exception e) {}
	            try { stmt.close(); } catch (Exception e) {}
	            try { con.close(); } catch (Exception e) {}
	        }

	        out.println("</table>");
	        out.println("<p><a href=\"/Sample1/Update.html\">更新</a></p>");
	        out.println("<p><a href=\"/Sample1/Insert.html\">追加</a></p>");
	        out.println("<p><a href=\"/Sample1/Delete.html\">削除</a></p>");
	        out.println("</body>");
	        out.println("</html>");
	        out.close();
	    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);

	}

	 public String getServletInfo() {
	        return "Short description";
	 }


}
