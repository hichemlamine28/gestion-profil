package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.profil;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ErrorsEnum;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ExportFileNotReadyException;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.FunctionalInvalidDataException;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ObjetNotFoundException;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.TechnicalInputOutputException;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.PageContactsDTOFactory;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.commun.PageableFactory;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.profil.ProfilMapper;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.profil.PublicProfilMapper;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.constants.DownloadStatus;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.constants.MediaType;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Profil;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Skill;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.UserAuth;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.neo4j.User;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ContactConsultationDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.MediaDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.PageContactsDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ParcoursDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.PatentDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ProductionsDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ProfilAdminDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ProfilDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.PublicProfilDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.QualificationDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.businessdelegate.ActiveSubscriptionDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.businessdelegate.ReseauSocialUserDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.businessdelegate.SubscriptionDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.CustomPageDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.utils.HelpPage;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.contact_consultation.ContactConsultationRSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.parcours.ParcoursRSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.productions.ProductionRSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.productions.patent.PatentRSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.qualification.QualificationRSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.skill.SkillRSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.businessdelegate.AbonnementMCS;
import com.arkeup.link_innov.gestion_profil_mcs.service.businessdelegate.MediaMCS;
import com.arkeup.link_innov.gestion_profil_mcs.service.businessdelegate.ReseauxSociauxMCS;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.profil.ProfilCUDSM;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.information.UserInformationRSM;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.profil.ProfilRSM;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.skill.SkillRSM;
import com.google.common.base.Strings;
import com.opencsv.CSVWriter;
import com.thoughtworks.xstream.converters.reflection.ObjectAccessException;

/**
 * @author bona
 */
@Service
public class ProfilRSAImpl implements ProfilRSA {

	@Autowired
	private ProfilMapper profilFactory;

	@Autowired
	private ProfilRSM profilRSM;

	@Autowired
	private ProfilCUDSM profilCUDSM;

	@Autowired
	private PageContactsDTOFactory pageContactsDTOFactory;

	@Autowired
	private UserInformationRSM userInformationRSM;

	@Autowired
	private ReseauxSociauxMCS reseauxSociauxMCS;

	@Autowired
	private MediaMCS mediaMCS;

	@Autowired
	private PublicProfilMapper publicProfilMapper;

	@Autowired
	private ProductionRSA productionRSA;

	@Autowired
	private ParcoursRSA parcoursRSA;

	@Autowired
	private PatentRSA patentRSA;

	@Autowired
	private QualificationRSA qualificationRSA;

	@Autowired
	private PageableFactory<Profil> pageableFactory;

	@Autowired
	private ContactConsultationRSA contactConsultationRSA;

	@Autowired
	private AbonnementMCS abonnementMCS;

	@Autowired
	private SkillRSA skillRSA;

	@Autowired
	private SkillRSM skillRSM;

	public static final SimpleDateFormat SDF = new SimpleDateFormat("dd/MMMM/yyyy HH:mm");

	/**
	 * Ticket : http://jira.arkeup.com/browse/LKV-2402
	 *
	 * @param username
	 * @return
	 */
	@Override
	public ProfilDTO getAuthInformation(String username) {

//		LOGGER.info("GetAuthInformation : Begin to save user history from connection");
//		userHistoryService.addOrUbdateHistory(username, ProfilAction.CONNECT.getValue(),
//				ProfilAction.IDCONNECT.getValue());
//		LOGGER.info("GetAuthInformation : End to save user history from connection");

		ProfilDTO profilDTO = getInfoProfil(username);
		if (profilDTO != null) {
			ActiveSubscriptionDTO activeSubscriptionDTO = abonnementMCS.getUserActiveSubscription();
			if (activeSubscriptionDTO != null) {
				SubscriptionDTO subscriptionDTO = abonnementMCS
						.getSubscription(activeSubscriptionDTO.getIdSubscription());
				if (subscriptionDTO != null) {
					profilDTO.setSubscriptionName(subscriptionDTO.getDisplayName());
				}
			}

			ContactConsultationDTO contactConsultationDTO = contactConsultationRSA.findByUserId(username);
			if (contactConsultationDTO != null) {
				profilDTO.setContactConsultationLastDate(contactConsultationDTO.getContactConsultationLastDate());
				profilDTO.setContactConsultation(true);
			} else {
				profilDTO.setContactConsultation(false);
			}
		}
		return profilDTO;
	}

