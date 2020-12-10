package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.profil;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.arkeup.link_innov.gestion_profil_mcs.ConvertMongoToDate;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ErrorsEnum;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.FunctionalInvalidDataException;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ObjectSaveException;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ObjetNotFoundException;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.profil.ProfilMapper;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.reseausocial.ReseauSocialUserDTOFactory;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.constants.AttachPMActionType;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.constants.MediaType;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Category;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Corporation;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Profil;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.CorporationDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.IsHasMediaUpdatedDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.MapMediaDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.MediaDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.PostDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ProfilAdminDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ProfilDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ProfilForBODTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ProfilHasMediaDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.businessdelegate.ReseauSocialUserDTO;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.corporation.CorporationCUDSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.group.GroupCUDSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.notification.NotificationSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.productions.ProductionCUDSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.corporation.CorporationRSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.profil.ProfilRSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.profil.UserHistoryService;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.profil.UserHistoryServiceImpl;
import com.arkeup.link_innov.gestion_profil_mcs.service.businessdelegate.MediaMCS;
import com.arkeup.link_innov.gestion_profil_mcs.service.businessdelegate.ReseauxSociauxMCS;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.profil.ProfilCUDSM;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.corporation.CorporationRSM;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.profil.ProfilRSM;
import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;

/**
 * @author bona
 */
@Service
public class ProfilCUDSAImpl implements ProfilCUDSA {
	@Autowired
	private UserHistoryService userHistoryService;

	@Autowired
	private ProfilCUDSM profilCUDSM;

	@Autowired
	private ProfilMapper profilFactory;

	@Autowired
	private ProfilRSA profilRSA;

	@Autowired
	private ProfilRSM profilRSM;

	@Autowired
	private ProfilCUDSA profilCUDSA;

	@Autowired
	private CorporationRSA corporationRSA;

	@Autowired
	private CorporationCUDSA corporationCUDSA;

	@Autowired
	private ProductionCUDSA productionCUDSA;

	@Autowired
	private CorporationRSM corporationRSM;

	@Autowired
	private NotificationSA notificationSA;

	@Autowired
	private ReseauxSociauxMCS reseauxSociauxMCS;

	@Autowired
	private ReseauSocialUserDTOFactory reseauSocialUserDTOFactory;

	@Autowired
	private MediaMCS mediaMCS;

	@Autowired
	private GroupCUDSA groupCUDSA;

	@Autowired
	private UserHistoryServiceImpl personService;

	// TODO BO remettre
//	@Value("${bo.link.send.profil}")
//	private String UrlSendRepportToBo;

	private static final Logger LOGGER = LoggerFactory.getLogger(ProfilCUDSAImpl.class);

	@Override
	public ProfilDTO update(ProfilDTO profilDTO) {

		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName = user.getUsername();

		if (profilDTO.getCompany().getId() == null) {
			profilDTO.getCompany().setId(UUID.randomUUID().toString());
		}

		ProfilDTO profilOld = profilRSA.getProfil(userName);

		// delete certification if company has changed
		CorporationDTO corporationOldDTO = profilOld.getCompany();
		boolean canDeleteCertification = false;
		if (corporationOldDTO != null && corporationOldDTO.getId() != null) {
			Optional<Corporation> optional = corporationRSM.getCorporation(corporationOldDTO.getId());
			if (optional.isPresent()) {
				canDeleteCertification = true;
			}
		}
		if (canDeleteCertification && (profilDTO.getCompany() == null || (profilDTO.getCompany() != null
				&& !StringUtils.equals(profilDTO.getCompany().getId(), corporationOldDTO.getId())))) {
			reseauxSociauxMCS.deleteCertificationByUser(corporationOldDTO.getId(), profilOld.getUsername());
		}

		// update post, recommandation, comment if name has changed
		if (!StringUtils.equals(profilDTO.getFirstname(), profilOld.getFirstname())
				|| !StringUtils.equals(profilDTO.getLastname(), profilOld.getLastname())) {
			String ownerDisplayName = String.format("%s %s",
					profilDTO.getFirstname() != null ? profilDTO.getFirstname() : "",
					profilDTO.getLastname() != null ? profilDTO.getLastname() : "");
			reseauxSociauxMCS.updatePostOwnerDisplayNameByUser(ownerDisplayName);
			reseauxSociauxMCS.updateCommentOwnerDisplayNameByUser(ownerDisplayName);
			reseauxSociauxMCS.updateRecommandationOwnerDisplayNameByUser(ownerDisplayName);
		}
		if (StringUtils.isEmpty(profilDTO.getMediaId())) {
			profilDTO.setMediaId(UUID.randomUUID().toString());
		}
		if (StringUtils.isEmpty(profilDTO.getBackgroundId())) {
			profilDTO.setBackgroundId(UUID.randomUUID().toString());
		}
		Profil profil = profilCUDSM.update(profilFactory.profilDTOToProfil(profilDTO));

		// Send to Back office
		profilCUDSA.sendUpdateAnnounceToBO(new ProfilForBODTO(profil));

		// Update Neo4j node
		if (profil != null) {
			ReseauSocialUserDTO reseauSocialUserDTO = reseauSocialUserDTOFactory.getInstance(profil);
			reseauxSociauxMCS.saveUser(reseauSocialUserDTO);
		} else {
			throw new ObjectSaveException(new ProfilDTO(), ErrorsEnum.ERR_MCS_PROFIL_0070);
		}

		profilDTO.setError(false);
		profilDTO.setMessage("User information updated");
		return profilDTO;
	}

