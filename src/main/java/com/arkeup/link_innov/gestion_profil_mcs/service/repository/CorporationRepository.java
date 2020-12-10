package com.arkeup.link_innov.gestion_profil_mcs.service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs.CorporationMongoRepository;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.elastic_search.CorporationESRepository;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Corporation;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.common.mongo_es.CommonMongoToESRepository;

public interface CorporationRepository extends CommonMongoToESRepository<Corporation, String, CorporationMongoRepository, CorporationESRepository> {

    Optional<Corporation> getCorporation(String id);

    Page<Corporation> listCorporation(Pageable pageable);

    Page<Corporation> findCorporation(String name, Pageable pageable);

    Page<Corporation> listCorporationByAdmin(String admin, Pageable pageable);

    Page<Corporation> getCorporationsInformationsByIds(List<String> corporationIds, Pageable pageable);

    Optional<Corporation> getCorporationByName(String name);

    Boolean isOtherCorporationExistByName(String id, String name);

    Page<Corporation> getCorporationsInformations(List<String> corporationIds, String filter, String categorie, Pageable pageable);

    List<Corporation> findAll();
}
