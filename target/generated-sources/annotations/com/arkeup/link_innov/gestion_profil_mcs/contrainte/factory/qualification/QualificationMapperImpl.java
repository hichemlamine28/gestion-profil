package com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.qualification;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Qualification;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.QualificationDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class QualificationMapperImpl implements QualificationMapper {

    @Override
    public QualificationDTO qualificationToQualificationDTO(Qualification qualification) {
        if ( qualification == null ) {
            return null;
        }

        QualificationDTO qualificationDTO = new QualificationDTO();

        qualificationDTO.setUserId( qualification.userId );
        qualificationDTO.setId( qualification.id );
        qualificationDTO.setInstitution( qualification.institution );
        qualificationDTO.setDegree( qualification.degree );
        qualificationDTO.setField( qualification.field );
        qualificationDTO.setResult( qualification.result );
        qualificationDTO.setStartDate( qualification.startDate );
        qualificationDTO.setEndDate( qualification.endDate );
        qualificationDTO.setDescription( qualification.description );
        qualificationDTO.setCreateDate( qualification.createDate );
        qualificationDTO.setModifDate( qualification.modifDate );

        return qualificationDTO;
    }

    @Override
    public Qualification qualificationDTOToQualification(QualificationDTO qualificationDTO) {
        if ( qualificationDTO == null ) {
            return null;
        }

        Qualification qualification = new Qualification();

        qualification.id = qualificationDTO.getId();
        qualification.institution = qualificationDTO.getInstitution();
        qualification.degree = qualificationDTO.getDegree();
        qualification.field = qualificationDTO.getField();
        qualification.result = qualificationDTO.getResult();
        qualification.startDate = qualificationDTO.getStartDate();
        qualification.endDate = qualificationDTO.getEndDate();
        qualification.description = qualificationDTO.getDescription();
        qualification.userId = qualificationDTO.getUserId();
        qualification.createDate = qualificationDTO.getCreateDate();
        qualification.modifDate = qualificationDTO.getModifDate();

        return qualification;
    }

    @Override
    public List<QualificationDTO> qualificationToQualificationDTOs(List<Qualification> qualifications) {
        if ( qualifications == null ) {
            return null;
        }

        List<QualificationDTO> list = new ArrayList<QualificationDTO>( qualifications.size() );
        for ( Qualification qualification : qualifications ) {
            list.add( qualificationToQualificationDTO( qualification ) );
        }

        return list;
    }

    @Override
    public List<Qualification> qualificationDTOToQualifications(List<QualificationDTO> qualificationDTOs) {
        if ( qualificationDTOs == null ) {
            return null;
        }

        List<Qualification> list = new ArrayList<Qualification>( qualificationDTOs.size() );
        for ( QualificationDTO qualificationDTO : qualificationDTOs ) {
            list.add( qualificationDTOToQualification( qualificationDTO ) );
        }

        return list;
    }
}
