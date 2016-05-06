package com.vogella.jersey.first;

import java.net.URI;
import java.sql.ResultSet;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import org.json.*;

import org.glassfish.jersey.client.ClientConfig;

public class Test {

	public static void main(String[] args) {
		ClientConfig config = new ClientConfig();

		Client client = ClientBuilder.newClient(config);

		WebTarget target = client.target(getBaseURI());

		/*
		 * String response =
		 * target.path("rest").path("hello").request().accept(MediaType.
		 * TEXT_PLAIN).get(Response.class) .toString();
		 * 
		 * String plainAnswer =
		 * target.path("rest").path("hello").request().accept(MediaType.
		 * TEXT_PLAIN).get(String.class); String xmlAnswer =
		 * target.path("rest").path("hello").request().accept(MediaType.TEXT_XML
		 * ).get(String.class); String htmlAnswer =
		 * target.path("rest").path("hello").path("aixx").request().accept(
		 * MediaType.TEXT_HTML) .get(String.class);
		 */

		// Create a new request object
		Form form = new Form();
		form.param("type", "legalstaff");
		form.param("user", "fsmith");
		form.param("pass", "1234");
		String res = target.path("rest").path("lawos").request().accept(MediaType.APPLICATION_JSON)
				.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), String.class);
		// System.out.println("Form response " + res.getStatus());

		// Create a new request object
		/*
		 * form = new Form(); form.param("date", "2016-10-10");
		 * form.param("time", "11:00:00"); form.param("clientID", "1");
		 * form.param("legalStaffID", "1"); form.param("caseID", "0"); String
		 * res2 =
		 * target.path("rest").path("lawos").path("receptionist").path("newapp")
		 * .request() .accept(MediaType.APPLICATION_JSON)
		 * .post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED),
		 * String.class);
		 * 
		 * System.out.println(res2);
		 */
		// Create a new request object
		/*
		 * form = new Form(); form.param("appointmentID", "21"); String res3 =
		 * target.path("rest").path("lawos").path("receptionist").path("delapp")
		 * .request() .accept(MediaType.APPLICATION_JSON)
		 * .post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED),
		 * String.class);
		 * 
		 * System.out.println(res3);
		 */
		/***************************************************/
		// View all incomplete appointments
		String res4 = target.path("rest").path("lawos").path("receptionist").path("app").path("inc").request()
				.accept(MediaType.APPLICATION_JSON).get(String.class);

		System.out.println(res4);
		/***************************************************/
		// View all completed appointments
		String res5 = target.path("rest").path("lawos").path("receptionist").path("app").path("com").request()
				.accept(MediaType.APPLICATION_JSON).get(String.class);

		System.out.println(res5);
		/***************************************************/
		// View all passed appointments
		String res6 = target.path("rest").path("lawos").path("receptionist").path("app").path("passed").request()
				.accept(MediaType.APPLICATION_JSON).get(String.class);

		System.out.println(res6);
		/***************************************************/
		// Mark current appointment
		form = new Form();
		form.param("appointmentID", "13");
		String res7 = target.path("rest").path("lawos").path("receptionist").path("app").path("mark").request()
				.accept(MediaType.APPLICATION_JSON)
				.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), String.class);

		System.out.println(res7);
		/***************************************************/
		// View current client record
		form = new Form();
		form.param("ID", "1");
		String res8 = target.path("rest").path("lawos").path("search").path("client").request()
				.accept(MediaType.APPLICATION_JSON)
				.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), String.class);

		System.out.println(res8);
		/***************************************************/
		// Get Recommendation strategies
		form = new Form();
		form.param("ID", "1");
		String res9 = target.path("rest").path("lawos").path("recom").path("strategies").request()
				.accept(MediaType.APPLICATION_JSON)
				.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), String.class);

		System.out.println(res9);
		/***************************************************/
		// Update current client
		form = new Form();
		form.param("ID", "1");
		form.param("Name", "Andreas");
		form.param("Surname", "Andreou");
		form.param("Unwillingness", "Simple solutions");
		String res10 = target.path("rest").path("lawos").path("lrs").path("edit").path("client").request()
				.accept(MediaType.APPLICATION_JSON)
				.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), String.class);

		System.out.println("Update client status -> " + res10);
		/***************************************************/
		// Get all transactions of a client
		form = new Form();
		form.param("ID", "1");
		String res11 = target.path("rest").path("lawos").path("lrs").path("transactions").path("client").request()
				.accept(MediaType.APPLICATION_JSON)
				.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), String.class);

		System.out.println("Transactions -> " + res11);
		/***************************************************/
		/**
		 * HEAD OFFICE MANAGEMENT REST TEST
		 */
		// viewNumofClientsPerBranch
		form = new Form();
		form.param("BranchID", "0");
		String res12 = target.path("rest").path("lawos").path("hom").path("reports").path("branch").path("nclient")
				.request().accept(MediaType.APPLICATION_JSON)
				.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), String.class);

		System.out.println("HOM : numofclients per branch -> " + res12);
		/***************************************************/
		// recommendations per month
		form = new Form();
		form.param("BranchID", "0");
		String res13 = target.path("rest").path("lawos").path("hom").path("reports").path("recom").path("permonth")
				.request().accept(MediaType.APPLICATION_JSON)
				.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), String.class);

		System.out.println("HOM : recommendations per month -> " + res13);
		/***************************************************/
		// legal opinions per month
		form = new Form();
		form.param("BranchID", "0");
		String res14 = target.path("rest").path("lawos").path("hom").path("reports").path("legalop").path("permonth")
				.request().accept(MediaType.APPLICATION_JSON)
				.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), String.class);

		System.out.println("HOM : legal opinions per month -> " + res14);
		/***************************************************/
		// completed app of a client
		form = new Form();
		form.param("ID", "1");
		String res15 = target.path("rest").path("lawos").path("hom").path("reports").path("client").path("timeswait")
				.request().accept(MediaType.APPLICATION_JSON)
				.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), String.class);

		System.out.println("HOM : completed app of a client (timeswait) -> " + res15);
		/***************************************************/
		// Weekly reports for attended number of clients per branch per weekday
		form = new Form();
		form.param("BranchID", "0");
		String res16 = target.path("rest").path("lawos").path("hom").path("reports").path("weekly").path("perbranch")
				.path("attended").request().accept(MediaType.APPLICATION_JSON)
				.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), String.class);

		System.out.println("HOM : Weekly reports for attended number of clients per branch per weekday -> " + res16);
		/***************************************************/
		// Weekly reports for total visits of clients per branch per weekday
		form = new Form();
		form.param("BranchID", "0");
		String res17 = target.path("rest").path("lawos").path("hom").path("reports").path("weekly").path("perbranch")
				.path("all").request().accept(MediaType.APPLICATION_JSON)
				.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), String.class);

		System.out.println("HOM : Weekly reports for total visits of clients per branch per weekday -> " + res17);
		/***************************************************/
		// Weekly reports per client
		form = new Form();
		form.param("ClientID", "1");
		String res18 = target.path("rest").path("lawos").path("hom").path("reports").path("weekly").path("perclient")
				.request().accept(MediaType.APPLICATION_JSON)
				.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), String.class);

		System.out.println("HOM : Weekly reports per client -> " + res18);
		/***************************************************/

		JSONObject json = null;
		try {
			json = new JSONObject(res);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// try {
		// json = JSONML.toJSONObject(res);
		// } catch (JSONException e) {
		// System.err.println("[!]Problem with JSON parsing");
		// e.printStackTrace();
		// }
		try {
			JSONObject json2 = new JSONObject(json.get("results_array").toString().substring(1,
					json.get("results_array").toString().length() - 1));
			System.out.println(json2.get("Username"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * System.out.println(response); System.out.println(plainAnswer);
		 * System.out.println(xmlAnswer); System.out.println(htmlAnswer);
		 */
	}

	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:80/LawOSREST").build();
	}
}