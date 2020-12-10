package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.category;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.category.CategoryMapper;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.CategoriesDTO;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.category.CategoryRSM;

@Service
public class CategoryRSAImpl implements CategoryRSA {

	@Autowired
	CategoryRSM categoryRSM;
	
	@Autowired
	CategoryMapper categoryFactory;
	
	@Override
	public CategoriesDTO listCategories() {
		
		CategoriesDTO result = new CategoriesDTO();
		result.setCategoriesDTO(categoryFactory.categoriesToCategoriesDTO(categoryRSM.listCategories()));
		result.setError(false);
		result.setMessage("List of all categories");
		return result;
	}

	@Override
	public Boolean isCategoryExist(String id) {
		return categoryRSM.isCategoryExist(id);
	}

	@Override
	public CategoryDTO findByName(String name){
		return categoryFactory.categoryToCategoryDTO(categoryRSM.findByName(name));
	}
}
