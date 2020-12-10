package com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.dynamic_page;

import java.util.List;

import org.mapstruct.Mapper;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.DynamicPage;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.dynamic_page.DynamicPageDTO;

/**
 * @author Stéphan R.
 *
 */
@Mapper
public interface DynamicPageMapper {

	DynamicPage toDO(DynamicPageDTO dynamicPageDTO);

	DynamicPageDTO toDTO(DynamicPage dynamicPage);

	List<DynamicPage> toDOs(List<DynamicPageDTO> dynamicPageDTOs);

	List<DynamicPageDTO> toDTOs(List<DynamicPage> dynamicPages);

}
