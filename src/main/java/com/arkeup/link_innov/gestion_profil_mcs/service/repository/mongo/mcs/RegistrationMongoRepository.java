/**
 * 
 */
package com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Registration;

/**
 * @author mikajy
 *
 */
public interface RegistrationMongoRepository extends MongoRepository<Registration, String> {
	/**
	 * 
	 * @param username
	 * @return
	 */
	Registration findOneByUseruid(String useruid);
	
	/**
	 * 
	 * @param id
	 * @param username
	 * @return
	 */
	Registration findByIdAndUseruid(String id, String useruid);
	
//	@Query()
//	void deleteByIdAndUsername(String id, String username);
}
