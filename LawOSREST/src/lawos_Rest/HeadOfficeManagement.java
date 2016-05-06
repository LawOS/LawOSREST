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

/**
 * Head Office Management Service
 *
 */
@Path("/lawos/hom")
public class HeadOfficeManagement {

	/**
	 * Returns the number of clients per branch, given a specific BranchID. It
	 * returns a JSON formated ResultSet of two columns.
	 * 
	 * @param BranchID
	 * @return
	 */
	@Path("/reports/branch/nclient")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String viewNumofClientsPerBranch(@FormParam("BranchID") String BranchID) {

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
			ResultSet rs = stmt
					.executeQuery("SELECT MONTH(`Date`), COUNT(DISTINCT ClientID) FROM `appointment` WHERE BranchID="
							+ BranchID + " GROUP BY MONTH(`Date`)");
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
	}// end of numofclients per branch

	/**
	 * Returns all recommendation given into current branch. The user sends a
	 * specific BranchID in order to review it and this returns a JSON formated
	 * ResultSet.
	 * 
	 * @param BranchID
	 * @return
	 */
	@Path("/reports/recom/permonth")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String viewRecomPerBranch(@FormParam("BranchID") String BranchID) {

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
			ResultSet rs = stmt
					.executeQuery("SELECT DISTINCT Recommendation FROM `appointment` WHERE BranchID=" + BranchID);
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
	}// end of viewRecomPerBranch

	/**
	 * Returns all legal opinions described for current branch. The user sends a
	 * specific BranchID in order to review it and this returns a JSON formated
	 * ResultSet.
	 * 
	 * @param BranchID
	 * @return
	 */
	@Path("/reports/legalop/permonth")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String viewLegalopPerBranch(@FormParam("BranchID") String BranchID) {

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
			ResultSet rs = stmt
					.executeQuery("SELECT DISTINCT LegalOpinion FROM `appointment` WHERE BranchID=" + BranchID);
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
	}// end of viewRecomPerBranch

	/**
	 * Returns all the completed appointments/consultations of a current client,
	 * by given ClientID.
	 * 
	 * @param ID
	 * @return
	 */
	@Path("/reports/client/timeswait")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String getClientComApp(@FormParam("ID") String ID) {

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
			ResultSet rs = stmt
					.executeQuery("SELECT * FROM `appointment` WHERE (ClientID='" + ID + "' AND Completed=1)");
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
	}// end of viewRecomPerBranch

	/**
	 * Returns a weekly report including the number of all clients attended a
	 * current branch, by given BranchID.
	 * 
	 * @param BranchID
	 * @return
	 */
	@Path("/reports/weekly/perbranch/attended")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String getWeeklyClientsAttendedPerBranch(@FormParam("BranchID") String BranchID) {

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
					"SELECT DAYOFWEEK(`Date`), COUNT(DISTINCT ClientID) FROM `appointment` WHERE (Completed=1 AND BranchID=" + BranchID
							+ " AND Date <= CURDATE()) GROUP BY DAYOFWEEK(`Date`)");
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
	}// end of getWeeklyClientsAttendedPerBranch
	
	/**
	 * Returns a weekly report including the number of all clients visited in
	 * general a current branch, by given BranchID.
	 * 
	 * @param BranchID
	 * @return
	 */
	@Path("/reports/weekly/perbranch/all")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String getWeeklyClientsTotalVisitedPerBranch(@FormParam("BranchID") String BranchID) {

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
					"SELECT DAYOFWEEK(`Date`), COUNT(DISTINCT ClientID) FROM `appointment` WHERE (BranchID=" + BranchID
							+ " AND Date <= CURDATE()) GROUP BY DAYOFWEEK(`Date`)");
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
	}// end of getWeeklyClientsTotalVisitedPerBranch

	/**
	 * Returns a weekly report including the total number of recommendations and
	 * legal opinions given to a current client, by given ClientID.
	 * 
	 * @param ClientID
	 * @return
	 */
	@Path("/reports/weekly/perclient")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String getWeeklyPerClient(@FormParam("ClientID") String ClientID) {

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
					"SELECT DAYOFWEEK(`Date`), COUNT(DISTINCT Recommendation), COUNT(DISTINCT LegalOpinion) FROM `appointment` WHERE (ClientID='" + ClientID
							+ "' AND Date <= CURDATE()) GROUP BY DAYOFWEEK(`Date`)");
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
	}// end of getWeeklyPerClient
}// end of class
