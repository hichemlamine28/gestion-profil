package com.arkeup.link_innov.gestion_profil_mcs.donnee.dto;

import java.util.List;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.UserHistoryActions;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.BaseDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.googlecode.jmapper.annotations.JGlobalMap;

@JGlobalMap
public class UserHistoryDTO extends BaseDTO {

	@JsonInclude
	String id;
	@JsonInclude
	String actionDate;
	@JsonInclude
	List<UserHistoryActions> userHistoryActions;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getActionDate() {
		return actionDate;
	}

	public void setActionDate(String actionDate) {
		this.actionDate = actionDate;
	}

	public List<UserHistoryActions> getUserHistoryActions() {
		return userHistoryActions;
	}

	public void setUserHistoryActions(List<UserHistoryActions> userHistoryActions) {
		this.userHistoryActions = userHistoryActions;
	}

}
