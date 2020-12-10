package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.corporation;

import java.util.*;
import java.util.stream.Collectors;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.constants.*;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.*;
import com.arkeup.link_innov.gestion_profil_mcs.service.businessdelegate.GestionDroitMCS;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.CustomNullPointerException;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ErrorsEnum;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.FunctionalInvalidDataException;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.FunctionalLimitExceededException;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.FunctionalOperationDeniedException;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ObjectDeleteException;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ObjectSaveException;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ObjetNotFoundException;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.corporation.CorporationMapper;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Corporation;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.businessdelegate.ReseauSocialUserDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.CustomPageDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.referential.KeywordDTO;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.notification.NotificationSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.referential.keyword.KeywordCUDSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.category.CategoryRSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.corporation.CorporationRSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.profil.ProfilRSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.businessdelegate.ChatMCS;
import com.arkeup.link_innov.gestion_profil_mcs.service.businessdelegate.MediaMCS;
import com.arkeup.link_innov.gestion_profil_mcs.service.businessdelegate.ReseauxSociauxMCS;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.corporation.CorporationCUDSM;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.corporation.CorporationRSM;

@Service
public class CorporationCUDSAImpl implements CorporationCUDSA {

    @Autowired
    private CorporationCUDSM corporationCUDSM;

    @Autowired
    private CorporationRSM corporationRSM;

    @Autowired
    private CorporationMapper corporationFactory;

    @Autowired
    private MediaMCS mediaMCS;

    @Autowired
    private CorporationRSA corporationRSA;

    @Autowired
    ProfilRSA profilRSA;

    @Autowired
    private NotificationSA notificationSA;

    @Autowired
    private ReseauxSociauxMCS reseauxSociauxMCS;

    @Autowired
    private CategoryRSA categoryRSA;

    @Autowired
    private ChatMCS chatMCS;

    @Autowired
    private KeywordCUDSA keywordCUDSA;

    @Autowired
    private GestionDroitMCS gestionDroitMCS;

    @Override
    public CorporationDTO create(String username, CorporationDTO corporationDTO) {
        ProfilDTO user = profilRSA.getProfilInformation(username);

        if (user.isError()) {
            throw new ObjetNotFoundException(new ProfilDTO(), ErrorsEnum.ERR_MCS_PROFIL_0007);
        }

        Optional<Corporation> resultat = corporationRSM.getCorporationByName(corporationDTO.getName());

        if (resultat.isPresent() && resultat.get() != null) {
            throw new ObjetNotFoundException(corporationDTO, ErrorsEnum.ERR_MCS_PROFIL_0077);
        }

        // test if category exist
        String categoryId = corporationDTO.getType().getId();

        if (categoryId != null && !categoryRSA.isCategoryExist(categoryId)) {
            throw new ObjetNotFoundException(corporationDTO, ErrorsEnum.ERR_MCS_PROFIL_0039);
        }

        corporationDTO.setSuperAdmin(user.getUsername());
        corporationDTO.getAdmins().add(user.getUsername());
        corporationDTO.setCertificationQuota(0);
        corporationDTO.setPremiumAccountQuota(0);

        List<String> adminsChatId = new ArrayList<>();
        adminsChatId.add(user.getChatId());
        corporationDTO.setAdminsChatId(adminsChatId);

        // Manage Keywords
        if (!CollectionUtils.isEmpty(corporationDTO.getKeywords())) {
            for (KeywordDTO key : corporationDTO.getKeywords()) {
                if (StringUtils.isEmpty(key.getId())) {
                    KeywordDTO newKey = keywordCUDSA.addKeyword(key);
                    key.setId(newKey.getId());
                }
            }
        }

        corporationDTO.setMediaId(UUID.randomUUID().toString());
        corporationDTO.setBackgroundId(UUID.randomUUID().toString());

        CorporationDTO result = corporationFactory.corporationToCorporationDTO(
                corporationCUDSM.create(corporationFactory.corporationDTOToCorporation(corporationDTO)));

        if (result == null || result.getId() == null) {
            throw new CustomNullPointerException(corporationDTO, ErrorsEnum.ERR_MCS_PROFIL_0014);
        }

        final String corporationId = result.getId();

        // Rollback if error
        if (reseauxSociauxMCS.create(result).isError()) {
            this.delete(result);
            throw new ObjectSaveException(result, ErrorsEnum.ERR_MCS_PROFIL_0014);
        }

        // send new Moral Person Action
        notificationSA.sendNewPMAction(username, corporationId);

        result.setError(false);
        result.setMessage("Corporation added");

        return result;
    }