	@Override
	public ProfilDTO getProfil(String username) {
		return getInfoProfil(username);
	}

	@Override
	public ProfilDTO getProfilInformation(String username) {
		return getInfoProfil(username);
	}

	@Override
	public ProfilDTO getProfilAnonymous(String username) {
		return getInfoProfil(username);
	}

	@Override
	public ProfilDTO getProfilByUserName(String username) {
		return getInfoProfil(username);
	}

	private ProfilDTO getInfoProfil(String username) {
		// TODO add enumeration for action names
		Profil entity = profilRSM.getInformation(username);
		ProfilDTO profilDTO = profilFactory.profilToProfilDTO(entity);
		if (profilDTO != null) {
			if (StringUtils.isEmpty(entity.getMediaId())) {
				profilDTO.setMediaId(UUID.randomUUID().toString());
			}
			if (StringUtils.isEmpty(entity.getBackgroundId())) {
				profilDTO.setBackgroundId(UUID.randomUUID().toString());
			}
			ContactConsultationDTO contactConsultationDTO = contactConsultationRSA.findByUserId(username);
			if (contactConsultationDTO != null) {
				profilDTO.setContactConsultationLastDate(contactConsultationDTO.getContactConsultationLastDate());
				profilDTO.setContactConsultation(true);
			} else {
				profilDTO.setContactConsultation(false);
			}
			int pourcentage = profilIsCompet(entity);
			profilDTO.setPorcentage(pourcentage);
			profilDTO.setError(false);
			profilDTO.setMessage("User information");
		}

		if (profilDTO == null) {
			profilDTO = new ProfilDTO();
			profilDTO.setError(true);
			profilDTO.setErrorMessage(ErrorsEnum.ERR_MCS_PROFIL_0007.getErrorMessage());
			profilDTO.setErrorCode("ERR_MCS_PROFIL_007");
		}
		return profilDTO;
	}

	private int profilIsCompet(Profil profil) {
		int pourcentage = 0;
		if (!Strings.isNullOrEmpty(profil.getMediaId())
				&& !profil.getMediaId().equals("df2567b9-158d-443b-8d38-a7f775549367")) {
			// photo
			pourcentage += 10;
		}
		if (!Strings.isNullOrEmpty(profil.getFirstname()) && !Strings.isNullOrEmpty(profil.getLastname())) {
			// nom et prénom
			pourcentage += 10;
		}
		if (!Strings.isNullOrEmpty(profil.getOccupation()) && !profil.getOccupation().equals("A préciser")) {
			// profession
			pourcentage += 10;
		}
		if (profil.getCompany() != null && !Strings.isNullOrEmpty(profil.getCompany().getName())) {
			// entreprise
			pourcentage += 10;

		}
		if (!Strings.isNullOrEmpty(profil.getZipCode()))

		{
			// codepostal
			pourcentage += 5;
		}
		if (!Strings.isNullOrEmpty(profil.getCity())) {
			// ville
			pourcentage += 3;
		}
		if (!Strings.isNullOrEmpty(profil.getResume())) {
			// résumé
			pourcentage += 5;
		}
		if (!CollectionUtils.isEmpty(profil.getActivityArea())) {
			// dans quel secteur travaillez-vous?
			pourcentage += 10;
		}
		if (!Strings.isNullOrEmpty(profil.getPhoneNumber())) {
			// téléphone
			pourcentage += 2;
		}
		// Production
		List<ProductionsDTO> productionsDTOs = productionRSA.findAllByOwnerId(profil.getUsername());
		if (!CollectionUtils.isEmpty(productionsDTOs)) {
			pourcentage += 10;
		}
		// Parcour
		Pageable pageableParcour = PageRequest.of(0, 100);
		List<ParcoursDTO> parcoursDTOsPerPage = parcoursRSA.getParcours(profil.getUsername(), pageableParcour)
				.getListParcoursDTO().getContent();
		if (!CollectionUtils.isEmpty(parcoursDTOsPerPage)) {
			pourcentage += 10;
		}
		// Qualification
		Pageable pageableQualification = PageRequest.of(0, 1000);
		List<QualificationDTO> qualificationsDTOsPerPage = qualificationRSA
				.listQualification(profil.getUsername(), pageableQualification).getQualificationDTOs().getContent();
		if (!CollectionUtils.isEmpty(qualificationsDTOsPerPage) && qualificationsDTOsPerPage.get(0) != null) {
			pourcentage += 5;
		}
		// Skill
		List<Skill> skills = skillRSM.listSkill(profil.getUsername(), pageableQualification).getContent();
		if (!CollectionUtils.isEmpty(skills) && skills.get(0) != null) {
			pourcentage += 10;
		}

		return pourcentage;
	}

