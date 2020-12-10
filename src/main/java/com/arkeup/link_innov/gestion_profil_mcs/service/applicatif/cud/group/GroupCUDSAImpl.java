package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.group;

import java.util.*;
import java.util.stream.Collectors;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.constants.AccessRightEntityType;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ErrorsEnum;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.FunctionalInvalidDataException;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.FunctionalOperationDeniedException;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ObjectSaveException;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ObjetNotFoundException;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.PermissionException;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.group.GroupMapper;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.constants.MediaType;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Group;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.businessdelegate.ActiveSubscriptionDTO;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.group.GroupRSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.profil.ProfilRSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.businessdelegate.AbonnementMCS;
import com.arkeup.link_innov.gestion_profil_mcs.service.businessdelegate.GestionDroitMCS;
import com.arkeup.link_innov.gestion_profil_mcs.service.businessdelegate.MediaMCS;
import com.arkeup.link_innov.gestion_profil_mcs.service.businessdelegate.ReseauxSociauxMCS;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.group.GroupCUDSM;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.group.GroupRSM;

/**
 * @author bona
 */
@Service
public class GroupCUDSAImpl implements GroupCUDSA {

    @Autowired
    ProfilRSA profilRSA;

    @Autowired
    GroupCUDSM groupCUDSM;

    @Autowired
    GroupRSM groupRSM;

    @Autowired
    GroupRSA groupRSA;

    @Autowired
    GroupMapper groupMapper;

    @Autowired
    ReseauxSociauxMCS reseauxSociauxMCS;

    @Autowired
    private MediaMCS mediaMCS;

    @Autowired
    private GestionDroitMCS gestionDroitMCS;

    @Autowired
    private AbonnementMCS abonnemntMCS;

    @Override
    public GroupDTO create(String username, GroupDTO groupDTO) {
        ProfilDTO user = profilRSA.getProfilInformation(username);

        if (user.isError()) {
            throw new ObjetNotFoundException(new ProfilDTO(), ErrorsEnum.ERR_MCS_PROFIL_0007);
        }

        ActiveSubscriptionDTO activeSubscriptionDTO = abonnemntMCS.permissionAccess("perm_group_create");

        if (activeSubscriptionDTO.isError()) {
            throw new PermissionException(groupDTO, ErrorsEnum.valueOf(activeSubscriptionDTO.getErrorCode()));
        }

        if (StringUtils.isNotEmpty(groupDTO.getName()) && groupRSM.isGroupExistByName(groupDTO.getName())) {
            throw new FunctionalInvalidDataException(new GroupDTO(), ErrorsEnum.ERR_MCS_PROFIL_0048);
        }

        groupDTO.setMediaId(UUID.randomUUID().toString());
        groupDTO.setBackgroundId(UUID.randomUUID().toString());
        groupDTO.setOwnerId(username);
        groupDTO.setError(false);
        groupDTO.setMessage("success creation group");
        groupDTO.setCreateDate(new Date());

        Group group = groupCUDSM.create(groupMapper.groupDtoToGroup(groupDTO));

        if (group == null) {
            throw new ObjectSaveException(groupDTO, ErrorsEnum.ERR_MCS_PROFIL_0073);
        }

        groupDTO.setId(group.getId());

        GroupDTO resultGroupDTO = reseauxSociauxMCS.createGroup(groupDTO);

        if (resultGroupDTO.isError()) {
            // Rollback if error
            this.groupCUDSM.delete(groupDTO.getId());
            throw new ObjectSaveException(groupDTO, ErrorsEnum.ERR_MCS_PROFIL_0073);
        }

        return groupDTO;
    }

