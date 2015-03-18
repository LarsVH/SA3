package softarch.portal.db.json;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import softarch.portal.data.CheapSubscription;
import softarch.portal.data.ExpensiveSubscription;
import softarch.portal.data.FreeSubscription;
import softarch.portal.data.UserProfile;
import softarch.portal.db.UserDatabaseInterface;
import softarch.portal.db.sql.DatabaseException;

public class UserDatabase extends Database implements UserDatabaseInterface {
	private final String DATABASE_USER = "UserDatabase";
	private final String TABLE_FREE_SUB = "FreeSubscription";
	private final String TABLE_CHEAP_SUB = "CheapSubscription";
	private final String TABLE_EXP_SUB = "ExpensiveSubscription";

	public UserDatabase(String dbUrl) {
		super(dbUrl);
	}
	
	public void insertFree(UserProfile profile) throws DatabaseException {
		insert(profile, TABLE_FREE_SUB);
	}

	public void insertCheap(UserProfile profile) throws DatabaseException {
		insert(profile, TABLE_CHEAP_SUB);
	}

	public void insertExpensive(UserProfile profile) throws DatabaseException {
		insert(profile, TABLE_EXP_SUB);
	}
	
	private void insert(UserProfile profile, String tableName) throws DatabaseException {
		UserProfileJson profileJson = new UserProfileJson(profile);
		insert(DATABASE_USER, tableName, gson.toJsonTree(profileJson));
	}

	
	public void update(UserProfile profile) throws DatabaseException {
		
		
	}
	
	public void updateFree(UserProfile profile) throws DatabaseException {
		update(profile, TABLE_FREE_SUB);
	}

	public void updateCheap(UserProfile profile) throws DatabaseException {
		update(profile, TABLE_CHEAP_SUB);
	}

	public void updateExpensive(UserProfile profile) throws DatabaseException {
		update(profile, TABLE_EXP_SUB);
	}
	
	private void update(UserProfile profile, String tableName) throws DatabaseException {
		UserProfileJson profileJson = new UserProfileJson(profile);
		update(DATABASE_USER, tableName, "userName", profileJson.userName, gson.toJsonTree(profileJson));
	}

	public UserProfile findUser(String username) throws DatabaseException {
		JsonElement elUserFree = find(DATABASE_USER, TABLE_FREE_SUB, "userName", username);
		if (elUserFree != null) {
			JsonObject obj = elUserFree.getAsJsonObject();
			try {
				FreeSubscription user = new FreeSubscription(obj.get("userName").getAsString(), 
						obj.get("password").getAsString(), 
						obj.get("firstName").getAsString(), 
						obj.get("lastName").getAsString(), 
						obj.get("emailAddress").getAsString(), 
						new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").parse(obj.get("lastLogin").getAsString()));
				return user;
			} catch (ParseException e) {
				// Should never happen
				e.printStackTrace();
				return null;
			}
		} 
		
		JsonElement elUserCheap = find(DATABASE_USER, TABLE_CHEAP_SUB, "userName", username);
		if(elUserCheap != null) {
			JsonObject obj = elUserCheap.getAsJsonObject();
			try {
				CheapSubscription user = new CheapSubscription(obj.get("userName").getAsString(), 
						obj.get("password").getAsString(), 
						obj.get("firstName").getAsString(), 
						obj.get("lastName").getAsString(), 
						obj.get("emailAddress").getAsString(), 
						new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").parse(obj.get("lastLogin").getAsString()));
				return user;
			} catch (ParseException e) {
				// Should never happen
				e.printStackTrace();
				return null;
			}
		}
		
		JsonElement elUserExpensive = find(DATABASE_USER, TABLE_EXP_SUB, "userName", username);
		if (elUserExpensive != null) {
			JsonObject obj = elUserExpensive.getAsJsonObject();
			try {
				ExpensiveSubscription user = new ExpensiveSubscription(obj.get("userName").getAsString(), 
						obj.get("password").getAsString(), 
						obj.get("firstName").getAsString(), 
						obj.get("lastName").getAsString(), 
						obj.get("emailAddress").getAsString(), 
						new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").parse(obj.get("lastLogin").getAsString()));
				return user;
			} catch (ParseException e) {
				// Should never happen
				e.printStackTrace();
				return null;
			}
		}
		
		throw new DatabaseException("Invalid username!");
	}

	public boolean userExists(String username) throws DatabaseException {
		JsonElement elUserFree = find(DATABASE_USER, TABLE_FREE_SUB, "userName", username);
		if (elUserFree != null) {
			return true;
		} 
		
		JsonElement elUserCheap = find(DATABASE_USER, TABLE_CHEAP_SUB, "userName", username);
		if(elUserCheap != null) {
			return true;
		}
		
		JsonElement elUserExpensive = find(DATABASE_USER, TABLE_EXP_SUB, "userName", username);
		if (elUserExpensive != null) {
			return true;
		}
		
		return false;
	}
}