	@Override
	public PageContactsDTO getProfilByIds(List<String> ids, String type, String filter, Pageable pageable) {
		PageContactsDTO pageContactsDTO = null;
		Page<Profil> foundContacts = profilRSM.getContactInformationsByIds(ids, type, filter, pageable);

		if (foundContacts != null && foundContacts.getContent() != null) {
			for (Profil profil : foundContacts.getContent()) {
				if (StringUtils.isEmpty(profil.getMediaId())) {
					profil.setMediaId(UUID.randomUUID().toString());
				}
				if (StringUtils.isEmpty(profil.getBackgroundId())) {
					profil.setBackgroundId(UUID.randomUUID().toString());
				}
			}
			pageContactsDTO = pageContactsDTOFactory.getInstance(foundContacts);
		}
		return pageContactsDTO;
	}

	@Override
	public ProfilDTO getProfilByPseudoName(String pseudoName) {
		UserAuth user = userInformationRSM.getUserByPseudo(pseudoName);
		ProfilDTO result = new ProfilDTO();
		result.setError(true);

		if (user != null) {
			Profil entity = profilRSM.getInformation(user.getUsername());

			if (entity != null) {
				result = profilFactory.profilToProfilDTO(entity);
				if (result != null && StringUtils.isEmpty(entity.getMediaId())) {
					result.setMediaId(UUID.randomUUID().toString());
				}
				if (result != null && StringUtils.isEmpty(entity.getBackgroundId())) {
					result.setBackgroundId(UUID.randomUUID().toString());
				}

				if (result != null) {
					result.setError(false);
					result.setMessage("User information");
					result.setPseudoName(pseudoName);

					return result;
				}
			}

		}

		result.setErrorMessage(ErrorsEnum.ERR_MCS_PROFIL_0007.getErrorMessage());
		result.setErrorCode("ERR_MCS_PROFIL_0007");

		return result;
	}

	@Override
	public List<ProfilDTO> getListProfilByIds(List<String> userIds) {
		List<ProfilDTO> profilsDto = null;
		List<Profil> profils = profilRSM.getProfilsInformationsByIds(userIds);

		if (profils != null) {
			for (Profil profil : profils) {
				if (StringUtils.isEmpty(profil.getMediaId())) {
					profil.setMediaId(UUID.randomUUID().toString());
				}
				if (StringUtils.isEmpty(profil.getBackgroundId())) {
					profil.setBackgroundId(UUID.randomUUID().toString());
				}
				profilsDto.add(profilFactory.profilToProfilDTO(profil));
			}
		}

		return profilsDto;
	}

	@Override
	public ProfilAdminDTO isOwner(String userId, String profilId) {
		ProfilAdminDTO profilAdminDTO = new ProfilAdminDTO();
		Profil profil = null;
		if (StringUtils.isNotEmpty(profilId)) {
			profil = profilRSM.getInformation(profilId);
			if (profil != null) {
				if (StringUtils.isNotEmpty(userId)) {
					profilAdminDTO.setIsAdmin(StringUtils.equals(userId, profil.getUsername()));
				} else {
					throw new FunctionalInvalidDataException(new ProfilDTO(), ErrorsEnum.ERR_MCS_PROFIL_0001);
				}
			} else {
				throw new ObjetNotFoundException(new ProfilDTO(), ErrorsEnum.ERR_MCS_PROFIL_0007);
			}
		} else {
			throw new FunctionalInvalidDataException(new ProfilDTO(), ErrorsEnum.ERR_MCS_PROFIL_0024);
		}
		return profilAdminDTO;
	}

	@Override
	public ProfilDTO checkisProfileInitialised(String userId) {

		ProfilDTO res = new ProfilDTO();

		ProfilDTO profilDTO = profilFactory.profilToProfilDTO(profilRSM.getInformation(userId));
		if (profilDTO == null) {
			throw new ObjetNotFoundException(new ProfilDTO(), ErrorsEnum.ERR_MCS_PROFIL_0007);
		}

		res.setId(profilDTO.getId());
		res.setIsProfileInitialised(profilDTO.getIsProfileInitialised());

		return res;
	}

