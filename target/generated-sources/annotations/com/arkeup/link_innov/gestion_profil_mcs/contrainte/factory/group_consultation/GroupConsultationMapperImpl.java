package com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.group_consultation;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.GroupConsultation;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.GroupConsultationDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class GroupConsultationMapperImpl implements GroupConsultationMapper {

    @Override
    public GroupConsultation groupConsultationDtoToGroupConsultation(GroupConsultationDTO groupConsultationDTO) {
        if ( groupConsultationDTO == null ) {
            return null;
        }

        GroupConsultation groupConsultation = new GroupConsultation();

        groupConsultation.setId( groupConsultationDTO.getId() );
        groupConsultation.setGroupId( groupConsultationDTO.getGroupId() );
        groupConsultation.setGroupConsultationLastDate( groupConsultationDTO.getGroupConsultationLastDate() );
        groupConsultation.setUserGroupId( groupConsultationDTO.getUserGroupId() );

        return groupConsultation;
    }

    @Override
    public GroupConsultationDTO groupConsultationToGroupConsultationDTO(GroupConsultation groupConsultation) {
        if ( groupConsultation == null ) {
            return null;
        }

        GroupConsultationDTO groupConsultationDTO = new GroupConsultationDTO();

        groupConsultationDTO.setId( groupConsultation.getId() );
        groupConsultationDTO.setGroupId( groupConsultation.getGroupId() );
        groupConsultationDTO.setGroupConsultationLastDate( groupConsultation.getGroupConsultationLastDate() );
        groupConsultationDTO.setUserGroupId( groupConsultation.getUserGroupId() );

        return groupConsultationDTO;
    }

    @Override
    public List<GroupConsultationDTO> listGroupConsultationToListGroupConsultationDTO(List<GroupConsultation> groupConsultation) {
        if ( groupConsultation == null ) {
            return null;
        }

        List<GroupConsultationDTO> list = new ArrayList<GroupConsultationDTO>( groupConsultation.size() );
        for ( GroupConsultation groupConsultation1 : groupConsultation ) {
            list.add( groupConsultationToGroupConsultationDTO( groupConsultation1 ) );
        }

        return list;
    }
}