	@Override
	public void initProfiles() {

		// add defalut users on mongoDB and ES
		Profil entity = new Profil();

		entity = initProfil("20fb0afc-f958-11e8-8eb2-f2801f1b9fd1", "20fb0afc-f958-11e8-8eb2-f2801f1b9fd1",
				"userTest@yopmail.com", "www.user-test.com", "user", "usertest");
		profilCUDSM.update(entity);

		entity = initProfil("20fb0d86-f958-11e8-8eb2-f2801f1b9fd1", "20fb0d86-f958-11e8-8eb2-f2801f1b9fd1",
				"adminTest@yopmail.com", "www.admin-test.com", "admin", "admintest");
		profilCUDSM.update(entity);

		entity = initProfil("20fb0eda-f958-11e8-8eb2-f2801f1b9fd1", "20fb0eda-f958-11e8-8eb2-f2801f1b9fd1",
				"superAdmin@yopmail.com", "www.super-admin.com", "superAdmin", "superAdmin");
		profilCUDSM.update(entity);

		entity = initProfil("20fdb0afc-fo98-11e8-8eb2-f2902f1b9fd1", "20fdb0afc-fo98-11e8-8eb2-f2902f1b9fd1",
				"dataflowUser@yopmail.com", "www.dataflow-user.com", "dataflowUser", "dataflowUser");
		profilCUDSM.update(entity);

		entity = initProfil("uuid-user-test-for-abonnement-premium", "uuid-user-test-for-abonnement-premium",
				"userTestPremium@yopmail.com", "www.user-test-premium.com", "userPremium", "userpremium");
		profilCUDSM.update(entity);
		try {

			udateCreationDate();
		} catch (Exception e) {
			LOGGER.error("Error update profil creation date");
		}

	}

	private List<Profil> udateCreationDate() {
		List<Profil> profils = profilRSM.findAll();
		ConvertMongoToDate test = new ConvertMongoToDate();
		profils.stream().filter(filtredProfile -> (filtredProfile.getCreationDate() == null)).forEach(profil -> {
			System.out.println(profil.getId() + "  " + profil.getCreationDate());
			if (!profil.getId().contains("-")) {
				System.out.println(profil.getId() + "  " + profil.getCreationDate());
				profil.setCreationDate(test.convertToDateFrom(profil.getId()));
				System.out.println(profil.getId() + "  " + profil.getCreationDate());
				profilCUDSM.update(profil);
			}
//			+ " " + test.convertToDateFrom(profil.getId())
		});
		return profils;
	}

	public Profil initProfil(String uuid, String username, String email, String webSite, String lastName,
			String chatId) {

		Profil profil = new Profil();
		Lorem lorem = LoremIpsum.getInstance();

		Corporation employer = new Corporation();
		employer.setId("employer_id");
		employer.setName("DEFAULT");
		Category type = new Category();
		type.setId("uuid-category-autre-acteur");
		type.setName("Autre acteur");

		profil.setId(uuid);
		profil.setUsername(username);
		profil.setEmail(email);
		profil.setWebSite(webSite);
		profil.setLastname(lastName);
		profil.setFirstname("Test");
		profil.setZipCode(lorem.getZipCode());
		profil.setCity(lorem.getCity());
		profil.setResume(lorem.getParagraphs(10, 20));
		profil.setCompany(employer);
		profil.setCategory(type);
		profil.setPhoneNumber(lorem.getPhone());
		profil.setChatId(chatId);
		return profil;

	}

