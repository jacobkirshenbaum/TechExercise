

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ShowAllEvents
 */
@WebServlet("/ShowAllEvents")
public class ShowAllEvents extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowAllEvents() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			String title = "All Planner Events";
			String docType = "<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">\n";
		 					out.println(docType + "<html>\n" + "<head><title>" + title + "</title></head>\n" +
		 					"<body>\n" + "<h1 align=/'center/'>" + title + "</h1><br/><a href=\"SearchEvent.html\">Search Planner</a><br/><a href=\"CreateEvent.html\">Add New Event To Planner</a><br/>\r\n" + 
		 							"\r\n" + 
		 							"\n");
		 					
		 	Connection connection = null;
		 	PreparedStatement preparedStatement = null;
		 	DBConnectionTechExercise.getDBConnection(getServletContext());
		 	connection = DBConnectionTechExercise.connection;
		
		 	String selectSQL = "SELECT * FROM myTable";
		 	preparedStatement = connection.prepareStatement(selectSQL);
		 	ResultSet rs = preparedStatement.executeQuery();
		 	
		 	while (rs.next()) {
		 		int id = rs.getInt("id");
		 		String name = rs.getString("name").trim();
		 		String date = rs.getString("eventdate").trim();
		 		out.println("ID: " + id + ", ");
		 		out.println("Name: " + name + ", ");
		 		out.println("Date: " + date + "<br>");
		 	}
		 	out.println("</body></html>");
		}
		catch(Exception e) {
			e.printStackTrace();
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
