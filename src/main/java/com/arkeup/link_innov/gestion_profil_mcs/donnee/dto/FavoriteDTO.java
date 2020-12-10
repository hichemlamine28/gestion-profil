package com.arkeup.link_innov.gestion_profil_mcs.donnee.dto;

import java.util.Date;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.BaseDTO;

public class FavoriteDTO extends BaseDTO {
	
	private String id;
	private Date createDate;
	private Date modifDate;
	private String ownerId;
	private String targetId;
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