	@Override
	public PublicProfilDTO getPublicProfil(String userId) {
		Profil entity = profilRSM.getInformation(userId);
		if (entity != null && StringUtils.isEmpty(entity.getMediaId())) {
			entity.setMediaId(UUID.randomUUID().toString());
		}
		if (entity != null && StringUtils.isEmpty(entity.getBackgroundId())) {
			entity.setBackgroundId(UUID.randomUUID().toString());
		}
		PublicProfilDTO profilPublicDTO = publicProfilMapper.profilToProfilPublicDTO(entity);
		if (profilPublicDTO == null) {
			throw new ObjetNotFoundException(new PublicProfilDTO(), ErrorsEnum.ERR_MCS_PROFIL_0007);
		}

		profilPublicDTO.setError(false);
		profilPublicDTO.setMessage("Public profil information");
		return profilPublicDTO;
	}

	@Override
	public ProfilAdminDTO verifyPermission(String userId) {
		return reseauxSociauxMCS.isOwner(userId);
	}

	@Override
	public MediaDTO getContactPicture(String userId) {
		ProfilDTO profilDTO = profilFactory.profilToProfilDTO(profilRSM.getInformation(userId));
		if (profilDTO == null) {
			throw new ObjetNotFoundException(new ProfilDTO(), ErrorsEnum.ERR_MCS_PROFIL_0007);
		}
		if (StringUtils.isEmpty(profilDTO.getMediaDTO().getId())) {
			return null;
		}
		// TODO Verify if profil is public (actually, all profiles are public)

		return mediaMCS.findPictureByUserId(userId);
	}

	@Override
	public CustomPageDTO<ProfilDTO> getPaginatedProfilByIds(List<String> userIds, Pageable pageable) {
		CustomPageDTO<ProfilDTO> customPageDTO = new CustomPageDTO<>();
		Page<Profil> profils = profilRSM.getPaginatedProfilsInformationsByIds(userIds, pageable);
		if (profils != null && profils.getContent() != null) {
			for (Profil entity : profils.getContent()) {
				if (entity != null && StringUtils.isEmpty(entity.getMediaId())) {
					entity.setMediaId(UUID.randomUUID().toString());
				}
				if (entity != null && StringUtils.isEmpty(entity.getBackgroundId())) {
					entity.setBackgroundId(UUID.randomUUID().toString());
				}
			}
		}
		HelpPage<Profil> helpPageProfil = pageableFactory.pageToHelpPage(profils);
		HelpPage<ProfilDTO> helpPageProfilDTO = profilFactory
				.helpPageCorporationToHelpPageCorporationDTOs(helpPageProfil);

		customPageDTO.setPageDTOs(helpPageProfilDTO);

		return customPageDTO;
	}

	@Override
	public DeferredResult<ProfilDTO> exportProfilToCSV(String token, String userId) {
		Profil profil = profilRSM.getInformation(userId);
		if (profil == null) {
			throw new ObjetNotFoundException(new ProfilDTO(), ErrorsEnum.ERR_MCS_PROFIL_0007);
		}
		DeferredResult<ProfilDTO> output = new DeferredResult<>();
		String exportId = profil.getExportId();
		if (StringUtils.isNotBlank(exportId)) {
			MediaDTO mediaDTO = mediaMCS.getMedia(exportId);
			long duration = new Date().getTime() - mediaDTO.getDateCreation().getTime();
			if (TimeUnit.MILLISECONDS.toHours(duration) < 24) {
				profil.setDownloadStatus(DownloadStatus.READY);
				profilCUDSM.update(profil);
				ProfilDTO profilDTO = profilFactory.profilToProfilDTO(profil);
				output.setResult(profilDTO);
				return output;
			}
		}
		output = generateExport(token, userId, profil);
		return output;
	}

	@Override
	public String getExportFileUrl(String userId) {
		Profil profil = profilRSM.getInformation(userId);
		if (profil == null) {
			throw new ObjetNotFoundException(new ProfilDTO(), ErrorsEnum.ERR_MCS_PROFIL_0007);
		}
		if (DownloadStatus.READY.equals(profil.getDownloadStatus())) {
			if (StringUtils.isNotEmpty(profil.getExportId())) {
				profil.setDownloadStatus(DownloadStatus.NONE);
				profilCUDSM.update(profil);
				MediaDTO mediaDTO = mediaMCS.getMedia(profil.getExportId());
				return mediaDTO.getDefaultUrl();
			} else {
				throw new ObjetNotFoundException(new MediaDTO(), ErrorsEnum.ERR_MCS_PROFIL_0075);
			}
		} else {
			throw new ExportFileNotReadyException(new ProfilDTO(), ErrorsEnum.ERR_MCS_PROFIL_0074);
		}
	}

