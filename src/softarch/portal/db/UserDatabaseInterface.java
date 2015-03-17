package softarch.portal.db;

import softarch.portal.data.UserProfile;
import softarch.portal.db.sql.DatabaseException;

public interface UserDatabaseInterface {

	/**
	 * Inserts a new user profile into the user database.
	 */
	//public abstract void insert(UserProfile profile) throws DatabaseException;
	
	public abstract void insertFree(UserProfile profile) throws DatabaseException;
	public abstract void insertCheap(UserProfile profile) throws DatabaseException;
	public abstract void insertExpensive(UserProfile profile) throws DatabaseException;

	/**
	 * Updates an existing user profile in the user database.
	 */
	public abstract void update(UserProfile profile) throws DatabaseException;
	
	public abstract void updateFree(UserProfile profile) throws DatabaseException;
	public abstract void updateCheap(UserProfile profile) throws DatabaseException;
	public abstract void updateExpensive(UserProfile profile) throws DatabaseException;

	/**
	 * Returns the user with the specified username.
	 */

	//-------------------------------------------------------------------------------------------------------------------------
	public abstract UserProfile findUser(String username)
			throws DatabaseException;

	/**
	 * Checks whether a user with the specified username exists.
	 */
	public abstract boolean userExists(String username)
			throws DatabaseException;

}