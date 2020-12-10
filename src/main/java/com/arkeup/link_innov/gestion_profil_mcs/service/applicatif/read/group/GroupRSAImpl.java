package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.group;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.commun.PageableFactory;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Profil;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.CustomPageDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.utils.HelpPage;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ErrorsEnum;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.FunctionalLimitExceededException;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ObjetNotFoundException;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.group.GroupMapper;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Group;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.AdminsDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.GroupConsultationDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.GroupDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.GroupsDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.MemberNumbersDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.PostDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ProfilDTO;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.group_consultation.GroupConsultationRSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.profil.ProfilRSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.businessdelegate.GestionDroitMCS;
import com.arkeup.link_innov.gestion_profil_mcs.service.businessdelegate.ReseauxSociauxMCS;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.group.GroupRSM;

/**
 * @author bona
 */
@Service
public class GroupRSAImpl implements GroupRSA {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ProfilRSA profilRSA;

	@Autowired
	GroupRSM groupRSM;

	@Autowired
	GroupMapper groupMapper;

	@Autowired
	private ReseauxSociauxMCS reseauxSociauxMCS;

	@Autowired
	GestionDroitMCS gestionDroitMCS;

	@Autowired
	private GroupConsultationRSA groupConsultationRSA;

	@Autowired
	private PageableFactory<Group> pageableFactory;

	@Override
	public GroupsDTO listGroupByOwner(String username, Pageable pageable) {

		GroupsDTO groupsDTO = checkPageSize(pageable.getPageSize());
		ProfilDTO user = profilRSA.getProfil(username);

		if (user.isError()) {
			groupsDTO.setError(true);
			groupsDTO.setErrorCode("ERR_MCS_PROFIL_0007");
			groupsDTO.setErrorMessage(ErrorsEnum.ERR_MCS_USER.getErrorMessage());
			return groupsDTO;
		}

		Page<Group> result = groupRSM.findAllByOwnerId(username, pageable);

		groupsDTO.setGroupDTOs(groupMapper.groupPageToGroupDtoPage(result, pageable));
		groupsDTO.setError(false);
		groupsDTO.setMessage("Group list");

		if (groupsDTO.getGroupDTOs() != null) {
			for (GroupDTO groupDTO : groupsDTO.getGroupDTOs().getContent()) {
				groupDTO.setMemberCount(reseauxSociauxMCS.getGroupMembersCount(groupDTO.getId()).getNbMembers());
				groupDTO.setNumberNewPost(getNumberNewPost(groupDTO.getId(), username));
			}
		}

		return groupsDTO;
	}

	@Override
	public GroupsDTO listGroupsIsMemberOrIsAdmin(String username, Pageable pageable) {
		List<String> groupIdsIsMember = reseauxSociauxMCS.getGroupsUserIsMemberByUserName(username);

		GroupsDTO groupsDTO = checkPageSize(pageable.getPageSize());
		ProfilDTO user = profilRSA.getProfil(username);

		if (user.isError()) {
			groupsDTO.setError(true);
			groupsDTO.setErrorCode("ERR_MCS_PROFIL_0007");
			groupsDTO.setErrorMessage(ErrorsEnum.ERR_MCS_USER.getErrorMessage());
			return groupsDTO;
		}

		List<Group> groupList = groupRSM.findAllByIdIn(groupIdsIsMember);

		List<GroupDTO> groupDTOList = groupMapper.listGroupToListGroupDTO(groupList);

		List<GroupDTO> groupDTOS = new ArrayList<>();

		for (GroupDTO groupDTO : groupDTOList) {
			groupDTO.setMemberCount(reseauxSociauxMCS.getGroupMembersCount(groupDTO.getId()).getNbMembers());
			groupDTO.setNumberNewPost(getNumberNewPost(groupDTO.getId(), username));
			groupDTOS.add(groupDTO);
		}

		groupDTOS.sort((groupDTO1, groupDTO2) -> groupDTO2.getNumberNewPost().compareTo(groupDTO1.getNumberNewPost()));

		int start = (int) pageable.getOffset();
		int end = (start + pageable.getPageSize()) > groupDTOS.size() ? groupDTOS.size()
				: (start + pageable.getPageSize());
		Page<GroupDTO> result = new PageImpl<>(groupDTOS.subList(start, end), pageable, groupDTOS.size());

		groupsDTO.setGroupDTOs(result);
		groupsDTO.setError(false);
		groupsDTO.setMessage("Group list");

		return groupsDTO;
	}

