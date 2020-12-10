package com.arkeup.link_innov.gestion_profil_mcs.service.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.Keyword;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.common.mongo_es.CommonMongoToESRepository;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.elastic_search.KeywordESRepository;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs.KeywordMongoRepository;

/**
 *
 * @author bona
 */
public interface KeywordRepository
		extends CommonMongoToESRepository<Keyword, String, KeywordMongoRepository, KeywordESRepository> {

	public Page<Keyword> findAll(Pageable pageable);

	public List<Keyword> findByLabel(String label);
}
