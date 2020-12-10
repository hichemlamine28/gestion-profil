package com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Oauth2ErrorDTO {
	
	private String error;
	private String errorDescription;
	
	public Oauth2ErrorDTO(String error, String errorDescription) {
		this.error = error;
		this.errorDescription = errorDescription;
	}

	public Oauth2ErrorDTO() {
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}
	
	

}
