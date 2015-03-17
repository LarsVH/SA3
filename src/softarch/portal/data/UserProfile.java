package softarch.portal.data;

import java.util.Date;

import softarch.portal.db.DatabaseFacade;
import softarch.portal.db.sql.DatabaseException;

/**
 * This is an abstract superclass for all user profiles.
 * @author Niels Joncheere
 */
public abstract class UserProfile extends Data {
	protected	String	username;
	protected	String	password;
	protected	String	firstName;
	protected	String	lastName;
	
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	protected	String	emailAddress;
	protected	Date	lastLogin;

	/**
	 * Returns an SQL string that allows the system to add the account
	 * to a relational database.
	 */
	//public abstract String asSql();

	/**
	 * When a user has logged in successfully, he will be
	 * redirected to this page.
	 */
	public abstract String getDefaultPage();

	/**
	 * Returns an SQL UPDATE string that allows the system to update
	 * the account in a relational database.
	 */
	//public abstract String asSqlUpdate();
	
	/**
	 * 
	 * insert userprofile into DB using DatabaseFacade
	 * @throws DatabaseException 
	 */
	
	public abstract void insertToDatabase(DatabaseFacade dbFacade, UserProfile profile) throws DatabaseException;

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public UserProfile setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
		return this;
	}

}
