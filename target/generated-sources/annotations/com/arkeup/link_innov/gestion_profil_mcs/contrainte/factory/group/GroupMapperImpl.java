package com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.group;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Group;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.GroupDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class GroupMapperImpl implements GroupMapper {

    @Override
    public Group groupDtoToGroup(GroupDTO groupDTO) {
        if ( groupDTO == null ) {
            return null;
        }

        Group group = new Group();

        group.setId( groupDTO.getId() );
        group.setOwnerId( groupDTO.getOwnerId() );
        group.setDescription( groupDTO.getDescription() );
        group.setBackgroundImage( groupDTO.getBackgroundImage() );
        group.setCreateDate( groupDTO.getCreateDate() );
        group.setModifDate( groupDTO.getModifDate() );
        group.setName( groupDTO.getName() );
        group.setIsPublic( groupDTO.getIsPublic() );
        group.setMediaId( groupDTO.getMediaId() );
        group.setIsMembersAllowedToPost( groupDTO.getIsMembersAllowedToPost() );
        group.setBackgroundId( groupDTO.getBackgroundId() );
        group.setIsPostValidationRequired( groupDTO.getIsPostValidationRequired() );
        group.setHasMedia( groupDTO.getHasMedia() );
        group.setHasBackground( groupDTO.getHasBackground() );

        return group;
    }

    @Override
    public GroupDTO groupToGroupDTO(Group group) {
        if ( group == null ) {
            return null;
        }

        GroupDTO groupDTO = new GroupDTO();

        groupDTO.setId( group.getId() );
        groupDTO.setOwnerId( group.getOwnerId() );
        groupDTO.setDescription( group.getDescription() );
        groupDTO.setBackgroundImage( group.getBackgroundImage() );
        groupDTO.setCreateDate( group.getCreateDate() );
        groupDTO.setModifDate( group.getModifDate() );
        groupDTO.setName( group.getName() );
        groupDTO.setIsPublic( group.getIsPublic() );
        groupDTO.setIsMembersAllowedToPost( group.getIsMembersAllowedToPost() );
        groupDTO.setIsPostValidationRequired( group.getIsPostValidationRequired() );
        groupDTO.setMediaId( group.getMediaId() );
        groupDTO.setBackgroundId( group.getBackgroundId() );
        groupDTO.setHasMedia( group.getHasMedia() );
        groupDTO.setHasBackground( group.getHasBackground() );

        return groupDTO;
    }

    @Override
    public List<GroupDTO> listGroupToListGroupDTO(List<Group> group) {
        if ( group == null ) {
            return null;
        }

        List<GroupDTO> list = new ArrayList<GroupDTO>( group.size() );
        for ( Group group1 : group ) {
            list.add( groupToGroupDTO( group1 ) );
        }

        return list;
    }

    @Override
    public List<Group> listGroupDTOToListGroup(List<GroupDTO> groupDTOS) {
        if ( groupDTOS == null ) {
            return null;
        }

        List<Group> list = new ArrayList<Group>( groupDTOS.size() );
        for ( GroupDTO groupDTO : groupDTOS ) {
            list.add( groupDtoToGroup( groupDTO ) );
        }

        return list;
    }
}