	private DeferredResult<ProfilDTO> generateExport(String token, String userId, Profil profil) {
		DeferredResult<ProfilDTO> output = new DeferredResult<>();
		profil.setDownloadStatus(DownloadStatus.PENDING);
		profilCUDSM.update(profil);
		ForkJoinPool.commonPool().submit(() -> {
			MediaDTO mediaDTO;
			try {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ZipOutputStream zipOutputStream = new ZipOutputStream(baos);

				// Base profil
				ByteArrayOutputStream profilBaos = exportProfil(profil);
				zipOutputStream.putNextEntry(new ZipEntry("profil.csv"));
				zipOutputStream.write(profilBaos.toByteArray());

				// Productions
				ByteArrayOutputStream productionBaos = exportProductions(userId);
				zipOutputStream.putNextEntry(new ZipEntry("production.csv"));
				zipOutputStream.write(productionBaos.toByteArray());

				// Parcours
				ByteArrayOutputStream parcoursBaos = exportParcours(userId);
				zipOutputStream.putNextEntry(new ZipEntry("parcours.csv"));
				zipOutputStream.write(parcoursBaos.toByteArray());

				// Patents
				ByteArrayOutputStream patentBaos = exportPatents(userId);
				zipOutputStream.putNextEntry(new ZipEntry("brevet.csv"));
				zipOutputStream.write(patentBaos.toByteArray());

				// Qualification
				ByteArrayOutputStream qualificationBaos = exportQualifications(userId);
				zipOutputStream.putNextEntry(new ZipEntry("qualification.csv"));
				zipOutputStream.write(qualificationBaos.toByteArray());

				// Contact
				ByteArrayOutputStream contactBaos = exportContacts(token, userId);
				zipOutputStream.putNextEntry(new ZipEntry("contact.csv"));
				zipOutputStream.write(contactBaos.toByteArray());

				zipOutputStream.closeEntry();

				// close the ZipOutputStream
				zipOutputStream.close();

				// Save file to google storage
				SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy");
				String zipFileName = String.format("Basic_LinkinnovDataExport_%s.zip", sdf.format(new Date()));
				String base64zip = Base64.getEncoder().encodeToString(baos.toByteArray());
				MediaDTO mediaDTOToken = mediaMCS.generateMediaToken(token, MediaType.DOCUMENT.name());
				mediaDTO = mediaMCS.uploadFile(token, mediaDTOToken.getId(), base64zip,
						MediaType.DOCUMENT.name().toLowerCase() + "/" + zipFileName);
				profil.setExportId(mediaDTO.getId());

			} catch (IOException ex) {
				throw new TechnicalInputOutputException(new ProfilDTO(), ErrorsEnum.ERR_MCS_INPUT_OUTPUT);
			}
			profil.setDownloadStatus(DownloadStatus.READY);
			profilCUDSM.update(profil);
			ProfilDTO profilDTO = profilFactory.profilToProfilDTO(profil);
			output.setResult(profilDTO);
		});
		return output;
	}

	private ByteArrayOutputStream exportProfil(Profil profil) {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		try {
			CSVWriter csvWriter = new CSVWriter(new OutputStreamWriter(byteArrayOutputStream));
			String[] header = { "Nom", "Prénom", "Poste", "Description", "Adresse" };
			csvWriter.writeNext(header);
			String[] data = { profil.getFirstname() != null ? profil.getFirstname().toUpperCase() : null,
					profil.getLastname(), profil.getOccupation(), profil.getResume(), profil.getCity() };
			csvWriter.writeNext(data);
			csvWriter.close();
		} catch (IOException e) {
			throw new TechnicalInputOutputException(new ProfilDTO(), ErrorsEnum.ERR_MCS_INPUT_OUTPUT);
		}
		return byteArrayOutputStream;
	}