    @Override
    public CorporationDTO update(CorporationDTO corporationDTO) {
        if (StringUtils.isEmpty(corporationDTO.getId())) {
            throw new FunctionalInvalidDataException(corporationDTO, ErrorsEnum.ERR_MCS_PROFIL_0081);
        }
        CorporationDTO oldCorporationDTO = corporationRSA.getCorporation(corporationDTO.getId());
        if (oldCorporationDTO == null || oldCorporationDTO.isError()) {
            throw new ObjetNotFoundException(corporationDTO, ErrorsEnum.ERR_MCS_PROFIL_0040);
        }

        if (corporationRSM.isOtherCorporationExistByName(corporationDTO.getId(), corporationDTO.getName())) {
            throw new ObjetNotFoundException(corporationDTO, ErrorsEnum.ERR_MCS_PROFIL_0077);
        }

        if (StringUtils.isEmpty(corporationDTO.getMediaId())) {
            corporationDTO.setMediaId(UUID.randomUUID().toString());
        }

        if (StringUtils.isEmpty(corporationDTO.getBackgroundId())) {
            corporationDTO.setBackgroundId(UUID.randomUUID().toString());
        }

        corporationDTO = corporationFactory.corporationToCorporationDTO(corporationCUDSM
                .update(corporationFactory.corporationDTOToCorporation(corporationDTO)));
        if (corporationDTO == null) {
            throw new ObjectSaveException(corporationDTO, ErrorsEnum.ERR_MCS_PROFIL_0041);
        }
        // update post, recommandation, comment if name has changed
        if (!StringUtils.equals(oldCorporationDTO.getName(), corporationDTO.getName())) {
            String corporationName = corporationDTO.getName();
            String corporationId = corporationDTO.getId();
            reseauxSociauxMCS.updateAllPostsCorporationName(corporationId, corporationName);
            reseauxSociauxMCS.updateAllCommentsCorporationName(corporationId, corporationName);
            reseauxSociauxMCS.updateAllRecommandationsCorporationName(corporationId, corporationName);
        }
        corporationDTO.setError(false);
        corporationDTO.setMessage("Corporation updated");

        return corporationDTO;
    }

    @Override
    public CorporationDTO addAdmin(String username, String userId, String corporationId) {
        ProfilDTO user = profilRSA.getProfilInformation(userId);
        if (user.isError()) {
            throw new ObjetNotFoundException(new ProfilDTO(), ErrorsEnum.ERR_MCS_PROFIL_0007);
        }

        CorporationDTO corporation = corporationRSA.getCorporation(corporationId);
        if (corporation.isError()) {
            return corporation;
        }

        if (corporation.getAdmins() == null) {
            corporation.setAdmins(new ArrayList<String>());
        }

        if ((!corporation.getSuperAdmin().equals(username)) && (!corporation.getAdmins().contains(username))) {
            corporation.setError(true);
            corporation.setErrorMessage(ErrorsEnum.ERR_MCS_PROFIL_0025.getErrorMessage());
            corporation.setErrorCode(ErrorsEnum.ERR_MCS_PROFIL_0025.getErrorCode());
            return corporation;
        }

        if (!corporation.getAdmins().contains(userId)) {
            corporation.getAdmins().add(userId);

            List<String> adminsChatId = corporation.getAdminsChatId();
            adminsChatId.add(user.getChatId());
            corporation.setAdminsChatId(adminsChatId);

        } else {
            corporation.setError(true);
            corporation.setErrorMessage(ErrorsEnum.ERR_MCS_USER.getErrorMessage());
            corporation.setErrorCode("ERR_MCS_USER");
            return corporation;
        }

        corporationCUDSM.update(corporationFactory.corporationDTOToCorporation(corporation));

        corporation.setError(false);
        corporation.setMessage("Admin added");

        // Add admin to accessRight
        if (corporation.getAdmins().contains(userId)) {
            reseauxSociauxMCS.addAdmins(corporation.getId(), Arrays.asList(userId));
        }
        // Send action to notify user and other admins
        notificationSA.sendAttachPMAction(username, corporationId, userId, AttachPMActionType.ADD_ADMIN.toString());

        // add admin in the all of conversation room of account ID
        CustomPageDTO<RoomDTO> rooms;
        Integer pageIndex = 0;
        do {
            rooms = chatMCS.findRoomByAccountId(username, corporationId, pageIndex);
            for (RoomDTO room : rooms.getPageDTOs().getContent()) {
                chatMCS.addAdminRoom(userId, room);
            }
            pageIndex++;
        } while (rooms.getPageDTOs().hasNext());
        return corporation;
    }

