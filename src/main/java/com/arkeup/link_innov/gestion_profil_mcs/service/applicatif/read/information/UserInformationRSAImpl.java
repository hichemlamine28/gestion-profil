package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.information;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ErrorsEnum;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.information.UserInformationMapper;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Corporation;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.UserInformation;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.UserInformationDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.UserValidatorDTO;
import com.arkeup.link_innov.gestion_profil_mcs.service.businessdelegate.ReseauxSociauxMCS;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.corporation.CorporationRSM;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.information.UserInformationRSM;
import java.util.Optional;

@Service
public class UserInformationRSAImpl implements UserInformationRSA {

    @Autowired
    private UserInformationMapper userInformationFactory;

    @Autowired
    private UserInformationRSM userInformationRSM;

    @Autowired
    CorporationRSM corporationRSM;

    @Autowired
    ReseauxSociauxMCS reseauxSociausMCS;

    @Override
    public UserInformationDTO getInformation(String username) {
        UserInformationDTO userInformationDTO = userInformationFactory.userInformationToUserInformationDTO(userInformationRSM.getInformation(username));

        if (userInformationDTO == null) {
            userInformationDTO = new UserInformationDTO();
            userInformationDTO.setError(true);
            userInformationDTO.setErrorMessage(ErrorsEnum.ERR_MCS_PROFIL_0007.getErrorMessage());
            userInformationDTO.setErrorCode("ERR_MCS_PROFIL_0007");
        } else {
            userInformationDTO.setError(false);
            userInformationDTO.setMessage("User information");
        }

        return userInformationDTO;
    }

    @Override
    public UserValidatorDTO userValidation(String id) {
        UserValidatorDTO userValidatorDTO = new UserValidatorDTO();

        UserInformation userInformation = userInformationRSM.getInformation(id);

        if (userInformation == null) {

            Optional<Corporation> res = corporationRSM.getCorporation(id);
            if (!res.isPresent()) {
                userValidatorDTO.setId(id);
                userValidatorDTO.setType("unknown");
                return userValidatorDTO;
            }

            userValidatorDTO.setId(id);
            userValidatorDTO.setType("corporation");
            return userValidatorDTO;
        }

        userValidatorDTO.setId(id);
        userValidatorDTO.setType("person");
        userValidatorDTO.setIsCertifier(reseauxSociausMCS.isCertifiedUsers(id).getIsCertificated());
        return userValidatorDTO;
    }
}
