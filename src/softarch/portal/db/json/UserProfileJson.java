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
	public String userName;
	public String password;
	public String firstName;
	public String lastName;
	public String emailAddress;
	public Date lastLogin;
	
	public UserProfileJson(UserProfile userProfile) {
		this.userName = userProfile.getUsername();
		this.password = userProfile.getPassword();
		this.firstName = userProfile.getFirstName();
		this.lastName = userProfile.getLastName();
		this.emailAddress = userProfile.getEmailAddress();
		this.lastLogin = userProfile.getLastLogin();
	}
}