	private Integer getNumberNewPost(String groupId, String userName) {
		List<PostDTO> postDTOS = reseauxSociauxMCS.getListGroupPost(groupId);
		GroupConsultationDTO groupConsultationDTO = groupConsultationRSA.findByGroupIdAndUserGroupId(groupId, userName);
		if (groupConsultationDTO != null) {

			return (int) (postDTOS.stream().filter(postDTO -> postDTO.getCreationDate()
					.compareTo(groupConsultationDTO.getGroupConsultationLastDate()) > 0).count());
		}
		return postDTOS.size();
	}

	@Override
	public GroupDTO getGroupById(String groupId) {
		Group group = groupRSM.findByIdGroup(groupId);

		if (group == null) {
			throw new ObjetNotFoundException(new GroupDTO(), ErrorsEnum.ERR_MCS_PROFIL_0079);
		}

		GroupDTO groupDTO = groupMapper.groupToGroupDTO(group);

		if (StringUtils.isEmpty(groupDTO.getBackgroundId())) {
			groupDTO.setBackgroundId(UUID.randomUUID().toString());
		}

		if (StringUtils.isEmpty(groupDTO.getMediaId())) {
			groupDTO.setMediaId(UUID.randomUUID().toString());
		}

		try {
			MemberNumbersDTO memberNumbersDTO = reseauxSociauxMCS.getGroupMembersCount(groupId);
			groupDTO.setMemberCount(memberNumbersDTO.getNbMembers());

			// Fetch admins
			List<AdminsDTO> adminsDTO = this.gestionDroitMCS.getAdministredAdmins(groupId, "GROUP", false);
			groupDTO.setAdmins(adminsDTO.stream().map(AdminsDTO::getUserId).collect(Collectors.toList()));
		} catch (Exception e) {
			log.info("error feign: ", e);
		}

		return groupDTO;
	}

	@Override
	public GroupsDTO findAllByNameAndOwnerId(String nameFilter, String ownerId, Pageable pageable) {
		GroupsDTO groupsDTO = checkPageSize(pageable.getPageSize());
		if (StringUtils.isNotEmpty(ownerId)) {
			ProfilDTO owner = profilRSA.getProfil(ownerId);
			if (owner.isError()) {
				throw new ObjetNotFoundException(owner, ErrorsEnum.ERR_MCS_PROFIL_0007);
			}
		}
		List<String> groupIds = reseauxSociauxMCS.getGroupsUserIsMember();
		Page<Group> result = groupRSM.findAllByNameAndOwnerId(nameFilter, ownerId, groupIds, pageable);
		groupsDTO.setGroupDTOs(groupMapper.groupPageToGroupDtoPage(result, pageable));
		groupsDTO.setError(false);
		groupsDTO.setMessage("Group list");

		if (groupsDTO.getGroupDTOs() != null) {
			for (GroupDTO groupDTO : groupsDTO.getGroupDTOs().getContent()) {
				groupDTO.setMemberCount(reseauxSociauxMCS.getGroupMembersCount(groupDTO.getId()).getNbMembers());
			}
		}

		return groupsDTO;
	}

	public GroupsDTO checkPageSize(Integer pageSize) {
		GroupsDTO result = new GroupsDTO();
		if (pageSize > 100) {
			throw new FunctionalLimitExceededException(result, ErrorsEnum.ERR_MCS_MAX_PAGE_SIZE);
		}
		return result;
	}

	@Override
	public GroupsDTO getFilteredGroups(String username, String filter, String stringQuery, Pageable pageable) {
		return listGroupByOwnerFiltered(username, filter, stringQuery, pageable);
	}

