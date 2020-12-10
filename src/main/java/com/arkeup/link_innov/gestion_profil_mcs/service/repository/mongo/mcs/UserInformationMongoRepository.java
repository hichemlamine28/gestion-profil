package com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.UserInformation;

public interface UserInformationMongoRepository extends MongoRepository<UserInformation,String> {
	UserInformation findByUsername(String username);
}
