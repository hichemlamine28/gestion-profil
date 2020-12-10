package com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.group;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Group;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.GroupDTO;
import java.util.List;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.utils.HelpPage;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author bona
 */
@Mapper
public interface GroupMapper {

    Group groupDtoToGroup(GroupDTO groupDTO);

    GroupDTO groupToGroupDTO(Group group);

    List<GroupDTO> listGroupToListGroupDTO(List<Group> group);

    List<Group> listGroupDTOToListGroup(List<GroupDTO> groupDTOS);

    default Page<GroupDTO> groupPageToGroupDtoPage(Page<Group> group, Pageable pageable) {

        List<GroupDTO> listGroupDto = listGroupToListGroupDTO(group.getContent());
        Page<GroupDTO> groupDTOPage = new PageImpl<>(listGroupDto, pageable, group.getTotalElements());
        return groupDTOPage;
    }

    default Page<GroupDTO> listGroupToGroupDtoPage(List<Group> groups, Pageable pageable) {
        List<GroupDTO> groupDTOs = listGroupToListGroupDTO(groups);
        Page<GroupDTO> groupDTOPage = new PageImpl<>(groupDTOs, pageable, groups.size());
        return groupDTOPage;
    }

    default HelpPage<GroupDTO> helpPageGroupToHelpPageGroupDTOs(HelpPage<Group> groups){

        List<GroupDTO> groupDTOS = this.listGroupToListGroupDTO(groups.getContent());

        return new HelpPage<>(groupDTOS, groups.getPageable(), groups.getTotalElements());

    }
}
