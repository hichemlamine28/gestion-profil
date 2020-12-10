/**
 * 
 */
package com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.businessdelegate;

import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author mikajy
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReseauSocialUserDTO {

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String id;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String firstName;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String lastName;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String employerName;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String profession;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String type;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Date lastConnectionDate;

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
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmployerName() {
		return employerName;
	}

	public void setEmployerName(String employerName) {
		this.employerName = employerName;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getLastConnectionDate() {
		return lastConnectionDate;
	}

	public void setLastConnectionDate(Date lastConnectionDate) {
		this.lastConnectionDate = lastConnectionDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof ReseauSocialUserDTO)) {
			return false;
		}
		ReseauSocialUserDTO other = (ReseauSocialUserDTO) obj;
		return Objects.equals(id, other.id);
	}

}
