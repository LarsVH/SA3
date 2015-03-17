package softarch.portal.db;

import softarch.portal.data.RawData;
import softarch.portal.data.RegularData;
import softarch.portal.data.UserProfile;
import softarch.portal.db.sql.DatabaseException;
import softarch.portal.db.sql.RawDatabase;
import softarch.portal.db.sql.RegularDatabase;
import softarch.portal.db.sql.UserDatabase;

import java.util.List;
import java.util.Date;

/**
 * This class implements a facade for all of the database layer's functionality.
 * @author Niels Joncheere
 */
public class DatabaseFacade {
	private UserDatabaseInterface	userDb;
	private RegularDatabaseInterface	regularDb;
	private RawDatabaseInterface	rawDb;

	/**
	 * Creates a new database facade.
	 */
	public DatabaseFacade(String dbUser, String dbPassword, String dbUrl, String dbType) {
		if (dbType.equals("sql")) {
			userDb		= new UserDatabase(	dbUser,
								dbPassword,
								dbUrl);
			regularDb	= new RegularDatabase(	dbUser,
								dbPassword,
								dbUrl);
			rawDb		= new RawDatabase(	dbUser,
								dbPassword,
								dbUrl);
		} else {
			userDb = new softarch.portal.db.json.UserDatabase(dbUrl);
			regularDb = new softarch.portal.db.json.RegularDatabase(dbUrl);
			rawDb = new softarch.portal.db.json.RawDatabase(dbUrl);
		}
	}

	/**
	 * Inserts a new free user profile into the user database.
	 */
	public void insertFree(UserProfile profile) throws DatabaseException{
		userDb.insertFree(profile);
	}
	
	/**
	 * Inserts a new cheap user profile into the user database.
	 */
	public void insertCheap(UserProfile profile) throws DatabaseException{
		userDb.insertCheap(profile);
	}
	
	/**
	 * Inserts a new expensive user profile into the user database.
	 */
	public void insertExpensive(UserProfile profile) throws DatabaseException{
		userDb.insertExpensive(profile);
	}

	public void updateFree(UserProfile profile) throws DatabaseException{
		userDb.updateFree(profile);
	}
	public void updateCheap(UserProfile profile) throws DatabaseException{
		userDb.updateCheap(profile);
	}
	public void updateExpensive(UserProfile profile) throws DatabaseException{
		userDb.updateExpensive(profile);
	}

	/**
	 * Returns the user with the specified username.
	 */
	public UserProfile findUser(String username)
			throws DatabaseException {

		return userDb.findUser(username);
	}

	/**
	 * Checks whether a user with the specified username exists.
	 */
	public boolean userExists(String username)
			throws DatabaseException {

		return userDb.userExists(username);
	}

	/**
	 * Returns a list containing all records of the given information type
	 * that match the given query string.
	 */
	public List findRecords(String informationType, String queryString)
			throws DatabaseException {

		return regularDb.findRecords(informationType, queryString);
	}

	/**
	 * Returns a list containing all records of the given information type
	 * that were added after the given date.
	 */
	public List findRecordsFrom(String informationType, Date date)
			throws DatabaseException {

		return regularDb.findRecordsFrom(informationType, date);
	}

	/**
	 * Adds a new regular data object to the regular database.
	 */
	public void add(RegularData rd)
			throws DatabaseException {

		regularDb.add(rd);
	}

	/**
	 * Returns the number of records of the given information type in the
	 * regular database.
	 */
	public int getNumberOfRegularRecords(String informationType)
			throws DatabaseException {

		return regularDb.getNumberOfRegularRecords(informationType);
	}

	/**
	 * Returns a list of all raw data.
	 */
	public List getRawData()
			throws DatabaseException {

		return rawDb.getRawData();
	}

	/**
	 * Returns a specific raw data object.
	 */
	public RawData getRawData(int id)
			throws DatabaseException {

		return rawDb.getRawData(id);
	}

	public void addRawData(RegularData rd)
			throws DatabaseException {

		rawDb.addRawData(rd);
	}

	/**
	 * Deletes a raw data object.
	 */
	public void deleteRawData(RawData rd)
			throws DatabaseException {

		rawDb.deleteRawData(rd);
	}

	/**
	 * Updates a raw data object.
	 */
	public void updateRawData(RawData rd)
			throws DatabaseException {

		rawDb.updateRawData(rd);
	}

	/**
	 * Returns the number of records in the raw database.
	 */
	public int getNumberOfRawRecords()
			throws DatabaseException {

		return rawDb.getNumberOfRawRecords();
	}
}
