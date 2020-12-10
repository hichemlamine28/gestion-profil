package com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.recommandation;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Recommandation;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.RecommandationDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class RecommandationMapperImpl implements RecommandationMapper {

    @Override
    public RecommandationDTO recommandationToRecommandationDTO(Recommandation recommandation) {
        if ( recommandation == null ) {
            return null;
        }

        RecommandationDTO recommandationDTO = new RecommandationDTO();

        recommandationDTO.setSkillId( recommandation.getSkillId() );
        recommandationDTO.setId( recommandation.getId() );
        recommandationDTO.setRecommandationType( recommandation.getRecommandationType() );
        recommandationDTO.setSkillLabel( recommandation.getSkillLabel() );
        recommandationDTO.setSkillOwner( recommandation.getSkillOwner() );
        recommandationDTO.setRecommandationOwner( recommandation.getRecommandationOwner() );
        recommandationDTO.setRecommandationOwnerName( recommandation.getRecommandationOwnerName() );

        return recommandationDTO;
    }

    @Override
    public Recommandation recommandationDTOToRecommandation(RecommandationDTO recommandationDTO) {
        if ( recommandationDTO == null ) {
            return null;
        }

        Recommandation recommandation = new Recommandation();

        recommandation.setId( recommandationDTO.getId() );
        recommandation.setRecommandationType( recommandationDTO.getRecommandationType() );
        recommandation.setSkillId( recommandationDTO.getSkillId() );
        recommandation.setRecommandationOwner( recommandationDTO.getRecommandationOwner() );
        recommandation.setSkillLabel( recommandationDTO.getSkillLabel() );
        recommandation.setSkillOwner( recommandationDTO.getSkillOwner() );
        recommandation.setRecommandationOwnerName( recommandationDTO.getRecommandationOwnerName() );

        return recommandation;
    }

    @Override
    public List<RecommandationDTO> listParcoursToListParcoursDTO(List<Recommandation> recommandation) {
        if ( recommandation == null ) {
            return null;
        }

        List<RecommandationDTO> list = new ArrayList<RecommandationDTO>( recommandation.size() );
        for ( Recommandation recommandation1 : recommandation ) {
            list.add( recommandationToRecommandationDTO( recommandation1 ) );
        }

        return list;
    }

    @Override
    public List<Recommandation> listParcoursDTOToListParcours(List<RecommandationDTO> recommandationDTO) {
        if ( recommandationDTO == null ) {
            return null;
        }

        List<Recommandation> list = new ArrayList<Recommandation>( recommandationDTO.size() );
        for ( RecommandationDTO recommandationDTO1 : recommandationDTO ) {
            list.add( recommandationDTOToRecommandation( recommandationDTO1 ) );
        }

        return list;
    }
}
