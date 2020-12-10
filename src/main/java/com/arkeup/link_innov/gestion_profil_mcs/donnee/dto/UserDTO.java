package com.arkeup.link_innov.gestion_profil_mcs.donnee.dto;

import java.util.Date;
import java.util.Objects;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.BaseDTO;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 *
 * @author Jéremy
 *
 */
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class UserDTO extends BaseDTO {

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

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
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof UserDTO)) {
			return false;
		}
		UserDTO other = (UserDTO) obj;
		return Objects.equals(id, other.id);
	}

}