    @Override
    public GroupDTO update(GroupDTO groupDTO) {
        if (StringUtils.isNotEmpty(groupDTO.getId()) && groupRSM.isGroupExistById(groupDTO.getId())) {
            if (StringUtils.isNotEmpty(groupDTO.getName()) && groupRSM.isOtherGroupExistByName(groupDTO.getName(), groupDTO.getId())) {
                throw new FunctionalInvalidDataException(new GroupDTO(), ErrorsEnum.ERR_MCS_PROFIL_0048);
            }
            if (gestionDroitMCS.isAdministredAdmin(groupDTO.getId(), "GROUP")) {
                groupDTO.setModifDate(new Date());
                if (StringUtils.isEmpty(groupDTO.getMediaId())) {
                    groupDTO.setMediaId(UUID.randomUUID().toString());
                }
                if (StringUtils.isEmpty(groupDTO.getBackgroundId())) {
                    groupDTO.setBackgroundId(UUID.randomUUID().toString());
                }
                groupDTO.setError(false);
                groupDTO.setMessage("success update group");
                groupCUDSM.create(groupMapper.groupDtoToGroup(groupDTO));
                return groupDTO;
            } else {
                throw new FunctionalOperationDeniedException(groupDTO, ErrorsEnum.ERR_MCS_PROFIL_GROUP_PERMISSION);
            }
        } else {
            throw new ObjetNotFoundException(groupDTO, ErrorsEnum.ERR_MCS_PROFIL_0047);
        }
    }

    @Override
    public GroupDTO delete(String groupId) {
        GroupDTO groupDTO = new GroupDTO();

        if (groupRSM.isGroupExistById(groupId)) {
            if (gestionDroitMCS.isAdministredAdmin(groupId, "GROUP")) {
                groupCUDSM.delete(groupId);
                reseauxSociauxMCS.deleteGroup(groupId);

                groupDTO.setId(groupId);
                groupDTO.setError(false);
                groupDTO.setMessage("success delete group");
                return groupDTO;
            } else {
                throw new FunctionalOperationDeniedException(groupDTO, ErrorsEnum.ERR_MCS_PROFIL_GROUP_PERMISSION);
            }
        } else {
            throw new ObjetNotFoundException(groupDTO, ErrorsEnum.ERR_MCS_PROFIL_0047);
        }
    }

    @Override
    public MediaDTO generatePictureToken(String idGroup) {
        MediaDTO mediaDTO = null;
        Group group = groupRSM.findByIdGroup(idGroup);
        if (group != null) {
            mediaDTO = mediaMCS.generateProfilPictureToken(idGroup, MediaType.PICTURE.toString());
            if (mediaDTO == null) {
                throw new ObjectSaveException(new MediaDTO(), ErrorsEnum.ERR_MCS_PROFIL_0051);
            }
        } else {
            throw new ObjetNotFoundException(new MediaDTO(), ErrorsEnum.ERR_MCS_PROFIL_0047);
        }

        return mediaDTO;
    }

    @Override
    public PostDTO publishInGroup(PostDTO postDTO) {
        if (postDTO != null) {
            for (String groupId : postDTO.getGroupId()) {
                Group group = groupRSM.findByIdGroup(groupId);
                if (group != null) {
                    postDTO = reseauxSociauxMCS.publishInGroup(postDTO, group.getIsMembersAllowedToPost(),
                            group.getIsPostValidationRequired());
                } else {
                    throw new ObjetNotFoundException(new GroupDTO(), ErrorsEnum.ERR_MCS_PROFIL_0047);
                }
            }
        } else {
            throw new FunctionalInvalidDataException(new GroupDTO(), ErrorsEnum.ERR_MCS_PROFIL_0069);
        }
        return postDTO;
    }

