package com.arkeup.link_innov.gestion_profil_mcs.service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.Typology;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.common.mongo_es.CommonMongoToESRepository;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.elastic_search.TypologyESRepository;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs.TypologyMongoRepository;

/**
 *
 * @author bona
 */
public interface TypologyRepository extends CommonMongoToESRepository<Typology, String, TypologyMongoRepository, TypologyESRepository> {

    public Page<Typology> findAll(Pageable pageable);
}
