package com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.parcours;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Category;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Corporation;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Parcours;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.ActivitySector;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.Classification;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.Keyword;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.NumberOfEmployee;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.ThematicArea;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.Typology;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.CategoryDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.CorporationDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ParcoursDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.referential.ActivitySectorDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.referential.ClassificationDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.referential.KeywordDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.referential.NumberOfEmployeeDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.referential.ThematicAreaDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.referential.TypologyDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class ParcoursMapperImpl implements ParcoursMapper {

    @Override
    public ParcoursDTO parcoursToParcoursDTO(Parcours parcours) {
        if ( parcours == null ) {
            return null;
        }

        ParcoursDTO parcoursDTO = new ParcoursDTO();

        parcoursDTO.setId( parcours.getId() );
        parcoursDTO.setProfil( parcours.getProfil() );
        parcoursDTO.setOccupation( parcours.getOccupation() );
        parcoursDTO.setZipCode( parcours.getZipCode() );
        parcoursDTO.setLieu( parcours.getLieu() );
        parcoursDTO.setDescription( parcours.getDescription() );
        parcoursDTO.setPhoto( parcours.getPhoto() );
        parcoursDTO.setStartDate( parcours.getStartDate() );
        parcoursDTO.setEndDate( parcours.getEndDate() );
        parcoursDTO.setCreateDate( parcours.getCreateDate() );
        parcoursDTO.setModifDate( parcours.getModifDate() );
        parcoursDTO.setCompany( corporationToCorporationDTO( parcours.getCompany() ) );
        parcoursDTO.setCategory( categoryToCategoryDTO( parcours.getCategory() ) );

        return parcoursDTO;
    }

    @Override
    public List<ParcoursDTO> listParcoursToListParcoursDTO(List<Parcours> parcours) {
        if ( parcours == null ) {
            return null;
        }

        List<ParcoursDTO> list = new ArrayList<ParcoursDTO>( parcours.size() );
        for ( Parcours parcours1 : parcours ) {
            list.add( parcoursToParcoursDTO( parcours1 ) );
        }

        return list;
    }

    @Override
    public List<Parcours> listParcoursDTOToListParcours(List<ParcoursDTO> parcoursDTO) {
        if ( parcoursDTO == null ) {
            return null;
        }

        List<Parcours> list = new ArrayList<Parcours>( parcoursDTO.size() );
        for ( ParcoursDTO parcoursDTO1 : parcoursDTO ) {
            list.add( parcoursDTOToParcours( parcoursDTO1 ) );
        }

        return list;
    }

    protected CategoryDTO categoryToCategoryDTO(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setId( category.getId() );
        categoryDTO.setName( category.getName() );

        return categoryDTO;
    }

    protected KeywordDTO keywordToKeywordDTO(Keyword keyword) {
        if ( keyword == null ) {
            return null;
        }

        KeywordDTO keywordDTO = new KeywordDTO();

        keywordDTO.setId( keyword.getId() );
        keywordDTO.setLabel( keyword.getLabel() );

        return keywordDTO;
    }

    protected List<KeywordDTO> keywordListToKeywordDTOList(List<Keyword> list) {
        if ( list == null ) {
            return null;
        }

        List<KeywordDTO> list1 = new ArrayList<KeywordDTO>( list.size() );
        for ( Keyword keyword : list ) {
            list1.add( keywordToKeywordDTO( keyword ) );
        }

        return list1;
    }

    protected NumberOfEmployeeDTO numberOfEmployeeToNumberOfEmployeeDTO(NumberOfEmployee numberOfEmployee) {
        if ( numberOfEmployee == null ) {
            return null;
        }

        NumberOfEmployeeDTO numberOfEmployeeDTO = new NumberOfEmployeeDTO();

        numberOfEmployeeDTO.setId( numberOfEmployee.getId() );
        numberOfEmployeeDTO.setLabel( numberOfEmployee.getLabel() );
        numberOfEmployeeDTO.setCode( numberOfEmployee.getCode() );

        return numberOfEmployeeDTO;
    }

    protected ActivitySectorDTO activitySectorToActivitySectorDTO(ActivitySector activitySector) {
        if ( activitySector == null ) {
            return null;
        }

        ActivitySectorDTO activitySectorDTO = new ActivitySectorDTO();

        activitySectorDTO.setId( activitySector.getId() );
        activitySectorDTO.setLabel( activitySector.getLabel() );

        return activitySectorDTO;
    }

    protected TypologyDTO typologyToTypologyDTO(Typology typology) {
        if ( typology == null ) {
            return null;
        }

        TypologyDTO typologyDTO = new TypologyDTO();

        typologyDTO.setId( typology.getId() );
        typologyDTO.setLabel( typology.getLabel() );

        return typologyDTO;
    }

    protected ClassificationDTO classificationToClassificationDTO(Classification classification) {
        if ( classification == null ) {
            return null;
        }

        ClassificationDTO classificationDTO = new ClassificationDTO();

        classificationDTO.setId( classification.getId() );
        classificationDTO.setLabel( classification.getLabel() );

        return classificationDTO;
    }

    protected ThematicAreaDTO thematicAreaToThematicAreaDTO(ThematicArea thematicArea) {
        if ( thematicArea == null ) {
            return null;
        }

        ThematicAreaDTO thematicAreaDTO = new ThematicAreaDTO();

        thematicAreaDTO.setId( thematicArea.getId() );
        thematicAreaDTO.setLabel( thematicArea.getLabel() );

        return thematicAreaDTO;
    }

    protected CorporationDTO corporationToCorporationDTO(Corporation corporation) {
        if ( corporation == null ) {
            return null;
        }

        CorporationDTO corporationDTO = new CorporationDTO();

        corporationDTO.setId( corporation.getId() );
        List<String> list = corporation.getAdmins();
        if ( list != null ) {
            corporationDTO.setAdmins( new ArrayList<String>( list ) );
        }
        else {
            corporationDTO.setAdmins( null );
        }
        corporationDTO.setSuperAdmin( corporation.getSuperAdmin() );
        corporationDTO.setType( categoryToCategoryDTO( corporation.getType() ) );
        corporationDTO.setDescription( corporation.getDescription() );
        corporationDTO.setKeywords( keywordListToKeywordDTOList( corporation.getKeywords() ) );
        corporationDTO.setEmployeesNumber( numberOfEmployeeToNumberOfEmployeeDTO( corporation.getEmployeesNumber() ) );
        corporationDTO.setActivityArea( activitySectorToActivitySectorDTO( corporation.getActivityArea() ) );
        corporationDTO.setSiret( corporation.getSiret() );
        corporationDTO.setName( corporation.getName() );
        corporationDTO.setStructure( corporation.getStructure() );
        corporationDTO.setTypology( typologyToTypologyDTO( corporation.getTypology() ) );
        corporationDTO.setClassification( classificationToClassificationDTO( corporation.getClassification() ) );
        corporationDTO.setAddress( corporation.getAddress() );
        corporationDTO.setWebSite( corporation.getWebSite() );
        corporationDTO.setPhoto( corporation.getPhoto() );
        corporationDTO.setThematicArea( thematicAreaToThematicAreaDTO( corporation.getThematicArea() ) );
        corporationDTO.setCertificationQuota( corporation.getCertificationQuota() );
        corporationDTO.setPremiumAccountQuota( corporation.getPremiumAccountQuota() );
        List<String> list2 = corporation.getAdminsChatId();
        if ( list2 != null ) {
            corporationDTO.setAdminsChatId( new ArrayList<String>( list2 ) );
        }
        else {
            corporationDTO.setAdminsChatId( null );
        }
        corporationDTO.setCreateDate( corporation.getCreateDate() );
        corporationDTO.setMediaId( corporation.getMediaId() );
        corporationDTO.setBackgroundId( corporation.getBackgroundId() );
        corporationDTO.setHasMedia( corporation.getHasMedia() );
        corporationDTO.setHasBackground( corporation.getHasBackground() );

        return corporationDTO;
    }

    protected Category categoryDTOToCategory(CategoryDTO categoryDTO) {
        if ( categoryDTO == null ) {
            return null;
        }

        Category category = new Category();

        category.setId( categoryDTO.getId() );
        category.setName( categoryDTO.getName() );

        return category;
    }

    protected Keyword keywordDTOToKeyword(KeywordDTO keywordDTO) {
        if ( keywordDTO == null ) {
            return null;
        }

        Keyword keyword = new Keyword();

        keyword.setId( keywordDTO.getId() );
        keyword.setLabel( keywordDTO.getLabel() );

        return keyword;
    }

    protected List<Keyword> keywordDTOListToKeywordList(List<KeywordDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Keyword> list1 = new ArrayList<Keyword>( list.size() );
        for ( KeywordDTO keywordDTO : list ) {
            list1.add( keywordDTOToKeyword( keywordDTO ) );
        }

        return list1;
    }

    protected NumberOfEmployee numberOfEmployeeDTOToNumberOfEmployee(NumberOfEmployeeDTO numberOfEmployeeDTO) {
        if ( numberOfEmployeeDTO == null ) {
            return null;
        }

        NumberOfEmployee numberOfEmployee = new NumberOfEmployee();

        numberOfEmployee.setId( numberOfEmployeeDTO.getId() );
        numberOfEmployee.setLabel( numberOfEmployeeDTO.getLabel() );
        numberOfEmployee.setCode( numberOfEmployeeDTO.getCode() );

        return numberOfEmployee;
    }

    protected ActivitySector activitySectorDTOToActivitySector(ActivitySectorDTO activitySectorDTO) {
        if ( activitySectorDTO == null ) {
            return null;
        }

        ActivitySector activitySector = new ActivitySector();

        activitySector.setId( activitySectorDTO.getId() );
        activitySector.setLabel( activitySectorDTO.getLabel() );

        return activitySector;
    }

    protected Typology typologyDTOToTypology(TypologyDTO typologyDTO) {
        if ( typologyDTO == null ) {
            return null;
        }

        Typology typology = new Typology();

        typology.setId( typologyDTO.getId() );
        typology.setLabel( typologyDTO.getLabel() );

        return typology;
    }

    protected Classification classificationDTOToClassification(ClassificationDTO classificationDTO) {
        if ( classificationDTO == null ) {
            return null;
        }

        Classification classification = new Classification();

        classification.setId( classificationDTO.getId() );
        classification.setLabel( classificationDTO.getLabel() );

        return classification;
    }

    protected ThematicArea thematicAreaDTOToThematicArea(ThematicAreaDTO thematicAreaDTO) {
        if ( thematicAreaDTO == null ) {
            return null;
        }

        ThematicArea thematicArea = new ThematicArea();

        thematicArea.setId( thematicAreaDTO.getId() );
        thematicArea.setLabel( thematicAreaDTO.getLabel() );

        return thematicArea;
    }

    protected Corporation corporationDTOToCorporation(CorporationDTO corporationDTO) {
        if ( corporationDTO == null ) {
            return null;
        }

        Corporation corporation = new Corporation();

        corporation.setId( corporationDTO.getId() );
        List<String> list = corporationDTO.getAdmins();
        if ( list != null ) {
            corporation.setAdmins( new ArrayList<String>( list ) );
        }
        else {
            corporation.setAdmins( null );
        }
        corporation.setSuperAdmin( corporationDTO.getSuperAdmin() );
        corporation.setType( categoryDTOToCategory( corporationDTO.getType() ) );
        corporation.setDescription( corporationDTO.getDescription() );
        corporation.setKeywords( keywordDTOListToKeywordList( corporationDTO.getKeywords() ) );
        corporation.setEmployeesNumber( numberOfEmployeeDTOToNumberOfEmployee( corporationDTO.getEmployeesNumber() ) );
        corporation.setActivityArea( activitySectorDTOToActivitySector( corporationDTO.getActivityArea() ) );
        corporation.setSiret( corporationDTO.getSiret() );
        corporation.setName( corporationDTO.getName() );
        corporation.setStructure( corporationDTO.getStructure() );
        corporation.setTypology( typologyDTOToTypology( corporationDTO.getTypology() ) );
        corporation.setClassification( classificationDTOToClassification( corporationDTO.getClassification() ) );
        corporation.setAddress( corporationDTO.getAddress() );
        corporation.setWebSite( corporationDTO.getWebSite() );
        corporation.setPhoto( corporationDTO.getPhoto() );
        corporation.setCreateDate( corporationDTO.getCreateDate() );
        corporation.setThematicArea( thematicAreaDTOToThematicArea( corporationDTO.getThematicArea() ) );
        corporation.setCertificationQuota( corporationDTO.getCertificationQuota() );
        corporation.setPremiumAccountQuota( corporationDTO.getPremiumAccountQuota() );
        List<String> list2 = corporationDTO.getAdminsChatId();
        if ( list2 != null ) {
            corporation.setAdminsChatId( new ArrayList<String>( list2 ) );
        }
        else {
            corporation.setAdminsChatId( null );
        }
        corporation.setMediaId( corporationDTO.getMediaId() );
        corporation.setBackgroundId( corporationDTO.getBackgroundId() );
        corporation.setHasMedia( corporationDTO.getHasMedia() );
        corporation.setHasBackground( corporationDTO.getHasBackground() );

        return corporation;
    }

    protected Parcours parcoursDTOToParcours(ParcoursDTO parcoursDTO) {
        if ( parcoursDTO == null ) {
            return null;
        }

        Parcours parcours = new Parcours();

        parcours.setId( parcoursDTO.getId() );
        parcours.setProfil( parcoursDTO.getProfil() );
        parcours.setOccupation( parcoursDTO.getOccupation() );
        parcours.setZipCode( parcoursDTO.getZipCode() );
        parcours.setLieu( parcoursDTO.getLieu() );
        parcours.setDescription( parcoursDTO.getDescription() );
        parcours.setPhoto( parcoursDTO.getPhoto() );
        parcours.setStartDate( parcoursDTO.getStartDate() );
        parcours.setEndDate( parcoursDTO.getEndDate() );
        parcours.setCreateDate( parcoursDTO.getCreateDate() );
        parcours.setModifDate( parcoursDTO.getModifDate() );
        parcours.setCompany( corporationDTOToCorporation( parcoursDTO.getCompany() ) );
        parcours.setCategory( categoryDTOToCategory( parcoursDTO.getCategory() ) );

        return parcours;
    }
}
