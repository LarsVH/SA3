package softarch.portal.db.json;

import java.util.Date;

import softarch.portal.data.UserProfile;

/**
 * Wrapper object for UserProfile to make sure that the serialization of the
 * UserProfile object to JSON happens correctly.
 * 
 * @author pieter
 *
 */
public class UserProfileJson {
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private Date lastLogin;
	
	public UserProfileJson(UserProfile userProfile) {
		this.userName = userProfile.getUsername();
		this.password = userProfile.getPassword();
		this.firstName = userProfile.getFirstName();
		this.lastName = userProfile.getLastName();
		this.emailAddress = userProfile.getEmailAddress();
		this.lastLogin = userProfile.getLastLogin();
	}
}
