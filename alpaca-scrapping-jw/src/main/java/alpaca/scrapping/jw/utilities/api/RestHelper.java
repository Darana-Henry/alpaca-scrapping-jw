package alpaca.scrapping.jw.utilities.api;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.MultiPartFeature;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class RestHelper {

	private static Client client;
	private static WebTarget webTarget;
	JsonParser parser;

	public RestHelper() {
		this.getClientBuilder();
		parser = new JsonParser();
	}

	private void getClientBuilder() {
		client = ClientBuilder.newBuilder().register(MultiPartFeature.class).build();
	}

	private void setWebTarget(String endPoint) {
		webTarget = client.target(endPoint);
	}

	public JsonObject submitGETRequest(String endpoint) {
		this.setWebTarget(endpoint);

		Response response = webTarget.request().get();
		String responseString = response.readEntity(String.class);
		parser = new JsonParser();
		JsonObject responseObject;
		responseObject = parser.parse(responseString).getAsJsonObject();
		// System.out.println(responseString);

		return responseObject;
	}

	public JsonObject submitPOSTRequest(String endpoint, String body) {
		this.setWebTarget(endpoint);

		Response response = webTarget.request().post(Entity.json(body));
		String responseString = response.readEntity(String.class);
		JsonObject responseObject = parser.parse(responseString).getAsJsonObject();
		// System.out.println(responseString);

		return responseObject;
	}

	public JsonArray submitGETRequestReturningArray(String endpoint) {
		this.setWebTarget(endpoint);

		Response response = webTarget.request().get();
		String responseString = response.readEntity(String.class);
		parser = new JsonParser();
		JsonArray responseObject;
		responseObject = parser.parse(responseString).getAsJsonArray();
		// System.out.println(responseString);

		return responseObject;
	}

}
