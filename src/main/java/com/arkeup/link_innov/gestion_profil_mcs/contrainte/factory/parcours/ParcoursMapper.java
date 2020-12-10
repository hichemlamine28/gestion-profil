package com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.parcours;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Parcours;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ParcoursDTO;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author bona
 */
@Mapper
public interface ParcoursMapper {

    ParcoursDTO parcoursToParcoursDTO(Parcours parcours);
    
    ParcoursMapper MAPPER = Mappers.getMapper(ParcoursMapper.class);

    List<ParcoursDTO> listParcoursToListParcoursDTO(List<Parcours> parcours);

    List<Parcours> listParcoursDTOToListParcours(List<ParcoursDTO> parcoursDTO);

    default Page<ParcoursDTO> parcoursPageToParcoursDtoPage(Page<Parcours> parcoursPage, Pageable pageable) {

        List<ParcoursDTO> listParcoursDto = MAPPER.listParcoursToListParcoursDTO(parcoursPage.getContent());
        Page<ParcoursDTO> parcoursDTOPage = new PageImpl<>(listParcoursDto, pageable, parcoursPage.getTotalElements());
        return parcoursDTOPage;
    }
}
