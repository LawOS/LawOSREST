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
 * Legal Records Staff Service
 *
 */
@Path("/lawos/lrs")
public class LegalRecordsStaff {

	/**
	 * Edits the record of a specific client and updates it successfully into
	 * the database. If the action was successful returns 1, otherwise it
	 * returns 0. It need as a parameter all the appropriate fields of a client
	 * record in order to first find the current client and update his fields.
	 * 
	 * @param ID
	 * @param name
	 * @param surname
	 * @param unwillingness
	 * @param comments
	 * @return
	 */
	@Path("/edit/client")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String editClient(@FormParam("ID") String ID, @FormParam("Name") String name,
			@FormParam("Surname") String surname, @FormParam("Unwillingness") String unwillingness, @FormParam("Comments") String comments) {

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
			response = stmt.executeUpdate("UPDATE `client` SET Name='" + name + "', Surname='" + surname
					+ "', Unwillingness='" + unwillingness + "', Comments='" + comments + "' WHERE ID='" + ID + "'");
			stmt.executeUpdate("INSERT INTO `Transaction`(`ClientID`, `Date`, `Time`, `Description`) VALUES ('" + ID
					+ "', " + "CURDATE()," + "CURTIME()," + "'Client record changed to: Name=" + name + ", Surname="
					+ surname + ", Unwillingness=" + unwillingness + ", Comments=" + comments + "');");

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
	}// end of edit client record
	
	/**
	 * Get all transactions made for a specific client, by given ClientID.
	 * 
	 * @param ID
	 * @return
	 */
	@Path("/transactions/client")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String getClientTransactions(@FormParam("ID") String ID) {

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
			ResultSet rs = stmt.executeQuery("SELECT * FROM `Transaction` WHERE ClientID='" + ID + "'");
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

}// end of class
