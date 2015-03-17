package softarch.portal.db.json;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import softarch.portal.db.sql.DatabaseException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
 * This class implements the behaviour that is shared by all JSON databases.
 * 
 * @author pieter
 */
public class Database {
	protected String dbUrl;
	protected Gson gson;
	
	public Database(String dbUrl) {
		this.dbUrl = dbUrl;
		this.gson = new GsonBuilder().setDateFormat("MM/dd/yyyy HH:mm:ss").create();
	}
	
	/**
	 * Reads the entire array from the json file, append element to that array and 
	 * write it back to json file.
	 * 
	 * @param database
	 * @param table
	 * @param json
	 * @throws IOException
	 */
	public void insert(String database, String table, JsonElement json) throws DatabaseException {
		try {
			File file = new File(dbUrl + database + "." + table + ".json");
			if (!file.exists())
				file.createNewFile();
			
			FileReader reader = new FileReader(file);
			JsonParser parser = new JsonParser();
			JsonElement el = parser.parse(reader);
			JsonArray array;
			if (el.isJsonArray()) {
				array = el.getAsJsonArray();
				array.add(json);
				
			} else { // Json file is empty
				array = new JsonArray();
				array.add(json);
			}
			reader.close();
			
			// Write back to disk
			FileWriter writer = new FileWriter(file);
			writer.write(array.toString());
			writer.close();
		} catch (IOException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	/**
	 * Reads the entire array from the json file, update the element in that array that has a matching key-value pair 
	 * and write it back to json file.
	 * 
	 * @param database
	 * @param table
	 * @param key
	 * @param value
	 * @param json
	 * @throws IOException
	 */
	public void update(String database, String table, String key, String value, JsonElement json) throws DatabaseException {
		try {
			File file = new File(dbUrl + database + "." + table + ".json");
			if (!file.exists())
				file.createNewFile();
			
			FileReader reader = new FileReader(file);
			JsonParser parser = new JsonParser();
			JsonElement el = parser.parse(reader);
			if (el.isJsonArray()) {
				JsonArray array = el.getAsJsonArray();
				Iterator<JsonElement> it = array.iterator();
				int index = 0;
				while(it.hasNext()) {
					JsonElement profile = it.next();
					if (profile.isJsonObject()) {
						if (profile.getAsJsonObject().get(key).getAsString().equals(value)) {
							array.set(index, json);
							break;
						}
					}
					index++;
				}
				
				FileWriter writer = new FileWriter(file);
				writer.write(array.toString());
				writer.close();
			} // Else there is nothing to do.
		} catch (IOException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	/**
	 * @param database
	 * @param table
	 * @param key
	 * @param value
	 * @return null if the requested element is not found.
	 * @throws DatabaseException
	 */
	public JsonElement find(String database, String table, String key, String value) throws DatabaseException {
		try {
			File file = new File(dbUrl + database + "." + table + ".json");
			if (!file.exists())
				file.createNewFile();
			
			FileReader reader = new FileReader(file);
			JsonParser parser = new JsonParser();
			JsonElement el = parser.parse(reader);
			if (el.isJsonArray()) {
				JsonArray array = el.getAsJsonArray();
				Iterator<JsonElement> it = array.iterator();
				int index = 0;
				while(it.hasNext()) {
					JsonElement element = it.next();
					if (element.isJsonObject()) {
						if (element.getAsJsonObject().get(key).getAsString().equals(value)) {
							return element;
						}
					}
					index++;
				}
				return null;
			} else {
				return null;
			}
		} catch (IOException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
}
