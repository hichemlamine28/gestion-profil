package com.arkeup.link_innov.gestion_profil_mcs.donnee.domain;

import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class Recommandation {

	private String id;

	private String recommandationType;

	private String skillId;
	
	private String skillLabel;
	
	private String skillOwner;
	
	private String recommandationOwner;
	
	private String recommandationOwnerName;

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

	public String getSkillId() {
		return skillId;
	}

	public void setSkillId(String skillId) {
		this.skillId = skillId;
	}

	public String getRecommandationOwner() {
		return recommandationOwner;
	}

	public void setRecommandationOwner(String recommandationOwner) {
		this.recommandationOwner = recommandationOwner;
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

	public String getRecommandationOwnerName() {
		return recommandationOwnerName;
	}

	public void setRecommandationOwnerName(String recommandationOwnerName) {
		this.recommandationOwnerName = recommandationOwnerName;
	}
	
	
	
}
