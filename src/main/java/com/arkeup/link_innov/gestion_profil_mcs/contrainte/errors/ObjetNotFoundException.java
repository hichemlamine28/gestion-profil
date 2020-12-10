package com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.BaseDTO;

public class ObjetNotFoundException extends TechnicalException {
	private static final long serialVersionUID = 1L;

	public ObjetNotFoundException(BaseDTO baseDTO, ErrorsEnum errorsEnum, Exception e) {
		super(baseDTO, errorsEnum, e);
	}

	public ObjetNotFoundException(BaseDTO baseDTO, ErrorsEnum errorsEnum) {
		super(baseDTO, errorsEnum);
	}

}