	private ByteArrayOutputStream exportProductions(String userId) {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		try {
			CSVWriter csvWriter = new CSVWriter(new OutputStreamWriter(byteArrayOutputStream));
			String[] header = { "Type", "Titre", "Collaborateurs", "Tags", "URL", "Description" };
			csvWriter.writeNext(header);

			// Production
			List<ProductionsDTO> productionDTOs = productionRSA.findAllByOwnerId(userId);

			for (ProductionsDTO productionDTO : productionDTOs) {
				StringBuffer sbCollaborators = new StringBuffer();
				for (User user : productionDTO.getCollaborators()) {
					sbCollaborators.append(
							String.format("%s %s", user.getFirstName() != null ? user.getFirstName().toUpperCase() : "",
									user.getLastName() != null ? user.getLastName() : ""))
							.append(",");
				}
				StringBuffer sbTags = new StringBuffer();
				if (CollectionUtils.isNotEmpty(productionDTO.getTags())) {
					for (String tag : productionDTO.getTags()) {
						sbTags.append(tag).append(",");
					}
				}
				String[] data = { productionDTO.getProductType(), productionDTO.getTitle(),
						sbCollaborators.length() > 0 ? sbCollaborators.substring(0, sbCollaborators.length() - 1)
								: null,
						sbTags.length() > 0 ? sbTags.substring(0, sbTags.length() - 1) : null, productionDTO.getUrl(),
						productionDTO.getDescription() };
				csvWriter.writeNext(data);
			}

			csvWriter.close();
		} catch (IOException e) {
			throw new TechnicalInputOutputException(new ProfilDTO(), ErrorsEnum.ERR_MCS_INPUT_OUTPUT);
		}
		return byteArrayOutputStream;
	}

	private ByteArrayOutputStream exportParcours(String userId) {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		try {
			CSVWriter csvWriter = new CSVWriter(new OutputStreamWriter(byteArrayOutputStream));
			String[] header = { "Profession", "Lieu", "Catégorie", "Entreprise", "Description", "Date début",
					"Date fin" };
			csvWriter.writeNext(header);

			int pageSize = 100;
			int pageIndex = 0;

			List<ParcoursDTO> parcoursDTOs = new ArrayList<ParcoursDTO>();
			boolean hasElements = true;
			pageIndex = 0;
			while (hasElements) {
				Pageable pageable = PageRequest.of(pageIndex, pageSize, Sort.by("createDate").descending());
				List<ParcoursDTO> parcoursDTOsPerPage = parcoursRSA.getParcours(userId, pageable).getListParcoursDTO()
						.getContent();
				if (CollectionUtils.isNotEmpty(parcoursDTOsPerPage)) {
					parcoursDTOs.addAll(parcoursDTOsPerPage);
					pageIndex++;
				} else {
					hasElements = false;
				}
			}

			for (ParcoursDTO parcoursDTO : parcoursDTOs) {
				String[] data = { parcoursDTO.getOccupation(), parcoursDTO.getLieu(),
						parcoursDTO.getCategory() != null ? parcoursDTO.getCategory().getName() : null,
						parcoursDTO.getCompany() != null ? parcoursDTO.getCompany().getName() : null,
						parcoursDTO.getDescription(),
						parcoursDTO.getStartDate() != null ? SDF.format(parcoursDTO.getStartDate()) : null,
						parcoursDTO.getEndDate() != null ? SDF.format(parcoursDTO.getEndDate()) : null };
				csvWriter.writeNext(data);
			}

			csvWriter.close();
		} catch (IOException e) {
			throw new TechnicalInputOutputException(new ProfilDTO(), ErrorsEnum.ERR_MCS_INPUT_OUTPUT);
		}
		return byteArrayOutputStream;
	}

