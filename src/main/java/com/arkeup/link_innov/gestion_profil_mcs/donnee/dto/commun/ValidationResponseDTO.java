package com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class ValidationResponseDTO {

	private Map<String, String> mapErrors;
	private Boolean isError;
	public Map<String, String> getMapErrors() {
		return mapErrors;
	}
	public void setMapErrors(Map<String, String> mapErrors) {
		this.mapErrors = mapErrors;
	}
	public Boolean getIsError() {
		return isError;
	}
	public void setIsError(Boolean isError) {
		this.isError = isError;
	}
	
	
}
