package com.arkeup.link_innov.gestion_profil_mcs.donnee.dto;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.BaseDTO;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class MemberNumbersDTO extends BaseDTO {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String members = "";
    private Integer nbMembers = 0;

	public String getMembers() {
		return members;
	}

	public void setMembers(String members) {
		this.members = members;
	}

	public Integer getNbMembers() {
		return nbMembers;
	}

	public void setNbMembers(Integer nbMembers) {
		this.nbMembers = nbMembers;
	}

}
