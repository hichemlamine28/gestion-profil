package com.arkeup.link_innov.gestion_profil_mcs.service.repository;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Category;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.common.mongo_es.CommonMongoToESRepositoryImpl;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.elastic_search.CategoryESRepository;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs.CategoryMongoRepository;
import java.util.List;

import org.springframework.stereotype.Repository;

/**
 *
 * @author bona
 */
@Repository
public class CategoryRepositoryImpl extends CommonMongoToESRepositoryImpl<Category, String, CategoryMongoRepository, CategoryESRepository> implements CategoryRepository {

    @Override
    public List<Category> findAll() {
        return this.mongoRepository.findAll();
    }

	@Override
	public Boolean isCategoryExist(String id) {
		return mongoRepository.existsById(id);
	}

    @Override
    public List<Category> findByName(String name) {
        return mongoRepository.findByName(name);
    }

}
