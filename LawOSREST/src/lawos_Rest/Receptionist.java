package lawos_Rest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Receptionist Service
 *
 */
@Path("/lawos/receptionist")
public class Receptionist {

	/**
	 * Add a new appointment into the system for a specific client and lawyer
	 * given with all the appropriate fields to be entered.
	 * 
	 * @param date
	 * @param time
	 * @param clientID
	 * @param legalStaffID
	 * @param caseID
	 * @param branchID
	 * @param recom
	 * @param legalop
	 * @return
	 */
	@Path("newapp")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String addAppointment(@FormParam("date") String date, @FormParam("time") String time,
			@FormParam("clientID") String clientID, @FormParam("legalStaffID") String legalStaffID,
			@FormParam("caseID") String caseID, @FormParam("branchID") String branchID,
			@FormParam("recom") String recom, @FormParam("legalop") String legalop) {

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int response = 0;
		java.sql.Statement stmt;
		try {
			stmt = conn.createStatement();
			response = stmt.executeUpdate(
					"INSERT INTO `appointment`(`Date`, `Time`, `Completed`, `clientID`, `legalStaffID`, `Case`, `BranchID`, `Recommendation`, `LegalOpinion`) VALUES ('"
							+ date + "', '" + time + "', 0, '" + clientID + "', " + legalStaffID + ", " + caseID + ", "
							+ branchID + ", '" + recom + "', '" + legalop + "');");
			// rs.close();
		} catch (SQLException e) {
			System.err.println("[!]Problem with requested statement");
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (response == 1)
			return "1";
		else
			return "0";
	}

	/**
	 * Delete a specific appointment from the system by given AppointmentID
	 * given.
	 * 
	 * @param appointmentID
	 * @return
	 */
	@Path("delapp")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String delAppointment(@FormParam("appointmentID") String appointmentID) {

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int response = 0;
		java.sql.Statement stmt;
		try {
			stmt = conn.createStatement();
			response = stmt.executeUpdate("DELETE FROM `appointment` WHERE AppointmendID=" + appointmentID);
			// rs.close();
		} catch (SQLException e) {
			System.err.println("[!]Problem with requested statement");
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (response == 1)
			return "1";
		else
			return "0";
	}

	/**
	 * Returns all the incomplete appointments (pending).
	 * 
	 * @return
	 */
	@Path("/app/inc")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String viewIncApp() {

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
			ResultSet rs = stmt.executeQuery("SELECT * FROM `appointment` WHERE Completed=0");
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
	}// end of view inc

	/**
	 * Returns all the completed appointments (previous and done).
	 * 
	 * @return
	 */
	@Path("/app/com")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String viewComApp() {

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
			ResultSet rs = stmt.executeQuery("SELECT * FROM `appointment` WHERE Completed=1");
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
	}// end of view com

	/**
	 * Returns all the passed and failed to complete - appointments.
	 * 
	 * @return
	 */
	@Path("/app/passed")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String viewPassedApp() {

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
					"SELECT * FROM `appointment` WHERE (Completed=0 AND  Date<CURDATE() AND Time<CURTIME())");
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
	}// end of view passed

	/**
	 * Marks a specific appointment as completed and updates it to the system.
	 * 
	 * @param appointmentID
	 * @return
	 */
	@Path("/app/mark")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String markAppointment(@FormParam("appointmentID") String appointmentID) {

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int response = 0;
		java.sql.Statement stmt;
		try {
			stmt = conn.createStatement();
			response = stmt.executeUpdate("UPDATE `appointment` SET Completed=1 WHERE AppointmendID=" + appointmentID);
		} catch (SQLException e) {
			System.err.println("[!]Problem with requested statement");
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (response == 1)
			return "1";
		else
			return "0";
	}// end of mark appointment

}// end of class