    @Override
    public CorporationDTO removeAdmin(String username, String adminIdToRemove, String corporationId) {
        ProfilDTO user = profilRSA.getProfilInformation(adminIdToRemove);

        if (user.isError()) {
            throw new ObjetNotFoundException(new ProfilDTO(), ErrorsEnum.ERR_MCS_PROFIL_0007);
        }

        CorporationDTO corporation = corporationRSA.getCorporation(corporationId);

        if (corporation.isError()) {
            return corporation;
        }

        if (corporation.getAdmins().contains(adminIdToRemove)) {
            corporation.getAdmins().remove(adminIdToRemove);
            corporation.getAdminsChatId().remove(user.getChatId());
        } else {
            corporation.setError(true);
            corporation.setErrorMessage(ErrorsEnum.ERR_MCS_PROFIL_0007.getErrorMessage());
            corporation.setErrorCode("ERR_MCS_PROFIL_0007");
            return corporation;
        }

        corporationCUDSM.update(corporationFactory.corporationDTOToCorporation(corporation));

        corporation.setError(false);
        corporation.setMessage("Admin removed");

        // Delete admin from access Right
        if (!corporation.getAdmins().contains(adminIdToRemove)) {
            reseauxSociauxMCS.deleteAdmins(corporation.getId(), Arrays.asList(adminIdToRemove));
        }

        this.notificationSA.sendAdminCorporationAction(username, corporationId, AdminActionEnum.REMOVE, adminIdToRemove);

        return corporation;
    }

    @Override
    public CorporationAdminDTO isCorporationAdmin(String userId, String corporationId) {
        CorporationDTO corporation = corporationRSA.getCorporation(corporationId);
        CorporationAdminDTO res = new CorporationAdminDTO();
        res.setIsAdmin(true);
        if (corporation.isError()) {
            res.setIsAdmin(false);
            return res;
        }

        if (corporation.getAdmins() == null) {
            corporation.setAdmins(new ArrayList<String>());
        }

        if ((!corporation.getSuperAdmin().equals(userId)) && (!corporation.getAdmins().contains(userId))) {
            res.setIsAdmin(false);
            return res;
        }
        return res;
    }

    @Override
    public CorporationDTO certifyUser(String corporationId, String userId) {

        CorporationDTO corporation = corporationRSA.getCorporation(corporationId);
        if (corporation == null) {
            throw new ObjetNotFoundException(new CorporationDTO(), ErrorsEnum.ERR_MCS_OBJECT_NOT_FOUND);
        }

        List<ReseauSocialUserDTO> certifiedUsers = corporationRSA.getCertifiedUsers(corporationId);
        if (certifiedUsers.size() >= corporation.getCertificationQuota()) {
            throw new FunctionalLimitExceededException(corporation, ErrorsEnum.ERR_MCS_PROFIL_0042);
        }
        CorporationDTO result = reseauxSociauxMCS.certifyUser(corporationId, userId);

        notificationSA.sendAttachPMAction(corporation.getName(), corporationId, userId,
                AttachPMActionType.CERTIFY_USER.toString());
        return result;
    }

    @Override
    public CorporationDTO deleteCertificationByUser(String corporationId, String userId) {
        CorporationDTO corporationDTO = reseauxSociauxMCS.deleteCertificationByUser(corporationId, userId);
        return corporationDTO;
    }

