package com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.profil;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Category;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Corporation;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Profil;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.ActivitySector;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.Classification;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.Keyword;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.NumberOfEmployee;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.ThematicArea;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.Typology;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.CategoryDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.CorporationDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ProfilDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.businessdelegate.ReseauSocialUserDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.reasearchgate.ProfilResearchGate;
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
public class ProfilMapperImpl implements ProfilMapper {

    @Override
    public ProfilDTO profilToProfilDTO(Profil profil) {
        if ( profil == null ) {
            return null;
        }

        ProfilDTO profilDTO = new ProfilDTO();

        profilDTO.setError( profil.isError() );
        profilDTO.setInfo( profil.isInfo() );
        profilDTO.setWarning( profil.isWarning() );
        profilDTO.setErrorCode( profil.getErrorCode() );
        profilDTO.setErrorMessage( profil.getErrorMessage() );
        profilDTO.setMessage( profil.getMessage() );
        profilDTO.setUuid( profil.getUuid() );
        profilDTO.setId( profil.getId() );
        profilDTO.setUsername( profil.getUsername() );
        profilDTO.setFirstname( profil.getFirstname() );
        profilDTO.setLastname( profil.getLastname() );
        profilDTO.setOccupation( profil.getOccupation() );
        profilDTO.setZipCode( profil.getZipCode() );
        profilDTO.setCity( profil.getCity() );
        profilDTO.setResume( profil.getResume() );
        profilDTO.setPhoto( profil.getPhoto() );
        profilDTO.setCompany( corporationToCorporationDTO( profil.getCompany() ) );
        profilDTO.setCategory( categoryToCategoryDTO( profil.getCategory() ) );
        profilDTO.setWebSite( profil.getWebSite() );
        profilDTO.setPhoneNumber( profil.getPhoneNumber() );
        profilDTO.setEmail( profil.getEmail() );
        profilDTO.setChatId( profil.getChatId() );
        profilDTO.setMale( profil.getMale() );
        profilDTO.setIsProfileInitialised( profil.getIsProfileInitialised() );
        profilDTO.setDownloadStatus( profil.getDownloadStatus() );
        profilDTO.setCreationDate( profil.getCreationDate() );
        profilDTO.setActivityArea( activitySectorListToActivitySectorDTOList( profil.getActivityArea() ) );
        profilDTO.setDomaine( profil.getDomaine() );
        List<String> list1 = profil.getLangages();
        if ( list1 != null ) {
            profilDTO.setLangages( new ArrayList<String>( list1 ) );
        }
        else {
            profilDTO.setLangages( null );
        }
        profilDTO.setOnBording( profil.getOnBording() );
        profilDTO.setKeyValidateProfil( profil.getKeyValidateProfil() );
        profilDTO.setExpirationKeyValidateProfil( profil.getExpirationKeyValidateProfil() );
        profilDTO.setIsActiveAccount( profil.getIsActiveAccount() );
        profilDTO.setMediaId( profil.getMediaId() );
        profilDTO.setBackgroundId( profil.getBackgroundId() );
        profilDTO.setExportId( profil.getExportId() );
        profilDTO.setHasMedia( profil.getHasMedia() );
        profilDTO.setHasBackground( profil.getHasBackground() );

        return profilDTO;
    }

