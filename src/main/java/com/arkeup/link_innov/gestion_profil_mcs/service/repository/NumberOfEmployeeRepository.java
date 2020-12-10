package com.arkeup.link_innov.gestion_profil_mcs.service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.NumberOfEmployee;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.common.mongo_es.CommonMongoToESRepository;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.elastic_search.NumberOfEmployeeESRepository;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs.NumberOfEmployeeMongoRepository;

/**
 *
 * @author bona
 */
public interface NumberOfEmployeeRepository extends CommonMongoToESRepository<NumberOfEmployee, String, NumberOfEmployeeMongoRepository, NumberOfEmployeeESRepository> {

    public Page<NumberOfEmployee> findAll(Pageable pageable);
}
