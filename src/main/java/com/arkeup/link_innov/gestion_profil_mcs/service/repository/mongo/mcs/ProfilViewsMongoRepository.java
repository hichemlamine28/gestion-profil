
package com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.ProfileViews;

/**
 *
 * @author njaka
 */
public interface ProfilViewsMongoRepository extends MongoRepository<ProfileViews, String> {

	List<ProfileViews> findByUserId(String userId);
}
