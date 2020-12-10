package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.recommandation;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.RecommandationDTO;

public interface RecommandationCUDSA {

	/**
	 * @param recommandationId
	 *
	 * @return
	 */
	RecommandationDTO getUserToNotifyAboutRecommandation(String recommandationId);
}
