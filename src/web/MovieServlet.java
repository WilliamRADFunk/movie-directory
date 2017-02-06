package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;

/**
 * Servlet implementation class MovieServlet
 */
@WebServlet("/movie")
public class MovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MovieServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String password = "williamchang";
		String username = "williamfunk";
		String url = "jdbc:mysql://mysql.creativecrew.org/creativecrew_cinemaempire";
		
		PrintWriter writer = response.getWriter();
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			
			java.sql.Connection connection = DriverManager.getConnection(url, username, password);
			
			Statement statement = connection.createStatement();
			
			ResultSet myResult;
			myResult = statement.executeQuery("SELECT * FROM Movies WHERE `Title`='" + request.getParameter("title") + "';");
			
			if(myResult.next())
			{
				String title = myResult.getString("Title");
				String synopsis = myResult.getString("Synopsis");
				String cost = myResult.getString("Cost License");
				String length = myResult.getString("License Length");
	
			    request.setAttribute("title", title);
			    request.setAttribute("synopsis", synopsis);
			    request.setAttribute("cost", cost);
			    request.setAttribute("length", length);
			    request.getRequestDispatcher("/index.jsp").forward(request, response);
			}
			else
			{
				request.setAttribute("title", "Nope");
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			}
		}
		catch (SQLException | ClassNotFoundException e)
		{
			String err = e.toString();
			writer.println("<h1>Error " + err + "</h1>");
		}
		writer.close();
	}

}