    @Override
    public Profil profilDTOToProfil(ProfilDTO profilDTO) {
        if ( profilDTO == null ) {
            return null;
        }

        Profil profil = new Profil();

        profil.setError( profilDTO.isError() );
        profil.setInfo( profilDTO.isInfo() );
        profil.setWarning( profilDTO.isWarning() );
        profil.setErrorCode( profilDTO.getErrorCode() );
        profil.setErrorMessage( profilDTO.getErrorMessage() );
        profil.setMessage( profilDTO.getMessage() );
        profil.setUuid( profilDTO.getUuid() );
        profil.setId( profilDTO.getId() );
        profil.setUsername( profilDTO.getUsername() );
        profil.setChatId( profilDTO.getChatId() );
        profil.setFirstname( profilDTO.getFirstname() );
        profil.setLastname( profilDTO.getLastname() );
        profil.setOccupation( profilDTO.getOccupation() );
        profil.setZipCode( profilDTO.getZipCode() );
        profil.setCity( profilDTO.getCity() );
        profil.setWebSite( profilDTO.getWebSite() );
        profil.setPhoneNumber( profilDTO.getPhoneNumber() );
        profil.setEmail( profilDTO.getEmail() );
        profil.setResume( profilDTO.getResume() );
        profil.setIsProfileInitialised( profilDTO.getIsProfileInitialised() );
        profil.setPhoto( profilDTO.getPhoto() );
        profil.setCompany( corporationDTOToCorporation( profilDTO.getCompany() ) );
        profil.setCategory( categoryDTOToCategory( profilDTO.getCategory() ) );
        profil.setMale( profilDTO.getMale() );
        profil.setMediaId( profilDTO.getMediaId() );
        profil.setBackgroundId( profilDTO.getBackgroundId() );
        profil.setExportId( profilDTO.getExportId() );
        profil.setDownloadStatus( profilDTO.getDownloadStatus() );
        profil.setCreationDate( profilDTO.getCreationDate() );
        profil.setActivityArea( activitySectorDTOListToActivitySectorList( profilDTO.getActivityArea() ) );
        profil.setDomaine( profilDTO.getDomaine() );
        List<String> list1 = profilDTO.getLangages();
        if ( list1 != null ) {
            profil.setLangages( new ArrayList<String>( list1 ) );
        }
        else {
            profil.setLangages( null );
        }
        profil.setOnBording( profilDTO.getOnBording() );
        profil.setKeyValidateProfil( profilDTO.getKeyValidateProfil() );
        profil.setExpirationKeyValidateProfil( profilDTO.getExpirationKeyValidateProfil() );
        profil.setIsActiveAccount( profilDTO.getIsActiveAccount() );
        profil.setHasMedia( profilDTO.getHasMedia() );
        profil.setHasBackground( profilDTO.getHasBackground() );

        return profil;
    }

    @Override
    public List<ProfilDTO> toDTOs(List<Profil> profils) {
        if ( profils == null ) {
            return null;
        }

        List<ProfilDTO> list = new ArrayList<ProfilDTO>( profils.size() );
        for ( Profil profil : profils ) {
            list.add( profilToProfilDTO( profil ) );
        }

        return list;
    }

    @Override
    public List<Profil> toDOs(List<ProfilDTO> profilDTOs) {
        if ( profilDTOs == null ) {
            return null;
        }

        List<Profil> list = new ArrayList<Profil>( profilDTOs.size() );
        for ( ProfilDTO profilDTO : profilDTOs ) {
            list.add( profilDTOToProfil( profilDTO ) );
        }

        return list;
    }

    @Override
    public List<ReseauSocialUserDTO> profilsToContactDTOs(List<Profil> profils) {
        if ( profils == null ) {
            return null;
        }

        List<ReseauSocialUserDTO> list = new ArrayList<ReseauSocialUserDTO>( profils.size() );
        for ( Profil profil : profils ) {
            list.add( profilToReseauSocialUserDTO( profil ) );
        }

        return list;
    }

    @Override
    public ProfilDTO profilResearchGateToProfilDTO(ProfilResearchGate profilResearchGate) {
        if ( profilResearchGate == null ) {
            return null;
        }

        ProfilDTO profilDTO = new ProfilDTO();

        profilDTO.setFirstname( profilResearchGate.getFirstname() );
        profilDTO.setLastname( profilResearchGate.getLastname() );
        profilDTO.setEmail( profilResearchGate.getEmail() );

        profilDTO.setDownloadStatus( null );
        profilDTO.setMale( profilResearchGate.getBooleanGender() );

        return profilDTO;
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

    protected List<ActivitySectorDTO> activitySectorListToActivitySectorDTOList(List<ActivitySector> list) {
        if ( list == null ) {
            return null;
        }

        List<ActivitySectorDTO> list1 = new ArrayList<ActivitySectorDTO>( list.size() );
        for ( ActivitySector activitySector : list ) {
            list1.add( activitySectorToActivitySectorDTO( activitySector ) );
        }

        return list1;
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

    protected List<ActivitySector> activitySectorDTOListToActivitySectorList(List<ActivitySectorDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<ActivitySector> list1 = new ArrayList<ActivitySector>( list.size() );
        for ( ActivitySectorDTO activitySectorDTO : list ) {
            list1.add( activitySectorDTOToActivitySector( activitySectorDTO ) );
        }

        return list1;
    }

    protected ReseauSocialUserDTO profilToReseauSocialUserDTO(Profil profil) {
        if ( profil == null ) {
            return null;
        }

        ReseauSocialUserDTO reseauSocialUserDTO = new ReseauSocialUserDTO();

        reseauSocialUserDTO.setId( profil.getId() );
        if ( profil.getType() != null ) {
            reseauSocialUserDTO.setType( profil.getType().name() );
        }
        reseauSocialUserDTO.setLastConnectionDate( profil.getLastConnectionDate() );

        return reseauSocialUserDTO;
    }
}
