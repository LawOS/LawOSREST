package lawos_Rest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/lawos/receptionist")
public class Receptionist {

	@Path("newapp")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String addAppointment(@FormParam("date") String date, @FormParam("time") String time,
			@FormParam("clientID") String clientID, @FormParam("legalStaffID") String legalStaffID,
			@FormParam("caseID") String caseID) {

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
		Boolean response=false;
		java.sql.Statement stmt;
		try {
			stmt = conn.createStatement();
			response = stmt.execute(
					"INSERT INTO `appointment`(`Date`, `Time`, `Completed`, `clientID`, `legalStaffID`, `Case`) VALUES ('"
							+ date + "', '" + time + "', 0, '" + clientID + "', " + legalStaffID + ", " + caseID
							+ ");");
			// rs.close();
		} catch (SQLException e) {
			System.err.println("[!]Problem with requested statement");
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (response)
			return "1";
		else
			return "0";
	}

}
