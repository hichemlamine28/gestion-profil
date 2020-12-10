package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.profil_views;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.NumberOfViewsDTO;

/**
 *
 * @author njaka
 */
public interface ProfilViewsCUDSA {

	NumberOfViewsDTO save(String connectedUserId, String userId);

}
