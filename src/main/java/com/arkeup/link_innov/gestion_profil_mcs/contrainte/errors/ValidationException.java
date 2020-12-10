package com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors;

import org.springframework.validation.Errors;

public class ValidationException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	private Errors errors;

	public ValidationException(Errors errors) {
		super();
		this.errors = errors;
	}

	public Errors getErrors() {
		return errors;
	}

	public void setErrors(Errors errors) {
		this.errors = errors;
	}
	
	

}
