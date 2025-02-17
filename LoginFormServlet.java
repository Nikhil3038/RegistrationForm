

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
 * Servlet implementation class LoginFormServlet
 */
public class LoginFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginFormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String n=request.getParameter("uname");
		String p=request.getParameter("password");
		RequestDispatcher e=request.getRequestDispatcher("/HomeForm.html");
		RequestDispatcher e2=request.getRequestDispatcher("/LoginForm.html");
		
		PrintWriter out=response.getWriter();
		out.print("<html><body>");
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Doit","Doit");
			System.out.println("Approved");
			PreparedStatement s=con.prepareStatement("SELECT * FROM INFO WHERE NAME=? AND PASSWORD=?");
			s.setString(1, n);
			s.setString(2, p);
			ResultSet r=s.executeQuery();
			if(r.next()) {
				e.forward(request, response);
			}
			else {
				out.print("PLEASE TRY");
				e2.include(request, response);
			}
			con.close();
			out.print("</body></html>");
		}
		catch (Exception e1) {
			e1.printStackTrace();
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
