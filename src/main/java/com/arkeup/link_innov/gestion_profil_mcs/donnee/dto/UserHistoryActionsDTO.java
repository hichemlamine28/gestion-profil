package com.arkeup.link_innov.gestion_profil_mcs.donnee.dto;

import java.util.List;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.BaseDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.googlecode.jmapper.annotations.JGlobalMap;

@JGlobalMap
public class UserHistoryActionsDTO extends BaseDTO {

	@JsonInclude
	private String actionId;
	@JsonInclude
	private String actionName;
	@JsonInclude
	private List<String> userId;

	public String getActionId() {
		return actionId;
	}

	public void setAction_id(String actionId) {
		this.actionId = actionId;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public List<String> getUserId() {
		return userId;
	}

	public void setUserId(List<String> userId) {
		this.userId = userId;
	}

}
