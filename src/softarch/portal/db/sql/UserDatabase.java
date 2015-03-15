package softarch.portal.db.sql;

import softarch.portal.data.CheapSubscription;
import softarch.portal.data.ExpensiveSubscription;
import softarch.portal.data.ExpertAdministrator;
import softarch.portal.data.ExternalAdministrator;
import softarch.portal.data.FreeSubscription;
import softarch.portal.data.Operator;
import softarch.portal.data.RegularAdministrator;
import softarch.portal.data.UserProfile;
import softarch.portal.db.UserDatabaseInterface;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

/**
 * This class encapsulates the user database.
 * @author Niels Joncheere
 */
public class UserDatabase extends Database implements UserDatabaseInterface {
	/**
	 * Creates a new user database.
	 */
	public UserDatabase(String dbUser, String dbPassword, String dbUrl) {
		super(dbUser, dbPassword, dbUrl);
	}

	/* (non-Javadoc)
	 * @see softarch.portal.db.sql.UserDatabaseInterface#insert(softarch.portal.data.UserProfile)
	 */
	public void insert(UserProfile profile)
		throws DatabaseException {
		
		executeSql(profile.asSql());
	}
	public void insertFree(UserProfile profile) {
		try {
			executeSql("INSERT INTO FreeSubscription (Username, Password, " +
				"FirstName, LastName, EmailAddress, LastLogin) " +
				"VALUES (\'" + normalizeSql(profile.getUsername()) + "\', \'" +
				normalizeSql(profile.getPassword()) + "\', \'" +
				normalizeSql(profile.getFirstName()) + "\', \'" +
				normalizeSql(profile.getLastName()) + "\', \'" +
				normalizeSql(profile.getEmailAddress()) + "\', \'" +
				df.format(profile.getLastLogin()) + "\');");
		} catch (DatabaseException e) {
			//Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void insertcheap(UserProfile profile) {
		
		try {
			executeSql("INSERT INTO CheapSubscription (Username, Password, " +
				"FirstName, LastName, EmailAddress, LastLogin) " +
				"VALUES (\'" + normalizeSql(profile.getUsername()) + "\', \'" +
				normalizeSql(profile.getPassword()) + "\', \'" +
				normalizeSql(profile.getFirstName()) + "\', \'" +
				normalizeSql(profile.getLastName()) + "\', \'" +
				normalizeSql(profile.getEmailAddress()) + "\', \'" +
				df.format(profile.getLastLogin()) + "\');");
		} catch (DatabaseException e) {
			//Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	public void insertExpensive(UserProfile profile) {
		try {
			executeSql("INSERT INTO ExpensiveSubscription (Username, " +
				"Password, FirstName, LastName, EmailAddress, " +
				"LastLogin) VALUES (\'" + normalizeSql(profile.getUsername()) +
				"\', \'" + normalizeSql(profile.getPassword()) +"\', \'" +
				normalizeSql(profile.getFirstName()) + "\', \'" +
				normalizeSql(profile.getLastName()) + "\', \'" +
				normalizeSql(profile.getEmailAddress()) + "\', \'" +
				df.format(profile.getLastLogin()) + "\');");
		} catch (DatabaseException e) {
			//Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/* (non-Javadoc)
	 * @see softarch.portal.db.sql.UserDatabaseInterface#update(softarch.portal.data.UserProfile)
	 */
	public void update(UserProfile profile)
		throws DatabaseException {
		
		executeSql(profile.asSqlUpdate());
	}

	/* (non-Javadoc)
	 * @see softarch.portal.db.sql.UserDatabaseInterface#findUser(java.lang.String)
	 */
	
	//-------------------------------------------------------------------------------------------------------------------------
	public UserProfile findUser(String username)
		throws DatabaseException {

		// Connect to the database:
		try {
			Statement statement
				= getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs;
			
			rs = statement.executeQuery(
				"SELECT * FROM FreeSubscription WHERE " +
				"Username = \'" + username + "\';");
			
			if (rs.first())
				return new FreeSubscription(rs);

			rs = statement.executeQuery(
				"SELECT * FROM CheapSubscription WHERE " +
				"Username = \'" + username + "\';");
			if (rs.first())
				return new CheapSubscription(rs);

			rs = statement.executeQuery(
				"SELECT * FROM ExpensiveSubscription WHERE " +
				"Username = \'" + username + "\';");
			if (rs.first())
				return new ExpensiveSubscription(rs);

			rs = statement.executeQuery(
				"SELECT * FROM Operator WHERE " +
				"Username = \'" + username + "\';");
			if (rs.first())
				return new Operator(rs);

			rs = statement.executeQuery(
				"SELECT * FROM ExternalAdministrator WHERE " +
				"Username = \'" + username + "\';");
			if (rs.first())
				return new ExternalAdministrator(rs);

			rs = statement.executeQuery(
				"SELECT * FROM RegularAdministrator WHERE " +
				"Username = \'" + username + "\';");
			if (rs.first())
				return new RegularAdministrator(rs);

			rs = statement.executeQuery(
				"SELECT * FROM ExpertAdministrator WHERE " +
				"Username = \'" + username + "\';");
			if (rs.first())
				return new ExpertAdministrator(rs);

			throw new DatabaseException("Invalid username!");
		}

		// Exception handling:
		catch (SQLException e) {
			throw new DatabaseException(
				"SQL Exception: " + e.getMessage());
		}
		catch (ParseException e) {
			throw new DatabaseException(
				"Parse Exception: " + e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see softarch.portal.db.sql.UserDatabaseInterface#userExists(java.lang.String)
	 */
	public boolean userExists(String username)
		throws DatabaseException {

		// Connect to the database:
		try {

			Statement statement = getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs;
			
			rs = statement.executeQuery(
				"SELECT * FROM FreeSubscription WHERE " +
				"Username = \'" + username + "\';");
			if (rs.first())
				return true;

			rs = statement.executeQuery(
				"SELECT * FROM CheapSubscription WHERE " +
				"Username = \'" + username + "\';");
			if (rs.first())
				return true;

			rs = statement.executeQuery(
				"SELECT * FROM ExpensiveSubscription WHERE " +
				"Username = \'" + username + "\';");
			if (rs.first())
				return true;

			rs = statement.executeQuery(
				"SELECT * FROM Operator WHERE " +
				"Username = \'" + username + "\';");
			if (rs.first())
				return true;

			rs = statement.executeQuery(
				"SELECT * FROM ExternalAdministrator WHERE " +
				"Username = \'" + username + "\';");
			if (rs.first())
				return true;

			rs = statement.executeQuery(
				"SELECT * FROM RegularAdministrator WHERE " +
				"Username = \'" + username + "\';");
			if (rs.first())
				return true;

			rs = statement.executeQuery(
				"SELECT * FROM ExpertAdministrator WHERE " +
				"Username = \'" + username + "\';");
			if (rs.first())
				return true;

			return false;
		}

		// Exception handling:
		catch (SQLException e) {
			throw new DatabaseException(
				"SQL Exception: " + e.getMessage());
		}
	}


}
