package com.arkeup.link_innov.gestion_profil_mcs.service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.ActivitySector;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.common.mongo_es.CommonMongoToESRepository;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.elastic_search.ActivitySectorESRepository;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs.ActivitySectorMongoRepository;

/**
 *
 * @author bona
 */
public interface ActivitySectorRepository extends CommonMongoToESRepository<ActivitySector, String, ActivitySectorMongoRepository, ActivitySectorESRepository> {

    public Page<ActivitySector> findAll(Pageable pageable);
}
