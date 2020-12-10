package com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.Typology;

/**
 *
 * @author bona
 */

public interface TypologyMongoRepository extends MongoRepository<Typology, String> {
    Page<Typology> findAll(Pageable pageable);
}
