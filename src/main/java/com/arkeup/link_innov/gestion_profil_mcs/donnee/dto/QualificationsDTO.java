package com.arkeup.link_innov.gestion_profil_mcs.donnee.dto;

import org.springframework.data.domain.Page;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.BaseDTO;
import com.googlecode.jmapper.annotations.JGlobalMap;

@JGlobalMap
public class QualificationsDTO extends BaseDTO {
	private Page<QualificationDTO> qualificationDTOs;

	public Page<QualificationDTO> getQualificationDTOs() {
		return qualificationDTOs;
	}

	public void setQualificationDTOs(Page<QualificationDTO> qualificationDTOs) {
		this.qualificationDTOs = qualificationDTOs;
	}
}