    @Override
    public GroupDTO updatePicture(String groupId, MediaDTO mediaDTO) {
        if (StringUtils.isNotEmpty(groupId)) {
            if (mediaDTO != null) {
                Group group = groupRSM.findByIdGroup(groupId);
                if (group != null) {
                    group.setMediaId(mediaDTO.getId());
                    groupCUDSM.update(group);
                    GroupDTO groupDTO = groupMapper.groupToGroupDTO(group);
                    groupDTO.setMediaDTO(mediaDTO);
                    return groupDTO;
                } else {
                    throw new ObjetNotFoundException(new GroupDTO(), ErrorsEnum.ERR_MCS_PROFIL_0047);
                }
            } else {
                throw new FunctionalInvalidDataException(new MediaDTO(), ErrorsEnum.ERR_MCS_PROFIL_0053);
            }
        } else {
            throw new FunctionalInvalidDataException(new GroupDTO(), ErrorsEnum.ERR_MCS_PROFIL_0055);
        }
    }

    @Override
    public GroupDTO share(String userName, String groupId, PostDTO postDTO) {
        GroupDTO groupDTO = groupRSA.getGroupById(groupId);

        if (groupDTO == null || groupDTO.isError()) {
            throw new ObjetNotFoundException(new GroupDTO(), ErrorsEnum.ERR_MCS_PROFIL_0079);
        }

        postDTO.setOwnerId(userName);
        postDTO.setMessage(groupDTO.getName());
        postDTO.setSharedFromPost(userName);
        postDTO.setType("SHARED_GROUP");
        postDTO.setGroupToShare(groupId);

        PostDTO post = reseauxSociauxMCS.createPost(postDTO);

        if (post.isError()) {
            throw new ObjectSaveException(groupDTO, ErrorsEnum.ERR_MCS_PROFIL_0056);
        }

        groupDTO.setError(false);
        groupDTO.setMessage("Group has been shared");
        return groupDTO;
    }

    @Override
    public GroupDTO updateBackground(String groupId, MediaDTO mediaDTO) {
        if (StringUtils.isNotEmpty(groupId)) {
            if (mediaDTO != null) {
                Group group = groupRSM.findByIdGroup(groupId);
                if (group != null) {
                    group.setBackgroundId(mediaDTO.getId());
                    groupCUDSM.update(group);
                    GroupDTO groupDTO = groupMapper.groupToGroupDTO(group);
                    groupDTO.setBackgroundDTO(mediaDTO);
                    return groupDTO;
                } else {
                    throw new ObjetNotFoundException(new GroupDTO(), ErrorsEnum.ERR_MCS_PROFIL_0047);
                }
            } else {
                throw new FunctionalInvalidDataException(new MediaDTO(), ErrorsEnum.ERR_MCS_PROFIL_0053);
            }
        } else {
            throw new FunctionalInvalidDataException(new GroupDTO(), ErrorsEnum.ERR_MCS_PROFIL_0055);
        }
    }

    @Override
    public GroupDTO deleteBackground(String groupId) {
        GroupDTO groupDTO = null;
        if (StringUtils.isNotEmpty(groupId)) {

            groupDTO = groupRSA.getGroupById(groupId);

            if (groupDTO == null || groupDTO.isError()) {
                throw new ObjetNotFoundException(new GroupDTO(), ErrorsEnum.ERR_MCS_PROFIL_0079);
            }
            MediaDTO backgroundMediaDTO = groupDTO.getBackgroundDTO();
            if (backgroundMediaDTO != null && backgroundMediaDTO.getId() != null && StringUtils.isNotEmpty(backgroundMediaDTO.getId())) {
                MediaDTO mediaDTO = mediaMCS.deleteMedia(backgroundMediaDTO.getId());
                if (mediaDTO != null && mediaDTO.getId() != null && StringUtils.isNotEmpty(mediaDTO.getId())) {
                    Group group = groupMapper.groupDtoToGroup(groupDTO);
                    group.setBackgroundId(null);
                    group = groupCUDSM.update(group);
                    groupDTO = groupMapper.groupToGroupDTO(group);
                }
            } else {
                throw new FunctionalInvalidDataException(new MediaDTO(), ErrorsEnum.ERR_MCS_PROFIL_0053);
            }

        } else {
            throw new FunctionalInvalidDataException(new MediaDTO(), ErrorsEnum.ERR_MCS_PROFIL_0055);
        }

        return groupDTO;
    }

