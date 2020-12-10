package com.arkeup.link_innov.gestion_profil_mcs.service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.ThematicArea;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.common.mongo_es.CommonMongoToESRepositoryImpl;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.elastic_search.ThematicAreaESRepository;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs.ThematicAreaMongoRepository;

/**
 *
 * @author bona
 */
@Repository
public class ThematicAreaRepositoryImpl extends CommonMongoToESRepositoryImpl<ThematicArea, String, ThematicAreaMongoRepository, ThematicAreaESRepository> implements ThematicAreaRepository {

    @Override
    public Page<ThematicArea> findAll(Pageable pageable) {
        return this.mongoRepository.findAll(pageable);
    }

}
