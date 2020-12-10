package com.arkeup.link_innov.gestion_profil_mcs.donnee.dto;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.constants.MediaStatus;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.BaseDTO;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class MediaDTO extends BaseDTO {
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String id;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String defaultUrl;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String type;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String attachedObjectId;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private MediaStatus status;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Map<String, String> urls = new HashMap<>();
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String ownerId;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Date dateCreation;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDefaultUrl() {
		return defaultUrl;
	}

	public void setDefaultUrl(String defaultUrl) {
		this.defaultUrl = defaultUrl;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAttachedObjectId() {
		return attachedObjectId;
	}

	public void setAttachedObjectId(String attachedObjectId) {
		this.attachedObjectId = attachedObjectId;
	}

	public MediaStatus getStatus() {
		return status;
	}

	public void setStatus(MediaStatus status) {
		this.status = status;
	}

	public Map<String, String> getUrls() {
		return urls;
	}

	public void setUrls(Map<String, String> urls) {
		this.urls = urls;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

}
