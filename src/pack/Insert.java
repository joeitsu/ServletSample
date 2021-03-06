package pack;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Insert
 */
@WebServlet("/Insert")
public class Insert extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Insert() {
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
		Connection con = null;
		Statement stmt = null;
		try {
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost/sampledb",
					"root","rontech");

			stmt = con.createStatement();



			String id = request.getParameter("id");
			String name = request.getParameter("name");
			String password = request.getParameter("password");

			StringBuffer buf = new StringBuffer();
			buf.append("insert into sampledata (");
			buf.append("id, name, password)");
			buf.append("values (");
			buf.append(id);
			buf.append(",'");
			buf.append(name);
			buf.append("','");
			buf.append(password);
			buf.append("')");
			stmt.executeUpdate(buf.toString());

			try { stmt.close(); } catch (Exception e) {}
			try { con.close(); } catch (Exception e) {}

			ServletContext cx = getServletContext ();
			RequestDispatcher rd = cx.getRequestDispatcher("/Data");

			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		return "short description";
	}

}
