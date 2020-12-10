package com.arkeup.link_innov.gestion_profil_mcs.service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.ActivitySector;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.common.mongo_es.CommonMongoToESRepositoryImpl;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.elastic_search.ActivitySectorESRepository;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs.ActivitySectorMongoRepository;

/**
 *
 * @author bona
 */
@Repository
public class ActivitySectorRepositoryImpl extends CommonMongoToESRepositoryImpl<ActivitySector, String, ActivitySectorMongoRepository, ActivitySectorESRepository> implements ActivitySectorRepository {

    @Override
    public Page<ActivitySector> findAll(Pageable pageable) {
        return this.mongoRepository.findAll(pageable);
    }

}