    @Override
    public CorporationDTO deleteCorporation(String corporationId) {
        CorporationDTO response = new CorporationDTO();
        response.setId(corporationId);
        // Delete picture and background
        response = corporationRSA.getCorporation(corporationId);
        if (response != null) {
            if (response.getMediaDTO() != null && StringUtils.isNotEmpty(response.getMediaDTO().getId())) {
                if (!mediaMCS.deletePicture(response.getMediaDTO().getId())) {
                    throw new ObjectDeleteException(new MediaDTO(), ErrorsEnum.ERR_MCS_PROFIL_0054);
                }
            }
            if (response.getBackgroundDTO() != null && StringUtils.isNotEmpty(response.getBackgroundDTO().getId())) {
                if (!mediaMCS.deletePicture(response.getBackgroundDTO().getId())) {
                    throw new ObjectDeleteException(new MediaDTO(), ErrorsEnum.ERR_MCS_PROFIL_0054);
                }
            }
        }
        // Delete the node
        if (reseauxSociauxMCS.deleteCorporation(corporationId) != null) {
            corporationCUDSM.deleteCorporation(corporationId);
        }

        return response;
    }

    @Override
    public CorporationDTO updateQuota(String corporationId, Integer quota, Boolean certification,
                                      Boolean premiumAccount) {

        CorporationDTO corporationDTO = corporationRSA.getCorporation(corporationId);
        if (corporationDTO == null) {
            throw new ObjetNotFoundException(new CorporationDTO(), ErrorsEnum.ERR_MCS_PROFIL_0040);
        }

        if (certification) {
            corporationDTO.setCertificationQuota(quota);
        }
        if (premiumAccount) {
            corporationDTO.setPremiumAccountQuota(quota);
        }

        try {
            return corporationFactory.corporationToCorporationDTO(
                    corporationCUDSM.update(corporationFactory.corporationDTOToCorporation(corporationDTO)));
        } catch (Exception e) {
            throw new ObjectSaveException(corporationDTO, ErrorsEnum.ERR_MCS_PROFIL_0041, e);
        }

    }

    @Override
    public MediaDTO generatePictureToken(String corporationId) {
        MediaDTO mediaDTO = null;
        CorporationDTO corporationDTO = corporationRSA.getCorporation(corporationId);
        if (corporationDTO != null) {
            mediaDTO = mediaMCS.generateProfilPictureToken(corporationId, MediaType.PICTURE.toString());
            if (mediaDTO == null) {
                throw new ObjectSaveException(new MediaDTO(), ErrorsEnum.ERR_MCS_PROFIL_0051);
            }
        } else {
            throw new ObjetNotFoundException(new MediaDTO(), ErrorsEnum.ERR_MCS_PROFIL_0040);
        }

        return mediaDTO;
    }

    @Override
    public CorporationDTO updatePictureInProfil(String corporationId, MediaDTO mediaDTO) {
        CorporationDTO corporationDTO = null;
        if (StringUtils.isNotEmpty(corporationId)) {
            if (mediaDTO != null) {
                corporationDTO = corporationRSA.getCorporation(corporationId);
                if (corporationDTO != null) {
                    corporationDTO.setMediaDTO(mediaDTO);
                    corporationCUDSM.update(corporationFactory.corporationDTOToCorporation(corporationDTO));
                    corporationDTO = corporationRSA.getCorporation(corporationId);
                } else {
                    throw new ObjetNotFoundException(new CorporationDTO(), ErrorsEnum.ERR_MCS_PROFIL_0007);
                }
            } else {
                throw new ObjetNotFoundException(new CorporationDTO(), ErrorsEnum.ERR_MCS_PROFIL_0053);
            }
        } else {
            throw new FunctionalInvalidDataException(new CorporationDTO(), ErrorsEnum.ERR_MCS_PROFIL_0001);
        }

        return corporationDTO;
    }

    @Override
    public CorporationDTO updateBackgroundInProfil(String corporationId, MediaDTO backgroundDTO) {
        CorporationDTO corporationDTO = null;
        if (StringUtils.isNotEmpty(corporationId)) {
            if (backgroundDTO != null) {
                corporationDTO = corporationRSA.getCorporation(corporationId);
                if (corporationDTO != null) {
                    corporationDTO.setBackgroundDTO(backgroundDTO);
                    corporationCUDSM.update(corporationFactory.corporationDTOToCorporation(corporationDTO));
                    corporationDTO = corporationRSA.getCorporation(corporationId);
                } else {
                    throw new ObjetNotFoundException(new CorporationDTO(), ErrorsEnum.ERR_MCS_PROFIL_0007);
                }
            } else {
                throw new ObjetNotFoundException(new CorporationDTO(), ErrorsEnum.ERR_MCS_PROFIL_0053);
            }
        } else {
            throw new FunctionalInvalidDataException(new CorporationDTO(), ErrorsEnum.ERR_MCS_PROFIL_0001);
        }

        return corporationDTO;
    }

