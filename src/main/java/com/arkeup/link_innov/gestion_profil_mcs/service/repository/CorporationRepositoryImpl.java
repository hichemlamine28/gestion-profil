package com.arkeup.link_innov.gestion_profil_mcs.service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Corporation;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs.CorporationMongoRepository;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.elastic_search.CorporationESRepository;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.common.mongo_es.CommonMongoToESRepositoryImpl;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

@Repository
public class CorporationRepositoryImpl extends CommonMongoToESRepositoryImpl<Corporation, String, CorporationMongoRepository, CorporationESRepository> implements CorporationRepository {

    @Override
    public Optional<Corporation> getCorporation(String id) {
        return this.mongoRepository.findById(id);
    }

    @Override
    public Page<Corporation> listCorporation(Pageable pageable) {
        return this.mongoRepository.findAll(pageable);
    }

    @Override
    public Page<Corporation> findCorporation(String name, Pageable pageable) {
        return this.mongoRepository.findByNameContaining(name, pageable);
    }

    @Override
    public Page<Corporation> listCorporationByAdmin(String admin, Pageable pageable) {
        return this.mongoRepository.findByAdminsOrSuperAdmin(admin, admin, pageable);
    }

    @Override
    public Page<Corporation> getCorporationsInformationsByIds(List<String> corporationIds, Pageable pageable) {
        return this.mongoRepository.findAllByIdIn(corporationIds, pageable);
    }

    @Override
    public Optional<Corporation> getCorporationByName(String name) {
        return this.mongoRepository.findByNameIgnoreCase(name);
    }

    @Override
    public Boolean isOtherCorporationExistByName(String id, String name) {
        List<Corporation> corporations = this.mongoRepository.findAllByNameIgnoreCaseAndIdNot(name, id);
        return CollectionUtils.isNotEmpty(corporations);
    }

    @Override
    public Page<Corporation> getCorporationsInformations(List<String> corporationIds, String filter, String categorie, Pageable pageable) {
        if (StringUtils.isEmpty(categorie)) {
            return this.mongoRepository.findAllById(corporationIds, filter, pageable);
        }
        return mongoRepository.findAllById(corporationIds, filter, categorie, pageable);
    }

    @Override
    public List<Corporation> findAll(){
        return mongoRepository.findAll();
    }
}
