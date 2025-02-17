

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Servlet implementation class ShowPasswordServlet
 */
public class ShowPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowPasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String n=request.getParameter("uname");
		long e=Long.parseLong(request.getParameter("mobile"));
		String h=request.getParameter("hint");
		String e1=request.getParameter("answer");
		PrintWriter out=response.getWriter();
		out.print("<html><body>");
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Doit","Doit");
			System.out.println("Approved");
			PreparedStatement s=con.prepareStatement("SELECT * FROM INFO WHERE NAME=? AND MOBILE=? AND HINT=? AND ANSWER=?");
			s.setString(1, n);
			s.setString(3, h);
			s.setString(4, e1);
			s.setLong(2, e);
			ResultSet r=s.executeQuery();
			if(r.next()) {
				out.print("PASSWORD: "+r.getString(2));
			}
			else {
				out.print("PLEASE TRY");
				
			}
			con.close();
			out.print("</body></html>");
		}
		catch (Exception e2) {
			e2.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
