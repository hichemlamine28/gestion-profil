package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ProfilDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.UserAuthDTO;

public interface ExternalSignUpSA {
    ProfilDTO extractRGData(UserAuthDTO userAuthDTO);
}