	@Override
	public ProfilDTO updateUserCorporation(String userName, String corporationId) {
		RestTemplate restTemplate = new RestTemplate();
		//
//	restTemplate.postForObject(UrlSendRepportToBo + "update/" + profilForBODTO.getProfil().getUsername(),
//			profilForBODTO, String.class);
		ProfilDTO res = new ProfilDTO();
		ProfilDTO profilDTO = profilRSA.getProfil(userName);

		if (profilDTO == null) {
			res.setError(true);
			res.setErrorMessage(ErrorsEnum.ERR_MCS_PROFIL_0007.getErrorMessage());
			res.setErrorCode(ErrorsEnum.ERR_MCS_PROFIL_0007.getErrorCode());
			return res;
		} else {
			CorporationDTO corporationDTO = corporationRSA.getCorporation(corporationId);
			if (corporationDTO.isError()) {
				res.setError(true);
				res.setErrorMessage(corporationDTO.getErrorMessage());
				res.setErrorCode(corporationDTO.getErrorCode());
				return res;
			}
			res = profilDTO;
			// delete certification if company has changed
			if (res.getCompany() != null && !StringUtils.equals(corporationId, res.getCompany().getId())) {
				reseauxSociauxMCS.deleteCertificationByUser(res.getCompany().getId(), res.getUsername());
			}
			res.getCompany().setId(corporationId);
			res.getCompany().setName(corporationDTO.getName());

			Profil profil = profilCUDSM.update(profilFactory.profilDTOToProfil(profilDTO));
			// Update Neo4j node
			if (profil != null) {
				ReseauSocialUserDTO reseauSocialUserDTO = reseauSocialUserDTOFactory.getInstance(profil);
				reseauxSociauxMCS.saveUser(reseauSocialUserDTO);
			}
			notificationSA.sendAttachPMAction(userName, corporationId, null, AttachPMActionType.ADD_PM.toString());
		}
		return res;
	}

	@Override
	public MediaDTO generatePictureToken(String userId) {
		MediaDTO mediaDTO = null;
		mediaDTO = mediaMCS.generateProfilPictureToken(userId, MediaType.PICTURE.toString());
		if (mediaDTO == null) {
			throw new ObjetNotFoundException(new MediaDTO(), ErrorsEnum.ERR_MCS_PROFIL_0051);
		}

		return mediaDTO;
	}

	@Override
	public ProfilDTO deletePicture(String profilId, String mediaId) {
		Boolean isDeleted = false;
		ProfilDTO profilDTO = null;
		if (StringUtils.isNotEmpty(profilId)) {
			ProfilAdminDTO profilAdminDTO = reseauxSociauxMCS.isOwner(profilId);
			if (profilAdminDTO.getIsAdmin()) {
				profilDTO = profilRSA.getProfil(profilId);
				if (profilDTO == null) {
					throw new ObjetNotFoundException(new ProfilDTO(), ErrorsEnum.ERR_MCS_PROFIL_0007);
				}
				if (StringUtils.isNotEmpty(mediaId)) {
					isDeleted = mediaMCS.deletePicture(mediaId);
					if (isDeleted) {
						Profil profil = profilFactory.profilDTOToProfil(profilDTO);
						profil.setMediaId(null);
						profil = profilCUDSM.update(profil);
						profilDTO = profilFactory.profilToProfilDTO(profil);
					}
				} else {
					throw new FunctionalInvalidDataException(new MediaDTO(), ErrorsEnum.ERR_MCS_PROFIL_0053);
				}
			}
		} else {
			throw new FunctionalInvalidDataException(new MediaDTO(), ErrorsEnum.ERR_MCS_PROFIL_0024);
		}

		return profilDTO;
	}

