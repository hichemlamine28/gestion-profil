package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.parcours;

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ErrorsEnum;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.parcours.ParcoursMapper;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Parcours;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Profil;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ParcoursListDTO;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.parcours.ParcoursRSM;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.profil.ProfilRSM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author bona
 */
@Service
public class ParcoursRSAImpl implements ParcoursRSA {

    @Autowired
    ProfilRSM profilRSM;

    @Autowired
    ParcoursRSM parcoursRSM;

    @Autowired
    ParcoursMapper parcoursFactory;

    @Override
    public ParcoursListDTO getParcours(String userName, Pageable pageable) {
        ParcoursListDTO parcoursDTOs = new ParcoursListDTO();
        Profil profil = profilRSM.getInformation(userName);
        if (profil == null) {
            parcoursDTOs.setError(true);
            parcoursDTOs.setErrorMessage(ErrorsEnum.ERR_MCS_PROFIL_0007.getErrorMessage());
            parcoursDTOs.setErrorCode("ERR_MCS_PROFIL_007");
            return parcoursDTOs;
        }

        String idProfil = profil.getId();
        Page<Parcours> res = parcoursRSM.getParcours(idProfil, pageable);
        parcoursDTOs.setListParcoursDTO(parcoursFactory.parcoursPageToParcoursDtoPage(res, pageable));
        parcoursDTOs.setError(false);
        parcoursDTOs.setMessage("Parcours list");
        
        return parcoursDTOs;
    }

}
