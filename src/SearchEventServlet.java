

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SearchEventServlet
 */
@WebServlet("/SearchEventServlet")
public class SearchEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchEventServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String eventName = request.getParameter("eventName");
		String eventDate = request.getParameter("eventDate");
		try {
			search(eventName, eventDate, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void search(String name, String date, HttpServletResponse response) throws Exception {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String title = "Database Result";
		String docType = "<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">\n";
						 out.println(docType + "<html>\n" + "<head><title>" + title + "</title></head>\n" +
						 "body>\n" + "<h1 align=/'center/'>" + title + "</h1>\n");
						 
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		DBConnectionTechExercise.getDBConnection(getServletContext());
		connection = DBConnectionTechExercise.connection;
		if (name.isEmpty() && date.isEmpty()) {
			String selectSQL = "SELECT * FROM myTable";
			preparedStatement = connection.prepareStatement(selectSQL);
		}
		else if (name.isEmpty() && !date.isEmpty()) {
			String selectSQL = "SELECT * FROM myTable WHERE EVENTDATE LIKE ?";
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, date + "%");
		}
		else if (!name.isEmpty() && date.isEmpty()) {
			String selectSQL = "SELECT * FROM myTable WHERE NAME LIKE ?";
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, name + "%");
		}
		else {
			String selectSQL = "SELECT * FROM myTable WHERE NAME LIKE ? and EVENTDATE LIKE ?";
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, name + "%");
			preparedStatement.setString(2, date + "%");
		}
		
		ResultSet rs = preparedStatement.executeQuery();
		
		while (rs.next()) {
			int id = rs.getInt("id");
			String nameIn = rs.getString("name").trim();
			String dateIn = rs.getString("eventdate").trim();
			if (name.isEmpty() || nameIn.contains(name) || date.isEmpty() || dateIn.contains(date)) {
				out.println("ID: " + id + ", ");
				out.println("Name: " + nameIn + ", ");
				out.println("Date: " + dateIn + "<br>");
			}
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
