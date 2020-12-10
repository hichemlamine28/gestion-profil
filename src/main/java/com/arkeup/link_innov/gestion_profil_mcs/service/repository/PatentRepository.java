package com.arkeup.link_innov.gestion_profil_mcs.service.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Patent;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.common.mongo_es.CommonMongoToESRepository;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.elastic_search.PatentESRepository;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs.PatentMongoRepository;

/**
 *
 * @author njaka
 *
 */
public interface PatentRepository
        extends CommonMongoToESRepository<Patent, String, PatentMongoRepository, PatentESRepository> {

    Page<Patent> findByOwnerId(String ownerId, Pageable pageable);

    List<Patent> findByOwnerId(String ownerId);

    List<Patent> findAllByIdIn(List<String> patentIds);

    Page<Patent> getPaginatedPatent(List<String> productionIds, String filter, Pageable pageable);

    List<Patent> findAll();

    List<Patent> findByPublicationNumber(String publicationNumber);
}
