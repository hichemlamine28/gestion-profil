
package com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Parcours;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author bona
 */

public interface ParcoursMongoRepository extends MongoRepository<Parcours,String> {
        Page<Parcours> findAllByProfil(String profil, Pageable pageable);
}
