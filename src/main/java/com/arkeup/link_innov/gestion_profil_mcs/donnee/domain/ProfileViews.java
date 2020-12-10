package com.arkeup.link_innov.gestion_profil_mcs.donnee.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;

/**
 *
 * @author njaka
 */
@org.springframework.data.mongodb.core.mapping.Document
@org.springframework.data.elasticsearch.annotations.Document(indexName = "gestion_profil_mcs", type = "ProfileViews")
public class ProfileViews {
	@Id
	private String id;

	private String userId;

	private List<ProfileView> viewList = new ArrayList<ProfileView>();

	private Date creationDate;

	private Date modificationDate;

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

	public List<ProfileView> getViewList() {
		return viewList;
	}

	public void setViewList(List<ProfileView> viewList) {
		this.viewList = viewList;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getModificationDate() {
		return modificationDate;
	}

	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
	}

}
