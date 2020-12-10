package com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.recommandation;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Recommandation;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.RecommandationDTO;

import java.util.List;
import org.mapstruct.Mapper;

@Mapper
public interface RecommandationMapper {

	RecommandationDTO recommandationToRecommandationDTO(Recommandation recommandation);
	
	Recommandation recommandationDTOToRecommandation(RecommandationDTO recommandationDTO);

    List<RecommandationDTO> listParcoursToListParcoursDTO(List<Recommandation> recommandation);

    List<Recommandation> listParcoursDTOToListParcours(List<RecommandationDTO> recommandationDTO);

}