	private GroupsDTO listGroupByOwnerFiltered(String username, String filter, String stringQuery, Pageable pageable) {

		GroupsDTO groupsDTO = checkPageSize(pageable.getPageSize());
		if (StringUtils.isNotEmpty(username)) {
			ProfilDTO owner = profilRSA.getProfil(username);
			if (owner.isError()) {
				throw new ObjetNotFoundException(owner, ErrorsEnum.ERR_MCS_PROFIL_0007);
			}
		}
		List<String> groupIds = reseauxSociauxMCS.getGroupsUserIsMemberByUserName(username);

		if (stringQuery == null) {
			stringQuery = StringUtils.EMPTY;
		}
		Page<Group> result;
		switch (filter) {
		case "CREATION_DATE_ASC":
			result = groupRSM.findAllByIdInAndNameIgnoreCaseContainingOrderByCreateDateAsc(groupIds, stringQuery,
					pageable);
			break;
		case "GROUPNAME_ASC":
			result = groupRSM.findAllByIdInAndNameIgnoreCaseContainingOrderByNameAsc(groupIds, stringQuery, pageable);
			break;
		case "GROUPNAME_DESC":
			result = groupRSM.findAllByIdInAndNameIgnoreCaseContainingOrderByNameDesc(groupIds, stringQuery, pageable);
			break;
		default:
			result = groupRSM.findAllByIdInAndNameIgnoreCaseContaining(groupIds, stringQuery, pageable);
		}

		groupsDTO.setGroupDTOs(groupMapper.groupPageToGroupDtoPage(result, pageable));
		groupsDTO.setError(false);
		groupsDTO.setMessage("Group list");

		if (groupsDTO.getGroupDTOs() != null) {
			for (GroupDTO groupDTO : groupsDTO.getGroupDTOs().getContent()) {
				groupDTO.setMemberCount(reseauxSociauxMCS.getGroupMembersCount(groupDTO.getId()).getNbMembers());
				groupDTO.setNumberNewPost(getNumberNewPost(groupDTO.getId(), username));
			}
		}
		return groupsDTO;
	}

	@Override
	public GroupsDTO listGroupIsAdmin(String username, Pageable pageable) {

		GroupsDTO groupsDTO = checkPageSize(pageable.getPageSize());
		ProfilDTO user = profilRSA.getProfil(username);

		if (user.isError()) {
			groupsDTO.setError(true);
			groupsDTO.setErrorCode("ERR_MCS_PROFIL_0007");
			groupsDTO.setErrorMessage(ErrorsEnum.ERR_MCS_USER.getErrorMessage());
			return groupsDTO;
		}
		List<String> groupIds = gestionDroitMCS.listGroupsUserIsAdmin(username);

		Page<Group> result = groupRSM.findAllByIdInAndNameIgnoreCaseContainingOrderByNameAsc(groupIds, "", pageable);

		groupsDTO.setGroupDTOs(groupMapper.groupPageToGroupDtoPage(result, pageable));

		if (groupsDTO.getGroupDTOs() != null) {
			groupsDTO.getGroupDTOs().getContent().stream().map((groupDTO) -> {
				groupDTO.setMemberCount(reseauxSociauxMCS.getGroupMembersCount(groupDTO.getId()).getNbMembers());
				return groupDTO;
			}).forEachOrdered((groupDTO) -> {
				groupDTO.setNumberNewPost(getNumberNewPost(groupDTO.getId(), username));
			});
		}

		groupsDTO.setError(false);
		groupsDTO.setMessage("Group list");

		return groupsDTO;
	}

	@Override
	public CustomPageDTO<GroupDTO> getPaginatedGroupes(List<String> groupIds, Pageable pageable) {
		CustomPageDTO<GroupDTO> customPageDTO = new CustomPageDTO<>();
		Page<Group> groups = groupRSM.getPaginatedGroupes(groupIds, pageable);
		HelpPage<Group> helpPageProfil = pageableFactory.pageToHelpPage(groups);
		HelpPage<GroupDTO> helpPageProfilDTO = groupMapper.helpPageGroupToHelpPageGroupDTOs(helpPageProfil);
		customPageDTO.setPageDTOs(helpPageProfilDTO);

		return customPageDTO;
	}

}
