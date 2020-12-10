package com.arkeup.link_innov.gestion_profil_mcs.donnee.dto;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Profil;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.BaseDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.googlecode.jmapper.annotations.JGlobalMap;

@JGlobalMap
public class ProfilForBODTO extends BaseDTO {

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Profil profile;

	public ProfilForBODTO(Profil profil) {
		super();
		this.profile = profil;
	}

	public Profil getProfil() {
		return profile;
	}

	public void setProfil(Profil profil) {
		this.profile = profil;
	}

}
