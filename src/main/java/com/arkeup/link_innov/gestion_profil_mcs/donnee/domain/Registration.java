/**
 * 
 */
package com.arkeup.link_innov.gestion_profil_mcs.donnee.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author mikajy
 *
 */
@Document
public final class Registration {
	/**
	 * 
	 */
	@Id
	private String id;
	
	/**
	 * 
	 */
	@Indexed(unique = true)
	private String useruid;
	
	private String email;
	
	/**
	 * 
	 */
	private Date creationDate;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
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