    @Override
    public MediaDTO deletePictureInProfil(String corporationId, String mediaId) {
        MediaDTO mediaDTO = null;
        Boolean isDeleted = false;
        if (StringUtils.isNotEmpty(corporationId)) {
            CorporationAdminDTO corporationAdminDTO = reseauxSociauxMCS.hasPermission(corporationId);
            if (corporationAdminDTO.getIsAdmin()) {
                CorporationDTO corporationDTO = corporationRSA.getCorporation(corporationId);
                if (corporationDTO == null) {
                    throw new ObjetNotFoundException(new CorporationDTO(), ErrorsEnum.ERR_MCS_PROFIL_0016);
                }
                if (StringUtils.isNotEmpty(mediaId)) {
                    mediaDTO = corporationDTO.getMediaDTO();
                    isDeleted = mediaMCS.deletePicture(mediaId);
                    if (isDeleted) {
                        Corporation corporation = corporationFactory.corporationDTOToCorporation(corporationDTO);
                        corporation.setMediaId(null);
                        corporationCUDSM.update(corporation);
                        mediaDTO.setStatus(MediaStatus.DELETED);
                    }
                } else {
                    throw new FunctionalInvalidDataException(new MediaDTO(), ErrorsEnum.ERR_MCS_PROFIL_0053);
                }
            }
        } else {
            throw new FunctionalInvalidDataException(new MediaDTO(), ErrorsEnum.ERR_MCS_PROFIL_0052);
        }

        return mediaDTO;
    }

    @Override
    public CorporationDTO deleteBackground(String corporationId) {
        CorporationDTO corporationDTO = null;
        if (StringUtils.isNotEmpty(corporationId)) {
            CorporationAdminDTO corporationAdminDTO = reseauxSociauxMCS.hasPermission(corporationId);
            if (corporationAdminDTO.getIsAdmin()) {
                corporationDTO = corporationRSA.getCorporation(corporationId);
                if (corporationDTO == null) {
                    throw new ObjetNotFoundException(new CorporationDTO(), ErrorsEnum.ERR_MCS_PROFIL_0016);
                }
                MediaDTO backgroundMediaDTO = corporationDTO.getBackgroundDTO();
                if (backgroundMediaDTO != null && backgroundMediaDTO.getId() != null && StringUtils.isNotEmpty(backgroundMediaDTO.getId())) {
                    MediaDTO mediaDTO = mediaMCS.deleteMedia(backgroundMediaDTO.getId());
                    if (mediaDTO != null && mediaDTO.getId() != null && StringUtils.isNotEmpty(mediaDTO.getId())) {
                        Corporation corporation = corporationFactory.corporationDTOToCorporation(corporationDTO);
                        corporation.setBackgroundId(null);
                        corporationCUDSM.update(corporation);
                        corporationDTO = corporationFactory.corporationToCorporationDTO(corporation);
                    }
                } else {
                    throw new FunctionalInvalidDataException(new MediaDTO(), ErrorsEnum.ERR_MCS_PROFIL_0053);
                }
            }
        } else {
            throw new FunctionalInvalidDataException(new MediaDTO(), ErrorsEnum.ERR_MCS_PROFIL_0052);
        }

        return corporationDTO;
    }

