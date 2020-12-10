package com.arkeup.link_innov.gestion_profil_mcs.donnee.dto;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.BaseDTO;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class HasCertificationDTO extends BaseDTO {


    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean isCertificated = false;

	public Boolean getIsCertificated() {
		return isCertificated;
	}

	public void setIsCertificated(Boolean isCertificated) {
		this.isCertificated = isCertificated;
	}

}
