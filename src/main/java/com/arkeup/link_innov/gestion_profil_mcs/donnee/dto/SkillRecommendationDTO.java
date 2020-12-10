package com.arkeup.link_innov.gestion_profil_mcs.donnee.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SkillRecommendationDTO {
	private String id;
	private @JsonProperty("skillName") String name;
	private List<RecommandationDTO> recommended = new ArrayList<>();
	private Integer recommendedCount;
	private String userId;
	private Date createDate;
	private Date modifDate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<RecommandationDTO> getRecommended() {
		return recommended;
	}
	public void setRecommended(List<RecommandationDTO> recommended) {
		this.recommended = recommended;
	}
	public Integer getRecommendedCount() {
		return recommendedCount;
	}
	public void setRecommendedCount(Integer recommendedCount) {
		this.recommendedCount = recommendedCount;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
