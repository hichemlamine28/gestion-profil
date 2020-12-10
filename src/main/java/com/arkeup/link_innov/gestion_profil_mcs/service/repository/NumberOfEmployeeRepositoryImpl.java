package com.arkeup.link_innov.gestion_profil_mcs.service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.NumberOfEmployee;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.common.mongo_es.CommonMongoToESRepositoryImpl;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.elastic_search.NumberOfEmployeeESRepository;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs.NumberOfEmployeeMongoRepository;

/**
 *
 * @author bona
 */
@Repository
public class NumberOfEmployeeRepositoryImpl extends CommonMongoToESRepositoryImpl<NumberOfEmployee, String, NumberOfEmployeeMongoRepository, NumberOfEmployeeESRepository> implements NumberOfEmployeeRepository {

    @Override
    public Page<NumberOfEmployee> findAll(Pageable pageable) {
        return this.mongoRepository.findAll(pageable);
    }

}
