package com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Category;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author bona
 */

public interface CategoryMongoRepository extends MongoRepository<Category, String> {
    List<Category> findAll();
    List<Category> findByName(String name);
}
