package com.arkeup.link_innov.gestion_profil_mcs.service.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.OtherProduction;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.common.mongo_es.CommonMongoToESRepositoryImpl;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.elastic_search.OtherProductionESRepository;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs.OtherProductionMongoRepository;

/**
 *
 * @author njaka
 *
 */
@Repository
public class OtherProductionRepositoryImpl extends
        CommonMongoToESRepositoryImpl<OtherProduction, String, OtherProductionMongoRepository, OtherProductionESRepository>
        implements OtherProductionRepository {

    @Override
    public Page<OtherProduction> findByOwnerId(String ownerId, Pageable pageable) {
        return this.mongoRepository.findByOwnerIdOrderByCreationDateDesc(ownerId, pageable);
    }

    @Override
    public List<OtherProduction> findByOwnerId(String ownerId) {
        return this.mongoRepository.findByOwnerIdOrderByCreationDateDesc(ownerId);
    }

    @Override
    public List<OtherProduction> findAllByIdIn(List<String> otherProductionIds) {
        return this.mongoRepository.findAllByIdInOrderByCreationDateDesc(otherProductionIds);
    }

    @Override
    public Page<OtherProduction> getPaginatedOtherProductions(List<String> productionIds, String filter, Pageable pageable) {
        return this.mongoRepository.findAllById(productionIds, filter, pageable);
    }

    @Override
    public List<OtherProduction> findAll() {
        return this.mongoRepository.findAll();
    }
}
