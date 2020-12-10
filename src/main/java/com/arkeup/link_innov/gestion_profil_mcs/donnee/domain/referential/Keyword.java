package com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

@org.springframework.data.mongodb.core.mapping.Document
@org.springframework.data.elasticsearch.annotations.Document(indexName = "gestion_profil_mcs", type = "Keyword")
public class Keyword {

	/**
	 * Keyword identifier
	 *
	 * @type {string}
	 * @memberof Keyword
	 */
	@Id
	private String id;

	/**
	 * Keyword label
	 *
	 * @type {string}
	 * @memberof Keyword
	 */
	private String label;

	@JsonIgnore
	private List<String> userIds = new ArrayList<String>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<String> getUserIds() {
		return userIds;
	}

	public void setUserIds(List<String> userIds) {
		this.userIds = userIds;
	}

}
