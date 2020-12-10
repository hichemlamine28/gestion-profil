package com.arkeup.link_innov.gestion_profil_mcs.donnee.dto;

import java.util.List;


import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.BaseDTO;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class CustomListDTO extends BaseDTO {

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<String> corporationAdmins;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String corporationName;

	public List<String> getCorporationAdmins() {
		return corporationAdmins;
	}

	public void setCorporationAdmins(List<String> corporationAdmins) {
		this.corporationAdmins = corporationAdmins;
	}

	public String getCorporationName() {
		return corporationName;
	}

	public void setCorporationName(String corporationName) {
		this.corporationName = corporationName;
	}



}
