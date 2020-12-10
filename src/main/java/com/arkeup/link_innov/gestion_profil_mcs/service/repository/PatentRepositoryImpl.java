package com.arkeup.link_innov.gestion_profil_mcs.service.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Patent;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.common.mongo_es.CommonMongoToESRepositoryImpl;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.elastic_search.PatentESRepository;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs.PatentMongoRepository;

/**
 *
 * @author njaka
 *
 */
@Repository
public class PatentRepositoryImpl
        extends CommonMongoToESRepositoryImpl<Patent, String, PatentMongoRepository, PatentESRepository>
        implements PatentRepository {

    @Override
    public Page<Patent> findByOwnerId(String ownerId, Pageable pageable) {
        return this.mongoRepository.findByOwnerIdOrderByCreationDateDesc(ownerId, pageable);
    }

    @Override
    public List<Patent> findByOwnerId(String ownerId) {
        return this.mongoRepository.findByOwnerIdOrderByCreationDateDesc(ownerId);
    }

    @Override
    public List<Patent> findAllByIdIn(List<String> patentIds) {
        return this.mongoRepository.findAllByIdInOrderByCreationDateDesc(patentIds);
    }

    @Override
    public Page<Patent> getPaginatedPatent(List<String> productionIds, String filter, Pageable pageable) {
        return this.mongoRepository.findAllById(productionIds, filter, pageable);
    }

    @Override
    public List<Patent> findAll() {
        return this.mongoRepository.findAll();
    }

    @Override
    public List<Patent> findByPublicationNumber(String publicationNumber){
            return this.mongoRepository.findByPublicationNumber(publicationNumber);
    }
}
