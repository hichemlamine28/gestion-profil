package com.arkeup.link_innov.gestion_profil_mcs.donnee.dto;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.BaseDTO;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class RecommandationDTO extends BaseDTO {
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String username;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String userPhoto;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String skillId;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String id;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String recommandationType;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String skillLabel;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String skillOwner;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String recommandationOwner;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String recommandationOwnerName;

	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserPhoto() {
		return userPhoto;
	}
	public void setUserPhoto(String userPhoto) {
		this.userPhoto = userPhoto;
	}
	public String getSkillId() {
		return skillId;
	}
	public void setSkillId(String skillId) {
		this.skillId = skillId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRecommandationType() {
		return recommandationType;
	}
	public void setRecommandationType(String recommandationType) {
		this.recommandationType = recommandationType;
	}
	public String getSkillLabel() {
		return skillLabel;
	}
	public void setSkillLabel(String skillLabel) {
		this.skillLabel = skillLabel;
	}
	public String getSkillOwner() {
		return skillOwner;
	}
	public void setSkillOwner(String skillOwner) {
		this.skillOwner = skillOwner;
	}
	public String getRecommandationOwner() {
		return recommandationOwner;
	}
	public void setRecommandationOwner(String recommandationOwner) {
		this.recommandationOwner = recommandationOwner;
	}
	public String getRecommandationOwnerName() {
		return recommandationOwnerName;
	}
	public void setRecommandationOwnerName(String recommandationOwnerName) {
		this.recommandationOwnerName = recommandationOwnerName;
	}
	
	
	
}
