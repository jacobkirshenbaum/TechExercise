import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletContext;

public class DBConnectionTechExercise {
	
	static Connection connection;
	
	protected static void getDBConnection(ServletContext servletContext) throws Exception {
		UtilPropTechExercise.loadProperty(servletContext);
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection(getURL(), getUserName(), getPassword());
	}
	
	protected static String getURL() {
		return UtilPropTechExercise.getProp("url");
	}
	
	protected static String getUserName() {
		return UtilPropTechExercise.getProp("user");
	}
	
	protected static String getPassword() {
		return UtilPropTechExercise.getProp("password");
	}

}
