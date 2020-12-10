package com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.category;

import java.util.List;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Category;

public interface CategoryRSM {
	List<Category> listCategories();
	
	public Boolean isCategoryExist(String id);

	Category findByName(String name);
}
