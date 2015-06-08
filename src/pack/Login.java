package pack;

import java.io.IOException;
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

import com.mysql.jdbc.Connection;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, java.io.IOException {
		response.setContentType("text/html; charset=UTF-8");
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        ResultSet sample = null;

		String username = request.getParameter("name");
		String userpassword = request.getParameter("password");

		try {
			con =  (Connection) DriverManager.getConnection(
	                "jdbc:mysql://localhost/sampledb",
	                "root", "rontech");

			stmt = con.createStatement();

			java.io.PrintWriter out = response.getWriter();
	        out.println("<html>");
	        out.println("<head>");
	        out.println("<title>ユーザー情報</title>");
	        out.println("</head>");
	        out.println("<body>");
	        out.println("<table border=\"1\"><tr><th>id<th>名前<th>パスワード");


			rs = stmt.executeQuery(
					"select * from sampledata where name = '" + username + "'");
			sample = stmt.executeQuery(
					"select * from sampledata where password = '" + userpassword + "'");

			if(rs.next()){
			    out.println("OK 認証に成功しました");
			}else {
			    out.println("NG パスワードまたはユーザー名が違います");
			}
			/*while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getInt("id") + "</td>" );
                out.println("<td>" + rs.getString("name") + "</td>" );
                out.println("<td>" + rs.getString("password") +  "</td>" );
                out.println("</tr>");
		    }*/
			out.close();

		} catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { rs.close(); } catch (Exception e) {}
            try { sample.close(); } catch (Exception e) {}
            try { stmt.close(); } catch (Exception e) {}
            try { con.close(); } catch (Exception e) {}
        }



		/*if (rs.equals(request.getParameter("name")) == true
			      && rs.equals(request.getParameter("password")) == true) {
			      out.println("OK 認証に成功しました");
			    } else {
			      out.println("NG パスワードまたはユーザー名が違います");
			    }*/


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
