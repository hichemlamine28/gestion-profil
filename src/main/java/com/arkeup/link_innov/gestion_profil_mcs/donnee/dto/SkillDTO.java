package com.arkeup.link_innov.gestion_profil_mcs.donnee.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.BaseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SkillDTO extends BaseDTO {

	private String id;
	private @JsonProperty("skillName") String name;
	private List<String> recommended = new ArrayList<>();
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

	public List<String> getRecommended() {
		return recommended;
	}

	public void setRecommended(List<String> recommended) {
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

	@Override
	public int hashCode() {
		return Objects.hash(createDate, id, modifDate, name, recommended, recommendedCount, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (!(obj instanceof SkillDTO)) {
			return false;
		}

		SkillDTO other = (SkillDTO) obj;

		return Objects.equals(createDate, other.createDate) && Objects.equals(id, other.id) && Objects.equals(
				modifDate, other.modifDate
		) && Objects.equals(name, other.name) && Objects.equals(recommended, other.recommended)
				&& Objects.equals(recommendedCount, other.recommendedCount) && Objects.equals(userId, other.userId);
	}
}
