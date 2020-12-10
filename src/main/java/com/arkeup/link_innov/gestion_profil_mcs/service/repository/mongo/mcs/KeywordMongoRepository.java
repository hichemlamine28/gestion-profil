package com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.Keyword;

/**
 *
 * @author bona
 */

public interface KeywordMongoRepository extends MongoRepository<Keyword, String> {
	Page<Keyword> findAll(Pageable pageable);

	List<Keyword> findByLabel(String label);
}
