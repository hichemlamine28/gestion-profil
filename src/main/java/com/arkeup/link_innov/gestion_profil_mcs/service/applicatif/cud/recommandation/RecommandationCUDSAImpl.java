package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.recommandation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.recommandation.RecommandationMapper;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.RecommandationDTO;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.recommandation.RecommandationCUDSM;

@Service
public class RecommandationCUDSAImpl implements RecommandationCUDSA {

	@Autowired
	private RecommandationCUDSM recommandationCUDSM;

	@Autowired
	private RecommandationMapper recommandationMapper;

	/* (non-Javadoc)
	 * @see com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.recommandation.RecommandationCUDSA#getUserToNotifyAboutRecommandation(java.lang.String)
	 */
	@Override
	public RecommandationDTO getUserToNotifyAboutRecommandation(String recommandationId) {
		return recommandationMapper.recommandationToRecommandationDTO(
				recommandationCUDSM.getRecommandation(recommandationId));
	}
}
