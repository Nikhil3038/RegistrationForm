

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
import java.util.Enumeration;

/**
 * Servlet implementation class RegistrationFormServlet
 */
public class RegistrationFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationFormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("uname");
		String password = request.getParameter("pwd");
		long mobile = Long.parseLong(request.getParameter("mobile"));
		String hint=request.getParameter("hint");
		String answer=request.getParameter("answer");
		
		/*
		 * Enumeration ee=request.getParameterNames();
		 * 
		 * while (ee.hasMoreElements()) { Object key = (Object) ee.nextElement(); String
		 * n1=request.getParameter(); }
		 */
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Doit","Doit");
			System.out.println("Accepted");
			PreparedStatement ps=con.prepareStatement("INSERT INTO INFO VALUES(?,?,?,?,?)");
			ps.setString(1,name);
			ps.setString(2, password);
			ps.setLong(3, mobile);
			ps.setString(4, hint);
			ps.setString(5, answer);
			int n=ps.executeUpdate();
			if(n>0) {
				PrintWriter out=response.getWriter();
				out.print("<html><body> REGISTERED </body></html>");
			}
			con.close();
			
		}
		catch (Exception e) {
			
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