    @Override
    public MediaDTO deleteBackgroundInProfil(String corporationId, String mediaId) {
        MediaDTO mediaDTO = null;
        Boolean isDeleted = false;
        if (StringUtils.isNotEmpty(corporationId)) {
            CorporationAdminDTO corporationAdminDTO = reseauxSociauxMCS.hasPermission(corporationId);
            if (corporationAdminDTO.getIsAdmin()) {
                CorporationDTO corporationDTO = corporationRSA.getCorporation(corporationId);
                if (corporationDTO == null) {
                    throw new ObjetNotFoundException(new CorporationDTO(), ErrorsEnum.ERR_MCS_PROFIL_0016);
                }
                if (StringUtils.isNotEmpty(mediaId)) {
                    mediaDTO = corporationDTO.getBackgroundDTO();
                    isDeleted = mediaMCS.deletePicture(mediaId);
                    if (isDeleted) {
                        Corporation corporation = corporationFactory.corporationDTOToCorporation(corporationDTO);
                        corporation.setBackgroundId(null);
                        corporationCUDSM.update(corporation);
                        mediaDTO.setStatus(MediaStatus.DELETED);
                    }
                } else {
                    throw new FunctionalInvalidDataException(new MediaDTO(), ErrorsEnum.ERR_MCS_PROFIL_0053);
                }
            }
        } else {
            throw new FunctionalInvalidDataException(new MediaDTO(), ErrorsEnum.ERR_MCS_PROFIL_0052);
        }

        return mediaDTO;
    }

    @Override
    public AdminListDTO setAdminList(String userName, AdminListDTO adminListDTO) {
        Boolean isCorporationAdmin = isCorporationAdmin(userName, adminListDTO.getId()).getIsAdmin();

        if (!isCorporationAdmin) {
            throw new FunctionalOperationDeniedException(adminListDTO, ErrorsEnum.ERR_MCS_PROFIL_0025);
        }

        Optional<Corporation> corporation = corporationRSM.getCorporation(adminListDTO.getId());

        if (!corporation.isPresent()) {
            throw new ObjetNotFoundException(adminListDTO, ErrorsEnum.ERR_MCS_PROFIL_0040);
        }

        Corporation corporationEntity = corporation.get();
        List<String> corporationAdmins = corporationEntity.getAdmins();
        List<String> listAdminToNotify = new ArrayList<>();
        List<String> adminsChatId = new ArrayList<>();

        for (String admin : adminListDTO.getAdmins()) {
            if (corporationAdmins == null || corporationAdmins.isEmpty()
                    || !corporationEntity.getAdmins().contains(admin)) {
                listAdminToNotify.add(admin);
            }

            ProfilDTO adminChat = profilRSA.getProfilInformation(admin);
            adminsChatId.add(adminChat.getChatId());
        }

        corporationEntity.setAdmins(adminListDTO.getAdmins());

        List<String> removedAdmins = corporationEntity.getAdminsChatId();
        removedAdmins.removeAll(adminsChatId);

        List<String> addedAdmins = adminsChatId;
        addedAdmins.removeAll(corporationEntity.getAdminsChatId());

        //nouvelle liste chatIDAmdins
        corporationEntity.setAdminsChatId(adminsChatId);
        corporationCUDSM.update(corporationEntity);

        // add admins room in openfire serveur
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setAccountID(corporationEntity.getId());
        roomDTO.setAdmins(addedAdmins);
        roomDTO.setRemovedAdmins(removedAdmins);
        chatMCS.updateAdmins(roomDTO);

        // Add admins to accessRight
        if (!corporationEntity.getAdmins().isEmpty()) {
            //reseauxSociauxMCS.addAdmins(corporationEntity.getId(), corporationEntity.getAdmins());
            AdminsDTO adminsDTO = new AdminsDTO();
            adminsDTO.setAdministredId(corporationEntity.getId());
            adminsDTO.setAdministredType(AccessRightEntityType.CORPORATION.toString());
            adminsDTO.setAdmins(corporationEntity.getAdmins());
            gestionDroitMCS.addAdminsToAdministred(adminsDTO);
        }

        this.notificationSA.sendAdminCorporationAction(userName, corporationEntity.getId(), AdminActionEnum.ADD,
                listAdminToNotify.toArray(new String[listAdminToNotify.size()]));

        adminListDTO.setError(false);
        adminListDTO.setMessage("List of admin updated");
        return adminListDTO;
    }

