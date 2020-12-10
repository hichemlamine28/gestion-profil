package com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.dynamic_page;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.DynamicPage;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.dynamic_page.DynamicPageDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class DynamicPageMapperImpl implements DynamicPageMapper {

    @Override
    public DynamicPage toDO(DynamicPageDTO dynamicPageDTO) {
        if ( dynamicPageDTO == null ) {
            return null;
        }

        DynamicPage dynamicPage = new DynamicPage();

        dynamicPage.setId( dynamicPageDTO.getId() );
        dynamicPage.setCreationDate( dynamicPageDTO.getCreationDate() );
        dynamicPage.setModificationDate( dynamicPageDTO.getModificationDate() );
        dynamicPage.setContent( dynamicPageDTO.getContent() );
        dynamicPage.setUrlLabel( dynamicPageDTO.getUrlLabel() );
        dynamicPage.setTag( dynamicPageDTO.getTag() );

        return dynamicPage;
    }

    @Override
    public DynamicPageDTO toDTO(DynamicPage dynamicPage) {
        if ( dynamicPage == null ) {
            return null;
        }

        DynamicPageDTO dynamicPageDTO = new DynamicPageDTO();

        dynamicPageDTO.setId( dynamicPage.getId() );
        dynamicPageDTO.setCreationDate( dynamicPage.getCreationDate() );
        dynamicPageDTO.setModificationDate( dynamicPage.getModificationDate() );
        dynamicPageDTO.setContent( dynamicPage.getContent() );
        dynamicPageDTO.setUrlLabel( dynamicPage.getUrlLabel() );
        dynamicPageDTO.setTag( dynamicPage.getTag() );

        return dynamicPageDTO;
    }

    @Override
    public List<DynamicPage> toDOs(List<DynamicPageDTO> dynamicPageDTOs) {
        if ( dynamicPageDTOs == null ) {
            return null;
        }

        List<DynamicPage> list = new ArrayList<DynamicPage>( dynamicPageDTOs.size() );
        for ( DynamicPageDTO dynamicPageDTO : dynamicPageDTOs ) {
            list.add( toDO( dynamicPageDTO ) );
        }

        return list;
    }

    @Override
    public List<DynamicPageDTO> toDTOs(List<DynamicPage> dynamicPages) {
        if ( dynamicPages == null ) {
            return null;
        }

        List<DynamicPageDTO> list = new ArrayList<DynamicPageDTO>( dynamicPages.size() );
        for ( DynamicPage dynamicPage : dynamicPages ) {
            list.add( toDTO( dynamicPage ) );
        }

        return list;
    }
}
