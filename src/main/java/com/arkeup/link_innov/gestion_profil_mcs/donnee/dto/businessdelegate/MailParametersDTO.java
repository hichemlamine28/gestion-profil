/**
 * 
 */
package com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.businessdelegate;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.BaseDTO;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author mikajy
 *
 */
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class MailParametersDTO extends BaseDTO {

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String userName;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private int type;
	@JsonInclude
	private String language;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String registrationId;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String mail;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String firstName;

	private String keyValidateProfil;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setKeyValidateProfil(String keyValidateProfil) {
		this.keyValidateProfil = keyValidateProfil;
	}

	public String getKeyValidateProfil() {
		return this.keyValidateProfil;
	}

}
