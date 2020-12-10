package com.arkeup.link_innov.gestion_profil_mcs.donnee.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.BaseDTO;

@org.springframework.data.mongodb.core.mapping.Document
@org.springframework.data.elasticsearch.annotations.Document(indexName = "gestion_profil_mcs", type = "UserHistory")
public class UserHistory extends BaseDTO {
	@Id
	String id;
	String actionDate;
	List<UserHistoryActions> userHistoryActions;

	public UserHistory() {
	}

	public UserHistory(String actionDate, List<UserHistoryActions> acns) {
		this.actionDate = actionDate;
		if (acns == null) {
			userHistoryActions = new ArrayList<>();
		}
		this.userHistoryActions = acns;

	}

	public String getActionDate() {
		return actionDate;
	}

	public void setActionDate(String actionDate) {
		this.actionDate = actionDate;
	}

	public List<UserHistoryActions> getActions() {
		return userHistoryActions;
	}

	public void setActions(List<UserHistoryActions> actions) {
		this.userHistoryActions = actions;
	}

	public String toString() {
		return "Action Date:" + actionDate + " User actions:" + userHistoryActions.toString();
	}
}