

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Calendar;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TechExerciseServlet
 */
@WebServlet("/TechExerciseServlet")
public class CreateEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateEventServlet() {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("eventName");
		String date = request.getParameter("eventDate");
		try {
			insert(name, date, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void insert(String name, String date, HttpServletResponse response) throws Exception {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String title = "Database Result";
		String docType = "<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">\n";
		 					out.println(docType + "<html>\n" + "<head><title>" + title + "</title></head>\n" +
		 					"<body>\n" + "<h1 align=/'center/'>" + title + "</h1>\n");
		 					
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		DBConnectionTechExercise.getDBConnection(getServletContext());
		connection = DBConnectionTechExercise.connection;
		
		if (name.isEmpty() || date.isEmpty()) {
			out.println("Must fill in all inputs to insert into the database");
		}
		else {
			String insertSQL = "INSERT INTO myTable (NAME, EVENTDATE) VALUES (?, ?)";
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, date);
			out.println("Name: " + name);
			out.println("Date: " + date);
		}
		out.println("</body></html>");
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
