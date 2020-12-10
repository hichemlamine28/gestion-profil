package com.arkeup.link_innov.gestion_profil_mcs.service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.Classification;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.common.mongo_es.CommonMongoToESRepository;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.elastic_search.ClassificationESRepository;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs.ClassificationMongoRepository;

/**
 *
 * @author bona
 */
public interface ClassificationRepository extends CommonMongoToESRepository<Classification, String, ClassificationMongoRepository, ClassificationESRepository> {

    public Page<Classification> findAll(Pageable pageable);
}
