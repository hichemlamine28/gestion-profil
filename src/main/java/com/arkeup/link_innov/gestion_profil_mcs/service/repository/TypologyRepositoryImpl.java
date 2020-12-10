package com.arkeup.link_innov.gestion_profil_mcs.service.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.Typology;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.common.mongo_es.CommonMongoToESRepositoryImpl;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.elastic_search.TypologyESRepository;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs.TypologyMongoRepository;

/**
 *
 * @author bona
 */
@Repository
public class TypologyRepositoryImpl extends CommonMongoToESRepositoryImpl<Typology, String, TypologyMongoRepository, TypologyESRepository> implements TypologyRepository {

    @Override
    public List<Typology> findAll() {
        return this.mongoRepository.findAll();
    }

}
