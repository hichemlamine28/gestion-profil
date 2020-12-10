package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.information;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.UserInformationDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.UserValidatorDTO;

public interface UserInformationRSA {

    UserInformationDTO getInformation(String username);

    public UserValidatorDTO userValidation(String id);

}
