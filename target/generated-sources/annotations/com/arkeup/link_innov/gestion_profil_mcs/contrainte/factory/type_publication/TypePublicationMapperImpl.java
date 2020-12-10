package com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.type_publication;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.TypePublication;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.TypePublicationDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class TypePublicationMapperImpl implements TypePublicationMapper {

    @Override
    public TypePublicationDTO typePublicationToTypePublicationDTO(TypePublication typePublication) {
        if ( typePublication == null ) {
            return null;
        }

        TypePublicationDTO typePublicationDTO = new TypePublicationDTO();

        typePublicationDTO.setId( typePublication.getId() );
        typePublicationDTO.setCode( typePublication.getCode() );
        typePublicationDTO.setLabel( typePublication.getLabel() );

        return typePublicationDTO;
    }

    @Override
    public TypePublication typePublicationDTOToTypePublication(TypePublicationDTO typePublicationDTO) {
        if ( typePublicationDTO == null ) {
            return null;
        }

        TypePublication typePublication = new TypePublication();

        typePublication.setId( typePublicationDTO.getId() );
        typePublication.setCode( typePublicationDTO.getCode() );
        typePublication.setLabel( typePublicationDTO.getLabel() );

        return typePublication;
    }

    @Override
    public List<TypePublicationDTO> typePublicationToTypePublicationDTOs(List<TypePublication> typePublications) {
        if ( typePublications == null ) {
            return null;
        }

        List<TypePublicationDTO> list = new ArrayList<TypePublicationDTO>( typePublications.size() );
        for ( TypePublication typePublication : typePublications ) {
            list.add( typePublicationToTypePublicationDTO( typePublication ) );
        }

        return list;
    }

    @Override
    public List<TypePublication> typePublicationDTOToTypePublications(List<TypePublicationDTO> typePublicationDTOs) {
        if ( typePublicationDTOs == null ) {
            return null;
        }

        List<TypePublication> list = new ArrayList<TypePublication>( typePublicationDTOs.size() );
        for ( TypePublicationDTO typePublicationDTO : typePublicationDTOs ) {
            list.add( typePublicationDTOToTypePublication( typePublicationDTO ) );
        }

        return list;
    }
}