    @Override
    public CorporationDTO shareCorporation(String userName, String corporationId, PostDTO postDTO, String groupId) {
        CorporationDTO corporationDTO = corporationRSA.getCorporation(corporationId);
        if (corporationDTO == null || corporationDTO.isError()) {
            throw new ObjetNotFoundException(new CorporationDTO(), ErrorsEnum.ERR_MCS_PROFIL_0040);
        }

        postDTO.setOwnerId(userName);
        postDTO.setMessage(corporationDTO.getName());
        postDTO.setSharedFromPost(corporationDTO.getId());
        postDTO.setType("SHARED_CORPORATION");

        PostDTO post;

        if (StringUtils.isNotEmpty(groupId)) {
            List<String> groupIds = new ArrayList<>();
            groupIds.add(groupId);
            postDTO.setGroupId(groupIds);
            post = reseauxSociauxMCS.createPostGroup(postDTO);
        } else {
            post = reseauxSociauxMCS.createPost(postDTO);
        }

        if (post.isError()) {
            throw new ObjectSaveException(postDTO, ErrorsEnum.ERR_MCS_PROFIL_0056);
        }

        corporationDTO.setError(false);
        corporationDTO.setMessage("Corporation has been shared");
        return corporationDTO;

    }

    @Override
    public CorporationDTO sharePostCorporation(String userName, String corporationId, PostDTO postDTO, String postId) {
        CorporationDTO corporationDTO = corporationRSA.getCorporation(corporationId);
        if (corporationDTO == null || corporationDTO.isError()) {
            throw new ObjetNotFoundException(new CorporationDTO(), ErrorsEnum.ERR_MCS_PROFIL_0040);
        }
        PostDTO postDTOOriginal = reseauxSociauxMCS.getPost(postId);
        if (postDTOOriginal != null) {

            postDTO.setPersonneMorale(corporationId);
            postDTO.setOwnerId(userName);
            postDTO.setMessage(corporationDTO.getName());
            postDTO.setSharedFromPost(postId);
            postDTO.setType("SHARED_POST_CORPORATION");

            PostDTO post = reseauxSociauxMCS.createPost(postDTO);

            if (post.isError()) {
                throw new ObjectSaveException(postDTO, ErrorsEnum.ERR_MCS_PROFIL_0056);
            }

            corporationDTO.setError(false);
            corporationDTO.setMessage("Corporation post has been shared");

        } else {
            throw new FunctionalInvalidDataException(new CorporationDTO(), ErrorsEnum.ERR_MCS_PROFIL_0080);
        }
        return corporationDTO;
    }

    @Override
    public void delete(CorporationDTO corporationDTO) {
        this.corporationCUDSM.delete(corporationFactory.corporationDTOToCorporation(corporationDTO));
    }

    @Override
    public CorporationDTO setCorporationHasMedia(CorporationHasMediaDTO corporationHasMediaDTO) {
        if (StringUtils.isEmpty(corporationHasMediaDTO.getCorporationId())) {
            throw new FunctionalInvalidDataException(new CorporationDTO(), ErrorsEnum.ERR_MCS_PROFIL_0081);
        }
        Corporation corporation = corporationRSM.getCorporation(corporationHasMediaDTO.getCorporationId())
                .orElse(null);
        if (corporationHasMediaDTO.getType() != null && corporation != null) {

            if (corporationHasMediaDTO.getType().equals("MEDIA")) {
                corporation.setHasMedia(corporationHasMediaDTO.getHasMedia() != null ? corporationHasMediaDTO.getHasMedia() : false);
            } else if (corporationHasMediaDTO.getType().equals("BACKGROUND")) {
                corporation.setHasMedia(corporationHasMediaDTO.getHasMedia() != null ? corporationHasMediaDTO.getHasMedia() : false);
            }

            return corporationFactory.corporationToCorporationDTO(corporationCUDSM.update(corporation));
        }
        throw new ObjetNotFoundException(new CorporationDTO(), ErrorsEnum.ERR_MCS_PROFIL_0040);
    }

