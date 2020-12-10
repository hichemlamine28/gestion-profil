package com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.oauth2;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.oauth2.OAuth2RefreshTokenDO;

public interface OAuth2RefreshTokenDOMongoRepository extends MongoRepository<OAuth2RefreshTokenDO,String> {
	
	List <OAuth2RefreshTokenDO> deleteByUsername(String userName);
	OAuth2RefreshTokenDO findOneByRefreshToken(String accessToken);

}