    @Override
    public GroupDTO deletePicture(String groupId) {
        GroupDTO groupDTO = null;
        if (StringUtils.isNotEmpty(groupId)) {

            groupDTO = groupRSA.getGroupById(groupId);

            if (groupDTO == null || groupDTO.isError()) {
                throw new ObjetNotFoundException(new GroupDTO(), ErrorsEnum.ERR_MCS_PROFIL_0079);
            }
            MediaDTO mediaDTO = groupDTO.getMediaDTO();
            if (mediaDTO != null && mediaDTO.getId() != null && StringUtils.isNotEmpty(mediaDTO.getId())) {
                Boolean isDeleted = mediaMCS.deletePicture(mediaDTO.getId());
                if (isDeleted) {
                    Group group = groupMapper.groupDtoToGroup(groupDTO);
                    group.setMediaId(null);
                    group = groupCUDSM.update(group);
                    groupDTO = groupMapper.groupToGroupDTO(group);
                }
            } else {
                throw new FunctionalInvalidDataException(new MediaDTO(), ErrorsEnum.ERR_MCS_PROFIL_0053);
            }

        } else {
            throw new FunctionalInvalidDataException(new MediaDTO(), ErrorsEnum.ERR_MCS_PROFIL_0055);
        }

        return groupDTO;
    }

    @Override
    public GroupDTO setGroupHasMedia(GroupHasMediaDTO groupHasMediaDTO) {
        if (StringUtils.isEmpty(groupHasMediaDTO.getGroupId())) {
            throw new FunctionalInvalidDataException(new GroupDTO(), ErrorsEnum.ERR_MCS_PROFIL_0055);
        }
        Group group = groupRSM.findByIdGroup(groupHasMediaDTO.getGroupId());
        if (groupHasMediaDTO.getType() != null && group != null) {
            if (groupHasMediaDTO.getType().equals("MEDIA")) {
                group.setHasMedia((groupHasMediaDTO.getHasMedia() != null) ? groupHasMediaDTO.getHasMedia() : false);
            } else if (groupHasMediaDTO.getType().equals("BACKGROUND")) {
                group.setHasBackground((groupHasMediaDTO.getHasMedia() != null) ? groupHasMediaDTO.getHasMedia() : false);
            }
            return groupMapper.groupToGroupDTO(groupCUDSM.update(group));
        }
        throw new ObjetNotFoundException(new GroupDTO(), ErrorsEnum.ERR_MCS_PROFIL_0079);
    }

    @Override
    public boolean updateHasMediaGroup() {
        boolean isUpdated = false;
        List<Group> groupList = groupRSM.findAll()
                .stream()
                .filter(p -> p.getHasMedia() == null || !p.getHasMedia())
                .collect(Collectors.toList());
        List<GroupDTO> groupDTOS = groupMapper.listGroupToListGroupDTO(groupList);
        if (groupDTOS != null && groupDTOS.size() > 0) {

            // MEDIA
            List<MapMediaDTO> mapMediaDTOS = groupDTOS
                    .stream()
                    .map(this::mapMediaDTO)
                    .collect(Collectors.toList());
            if (mapMediaDTOS.size() > 0) {
                List<String> postIds = mediaMCS.listHasMedia(mapMediaDTOS);
                if (postIds.size() > 0) {
                    List<GroupDTO> groupDTOList = groupDTOS
                            .stream()
                            .filter(profilDTO -> postIds.contains(profilDTO.getId()))
                            .map(this::mapToHasMedia)
                            .collect(Collectors.toList());
                    List<Group> posts = groupCUDSM.updateAll(groupMapper.listGroupDTOToListGroup(groupDTOList));
                    isUpdated = posts.stream().allMatch(Group::getHasMedia);
                }
            }
            // BACKGROUND
            List<MapMediaDTO> mapMediaBackgroundDTOS = groupDTOS
                    .stream()
                    .map(this::mapMediaBackgroundDTO)
                    .collect(Collectors.toList());

            if (mapMediaBackgroundDTOS.size() > 0) {
                List<String> postIds = mediaMCS.listHasMedia(mapMediaBackgroundDTOS);
                if (postIds.size() > 0) {
                    List<GroupDTO> groupDTOList = groupDTOS
                            .stream()
                            .filter(groupDTO -> postIds.contains(groupDTO.getId()))
                            .map(this::mapToHasBackground)
                            .collect(Collectors.toList());
                    List<Group> posts = groupCUDSM.updateAll(groupMapper.listGroupDTOToListGroup(groupDTOList));
                    isUpdated = posts.stream().allMatch(Group::getHasBackground);
                }
            }
        }
        return isUpdated;
    }

