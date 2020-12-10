package com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class RabbitMqErrorDTO extends BaseDTO{
	
	private String message;
	
	public RabbitMqErrorDTO(String message) {
		this.message = message;
	}

	public RabbitMqErrorDTO() {
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	

}
