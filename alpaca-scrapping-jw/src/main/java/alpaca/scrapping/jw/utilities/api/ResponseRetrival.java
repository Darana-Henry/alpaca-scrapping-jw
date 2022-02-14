package alpaca.scrapping.jw.utilities.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import alpaca.scrapping.jw.engine.CoreEngineDeux;

public class ResponseRetrival {

	public static String getMovieTitle(JsonObject results) {
		return results.get("title").getAsString();
	}

	public static String getMovieReleaseYear(JsonObject results) {
		return results.get("original_release_year").getAsString();
	}

	public static String getMovieReleaseDate(JsonObject results) {
		try {
			return results.get("localized_release_date").getAsString();
		} catch (Exception e) {
			return "";
		}
	}

	public static String getMovieAgeCertification(JsonObject results) {
		try {
			return results.get("age_certification").getAsString();
		} catch (Exception e) {
			return "";
		}
	}
	
	public static String getMovieOriginalLanguageTitle(JsonObject results) {
		try {
			return results.get("original_title").getAsString();
		} catch (Exception e) {
			return "";
		}
	}

	public static String getMovieRuntime(JsonObject results) {
		try {
			return results.get("runtime").getAsString();
		} catch (Exception e) {
			return "";
		}
	}

	public static String getMovieCountry(JsonObject results) {
		try {
			return results.getAsJsonArray("production_countries").get(0).getAsString();
		} catch (Exception e) {
			return "";
		}
	}

	public static String getTopBilledActor(JsonObject results) {
		try {
			JsonArray credits = results.getAsJsonArray("credits");
			for (JsonElement credit : credits) {
				JsonObject cred = credit.getAsJsonObject();
				if (cred.get("role").getAsString().equalsIgnoreCase("ACTOR")) {
					return cred.get("name").getAsString();
				}
			}
		} catch (Exception e) {
			return "";
		}
		return "";
	}

	public static String getDirector(JsonObject results) {
		try {
			JsonArray credits = results.getAsJsonArray("credits");
			for (JsonElement credit : credits) {
				JsonObject cred = credit.getAsJsonObject();
				if (cred.get("role").getAsString().equalsIgnoreCase("DIRECTOR")) {
					return cred.get("name").getAsString();
				}
			}
		} catch (Exception e) {
			return "";
		}
		return "";
	}

	public static String getIMDBRating(JsonObject results) {
		try {
			JsonArray scorings = results.getAsJsonArray("scoring");
			for (JsonElement scoring : scorings) {
				JsonObject score = scoring.getAsJsonObject();
				if (score.get("provider_type").getAsString().equalsIgnoreCase("imdb:score")) {
					return score.get("value").getAsString();
				}
			}
		} catch (Exception e) {
			return "";
		}
		return "";
	}

	public static String getIMDBVotes(JsonObject results) {
		try {
			JsonArray scorings = results.getAsJsonArray("scoring");
			for (JsonElement scoring : scorings) {
				JsonObject score = scoring.getAsJsonObject();
				if (score.get("provider_type").getAsString().equalsIgnoreCase("imdb:votes")) {
					return score.get("value").getAsString();
				}
			}
		} catch (Exception e) {
			return "";
		}
		return "";
	}

	public static String getGenres(JsonObject results) {
		String translatedGenre = "";

		try {
			JsonArray genres = CoreEngineDeux.genres;

			JsonArray movieGenres = results.getAsJsonArray("genre_ids");

			for (JsonElement movieGenre : movieGenres) {

				for (JsonElement genre : genres) {
					JsonObject gen = genre.getAsJsonObject();
					if (gen.get("id").getAsString().equalsIgnoreCase(movieGenre.getAsString())) {
						String translation = gen.get("translation").getAsString();
						translatedGenre += translation + ", ";
					}
				}

			}
			translatedGenre = translatedGenre.substring(0, translatedGenre.length() - 2);
		} catch (Exception e) {
			//e.printStackTrace();
			return translatedGenre;
		}
		return translatedGenre;
	}

}
