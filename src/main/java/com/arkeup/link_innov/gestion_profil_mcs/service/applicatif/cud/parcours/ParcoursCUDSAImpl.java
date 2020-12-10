package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.parcours;

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ErrorsEnum;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.corporation.CorporationMapper;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.parcours.ParcoursFactory;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.parcours.ParcoursMapper;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Corporation;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Parcours;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Profil;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.CorporationDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ParcoursDTO;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.parcours.ParcoursCUDSM;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.corporation.CorporationRSM;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.parcours.ParcoursRSM;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.profil.ProfilRSM;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author bona
 */
@Service
public class ParcoursCUDSAImpl implements ParcoursCUDSA {

    @Autowired
    ProfilRSM profilRSM;

    @Autowired
    ParcoursCUDSM parcoursCUDSM;

    @Autowired
    ParcoursRSM parcoursRSM;

    @Autowired
    ParcoursFactory parcoursFactory;

    @Autowired
    ParcoursMapper parcoursMapper;

    @Autowired
    CorporationRSM corporationRSM;

    @Autowired
    private CorporationMapper corporationFactory;

    @Override
    public ParcoursDTO addParcours(String userName, ParcoursDTO parcoursDTO) {
        Profil profil = profilRSM.getInformation(userName);
        if (profil == null) {
            ParcoursDTO parcoursErrorDTO = new ParcoursDTO();
            parcoursErrorDTO.setError(true);
            parcoursErrorDTO.setErrorMessage(ErrorsEnum.ERR_MCS_PROFIL_0007.getErrorMessage());
            parcoursErrorDTO.setErrorCode("ERR_MCS_PROFIL_007");
            return parcoursErrorDTO;
        }

        if (StringUtils.isNotEmpty(parcoursDTO.getCompany().getId())) {
            Optional<Corporation> res = corporationRSM.getCorporation(parcoursDTO.getCompany().getId());
            CorporationDTO result = new CorporationDTO();
            if (res.isPresent()) {
                CorporationDTO corporationDTO = corporationFactory.corporationToCorporationDTO(res.get());
                parcoursDTO.setPhoto(corporationDTO.getPhoto());
            }
        } else {
            parcoursDTO.getCompany().setId(UUID.randomUUID().toString());
        }

        parcoursDTO.setProfil(profil.getId());
        parcoursDTO.setCreateDate(new Date());

        ParcoursDTO result = parcoursMapper.parcoursToParcoursDTO(parcoursCUDSM.saveParcours(parcoursFactory.getEntityInstance(parcoursDTO)));

        if (result.getId() == null) {
            result.setError(true);
            result.setErrorMessage(ErrorsEnum.ERR_MCS_PROFIL_0020.getErrorMessage());
            result.setErrorCode("ERR_MCS_PROFIL_0020");
        } else {
            result.setError(false);
            result.setMessage("parcours information added");
            result.setId(result.getId());
        }

        return result;
    }

    @Override
    public ParcoursDTO updateParcours(String userName, ParcoursDTO parcoursDTO) {
        Profil profil = profilRSM.getInformation(userName);
        if (profil == null) {
            ParcoursDTO parcoursErrorDTO = new ParcoursDTO();
            parcoursErrorDTO.setError(true);
            parcoursErrorDTO.setErrorMessage(ErrorsEnum.ERR_MCS_PROFIL_0007.getErrorMessage());
            parcoursErrorDTO.setErrorCode("ERR_MCS_PROFIL_007");
            return parcoursErrorDTO;
        }

        if (parcoursDTO.getId() == null) {
            ParcoursDTO parcoursErrorDTO = new ParcoursDTO();
            parcoursErrorDTO.setError(true);
            parcoursErrorDTO.setErrorMessage(ErrorsEnum.ERR_MCS_PROFIL_0017.getErrorMessage());
            parcoursErrorDTO.setErrorCode("ERR_MCS_PROFIL_0017");
            return parcoursErrorDTO;
        }

        if (StringUtils.isNotEmpty(parcoursDTO.getCompany().getId())) {
            Optional<Corporation> res = corporationRSM.getCorporation(parcoursDTO.getCompany().getId());
            CorporationDTO result = new CorporationDTO();
            if (res.isPresent()) {
                CorporationDTO corporationDTO = corporationFactory.corporationToCorporationDTO(res.get());
                parcoursDTO.setPhoto(corporationDTO.getPhoto());
            }
        }

        parcoursDTO.setProfil(profil.getId());
        parcoursDTO.setModifDate(new Date());
        parcoursCUDSM.saveParcours(parcoursFactory.getEntityInstance(parcoursDTO));
        parcoursDTO.setError(false);
        parcoursDTO.setMessage("Parcours information updated");
        return parcoursDTO;
    }

    @Override
    public ParcoursDTO deleteParcours(String idParcours) {
        ParcoursDTO parcoursDTO = new ParcoursDTO();
        Parcours parcours = parcoursRSM.findParcoursById(idParcours);
        if (parcours == null) {
            parcoursDTO.setError(true);
            parcoursDTO.setErrorMessage(ErrorsEnum.ERR_MCS_PROFIL_0017.getErrorMessage());
            parcoursDTO.setErrorCode("ERR_MCS_PROFIL_0017");
            return parcoursDTO;
        } else {
            parcoursCUDSM.deleteParcours(idParcours);
            parcoursDTO.setError(false);
            parcoursDTO.setMessage("Parcours deleted");
            return parcoursDTO;
        }

    }

}
