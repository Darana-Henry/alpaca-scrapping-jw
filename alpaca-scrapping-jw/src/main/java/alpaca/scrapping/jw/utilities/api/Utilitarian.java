package alpaca.scrapping.jw.utilities.api;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.io.FileUtils;

import com.google.gson.JsonObject;

public class Utilitarian {

	public static String getmovieNamefromJW(String movie) {
		return movie.replaceAll("https://www.justwatch.com/in/movie/", "");
	}

	public static String getUpdateSearchBody(String movie, String body) {
		return body.replaceAll("movieName", movie);
	}

	public static String getFileContent(String fileName) {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());

		try {
			return FileUtils.readFileToString(file, Charset.defaultCharset());
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}

	public static String getmovieID(JsonObject results) {
		return results.getAsJsonArray("items").get(0).getAsJsonObject().get("id").getAsString();
	}

	public static String updateEndpointWithID(String endpoint, String id) {
		return endpoint.replaceAll("movieID", id);
	}
}
