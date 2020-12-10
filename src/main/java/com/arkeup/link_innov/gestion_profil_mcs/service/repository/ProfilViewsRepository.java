
package com.arkeup.link_innov.gestion_profil_mcs.service.repository;

import java.util.List;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.ProfileViews;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.common.mongo_es.CommonMongoToESRepository;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.elastic_search.ProfilViewsESRepository;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs.ProfilViewsMongoRepository;

/**
 *
 * @author njaka
 */
public interface ProfilViewsRepository
		extends CommonMongoToESRepository<ProfileViews, String, ProfilViewsMongoRepository, ProfilViewsESRepository> {
	Integer getNbProfilViews(String userId);

	List<ProfileViews> findByUserId(String userId);

}
