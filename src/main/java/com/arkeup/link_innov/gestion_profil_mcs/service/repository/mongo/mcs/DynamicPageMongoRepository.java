package com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.DynamicPage;

/**
 * @author Stéphan R.
 *
 */
public interface DynamicPageMongoRepository extends MongoRepository<DynamicPage, String> {

}