	@Override
	public ProfilDTO deleteBackground(String profilId) {
		if (StringUtils.isNotEmpty(profilId)) {
			ProfilAdminDTO profilAdminDTO = reseauxSociauxMCS.isOwner(profilId);
			if (profilAdminDTO.getIsAdmin()) {
				ProfilDTO profilDTO = profilRSA.getProfil(profilId);

				if (profilDTO == null) {
					throw new ObjetNotFoundException(new ProfilDTO(), ErrorsEnum.ERR_MCS_PROFIL_0007);
				}
				MediaDTO backgroundMediaDTO = profilDTO.getBackgroundDTO();
				if (backgroundMediaDTO != null && backgroundMediaDTO.getId() != null
						&& StringUtils.isNotEmpty(backgroundMediaDTO.getId())) {
					MediaDTO mediaDTO = mediaMCS.deleteMedia(backgroundMediaDTO.getId());
					if (mediaDTO != null) {
						Profil profil = profilFactory.profilDTOToProfil(profilDTO);
						profil.setBackgroundId(null);
						profil = profilCUDSM.update(profil);
						profilDTO = profilFactory.profilToProfilDTO(profil);
					}
					return profilDTO;
				} else {
					throw new FunctionalInvalidDataException(new MediaDTO(), ErrorsEnum.ERR_MCS_PROFIL_0053);
				}
			}
		} else {
			throw new FunctionalInvalidDataException(new MediaDTO(), ErrorsEnum.ERR_MCS_PROFIL_0024);
		}

		return null;
	}

	@Override
	public ProfilDTO updatePictureInProfil(String userId, MediaDTO mediaDTO) {
		ProfilDTO profilDTO = null;
		if (StringUtils.isNotEmpty(userId)) {
			if (mediaDTO != null) {
				profilDTO = profilRSA.getProfil(userId);
				if (profilDTO != null) {
					profilDTO.setMediaDTO(mediaDTO);
					profilCUDSM.update(profilFactory.profilDTOToProfil(profilDTO));
					profilDTO = profilRSA.getProfil(userId);
				} else {
					throw new ObjetNotFoundException(new ProfilDTO(), ErrorsEnum.ERR_MCS_PROFIL_0007);
				}
			} else {
				throw new ObjetNotFoundException(new ProfilDTO(), ErrorsEnum.ERR_MCS_PROFIL_0053);
			}
		} else {
			throw new ObjetNotFoundException(new ProfilDTO(), ErrorsEnum.ERR_MCS_PROFIL_0001);
		}

		return profilDTO;
	}

	@Override
	public ProfilDTO updateProfileInitialisedStatus(String userName, Boolean initialisedStatus) {

		ProfilDTO profilDTO = profilRSA.getProfil(userName);

		if (profilDTO == null) {
			throw new ObjetNotFoundException(new ProfilDTO(), ErrorsEnum.ERR_MCS_PROFIL_0007);
		}

		profilDTO.setIsProfileInitialised(initialisedStatus);
		profilCUDSM.update(profilFactory.profilDTOToProfil(profilDTO));

		return profilDTO;
	}

	@Override
	public ProfilDTO share(String userName, String userId, PostDTO postDTO) {
		ProfilDTO profilDTO = profilRSA.getProfil(userId);

		if (profilDTO.isError()) {
			throw new ObjetNotFoundException(new ProfilDTO(), ErrorsEnum.ERR_MCS_PROFIL_0056);
		}

		postDTO.setOwnerId(userName);
		postDTO.setMessage(profilDTO.getFirstname() + profilDTO.getLastname());
		postDTO.setSharedFromPost(userId);
		postDTO.setType("SHARED_PROFIL");

		PostDTO post;

		if (!postDTO.getGroupId().isEmpty()) {
			post = reseauxSociauxMCS.createPostGroup(postDTO);
		} else {
			post = reseauxSociauxMCS.createPost(postDTO);
		}

		if (post.isError()) {
			throw new ObjectSaveException(profilDTO, ErrorsEnum.ERR_MCS_PROFIL_0056);
		}

		profilDTO.setError(false);
		profilDTO.setMessage("Profil has been shared");
		return profilDTO;

	}

