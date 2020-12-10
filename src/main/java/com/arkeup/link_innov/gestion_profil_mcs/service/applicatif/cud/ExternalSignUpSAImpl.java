/**
 *
 */
package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud;

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ErrorsEnum;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ResearchGateExtractException;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ResearchGateLoginException;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.profil.ProfilMapper;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ProfilDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.UserAuthDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.reasearchgate.ProfilResearchGate;
import com.arkeup.link_innov.gestion_profil_mcs.service.businessdelegate.researchgate.ResearchGateBD;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author msb-arkeup
 *
 */
@Service
public class ExternalSignUpSAImpl implements ExternalSignUpSA {

    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private ProfilMapper profilMapper;

    @Autowired
    private ResearchGateBD researchGateBD;

    @Override
    public ProfilDTO extractRGData(UserAuthDTO userAuthDTO){

        ProfilResearchGate profilResearchGate = null;
        try {
            profilResearchGate = researchGateBD.extractProfileInfo(userAuthDTO.getUsername(),userAuthDTO.getPassword());

        }catch (ResearchGateLoginException e) {
            userAuthDTO.setPassword("");
            throw new ResearchGateExtractException(userAuthDTO, ErrorsEnum.ERR_MCS_PROFIL_INVALID_PASSWORD, e);
        }
        catch (Exception e) {
            userAuthDTO.setPassword("");
            throw new ResearchGateExtractException(userAuthDTO, ErrorsEnum.ERR_MCS_PROFIL_RG_ERROR, e);
        }

        ProfilDTO profilDTO = profilMapper.profilResearchGateToProfilDTO(profilResearchGate);

        return profilDTO;

    }

}
