package com.arkeup.link_innov.gestion_profil_mcs.service.repository;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Parcours;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.common.mongo_es.CommonMongoToESRepositoryImpl;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.elastic_search.ParcoursESRepository;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs.ParcoursMongoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

/**
 *
 * @author bona
 */
@Repository
public class ParcoursRepositoryImpl extends CommonMongoToESRepositoryImpl<Parcours, String, ParcoursMongoRepository, ParcoursESRepository> implements ParcoursRepository {

    @Override
    public Page<Parcours> getParcours(String profil, Pageable pageable) {
       return this.mongoRepository.findAllByProfil(profil, pageable);
    }
}
