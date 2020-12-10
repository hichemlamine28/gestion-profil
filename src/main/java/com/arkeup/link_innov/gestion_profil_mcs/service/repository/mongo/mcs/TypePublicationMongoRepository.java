package com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.TypePublication;

/**
 * n'a pas besoin d'être implémenté
 */
public interface TypePublicationMongoRepository extends MongoRepository<TypePublication, String> {
}