	private ByteArrayOutputStream exportPatents(String userId) {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		try {
			CSVWriter csvWriter = new CSVWriter(new OutputStreamWriter(byteArrayOutputStream));
			String[] header = { "Titre", "Collaborateurs", "Tags", "URL", "Description", "Date de dépôt", "Inventeurs",
					"N° de publication", "Titre non confidentiel" };
			csvWriter.writeNext(header);

			List<PatentDTO> patentDTOs = patentRSA.findAllByOwnerId(userId);

			for (PatentDTO patentDTO : patentDTOs) {
				StringBuffer sbCollaborators = new StringBuffer();
				for (User user : patentDTO.getCollaborators()) {
					sbCollaborators.append(
							String.format("%s %s", user.getFirstName() != null ? user.getFirstName().toUpperCase() : "",
									user.getLastName() != null ? user.getLastName() : ""))
							.append(",");
				}
				StringBuffer sbTags = new StringBuffer();
				if (CollectionUtils.isNotEmpty(patentDTO.getTags())) {
					for (String tag : patentDTO.getTags()) {
						sbTags.append(tag).append(",");
					}
				}
				StringBuffer sbInventors = new StringBuffer();
				for (User user : patentDTO.getInventors()) {
					sbInventors.append(
							String.format("%s %s", user.getFirstName() != null ? user.getFirstName().toUpperCase() : "",
									user.getLastName() != null ? user.getLastName() : ""))
							.append(",");
				}
				String[] data = { patentDTO.getTitle(),
						sbCollaborators.length() > 0 ? sbCollaborators.substring(0, sbCollaborators.length() - 1)
								: null,
						sbTags.length() > 0 ? sbTags.substring(0, sbTags.length() - 1) : null, patentDTO.getUrl(),
						patentDTO.getDescription(),
						patentDTO.getPriorityDepositDate() != null ? SDF.format(patentDTO.getPriorityDepositDate())
								: null,
						sbInventors.length() > 0 ? sbInventors.substring(0, sbInventors.length() - 1) : null,
						patentDTO.getPublicationNumber(), patentDTO.getTitleNonConfidential() };

				csvWriter.writeNext(data);
			}
			csvWriter.close();
		} catch (IOException e) {
			throw new TechnicalInputOutputException(new ProfilDTO(), ErrorsEnum.ERR_MCS_INPUT_OUTPUT);
		}
		return byteArrayOutputStream;
	}

	private ByteArrayOutputStream exportQualifications(String userId) {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		try {
			CSVWriter csvWriter = new CSVWriter(new OutputStreamWriter(byteArrayOutputStream));
			String[] header = { "Enseigne", "Diplôme", "Domaine", "résultat", "Description", "Date début", "Date fin" };
			csvWriter.writeNext(header);

			int pageSize = 100;
			int pageIndex = 0;

			List<QualificationDTO> qualificationDTOs = new ArrayList<QualificationDTO>();
			boolean hasElements = true;
			pageIndex = 0;
			while (hasElements) {
				Pageable pageable = PageRequest.of(pageIndex, pageSize, Sort.by("createDate").descending());
				List<QualificationDTO> qualificationsDTOsPerPage = qualificationRSA.listQualification(userId, pageable)
						.getQualificationDTOs().getContent();
				if (CollectionUtils.isNotEmpty(qualificationsDTOsPerPage)) {
					qualificationDTOs.addAll(qualificationsDTOsPerPage);
					pageIndex++;
				} else {
					hasElements = false;
				}
			}

			for (QualificationDTO qualificationDTO : qualificationDTOs) {
				String[] data = { qualificationDTO.getInstitution(), qualificationDTO.getDegree(),
						qualificationDTO.getField(), qualificationDTO.getResult(), qualificationDTO.getDescription(),
						qualificationDTO.getStartDate() != null ? SDF.format(qualificationDTO.getStartDate()) : null,
						qualificationDTO.getEndDate() != null ? SDF.format(qualificationDTO.getEndDate()) : null };
				csvWriter.writeNext(data);
			}

			csvWriter.close();
		} catch (IOException e) {
			throw new TechnicalInputOutputException(new ProfilDTO(), ErrorsEnum.ERR_MCS_INPUT_OUTPUT);
		}
		return byteArrayOutputStream;
	}

	private ByteArrayOutputStream exportContacts(String token, String userId) {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		try {
			CSVWriter csvWriter = new CSVWriter(new OutputStreamWriter(byteArrayOutputStream));
			String[] header = { "Nom", "Prénom", "Employeur", "Profession" };
			csvWriter.writeNext(header);

			int pageSize = 100;
			int pageIndex = 0;

			List<ReseauSocialUserDTO> contactDTOs = new ArrayList<ReseauSocialUserDTO>();
			boolean hasElements = true;
			pageIndex = 0;
			List<String> sorts = new ArrayList<String>();
			sorts.add("f.firstName,asc");
			sorts.add("f.lastName,asc");
			while (hasElements) {
				List<ReseauSocialUserDTO> contactsDTOsPerPage = reseauxSociauxMCS
						.getContacts(token, pageIndex, pageSize, sorts).getContactDTOs().getContent();
				if (CollectionUtils.isNotEmpty(contactsDTOsPerPage)) {
					contactDTOs.addAll(contactsDTOsPerPage);
					pageIndex++;
				} else {
					hasElements = false;
				}
			}
			for (ReseauSocialUserDTO contact : contactDTOs) {
				String[] data = { contact.getFirstName() != null ? contact.getFirstName().toUpperCase() : null,
						contact.getLastName(), contact.getEmployerName(), contact.getProfession() };
				csvWriter.writeNext(data);
			}

			csvWriter.close();
		} catch (IOException e) {
			throw new TechnicalInputOutputException(new ProfilDTO(), ErrorsEnum.ERR_MCS_INPUT_OUTPUT);
		}
		return byteArrayOutputStream;
	}

