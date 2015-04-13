package softarch.portal.data;

import javax.servlet.http.HttpServletRequest;

import softarch.portal.db.DatabaseFacade;
import softarch.portal.db.sql.DatabaseException;

import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

/**
 * Represents an <i>expensive subscription</i> user account.
 * @author Niels Joncheere
 */
public class ExpensiveSubscription extends RegularUser {
	/**
	 * Creates a new <i>expensive subscription</i> account from a
	 * <code>javax.servlet.http.HttpServletRequest</code> object.
	 * @see javax.servlet.http.HttpServletRequest
	 */
	public ExpensiveSubscription(HttpServletRequest request) {
		this(	request.getParameter("Username"),
			request.getParameter("Password"),
			request.getParameter("FirstName"),
			request.getParameter("LastName"),
			request.getParameter("EmailAddress"),
			new Date());
	}

	/**
	 * Creates a new <i>expensive subscription</i> account from a
	 * <code>java.sql.ResultSet</code> object.
	 * @see java.sql.ResultSet
	 */
	public ExpensiveSubscription(ResultSet rs)
		throws SQLException, ParseException {
	
		this.username		= rs.getString("Username");
		this.password		= rs.getString("Password");
		this.firstName		= rs.getString("FirstName");
		this.lastName		= rs.getString("LastName");
		this.emailAddress	= rs.getString("EmailAddress");
		this.lastLogin		= df.parse(rs.getString("LastLogin"));
	}

	/**
	 * Creates a new <i>expensive subscription</i> account.
	 */
	public ExpensiveSubscription(	String	username,
					String	password,
					String	firstName,
					String	lastName,
					String	emailAddress,
					Date	lastLogin) {
					
		this.username		= username;
		this.password		= password;
		this.firstName		= firstName;
		this.lastName		= lastName;
		this.emailAddress	= emailAddress;
		this.lastLogin		= lastLogin;
	}

	/**
	 * Returns an XML representation of the object.
	 */
	public String asXml() {
		return	"<ExpensiveSubscription>" +
			"<username>" + normalizeXml(username) + "</username>" +
			// password is not returned,
			// as it should only be used internally
			"<firstName>" +
			normalizeXml(firstName) +
			"</firstName>" +
			"<lastName>" + normalizeXml(lastName) + "</lastName>" +
			"<emailAddress>" +
			normalizeXml(emailAddress) +
			"</emailAddress>" +
			"<lastLogin>" + df.format(lastLogin) + "</lastLogin>" +
			"</ExpensiveSubscription>";
	}

	@Override
	public void insertToDatabase(DatabaseFacade dbFacade) throws DatabaseException {
		dbFacade.insertExpensive(this);
	}

	@Override
	public void updateToDatabase(DatabaseFacade dbFacade)
			throws DatabaseException {
		dbFacade.updateExpensive(this);
		
	}


}
