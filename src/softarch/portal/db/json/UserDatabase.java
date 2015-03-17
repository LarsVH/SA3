package softarch.portal.db.json;

import java.io.IOException;

import com.google.gson.Gson;

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
	
	public void insert(UserProfile profile) throws DatabaseException {
		// TODO methode wordt verwijderd
	}

	public void insertFree(UserProfile profile) {
		insert(profile, TABLE_FREE_SUB);
	}

	public void insertcheap(UserProfile profile) throws DatabaseException {
		insert(profile, TABLE_CHEAP_SUB);
	}

	public void insertExpensive(UserProfile profile) {
		insert(profile, TABLE_EXP_SUB);
	}
	
	private void insert(UserProfile profile, String tableName) {
		try {
			UserProfileJson profileJson = new UserProfileJson(profile);
			insert(DATABASE_USER, tableName, new Gson().toJsonTree(profileJson));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void update(UserProfile profile) throws DatabaseException {
		// TODO Auto-generated method stub
		
	}

	public UserProfile findUser(String username) throws DatabaseException {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean userExists(String username) throws DatabaseException {
		// TODO Auto-generated method stub
		return false;
	}

}
