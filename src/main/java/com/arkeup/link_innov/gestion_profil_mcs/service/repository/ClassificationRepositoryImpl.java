package com.arkeup.link_innov.gestion_profil_mcs.service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.Classification;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.common.mongo_es.CommonMongoToESRepositoryImpl;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.elastic_search.ClassificationESRepository;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs.ClassificationMongoRepository;

/**
 *
 * @author bona
 */
@Repository
public class ClassificationRepositoryImpl extends CommonMongoToESRepositoryImpl<Classification, String, ClassificationMongoRepository, ClassificationESRepository> implements ClassificationRepository {

    @Override
    public Page<Classification> findAll(Pageable pageable) {
        return this.mongoRepository.findAll(pageable);
    }

}
