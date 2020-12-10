package com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.oauth2;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.UserConnexion;

public interface UserConnexionRepository extends MongoRepository<UserConnexion,String> {
	
	UserConnexion findByUsername(String username);
	
}
