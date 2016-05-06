package lawos_Rest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/lawos")
public class GeneralServices {

	// public java.sql.Connection conn = null;

	// Connection makeConnection() {
	//
	// try {
	// // This will load the MySQL driver, each DB has its own driver
	// // Class.forName("com.mysql.jdbc.Driver");
	// // Setup the connection with the DB
	// conn = DriverManager.getConnection(
	// "jdbc:mysql://localhost:3306/lawosdb?autoReconnect=true&useSSL=false&useUnicode=true&"
	// + "user=root&password=root");
	//
	// } catch (SQLException e) {
	// System.err.println("[!]Problem in establishing the connection..");
	// e.printStackTrace();
	// }
	//
	// return null;
	// }

	public static String parseJSON(ResultSet rs) throws Exception {

		String ArrayJSON = "";
		String culName;
		String content;
		int j = 0;
		ResultSetMetaData culnames = rs.getMetaData();
		ArrayJSON = "[  ";
		while (rs.next()) {
			int numOfColumns = culnames.getColumnCount();
			ArrayJSON = ArrayJSON + "{";
			for (int i = 1; i <= numOfColumns; i++) {
				culName = culnames.getColumnName(i);
				content = rs.getString(i);
				if (i != numOfColumns) {
					ArrayJSON = ArrayJSON + " \"" + culName + "\"" + ": " + "\"" + content + "\"" + ", ";
				} else {
					ArrayJSON = ArrayJSON + "\"" + culName + "\"" + ": " + "\"" + content + "\"" + " ";
				}
			}
			ArrayJSON = ArrayJSON + "}";
			j++;
			if (!rs.isLast()) {
				ArrayJSON = ArrayJSON + ",";
			}
		}
		ArrayJSON = ArrayJSON + "]";

		String finalJSON = "{ \"size\" : \"" + j + "\",  \"results_array\": " + ArrayJSON + "}";
		return finalJSON;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String login(@FormParam("type") String type, @FormParam("user") String user,
			@FormParam("pass") String pass) {
		String resuser = null;
		String respass = null;
		String response = null;

		Connection conn = null;
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			// conn =
			// DriverManager.getConnection("jdbc:mysql://localhost:3306/lawosdb?autoReconnect=true&useSSL=false&useUnicode=true&"+
			// "user=root&password=root");
			conn = DriverManager.getConnection(
					"jdbc:mysql://phpmyadmin.in.cs.ucy.ac.cy/cs363db?" + "user=cs363db&password=NjFU2pKz");

		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (type.compareTo("legalstaff") == 0) {
			java.sql.Statement stmt;
			try {
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(
						"SELECT * FROM legalstaff WHERE (Username = '" + user + "' AND Password='" + pass + "' AND Type='LegalStaff')");

				response = parseJSON(rs);
				rs.close();
			} catch (SQLException e) {
				System.err.println("[!]Problem with requested statement");
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return response;

		} else if (type.compareTo("legalrecordsstaff") == 0) {
			java.sql.Statement stmt;
			try {
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(
						"SELECT * FROM legalstaff WHERE (Username = '" + user + "' AND Password='" + pass +"' AND Type='LegalRecordStaff')");

				response = parseJSON(rs);
				rs.close();
			} catch (SQLException e) {
				System.err.println("[!]Problem with requested statement");
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return response;
		} else if (type.compareTo("receptionist") == 0) {
			java.sql.Statement stmt;
			try {
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(
						"SELECT * FROM receptionist WHERE (Username = '" + user + "' AND Password='" + pass + "')");

				response = parseJSON(rs);
				rs.close();
			} catch (SQLException e) {
				System.err.println("[!]Problem with requested statement");
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return response;
		} else if (type.compareTo("headoffice") == 0) {
			java.sql.Statement stmt;
			try {
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(
						"SELECT * FROM manager WHERE (Username = '" + user + "' AND Password='" + pass +"')");

				response = parseJSON(rs);
				rs.close();
			} catch (SQLException e) {
				System.err.println("[!]Problem with requested statement");
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return response;
		}
		// return "<?xml version=\"1.0\"?>" + "<response> Wrong Type given

		// return "<?xml version=\"1.0\"?>" + "<user>" + user + "</user>" +
		// "<pass>" + pass + "</pass>";
		return "{\"response\"unsure :\"Wrong user type requested\"}";
	}

	@Path("/search/client")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String viewClient(@FormParam("ID") String ID) {

		Connection conn = null;
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			conn = DriverManager.getConnection(
					"jdbc:mysql://phpmyadmin.in.cs.ucy.ac.cy/cs363db?" + "user=cs363db&password=NjFU2pKz");

		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block.
			e.printStackTrace();
		}
		String response = null;
		java.sql.Statement stmt;

		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT * FROM `client` WHERE ID="+ID);
			response = GeneralServices.parseJSON(rs);
			rs.close();
		} catch (SQLException e) {
			System.err.println("[!]Problem with requested statement");
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return response;
	}// end of view client
	
	@Path("/recom/strategies")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String getRecom(@FormParam("ID") String ID) {

		Connection conn = null;
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			conn = DriverManager.getConnection(
					"jdbc:mysql://phpmyadmin.in.cs.ucy.ac.cy/cs363db?" + "user=cs363db&password=NjFU2pKz");

		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block.
			e.printStackTrace();
		}
		String response = null;
		java.sql.Statement stmt;

		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT DISTINCT Strategy FROM `case` WHERE Client="+ID);
			response = GeneralServices.parseJSON(rs);
			rs.close();
		} catch (SQLException e) {
			System.err.println("[!]Problem with requested statement");
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return response;
	}// end of get recommendations for current client
	
}// end of class
