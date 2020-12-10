package com.arkeup.link_innov.gestion_profil_mcs.donnee.dto;

import java.util.ArrayList;
import java.util.List;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.BaseDTO;

public class CategoriesDTO extends BaseDTO {

    private List<CategoryDTO> categoriesDTO = new ArrayList<CategoryDTO>();

    public List<CategoryDTO> getCategoriesDTO() {
        return categoriesDTO;
    }

    public void setCategoriesDTO(List<CategoryDTO> categoriesDTO) {
        this.categoriesDTO = categoriesDTO;
    }

}
