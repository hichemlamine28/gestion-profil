package com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.referential;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.Typology;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.referential.TypologyDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class TypologyMapperImpl implements TypologyMapper {

    @Override
    public TypologyDTO typologyToTypologyDTO(Typology typology) {
        if ( typology == null ) {
            return null;
        }

        TypologyDTO typologyDTO = new TypologyDTO();

        typologyDTO.setId( typology.getId() );
        typologyDTO.setLabel( typology.getLabel() );

        return typologyDTO;
    }

    @Override
    public Typology typologyDTOToTypology(TypologyDTO typologyDTO) {
        if ( typologyDTO == null ) {
            return null;
        }

        Typology typology = new Typology();

        typology.setId( typologyDTO.getId() );
        typology.setLabel( typologyDTO.getLabel() );

        return typology;
    }

    @Override
    public List<TypologyDTO> typologyToTypologyDTOs(List<Typology> typologys) {
        if ( typologys == null ) {
            return null;
        }

        List<TypologyDTO> list = new ArrayList<TypologyDTO>( typologys.size() );
        for ( Typology typology : typologys ) {
            list.add( typologyToTypologyDTO( typology ) );
        }

        return list;
    }

    @Override
    public List<Typology> typologyDTOToTypologys(List<TypologyDTO> typologyDTOs) {
        if ( typologyDTOs == null ) {
            return null;
        }

        List<Typology> list = new ArrayList<Typology>( typologyDTOs.size() );
        for ( TypologyDTO typologyDTO : typologyDTOs ) {
            list.add( typologyDTOToTypology( typologyDTO ) );
        }

        return list;
    }
}
