package com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.production;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Productions;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.neo4j.User;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ProductionsDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class ProductionMapperImpl implements ProductionMapper {

    @Override
    public List<ProductionsDTO> productionToProductionDTOs(List<Productions> productions) {
        if ( productions == null ) {
            return null;
        }

        List<ProductionsDTO> list = new ArrayList<ProductionsDTO>( productions.size() );
        for ( Productions productions1 : productions ) {
            list.add( productionToProductionDTO( productions1 ) );
        }

        return list;
    }

    @Override
    public List<Productions> productionDTOsToProduction(List<ProductionsDTO> productionsDTOS) {
        if ( productionsDTOS == null ) {
            return null;
        }

        List<Productions> list = new ArrayList<Productions>( productionsDTOS.size() );
        for ( ProductionsDTO productionsDTO : productionsDTOS ) {
            list.add( productionsDTOToProductions( productionsDTO ) );
        }

        return list;
    }

    protected Productions productionsDTOToProductions(ProductionsDTO productionsDTO) {
        if ( productionsDTO == null ) {
            return null;
        }

        Productions productions = new Productions();

        productions.setId( productionsDTO.getId() );
        productions.setProductType( productionsDTO.getProductType() );
        List<User> list = productionsDTO.getContributors();
        if ( list != null ) {
            productions.setContributors( new ArrayList<User>( list ) );
        }
        else {
            productions.setContributors( null );
        }
        productions.setCreationDate( productionsDTO.getCreationDate() );
        productions.setModificationDate( productionsDTO.getModificationDate() );
        List<String> list1 = productionsDTO.getTags();
        if ( list1 != null ) {
            productions.setTags( new ArrayList<String>( list1 ) );
        }
        else {
            productions.setTags( null );
        }
        productions.setTitle( productionsDTO.getTitle() );
        productions.setUrl( productionsDTO.getUrl() );
        productions.setOwnerId( productionsDTO.getOwnerId() );
        productions.setMediaId( productionsDTO.getMediaId() );
        productions.setHasMedia( productionsDTO.getHasMedia() );

        return productions;
    }
}