	@Override
	public ProfilDTO updateBackgroundPictureInProfil(String userId, MediaDTO mediaDTO) {
		ProfilDTO profilDTO = null;
		if (StringUtils.isNotEmpty(userId)) {
			if (mediaDTO != null) {
				profilDTO = profilRSA.getProfil(userId);
				if (profilDTO != null) {
					profilDTO.setBackgroundDTO(mediaDTO);
					profilCUDSM.update(profilFactory.profilDTOToProfil(profilDTO));
					profilDTO = profilRSA.getProfil(userId);
				} else {
					throw new ObjetNotFoundException(new ProfilDTO(), ErrorsEnum.ERR_MCS_PROFIL_0007);
				}
			} else {
				throw new ObjetNotFoundException(new ProfilDTO(), ErrorsEnum.ERR_MCS_PROFIL_0053);
			}
		} else {
			throw new ObjetNotFoundException(new ProfilDTO(), ErrorsEnum.ERR_MCS_PROFIL_0001);
		}

		return profilDTO;
	}

	@Override
	public ProfilDTO updatePhoneNumber(String phoneNumber) {
		ProfilDTO profilDTO = new ProfilDTO();

		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName = user.getUsername();

		if (StringUtils.isEmpty(userName)) {
			profilDTO.setError(true);
			profilDTO.setErrorMessage(ErrorsEnum.ERR_MCS_PROFIL_0001.getErrorMessage());
			profilDTO.setErrorCode("ERR_MCS_PROFIL_0001");
			return profilDTO;
		}

		profilDTO = profilRSA.getProfil(userName);
		if (profilDTO != null && StringUtils.isNotEmpty(profilDTO.getId())) {
			profilDTO.setPhoneNumber(phoneNumber);
		} else {
			profilDTO.setError(true);
			profilDTO.setErrorMessage(ErrorsEnum.ERR_MCS_PROFIL_0001.getErrorMessage());
			profilDTO.setErrorCode("ERR_MCS_PROFIL_0001");
			return profilDTO;
		}

		// to do a different forms of validation phone number
		profilCUDSM.update(profilFactory.profilDTOToProfil(profilDTO));
		profilDTO.setError(false);
		profilDTO.setMessage("User information updated");
		return profilDTO;
	}

	@Override
	public ProfilDTO setProfilHasMedia(ProfilHasMediaDTO profilHasMediaDTO) {
		if (StringUtils.isEmpty(profilHasMediaDTO.getProfilId())) {
			throw new FunctionalInvalidDataException(new ProfilDTO(), ErrorsEnum.ERR_MCS_PROFIL_0024);
		}
		Profil profil = profilRSM.getProfilById(profilHasMediaDTO.getProfilId());
		if (profil == null)
			throw new ObjetNotFoundException(new ProfilDTO(), ErrorsEnum.ERR_MCS_PROFIL_0082);
		if (profilHasMediaDTO.getType() != null) {
			if (profilHasMediaDTO.getType().equals("MEDIA"))
				profil.setHasMedia(profilHasMediaDTO.getHasMedia() != null ? profilHasMediaDTO.getHasMedia() : false);
			else if (profilHasMediaDTO.getType().equals("BACKGROUND"))
				profil.setHasBackground(
						profilHasMediaDTO.getHasMedia() != null ? profilHasMediaDTO.getHasMedia() : false);
			profilCUDSM.update(profil);
		}

		return profilRSA.getProfilInformation(profil.getUsername());
	}

	@Override
	public IsHasMediaUpdatedDTO updateAllHasMedia(String type) {
		IsHasMediaUpdatedDTO isHasMediaUpdatedDTO = new IsHasMediaUpdatedDTO();
		if (type == null)
			return isHasMediaUpdatedDTO;
		switch (type) {
		case "PROFIL":
			isHasMediaUpdatedDTO.setHasMediaProfilUpdated(updateProfilHasMedia());
			break;
		case "GROUP":
			isHasMediaUpdatedDTO.setHasMediaGroupUpdated(groupCUDSA.updateHasMediaGroup());
			break;
		case "CORPORATION":
			isHasMediaUpdatedDTO.setHasMediaCorporationUpdated(corporationCUDSA.updateHasMediaCorporation());
			break;
		case "PRODUCTION":
			isHasMediaUpdatedDTO.setHasMediaProductionUpdated(productionCUDSA.updateHasMediaProduction());
			break;
		case "ALL":
			isHasMediaUpdatedDTO.setHasMediaProfilUpdated(updateProfilHasMedia());
			isHasMediaUpdatedDTO.setHasMediaGroupUpdated(groupCUDSA.updateHasMediaGroup());
			isHasMediaUpdatedDTO.setHasMediaCorporationUpdated(corporationCUDSA.updateHasMediaCorporation());
			isHasMediaUpdatedDTO.setHasMediaProductionUpdated(productionCUDSA.updateHasMediaProduction());
			break;
		}
		return isHasMediaUpdatedDTO;
	}