	@Override
	public CustomPageDTO<ProfilDTO> getNewSubscribedUsers(Pageable pageable) {
		CustomPageDTO<ProfilDTO> customPageDTO = new CustomPageDTO<>();
		Page<Profil> profils = profilRSM.getNewSubscribedUsers(pageable);
		HelpPage<Profil> helpPageProfil = pageableFactory.pageToHelpPage(profils);
		HelpPage<ProfilDTO> helpPageProfilDTO = profilFactory
				.helpPageCorporationToHelpPageCorporationDTOs(helpPageProfil);

		customPageDTO.setPageDTOs(helpPageProfilDTO);

		return customPageDTO;
	}

	@Override
	public CustomPageDTO<ProfilDTO> getPaginatedProfil(List<String> userIds, String filter, String categorie,
			Pageable pageable) {
		CustomPageDTO<ProfilDTO> customPageDTO = new CustomPageDTO<>();
		Page<Profil> profils = profilRSM.getPaginatedProfilsInformations(userIds, filter, categorie, pageable);
		HelpPage<Profil> helpPageProfil = pageableFactory.pageToHelpPage(profils);
		HelpPage<ProfilDTO> helpPageProfilDTO = profilFactory
				.helpPageCorporationToHelpPageCorporationDTOs(helpPageProfil);

		customPageDTO.setPageDTOs(helpPageProfilDTO);

		return customPageDTO;
	}

	@Override
	public Profil isExistMail(String mail) {
		Profil profil = new Profil();
		if (profilRSM.isExistMail(mail)) {
			throw new TechnicalInputOutputException(new ProfilDTO(), ErrorsEnum.ERR_MCS_PROFIL_EMAIL_ALREADY_EXIST);
		}
		profil.setError(false);
		return null;
	}

	@Override
	public ProfilDTO getProfilByUsername(String username) {
		Profil profil = profilRSM.getInformation(username);

		if (profil != null) {
			return profilFactory.profilToProfilDTO(profil);
		}

//		throw new ObjetNotFoundException(new ProfilDTO(), ErrorsEnum.ERR_MCS_PROFIL_0082);

		throw new ObjectAccessException(
				"This user Name ... " + username + " Error message " + ErrorsEnum.ERR_MCS_PROFIL_0082);
	}

	// TODO
	@Override
	public ProfilDTO getProfilById(String userId) {
		Profil profil = profilRSM.getProfilById(userId);
		return profilFactory.profilToProfilDTO(profil);

	}

	@Override
	public List<Profil> getListProfilByFirstName(User sugUser) {
		List<Profil> profils = new ArrayList<>();
		if (sugUser != null) {
			profils.addAll(profilRSM.getProfilsInformationsFirstName(sugUser.getFirstName()));
			return profils;
		} else
			return null;
	}

	@Override
	public List<Profil> getListProfilByFirstNameConcatLastName(User sugUser) {
		List<Profil> profils = new ArrayList<>();
		if (sugUser != null) {
			if (!StringUtils.isBlank(sugUser.getFirstName()) && !StringUtils.isBlank(sugUser.getLastName())) {

				profils.addAll(profilRSM.getListProfilByFirstNameConcatLastName(
						sugUser.getFirstName().concat(" ").concat(sugUser.getLastName())));
			} else
				profils.addAll(profilRSM.getListProfilByFirstNameConcatLastName(sugUser.getFirstName()));
			return profils;
		} else
			return null;
	}

	@Override
	public List<ProfilDTO> getAllProfils(ProfilDTO profilDTO) {
		List<Profil> profils = new ArrayList<>();
		if (profilDTO.getShouldFetchAll()) {

			profils.addAll(profilRSM.findAll());
			return profilFactory.toDTOs(profils);
		} else {
			// TODO Today -7
			profils.addAll(profilRSM.findLastCreatedProfil());
			return profilFactory.toDTOs(profils);

		}

	}
}
