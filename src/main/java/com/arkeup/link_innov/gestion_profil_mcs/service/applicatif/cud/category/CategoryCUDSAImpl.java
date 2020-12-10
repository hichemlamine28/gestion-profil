package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.category;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Category;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.CategoryDTO;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.CategoryRepository;

@Service
public class CategoryCUDSAImpl implements CategoryCUDSA {

	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public CategoryDTO initDatabase() {

		List<Category> categories = new ArrayList<>();
		Category cat = new Category();
		categoryRepository.deleteAll();

		cat.setId("uuid-category-industriel");
		cat.setName("Industriel");
		categories.add(cat);

		cat = new Category();
		cat.setId("uuid-category-academique");
		cat.setName("Académique");
		categories.add(cat);

		cat = new Category();
		cat.setId("uuid-category-autre-acteur");
		cat.setName("Autres acteurs de l'innovation");
		categories.add(cat);

		// TODO LIN-433
		cat = new Category();
		cat.setId("uuid-category-chercheur");
		cat.setName("Chercheur");
		categories.add(cat);

		categoryRepository.saveAll(categories);

		CategoryDTO result = new CategoryDTO();
		result.setMessage("Init category list");
		result.setError(false);
		return result;
	}

}