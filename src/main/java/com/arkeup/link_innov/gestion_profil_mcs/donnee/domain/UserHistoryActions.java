package com.arkeup.link_innov.gestion_profil_mcs.donnee.domain;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.annotation.Id;

@org.springframework.data.mongodb.core.mapping.Document
@org.springframework.data.elasticsearch.annotations.Document(indexName = "gestion_profil_mcs", type = "userHistoryActions")
public class UserHistoryActions {

	@Id
	private String actionId;
	private String actionName;
	private Map<String, Integer> userId = new HashMap<>();
//	private int occurence = 1;

	public UserHistoryActions() {
	}

	public UserHistoryActions(String actionId, String actionName, Map<String, Integer> userId) {
		super();
		this.actionId = actionId;
		this.actionName = actionName;
		if (userId == null) {
			userId = new HashMap<>();
			userId.put("non", 0);

		}
		this.userId = userId;
	}

	public String getActionId() {
		return actionId;
	}

	public void setActionId(String actionId) {
		this.actionId = actionId;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public Map<String, Integer> getUserId() {
		return userId;
	}

	public void setUserId(Map<String, Integer> userId) {
		this.userId = userId;
	}

//	public int getOccurence() {
//		return occurence;
//	}
//
//	public void setOccurence(int occurence) {
//		this.occurence = occurence;
//	}

}
