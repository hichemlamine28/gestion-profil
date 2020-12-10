/**
 * 
 */
package com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.inscription;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.BaseDTO;

/**
 * @author mikajy
 *
 */
public class RegistrationDTO extends BaseDTO {
	
	private boolean valid;
	
	private String useruid;
	
	private String email;
	
	public RegistrationDTO() {}
	
	public RegistrationDTO(boolean valid, String useruid) {
		super();
		this.valid = valid;
		this.useruid = useruid;
	}

	public RegistrationDTO(boolean valid) {
		super();
		this.valid = valid;
	}

	public RegistrationDTO(boolean isValid, String useruid, String email) {
		super();
		this.useruid = useruid;
		this.valid = isValid;
		this.email = email;
	}	

	/**
	 * @return the valid
	 */
	public boolean isValid() {
		return valid;
	}

	/**
	 * @param valid the valid to set
	 */
	public void setValid(boolean valid) {
		this.valid = valid;
	}

	/**
	 * @return the useruid
	 */
	public String getUseruid() {
		return useruid;
	}

	/**
	 * @param useruid the useruid to set
	 */
	public void setUseruid(String useruid) {
		this.useruid = useruid;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	
	
}
