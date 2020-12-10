package com.arkeup.link_innov.gestion_profil_mcs.service.repository;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Category;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.common.mongo_es.CommonMongoToESRepository;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.elastic_search.CategoryESRepository;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs.CategoryMongoRepository;
import java.util.List;

/**
 *
 * @author bona
 */
public interface CategoryRepository extends CommonMongoToESRepository<Category, String, CategoryMongoRepository, CategoryESRepository> {

    public List<Category> findAll();
    public Boolean isCategoryExist(String id);
    List<Category> findByName(String name);
}
