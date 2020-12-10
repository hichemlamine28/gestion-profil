package com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.referential;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.ThematicArea;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.referential.ThematicAreaDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class ThematicAreaMapperImpl implements ThematicAreaMapper {

    @Override
    public ThematicAreaDTO thematicAreaToThematicAreaDTO(ThematicArea thematicArea) {
        if ( thematicArea == null ) {
            return null;
        }

        ThematicAreaDTO thematicAreaDTO = new ThematicAreaDTO();

        thematicAreaDTO.setId( thematicArea.getId() );
        thematicAreaDTO.setLabel( thematicArea.getLabel() );

        return thematicAreaDTO;
    }

    @Override
    public ThematicArea thematicAreaDTOToThematicArea(ThematicAreaDTO thematicAreaDTO) {
        if ( thematicAreaDTO == null ) {
            return null;
        }

        ThematicArea thematicArea = new ThematicArea();

        thematicArea.setId( thematicAreaDTO.getId() );
        thematicArea.setLabel( thematicAreaDTO.getLabel() );

        return thematicArea;
    }

    @Override
    public List<ThematicAreaDTO> thematicAreaToThematicAreaDTOs(List<ThematicArea> thematicAreas) {
        if ( thematicAreas == null ) {
            return null;
        }

        List<ThematicAreaDTO> list = new ArrayList<ThematicAreaDTO>( thematicAreas.size() );
        for ( ThematicArea thematicArea : thematicAreas ) {
            list.add( thematicAreaToThematicAreaDTO( thematicArea ) );
        }

        return list;
    }

    @Override
    public List<ThematicArea> thematicAreaDTOToThematicAreas(List<ThematicAreaDTO> thematicAreaDTOs) {
        if ( thematicAreaDTOs == null ) {
            return null;
        }

        List<ThematicArea> list = new ArrayList<ThematicArea>( thematicAreaDTOs.size() );
        for ( ThematicAreaDTO thematicAreaDTO : thematicAreaDTOs ) {
            list.add( thematicAreaDTOToThematicArea( thematicAreaDTO ) );
        }

        return list;
    }
}
