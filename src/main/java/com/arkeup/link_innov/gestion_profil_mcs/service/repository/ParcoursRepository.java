package com.arkeup.link_innov.gestion_profil_mcs.service.repository;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Parcours;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.common.mongo_es.CommonMongoToESRepository;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.elastic_search.ParcoursESRepository;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs.ParcoursMongoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author bona
 */

public interface ParcoursRepository extends CommonMongoToESRepository<Parcours, String, ParcoursMongoRepository, ParcoursESRepository> {

    public Page<Parcours> getParcours(String idProfil, Pageable pageable);
}
