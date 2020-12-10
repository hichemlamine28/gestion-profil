package com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.referential;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.Classification;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.referential.ClassificationDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class ClassificationMapperImpl implements ClassificationMapper {

    @Override
    public ClassificationDTO classificationToClassificationDTO(Classification classification) {
        if ( classification == null ) {
            return null;
        }

        ClassificationDTO classificationDTO = new ClassificationDTO();

        classificationDTO.setId( classification.getId() );
        classificationDTO.setLabel( classification.getLabel() );

        return classificationDTO;
    }

    @Override
    public Classification classificationDTOToClassification(ClassificationDTO classificationDTO) {
        if ( classificationDTO == null ) {
            return null;
        }

        Classification classification = new Classification();

        classification.setId( classificationDTO.getId() );
        classification.setLabel( classificationDTO.getLabel() );

        return classification;
    }

    @Override
    public List<ClassificationDTO> classificationToClassificationDTOs(List<Classification> classifications) {
        if ( classifications == null ) {
            return null;
        }

        List<ClassificationDTO> list = new ArrayList<ClassificationDTO>( classifications.size() );
        for ( Classification classification : classifications ) {
            list.add( classificationToClassificationDTO( classification ) );
        }

        return list;
    }

    @Override
    public List<Classification> classificationDTOToClassifications(List<ClassificationDTO> classificationDTOs) {
        if ( classificationDTOs == null ) {
            return null;
        }

        List<Classification> list = new ArrayList<Classification>( classificationDTOs.size() );
        for ( ClassificationDTO classificationDTO : classificationDTOs ) {
            list.add( classificationDTOToClassification( classificationDTO ) );
        }

        return list;
    }
}