	@Override
	public void sendNewAnnounceToBO(ProfilForBODTO profilForBODTO) {

		// Send request to BO
		// TODO BO remettre
//		RestTemplate restTemplate = new RestTemplate();
//		restTemplate.postForObject(UrlSendRepportToBo + "create", profilForBODTO, String.class);

	}

	@Override
	public void sendUpdateAnnounceToBO(ProfilForBODTO profilForBODTO) {

		// Send request to BO
		// TODO BO remettre
//		RestTemplate restTemplate = new RestTemplate();
//
//		restTemplate.postForObject(UrlSendRepportToBo + "update/" + profilForBODTO.getProfil().getUsername(),
//				profilForBODTO, String.class);
	}

	private boolean updateProfilHasMedia() {
		boolean isUpdated = false;
		List<Profil> profilList = profilRSM.findAll().stream().filter(p -> p.getHasMedia() == null || !p.getHasMedia())
				.collect(Collectors.toList());
		for (Profil profil : profilList) {
			if (StringUtils.isEmpty(profil.getMediaId()))
				profil.setMediaId(UUID.randomUUID().toString());
		}
		List<ProfilDTO> profilDTOS = profilFactory.toDTOs(profilList);
		if (profilDTOS != null && profilDTOS.size() > 0) {

			// MEDIA
			List<MapMediaDTO> mapMediaDTOS = profilDTOS.stream().map(this::mapMediaDTO).collect(Collectors.toList());
			if (mapMediaDTOS.size() > 0) {
				List<String> postIds = mediaMCS.listHasMedia(mapMediaDTOS);
				if (postIds.size() > 0) {
					List<ProfilDTO> profilDTOList = profilDTOS.stream()
							.filter(profilDTO -> postIds.contains(profilDTO.getId())).map(this::mapProfilToHasMedia)
							.collect(Collectors.toList());
					List<Profil> posts = profilCUDSM.updateAllProfil(profilFactory.toDOs(profilDTOList));
					isUpdated = posts.stream().allMatch(Profil::getHasMedia);
				}
			}
			// BACKGROUND
			List<MapMediaDTO> mapMediaBackgroundDTOS = profilDTOS.stream().map(this::mapMediaBackgroundDTO)
					.collect(Collectors.toList());

			if (mapMediaBackgroundDTOS.size() > 0) {
				List<String> postIds = mediaMCS.listHasMedia(mapMediaBackgroundDTOS);
				if (postIds.size() > 0) {
					List<ProfilDTO> profilDTOList = profilDTOS.stream()
							.filter(profilDTO -> postIds.contains(profilDTO.getId()))
							.map(this::mapProfilToHasBackground).collect(Collectors.toList());
					List<Profil> posts = profilCUDSM.updateAllProfil(profilFactory.toDOs(profilDTOList));
					isUpdated = posts.stream().allMatch(Profil::getHasBackground);
				}
			}
		}
		return isUpdated;
	}

	private MapMediaDTO mapMediaDTO(ProfilDTO profilDTO) {
		MapMediaDTO mapMediaDTO = new MapMediaDTO();
		mapMediaDTO.setMediaId(profilDTO.getMediaId());
		mapMediaDTO.setTarget(profilDTO.getId());
		return mapMediaDTO;
	}

	private MapMediaDTO mapMediaBackgroundDTO(ProfilDTO profilDTO) {
		MapMediaDTO mapMediaDTO = new MapMediaDTO();
		mapMediaDTO.setMediaId(profilDTO.getBackgroundId());
		mapMediaDTO.setTarget(profilDTO.getId());
		return mapMediaDTO;
	}

	private ProfilDTO mapProfilToHasMedia(ProfilDTO profilDTO) {
		profilDTO.setHasMedia(true);
		return profilDTO;
	}

	private ProfilDTO mapProfilToHasBackground(ProfilDTO profilDTO) {
		profilDTO.setHasBackground(true);
		return profilDTO;
	}
}
