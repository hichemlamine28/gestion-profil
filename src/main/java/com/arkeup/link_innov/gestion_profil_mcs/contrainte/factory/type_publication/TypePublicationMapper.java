package com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.type_publication;

import java.util.List;

import org.mapstruct.Mapper;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.TypePublication;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.TypePublicationDTO;

@Mapper
public interface TypePublicationMapper {

	TypePublicationDTO typePublicationToTypePublicationDTO(TypePublication typePublication);

	TypePublication typePublicationDTOToTypePublication(TypePublicationDTO typePublicationDTO);

	List<TypePublicationDTO> typePublicationToTypePublicationDTOs(List<TypePublication> typePublications);

	List<TypePublication> typePublicationDTOToTypePublications(List<TypePublicationDTO> typePublicationDTOs);

}