    @Override
    public RepairRightsResultDTO repairGroupAdminRights() {
        RepairRightsResultDTO repairRightsResultDTO = new RepairRightsResultDTO();
        List<Group> corporationList = groupRSM.findAll();

        List<AdminDetailsDTO> adminDetailsDTOS = corporationList.stream().map(group -> {
            AdminDetailsDTO adminDetailsDTO = new AdminDetailsDTO();
            adminDetailsDTO.setAdministredId(group.getId());
            adminDetailsDTO.setAdministredType(AccessRightEntityType.GROUP.toString());
            Set<String> adminUserIds = new HashSet<>();
            adminUserIds.add(group.getOwnerId());
            adminDetailsDTO.setAdminUserIds(adminUserIds);
            return adminDetailsDTO;
        }).collect(Collectors.toList());

        if (adminDetailsDTOS.size() > 0) {
            List<String> idGroupHasRepair = gestionDroitMCS.repairCorporationAdminRights(adminDetailsDTOS);
            if (idGroupHasRepair.isEmpty())
                repairRightsResultDTO.setResultMessage("Not Group has repair in gestion droit MCS");
            else {
                int ownerNotMember = 0;
                for (String id : idGroupHasRepair) {
                    Group group = groupRSM.findByIdGroup(id);
                    if (group != null) {
                        IsMemberDTO isMemberDTO = reseauxSociauxMCS.isUserMemberOfTheGroup(group.getOwnerId(), group.getId());
                        if (isMemberDTO != null && !isMemberDTO.getIsMember()) {
                            reseauxSociauxMCS.addUserToMember(group.getId(), group.getOwnerId());
                            ++ownerNotMember;
                        }
                    }
                }
                String resultForOwnerNotMember = ownerNotMember > 0 ? " Owner group not member is " + ownerNotMember : "";
                repairRightsResultDTO.setResultMessage("The group number repaired by gestion droit MCS is : " + idGroupHasRepair.size() + "" + resultForOwnerNotMember);
            }
        } else {
            repairRightsResultDTO.setResultMessage("Not Group stored in database");
        }
        return repairRightsResultDTO;
    }

    private MapMediaDTO mapMediaDTO(GroupDTO groupDTO) {
        MapMediaDTO mapMediaDTO = new MapMediaDTO();
        mapMediaDTO.setMediaId(groupDTO.getMediaId());
        mapMediaDTO.setTarget(groupDTO.getId());
        return mapMediaDTO;
    }


    private MapMediaDTO mapMediaBackgroundDTO(GroupDTO groupDTO) {
        MapMediaDTO mapMediaDTO = new MapMediaDTO();
        mapMediaDTO.setMediaId(groupDTO.getBackgroundId());
        mapMediaDTO.setTarget(groupDTO.getId());
        return mapMediaDTO;
    }


    private GroupDTO mapToHasMedia(GroupDTO groupDTO) {
        groupDTO.setHasMedia(true);
        return groupDTO;
    }

    private GroupDTO mapToHasBackground(GroupDTO groupDTO) {
        groupDTO.setHasBackground(true);
        return groupDTO;
    }

}
