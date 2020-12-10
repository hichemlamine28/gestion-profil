
package com.arkeup.link_innov.gestion_profil_mcs.donnee.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class AdminDetailsDTO {

	private String administredId;

	private String administredType;

	private Set<String> adminUserIds;

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

	public Set<String> getAdminUserIds() {
		return adminUserIds;
	}

	public void setAdminUserIds(Set<String> adminUserIds) {
		this.adminUserIds = adminUserIds;
	}
}
