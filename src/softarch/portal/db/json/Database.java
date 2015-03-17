package softarch.portal.db.json;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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
	
	public Database(String dbUrl) {
		this.dbUrl = dbUrl;
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
	public void insert(String database, String table, JsonElement json) throws IOException {
		File file = new File(dbUrl + database + "." + table + ".json");
		if (!file.exists())
			file.createNewFile();
		
		FileReader reader = new FileReader(file);
		JsonParser parser = new JsonParser();
		JsonElement el = parser.parse(reader);
		// TODO controleren of programme niet crasht met lege json file.
		JsonArray array = el.getAsJsonArray();
		array.add(json);
		reader.close();
		
		// Write back to disk
		FileWriter writer = new FileWriter(file);
		writer.write(array.toString());
		writer.close();
	}
}
