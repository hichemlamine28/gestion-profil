
package com.arkeup.link_innov.gestion_profil_mcs.donnee.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 *
 * @author bona
 */

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class AdminsDTO {
	private String id;

	private String administredId;

	private String administredType;

	private String userId;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<String> admins = new ArrayList<String>();

	public AdminsDTO() {
		super();
	}

	public AdminsDTO(String administredId, String administredType) {
		super();
		this.administredId = administredId;
		this.administredType = administredType;
	}

	public String getAdministredId() {
		return administredId;
	}

	public void setAdministredId(String administredId) {
		this.administredId = administredId;
	}

	public String getAdministredType() {
		return administredType;
	}

	public void setAdministredType(String administredType) {
		this.administredType = administredType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<String> getAdmins() {
		return admins;
	}

	public void setAdmins(List<String> admins) {
		this.admins = admins;
	}
}
