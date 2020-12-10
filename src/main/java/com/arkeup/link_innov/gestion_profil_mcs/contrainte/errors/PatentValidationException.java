package com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors;

import org.springframework.validation.Errors;


public class PatentValidationException extends ValidationException{
	private static final long serialVersionUID = 1L;
	
	public PatentValidationException(Errors errors) {
		super(errors);
	}
	
	
}
