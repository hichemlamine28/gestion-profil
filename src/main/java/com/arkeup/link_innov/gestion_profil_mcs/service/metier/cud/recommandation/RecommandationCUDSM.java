package com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.recommandation;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Recommandation;

public interface RecommandationCUDSM {

	Recommandation saveRecommandation(final Recommandation recommandationAction);
	Recommandation getRecommandation(String id);
}
