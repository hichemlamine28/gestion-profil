package com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.oauth2;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.oauth2.OAuth2AccessTokenDO;


public interface OAuth2AccessTokenDOMongoRepository extends MongoRepository<OAuth2AccessTokenDO,String> {
	
	List <OAuth2AccessTokenDO> deleteByUsername(String userName);
	OAuth2AccessTokenDO findOneByAccessToken(String accessToken);
	
}
