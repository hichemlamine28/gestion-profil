package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.group;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.CustomPageDTO;
import org.springframework.data.domain.Pageable;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.GroupDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.GroupsDTO;

import java.util.List;

/**
 *
 * @author bona
 */
public interface GroupRSA {

    GroupsDTO listGroupByOwner(String username, Pageable pageable);

    GroupsDTO listGroupsIsMemberOrIsAdmin(String username, Pageable pageable);

    GroupDTO getGroupById(String groupId);

    GroupsDTO findAllByNameAndOwnerId(String nameFilter, String ownerId, Pageable pageable);

    GroupsDTO getFilteredGroups(String username, String filter, String stringQuery, Pageable pageable);

    GroupsDTO listGroupIsAdmin(String username, Pageable pageable);

    CustomPageDTO<GroupDTO> getPaginatedGroupes(List<String> groupIds, Pageable pageable);
}
