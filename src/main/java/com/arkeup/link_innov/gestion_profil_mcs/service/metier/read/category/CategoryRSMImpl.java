package com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Category;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.CategoryRepository;

@Service
public class CategoryRSMImpl implements CategoryRSM {

	@Autowired
	CategoryRepository categoryRepository;
	
	@Override
	public List<Category> listCategories() {
		return categoryRepository.findAll();
	}

	@Override
	public Boolean isCategoryExist(String id) {
		return categoryRepository.isCategoryExist(id);
	}

	@Override
	public Category findByName(String name) {
		List<Category> categories = categoryRepository.findByName(name);
		return ( categories.size() > 0 ) ? categories.get(0) : null;
	}

}
