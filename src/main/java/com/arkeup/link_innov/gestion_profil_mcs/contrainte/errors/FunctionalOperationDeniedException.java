package com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.BaseDTO;

public class FunctionalOperationDeniedException extends FunctionalException{
	private static final long serialVersionUID = 1L;
	

	public FunctionalOperationDeniedException(BaseDTO baseDTO, ErrorsEnum errorsEnum, Exception e) {
		super(baseDTO, errorsEnum, e);
	}
	
	public FunctionalOperationDeniedException(BaseDTO baseDTO, ErrorsEnum errorsEnum) {
		super(baseDTO, errorsEnum);
	}
	
}
