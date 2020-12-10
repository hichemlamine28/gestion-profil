package com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.BaseDTO;

public class ResearchGateExtractException extends TechnicalException {


    public ResearchGateExtractException(BaseDTO baseDTO, ErrorsEnum errorsEnum, Exception e) {
        super(baseDTO, errorsEnum, e);
    }

    public ResearchGateExtractException(BaseDTO baseDTO, ErrorsEnum errorsEnum) {
        super(baseDTO, errorsEnum);
    }
}
