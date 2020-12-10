package com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.category;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Category;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.CategoryDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public CategoryDTO categoryToCategoryDTO(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setId( category.getId() );
        categoryDTO.setName( category.getName() );

        return categoryDTO;
    }

    @Override
    public Category categoryDTOToCategory(CategoryDTO categoryDTO) {
        if ( categoryDTO == null ) {
            return null;
        }

        Category category = new Category();

        category.setId( categoryDTO.getId() );
        category.setName( categoryDTO.getName() );

        return category;
    }

    @Override
    public List<CategoryDTO> categoriesToCategoriesDTO(List<Category> categories) {
        if ( categories == null ) {
            return null;
        }

        List<CategoryDTO> list = new ArrayList<CategoryDTO>( categories.size() );
        for ( Category category : categories ) {
            list.add( categoryToCategoryDTO( category ) );
        }

        return list;
    }

    @Override
    public List<Category> categoriesDTOToCategories(List<CategoryDTO> categoriesDTO) {
        if ( categoriesDTO == null ) {
            return null;
        }

        List<Category> list = new ArrayList<Category>( categoriesDTO.size() );
        for ( CategoryDTO categoryDTO : categoriesDTO ) {
            list.add( categoryDTOToCategory( categoryDTO ) );
        }

        return list;
    }
}
