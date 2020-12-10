package com.arkeup.link_innov.gestion_profil_mcs.service.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.OtherProduction;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.common.mongo_es.CommonMongoToESRepository;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.elastic_search.OtherProductionESRepository;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs.OtherProductionMongoRepository;

/**
 *
 * @author njaka
 *
 */
public interface OtherProductionRepository extends
        CommonMongoToESRepository<OtherProduction, String, OtherProductionMongoRepository, OtherProductionESRepository> {

    public Page<OtherProduction> findByOwnerId(String ownerId, Pageable pageable);

    public List<OtherProduction> findByOwnerId(String ownerId);

    public List<OtherProduction> findAllByIdIn(List<String> otherProductionIds);

    List<OtherProduction> findAll();

    Page<OtherProduction> getPaginatedOtherProductions(List<String> productionIds, String filter, Pageable pageable);
}
