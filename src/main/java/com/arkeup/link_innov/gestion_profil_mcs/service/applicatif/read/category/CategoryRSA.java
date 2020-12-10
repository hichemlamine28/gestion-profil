package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.category;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.CategoriesDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.CategoryDTO;

public interface CategoryRSA {
	CategoriesDTO listCategories();
	public Boolean isCategoryExist(String id);
	CategoryDTO findByName(String name);
}
