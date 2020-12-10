package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.profil_views;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.NumberOfViewsDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ProfilViewersDTO;

/**
 *
 * @author njaka
 */
public interface ProfilViewsRSA {

    NumberOfViewsDTO getNbProfilViews(String userId);

    ProfilViewersDTO getProfilViews(String connectedUserId);
}
