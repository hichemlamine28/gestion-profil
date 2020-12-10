package com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.production.project;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Productions;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Project;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.neo4j.User;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ProductionsDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ProjectDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class ProjectMapperImpl implements ProjectMapper {

    @Override
    public ProjectDTO toDTO(Project project) {
        if ( project == null ) {
            return null;
        }

        ProjectDTO projectDTO = new ProjectDTO();

        projectDTO.setId( project.getId() );
        projectDTO.setProductType( project.getProductType() );
        List<User> list = project.getContributors();
        if ( list != null ) {
            projectDTO.setContributors( new ArrayList<User>( list ) );
        }
        else {
            projectDTO.setContributors( null );
        }
        projectDTO.setCreationDate( project.getCreationDate() );
        projectDTO.setModificationDate( project.getModificationDate() );
        List<String> list1 = project.getTags();
        if ( list1 != null ) {
            projectDTO.setTags( new ArrayList<String>( list1 ) );
        }
        else {
            projectDTO.setTags( null );
        }
        projectDTO.setTitle( project.getTitle() );
        projectDTO.setUrl( project.getUrl() );
        projectDTO.setOwnerId( project.getOwnerId() );
        projectDTO.setMediaId( project.getMediaId() );
        projectDTO.setHasMedia( project.getHasMedia() );
        List<User> list2 = project.getCollaborators();
        if ( list2 != null ) {
            projectDTO.setCollaborators( new ArrayList<User>( list2 ) );
        }
        else {
            projectDTO.setCollaborators( null );
        }
        projectDTO.setDate( project.getDate() );
        projectDTO.setDateDebut( project.getDateDebut() );
        projectDTO.setDateFin( project.getDateFin() );
        projectDTO.setDescription( project.getDescription() );

        return projectDTO;
    }

    @Override
    public Project toDO(ProjectDTO projectDTO) {
        if ( projectDTO == null ) {
            return null;
        }

        Project project = new Project();

        project.setId( projectDTO.getId() );
        project.setProductType( projectDTO.getProductType() );
        List<User> list = projectDTO.getContributors();
        if ( list != null ) {
            project.setContributors( new ArrayList<User>( list ) );
        }
        else {
            project.setContributors( null );
        }
        project.setCreationDate( projectDTO.getCreationDate() );
        project.setModificationDate( projectDTO.getModificationDate() );
        List<String> list1 = projectDTO.getTags();
        if ( list1 != null ) {
            project.setTags( new ArrayList<String>( list1 ) );
        }
        else {
            project.setTags( null );
        }
        project.setTitle( projectDTO.getTitle() );
        project.setUrl( projectDTO.getUrl() );
        project.setOwnerId( projectDTO.getOwnerId() );
        project.setMediaId( projectDTO.getMediaId() );
        project.setHasMedia( projectDTO.getHasMedia() );
        List<User> list2 = projectDTO.getCollaborators();
        if ( list2 != null ) {
            project.setCollaborators( new ArrayList<User>( list2 ) );
        }
        else {
            project.setCollaborators( null );
        }
        project.setDate( projectDTO.getDate() );
        project.setDateDebut( projectDTO.getDateDebut() );
        project.setDateFin( projectDTO.getDateFin() );
        project.setDescription( projectDTO.getDescription() );

        return project;
    }

    @Override
    public List<ProjectDTO> toDTOs(List<Project> projects) {
        if ( projects == null ) {
            return null;
        }

        List<ProjectDTO> list = new ArrayList<ProjectDTO>( projects.size() );
        for ( Project project : projects ) {
            list.add( toDTO( project ) );
        }

        return list;
    }

    @Override
    public List<Project> toDOs(List<ProjectDTO> projectDTOs) {
        if ( projectDTOs == null ) {
            return null;
        }

        List<Project> list = new ArrayList<Project>( projectDTOs.size() );
        for ( ProjectDTO projectDTO : projectDTOs ) {
            list.add( toDO( projectDTO ) );
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
