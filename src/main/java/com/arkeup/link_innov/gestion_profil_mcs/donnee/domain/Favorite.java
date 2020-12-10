package com.arkeup.link_innov.gestion_profil_mcs.donnee.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@org.springframework.data.mongodb.core.mapping.Document
@org.springframework.data.elasticsearch.annotations.Document(indexName = "gestion_profil_mcs", type = "Favorite")
public class Favorite {
	
	@Id
	private String id;
	
	/**
	 * Date of creation
	 *
	 * @type {date}
	 * @memberof Favorite
	 */
	private Date createDate;

	/**
	 * Date of modification
	 *
	 * @type {date}
	 * @memberof Favorite
	 */
	private Date modifDate;
	
	/**
	 * Owner
	 *
	 * @type {string}
	 * @memberof Favorite
	 */
	private String ownerId;
	
	/**
	 * Target
	 *
	 * @type {string}
	 * @memberof Favorite
	 */
	private String targetId;
	
	/**
	 * Type
	 *
	 * @type {string}
	 * @memberof Favorite
	 */
	@JsonProperty("favoriteType")
	private String type;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifDate() {
		return modifDate;
	}

	public void setModifDate(Date modifDate) {
		this.modifDate = modifDate;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
