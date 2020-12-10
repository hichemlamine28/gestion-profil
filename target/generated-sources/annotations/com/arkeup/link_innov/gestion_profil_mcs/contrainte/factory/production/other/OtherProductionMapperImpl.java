package com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.production.other;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.OtherProduction;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Productions;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.neo4j.User;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.OtherProductionDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ProductionsDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class OtherProductionMapperImpl implements OtherProductionMapper {

    @Override
    public OtherProductionDTO otherProductionToOtherProductionDTO(OtherProduction otherProduction) {
        if ( otherProduction == null ) {
            return null;
        }

        OtherProductionDTO otherProductionDTO = new OtherProductionDTO();

        otherProductionDTO.setId( otherProduction.getId() );
        otherProductionDTO.setProductType( otherProduction.getProductType() );
        List<User> list = otherProduction.getContributors();
        if ( list != null ) {
            otherProductionDTO.setContributors( new ArrayList<User>( list ) );
        }
        else {
            otherProductionDTO.setContributors( null );
        }
        otherProductionDTO.setCreationDate( otherProduction.getCreationDate() );
        otherProductionDTO.setModificationDate( otherProduction.getModificationDate() );
        List<String> list1 = otherProduction.getTags();
        if ( list1 != null ) {
            otherProductionDTO.setTags( new ArrayList<String>( list1 ) );
        }
        else {
            otherProductionDTO.setTags( null );
        }
        otherProductionDTO.setTitle( otherProduction.getTitle() );
        otherProductionDTO.setUrl( otherProduction.getUrl() );
        otherProductionDTO.setOwnerId( otherProduction.getOwnerId() );
        otherProductionDTO.setMediaId( otherProduction.getMediaId() );
        otherProductionDTO.setHasMedia( otherProduction.getHasMedia() );
        List<User> list2 = otherProduction.getAuthors();
        if ( list2 != null ) {
            otherProductionDTO.setAuthors( new ArrayList<User>( list2 ) );
        }
        else {
            otherProductionDTO.setAuthors( null );
        }
        otherProductionDTO.setDate( otherProduction.getDate() );
        otherProductionDTO.setDescription( otherProduction.getDescription() );
        otherProductionDTO.setLink( otherProduction.getLink() );
        otherProductionDTO.setDownloadPossibility( otherProduction.getDownloadPossibility() );
        otherProductionDTO.setPostType( otherProduction.getPostType() );

        return otherProductionDTO;
    }

    @Override
    public OtherProduction otherProductionDTOToOtherProduction(OtherProductionDTO otherProductionDTO) {
        if ( otherProductionDTO == null ) {
            return null;
        }

        OtherProduction otherProduction = new OtherProduction();

        otherProduction.setId( otherProductionDTO.getId() );
        otherProduction.setProductType( otherProductionDTO.getProductType() );
        List<User> list = otherProductionDTO.getContributors();
        if ( list != null ) {
            otherProduction.setContributors( new ArrayList<User>( list ) );
        }
        else {
            otherProduction.setContributors( null );
        }
        otherProduction.setCreationDate( otherProductionDTO.getCreationDate() );
        otherProduction.setModificationDate( otherProductionDTO.getModificationDate() );
        List<String> list1 = otherProductionDTO.getTags();
        if ( list1 != null ) {
            otherProduction.setTags( new ArrayList<String>( list1 ) );
        }
        else {
            otherProduction.setTags( null );
        }
        otherProduction.setTitle( otherProductionDTO.getTitle() );
        otherProduction.setUrl( otherProductionDTO.getUrl() );
        otherProduction.setOwnerId( otherProductionDTO.getOwnerId() );
        otherProduction.setMediaId( otherProductionDTO.getMediaId() );
        otherProduction.setHasMedia( otherProductionDTO.getHasMedia() );
        List<User> list2 = otherProductionDTO.getAuthors();
        if ( list2 != null ) {
            otherProduction.setAuthors( new ArrayList<User>( list2 ) );
        }
        else {
            otherProduction.setAuthors( null );
        }
        otherProduction.setDate( otherProductionDTO.getDate() );
        otherProduction.setDescription( otherProductionDTO.getDescription() );
        otherProduction.setLink( otherProductionDTO.getLink() );
        otherProduction.setDownloadPossibility( otherProductionDTO.getDownloadPossibility() );
        otherProduction.setPostType( otherProductionDTO.getPostType() );

        return otherProduction;
    }

    @Override
    public List<OtherProductionDTO> otherProductionToOtherProductionDTOs(List<OtherProduction> otherProductions) {
        if ( otherProductions == null ) {
            return null;
        }

        List<OtherProductionDTO> list = new ArrayList<OtherProductionDTO>( otherProductions.size() );
        for ( OtherProduction otherProduction : otherProductions ) {
            list.add( otherProductionToOtherProductionDTO( otherProduction ) );
        }

        return list;
    }

    @Override
    public List<OtherProduction> otherProductionDTOToOtherProductions(List<OtherProductionDTO> otherProductionDTOs) {
        if ( otherProductionDTOs == null ) {
            return null;
        }

        List<OtherProduction> list = new ArrayList<OtherProduction>( otherProductionDTOs.size() );
        for ( OtherProductionDTO otherProductionDTO : otherProductionDTOs ) {
            list.add( otherProductionDTOToOtherProduction( otherProductionDTO ) );
        }

        return list;
    }

    @Override
    public ProductionsDTO productionsToProductionsDTO(Productions productions) {
        if ( productions == null ) {
            return null;
        }

        ProductionsDTO productionsDTO = new ProductionsDTO();

        productionsDTO.setId( productions.getId() );
        productionsDTO.setProductType( productions.getProductType() );
        List<User> list = productions.getContributors();
        if ( list != null ) {
            productionsDTO.setContributors( new ArrayList<User>( list ) );
        }
        else {
            productionsDTO.setContributors( null );
        }
        productionsDTO.setCreationDate( productions.getCreationDate() );
        productionsDTO.setModificationDate( productions.getModificationDate() );
        List<String> list1 = productions.getTags();
        if ( list1 != null ) {
            productionsDTO.setTags( new ArrayList<String>( list1 ) );
        }
        else {
            productionsDTO.setTags( null );
        }
        productionsDTO.setTitle( productions.getTitle() );
        productionsDTO.setUrl( productions.getUrl() );
        productionsDTO.setOwnerId( productions.getOwnerId() );
        productionsDTO.setMediaId( productions.getMediaId() );
        productionsDTO.setHasMedia( productions.getHasMedia() );

        return productionsDTO;
    }

    @Override
    public Productions productionsDTOToProductions(ProductionsDTO productionsDTO) {
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
