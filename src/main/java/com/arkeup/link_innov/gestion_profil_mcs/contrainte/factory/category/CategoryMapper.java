package com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.category;

import java.util.List;

import org.mapstruct.Mapper;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Category;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.CategoryDTO;

@Mapper
public interface CategoryMapper {
	CategoryDTO categoryToCategoryDTO(Category category);
	Category categoryDTOToCategory (CategoryDTO categoryDTO);
	List <CategoryDTO> categoriesToCategoriesDTO(List <Category> categories);
	List <Category> categoriesDTOToCategories(List <CategoryDTO> categoriesDTO);
}
