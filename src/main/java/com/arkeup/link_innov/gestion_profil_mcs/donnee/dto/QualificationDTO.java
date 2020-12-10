package com.arkeup.link_innov.gestion_profil_mcs.donnee.dto;

import java.util.Date;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.BaseDTO;
import com.googlecode.jmapper.annotations.JGlobalMap;

@JGlobalMap
public class QualificationDTO extends BaseDTO {

	private String id;
	private String institution;
	private String degree;
	private String field;
	private String result; 
	private Date startDate; 
	private Date endDate;
	private String description;
	private String userId;
	private Date createDate; 
	private Date modifDate;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getInstitution() {
		return institution;
	}
	public void setInstitution(String institution) {
		this.institution = institution;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	
}