    @Override
    public boolean updateHasMediaCorporation() {
        boolean isUpdated = false;
        List<Corporation> corporationList = corporationRSM.findAll()
                .stream()
                .filter(c -> c.getHasMedia() == null || !c.getHasMedia())
                .collect(Collectors.toList());
        List<CorporationDTO> corporationDTOS = corporationFactory.corporationToCorporationDTOs(corporationList);
        if (corporationDTOS != null && corporationDTOS.size() > 0) {

            // MEDIA
            List<MapMediaDTO> mapMediaDTOS = corporationDTOS
                    .stream()
                    .map(this::mapMediaDTO)
                    .collect(Collectors.toList());
            if (mapMediaDTOS.size() > 0) {
                List<String> postIds = mediaMCS.listHasMedia(mapMediaDTOS);
                if (postIds.size() > 0) {
                    List<CorporationDTO> corporationDTOList = corporationDTOS
                            .stream()
                            .filter(corporationDTO -> postIds.contains(corporationDTO.getId()))
                            .map(this::mapToHasMedia)
                            .collect(Collectors.toList());
                    List<Corporation> posts = corporationCUDSM.updateAll(corporationFactory.corporationDTOToCorporations(corporationDTOList));
                    isUpdated = posts.stream().allMatch(Corporation::getHasMedia);
                }
            }
            // BACKGROUND
            List<MapMediaDTO> mapMediaBackgroundDTOS = corporationDTOS
                    .stream()
                    .map(this::mapMediaBackgroundDTO)
                    .collect(Collectors.toList());

            if (mapMediaBackgroundDTOS.size() > 0) {
                List<String> postIds = mediaMCS.listHasMedia(mapMediaBackgroundDTOS);
                if (postIds.size() > 0) {
                    List<CorporationDTO> corporationDTOList = corporationDTOS
                            .stream()
                            .filter(groupDTO -> postIds.contains(groupDTO.getId()))
                            .map(this::mapToHasBackground)
                            .collect(Collectors.toList());
                    List<Corporation> posts = corporationCUDSM.updateAll(corporationFactory.corporationDTOToCorporations(corporationDTOList));
                    isUpdated = posts.stream().allMatch(Corporation::getHasBackground);
                }
            }
        }
        return isUpdated;
    }

    @Override
    public RepairRightsResultDTO repairCorporationAdminRights() {
        RepairRightsResultDTO repairRightsResultDTO = new RepairRightsResultDTO();
        List<Corporation> corporationList = corporationRSM.findAll();
        List<AdminDetailsDTO> adminDetailsDTOS = corporationList.stream().map(this::mapToCorporationAdminsDTO).collect(Collectors.toList());
        if (!adminDetailsDTOS.isEmpty()) {
            List<String> idCorporationsHasRepair = gestionDroitMCS.repairCorporationAdminRights(adminDetailsDTOS);
            if (idCorporationsHasRepair.isEmpty())
                repairRightsResultDTO.setResultMessage("Not Corporation has repair in gestion droit MCS");
            else
                repairRightsResultDTO.setResultMessage("The corporation number repaired by gestion droit MCS is : " + idCorporationsHasRepair.size());
        } else {
            repairRightsResultDTO.setResultMessage("Not Corporation stored in database");
        }
        return repairRightsResultDTO;
    }

    private AdminDetailsDTO mapToCorporationAdminsDTO(Corporation corporation) {
        AdminDetailsDTO adminDetailsDTO = new AdminDetailsDTO();
        adminDetailsDTO.setAdministredId(corporation.getId());
        adminDetailsDTO.setAdministredType(AccessRightEntityType.CORPORATION.toString());
        Set<String> adminUserIds = new HashSet<>();
        adminUserIds.add(corporation.getSuperAdmin());
        adminUserIds.addAll(corporation.getAdmins());
        adminDetailsDTO.setAdminUserIds(adminUserIds);
        return adminDetailsDTO;
    }

    private MapMediaDTO mapMediaDTO(CorporationDTO corporationDTO) {
        MapMediaDTO mapMediaDTO = new MapMediaDTO();
        mapMediaDTO.setMediaId(corporationDTO.getMediaId());
        mapMediaDTO.setTarget(corporationDTO.getId());
        return mapMediaDTO;
    }


    private MapMediaDTO mapMediaBackgroundDTO(CorporationDTO corporationDTO) {
        MapMediaDTO mapMediaDTO = new MapMediaDTO();
        mapMediaDTO.setMediaId(corporationDTO.getBackgroundId());
        mapMediaDTO.setTarget(corporationDTO.getId());
        return mapMediaDTO;
    }


    private CorporationDTO mapToHasMedia(CorporationDTO corporationDTO) {
        corporationDTO.setHasMedia(true);
        return corporationDTO;
    }

    private CorporationDTO mapToHasBackground(CorporationDTO corporationDTO) {
        corporationDTO.setHasBackground(true);
        return corporationDTO;
    }
}
