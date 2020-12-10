package com.arkeup.link_innov.gestion_profil_mcs.service.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.Keyword;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.common.mongo_es.CommonMongoToESRepositoryImpl;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.elastic_search.KeywordESRepository;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs.KeywordMongoRepository;

/**
 *
 * @author bona
 */
@Repository
public class KeywordRepositoryImpl
		extends CommonMongoToESRepositoryImpl<Keyword, String, KeywordMongoRepository, KeywordESRepository>
		implements KeywordRepository {

	@Override
	public Page<Keyword> findAll(Pageable pageable) {
		return this.mongoRepository.findAll(pageable);
	}

	@Override
	public List<Keyword> findByLabel(String label) {
		return this.mongoRepository.findByLabel(label);
	}

}
