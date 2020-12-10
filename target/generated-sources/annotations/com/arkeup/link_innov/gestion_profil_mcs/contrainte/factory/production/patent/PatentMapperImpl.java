package com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.production.patent;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Patent;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Productions;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.neo4j.User;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.PatentDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ProductionsDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class PatentMapperImpl implements PatentMapper {

    @Override
    public PatentDTO patentToPatentDTO(Patent patent) {
        if ( patent == null ) {
            return null;
        }

        PatentDTO patentDTO = new PatentDTO();

        patentDTO.setId( patent.getId() );
        patentDTO.setProductType( patent.getProductType() );
        List<User> list = patent.getContributors();
        if ( list != null ) {
            patentDTO.setContributors( new ArrayList<User>( list ) );
        }
        else {
            patentDTO.setContributors( null );
        }
        patentDTO.setCreationDate( patent.getCreationDate() );
        patentDTO.setModificationDate( patent.getModificationDate() );
        List<String> list1 = patent.getTags();
        if ( list1 != null ) {
            patentDTO.setTags( new ArrayList<String>( list1 ) );
        }
        else {
            patentDTO.setTags( null );
        }
        patentDTO.setTitle( patent.getTitle() );
        patentDTO.setUrl( patent.getUrl() );
        patentDTO.setOwnerId( patent.getOwnerId() );
        patentDTO.setMediaId( patent.getMediaId() );
        patentDTO.setHasMedia( patent.getHasMedia() );
        patentDTO.setPriorityDepositDate( patent.getPriorityDepositDate() );
        patentDTO.setDescription( patent.getDescription() );
        List<User> list2 = patent.getInventors();
        if ( list2 != null ) {
            patentDTO.setInventors( new ArrayList<User>( list2 ) );
        }
        else {
            patentDTO.setInventors( null );
        }
        patentDTO.setPublicationNumber( patent.getPublicationNumber() );
        patentDTO.setTitleNonConfidential( patent.getTitleNonConfidential() );
        patentDTO.setDepositor( patent.getDepositor() );
        patentDTO.setPublicationDate( patent.getPublicationDate() );

        return patentDTO;
    }

    @Override
    public Patent patentDTOToPatent(PatentDTO patentDTO) {
        if ( patentDTO == null ) {
            return null;
        }

        Patent patent = new Patent();

        patent.setId( patentDTO.getId() );
        patent.setProductType( patentDTO.getProductType() );
        List<User> list = patentDTO.getContributors();
        if ( list != null ) {
            patent.setContributors( new ArrayList<User>( list ) );
        }
        else {
            patent.setContributors( null );
        }
        patent.setCreationDate( patentDTO.getCreationDate() );
        patent.setModificationDate( patentDTO.getModificationDate() );
        List<String> list1 = patentDTO.getTags();
        if ( list1 != null ) {
            patent.setTags( new ArrayList<String>( list1 ) );
        }
        else {
            patent.setTags( null );
        }
        patent.setTitle( patentDTO.getTitle() );
        patent.setUrl( patentDTO.getUrl() );
        patent.setOwnerId( patentDTO.getOwnerId() );
        patent.setMediaId( patentDTO.getMediaId() );
        patent.setHasMedia( patentDTO.getHasMedia() );
        patent.setPriorityDepositDate( patentDTO.getPriorityDepositDate() );
        patent.setDescription( patentDTO.getDescription() );
        List<User> list2 = patentDTO.getInventors();
        if ( list2 != null ) {
            patent.setInventors( new ArrayList<User>( list2 ) );
        }
        else {
            patent.setInventors( null );
        }
        patent.setPublicationNumber( patentDTO.getPublicationNumber() );
        patent.setTitleNonConfidential( patentDTO.getTitleNonConfidential() );
        patent.setDepositor( patentDTO.getDepositor() );
        patent.setPublicationDate( patentDTO.getPublicationDate() );

        return patent;
    }

    @Override
    public List<PatentDTO> patentToPatentDTOs(List<Patent> patents) {
        if ( patents == null ) {
            return null;
        }

        List<PatentDTO> list = new ArrayList<PatentDTO>( patents.size() );
        for ( Patent patent : patents ) {
            list.add( patentToPatentDTO( patent ) );
        }

        return list;
    }

    @Override
    public List<Patent> patentDTOToPatents(List<PatentDTO> patentDTOs) {
        if ( patentDTOs == null ) {
            return null;
        }

        List<Patent> list = new ArrayList<Patent>( patentDTOs.size() );
        for ( PatentDTO patentDTO : patentDTOs ) {
            list.add( patentDTOToPatent( patentDTO ) );
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
