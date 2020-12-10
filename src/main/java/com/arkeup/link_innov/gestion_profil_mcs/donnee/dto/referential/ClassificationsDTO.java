package com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.referential;

import org.springframework.data.domain.Page;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.BaseDTO;

public class ClassificationsDTO extends BaseDTO {

	private Page<ClassificationDTO> classificationsDTO;

	public Page<ClassificationDTO> getClassificationsDTO() {
		return classificationsDTO;
	}

	public void setClassificationsDTO(Page<ClassificationDTO> classificationsDTO) {
		this.classificationsDTO = classificationsDTO;
	}
	
}
