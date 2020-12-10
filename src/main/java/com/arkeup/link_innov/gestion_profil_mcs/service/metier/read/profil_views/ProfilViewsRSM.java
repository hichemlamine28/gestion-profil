package com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.profil_views;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.ProfileViews;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ProfilViewersDTO;

/**
 *
 * @author njaka
 */
public interface ProfilViewsRSM {

    ProfileViews getByUserId(String userId);

    Integer getNbProfilViews(String userId);

    ProfilViewersDTO getProfilViews(String connectedUserId, Boolean limited);

}
