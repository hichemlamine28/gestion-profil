/**
 *
 */
package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ldap.NameAlreadyBoundException;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ErrorsEnum;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.FunctionalInvalidDataException;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ObjectSaveException;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ObjetNotFoundException;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.MailParametersDTOFactory;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.RabbitMQUserDTOFactory;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.RegistrationFactory;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.profil.ProfilFactory;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.profil.ProfilMapper;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.reseausocial.ReseauSocialUserDTOFactory;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.user_auth.UserAuthMapper;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.constants.AttachPMActionType;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.constants.MediaType;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.constants.RoleEnum;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Profil;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Registration;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.UserAuth;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.CategoryDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.CorporationDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.IsLinkValidDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.IsMailSendDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.MediaDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ProfilDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ProfilForBODTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.UserAuthDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.businessdelegate.MailParametersDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.businessdelegate.RabbitMQUserDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.businessdelegate.ReseauSocialUserDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.inscription.InscriptionDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.inscription.SignUpDTO;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.category.CategoryCUDSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.notification.NotificationSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.profil.ProfilCUDSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.user_auth.UserAuthCUDSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.category.CategoryRSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.corporation.CorporationRSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.profil.ProfilRSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.user_auth.UserAuthRSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.businessdelegate.AbonnementMCS;
import com.arkeup.link_innov.gestion_profil_mcs.service.businessdelegate.MediaMCS;
import com.arkeup.link_innov.gestion_profil_mcs.service.businessdelegate.NotificationMCS;
import com.arkeup.link_innov.gestion_profil_mcs.service.businessdelegate.RabbitMQUsersMCS;
import com.arkeup.link_innov.gestion_profil_mcs.service.businessdelegate.ReseauxSociauxMCS;
import com.arkeup.link_innov.gestion_profil_mcs.service.businessdelegate.ReseauxSociauxOAuthCredentialsMCS;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.RegistrationCUDSM;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.profil.ProfilCUDSM;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.user_auth.UserAuthCUDSM;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.profil.ProfilRSM;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.user_auth.UserAuthRSM;

/**
 * @author mikajy
 *
 */
@Service
public class SignUpSAImpl implements SignUpSA {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Value("${security.jwt.client-id}")
	private String clientId;

	@Value("${security.jwt.client-secret}")
	private String clientSecret;

	@Autowired
	private UserAuthCUDSA userAuthCUDSA;

	@Autowired
	private ProfilCUDSM profilCUDSM;

	@Autowired
	private ProfilCUDSA profilCUDSA;

	@Autowired
	private ProfilRSM profilRSM;

	@Autowired
	private ProfilFactory profilFactory;

	@Autowired
	private ProfilMapper profilMapper;

	@Autowired
	private RegistrationFactory registrationFactory;

	@Autowired
	private RegistrationCUDSM registrationCUDSM;

	@Autowired
	private UserAuthCUDSM userAuthCUDSM;

	@Autowired
	private RabbitMQUsersMCS rabbitMQUsersMCS;

	@Autowired
	private RabbitMQUserDTOFactory rabbitMQUserDTOFactory;

	@Autowired
	private NotificationSA notificationSA;

	@Autowired
	private NotificationMCS notificationMCS;

	@Autowired
	private ReseauxSociauxOAuthCredentialsMCS reseauxSociauxOAuthCredentialsMCS;

	@Autowired
	private MailParametersDTOFactory mailParametersDTOFactory;

	@Autowired
	private ReseauSocialUserDTOFactory reseauSocialUserDTOFactory;

	@Autowired
	private CorporationRSA corporationRSA;

	@Autowired
	private UserAuthCUDSM userCUDSM;

	@Autowired
	private UserAuthRSM userAuthRSM;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserAuthRSA userAuthRSA;

	@Autowired
	private UserAuthMapper userAuthMapper;

	@Autowired
	private ReseauxSociauxMCS reseauxSociauxMCS;

	@Autowired
	private AbonnementMCS abonnementMCS;

	@Value("${ldap.spring.peopleBase}")
	private String peopleDN;

	@Autowired
	private MediaMCS mediaMCS;

	@Autowired
	private ProfilRSA profilRSA;

	@Autowired
	CategoryRSA categoryRSA;

	@Autowired
	CategoryCUDSA categoryCUDSA;

	private static final Logger LOGGER = LoggerFactory.getLogger(SignUpSAImpl.class);

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.SignUpSA#
	 * doSignUp(com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.SignUpDTO)
	 */
	@Override
	public ProfilDTO doSignUp(SignUpDTO signUpdto) {
		Profil profil = null;
		Registration registration = null;
		RabbitMQUserDTO rabbitMQUserDTO = null;
		ProfilDTO result = new ProfilDTO();
		LOGGER.info("" + signUpdto.toString() + " Language is :" + signUpdto.getLanguage() + "Type value : "
				+ signUpdto.getType());

		try {

			// Create ldap user and check if mail and pseudo already exist
			UserAuthDTO userAuthDto = userAuthCUDSA.create(signUpdto);
			if (userAuthDto.isError()) {
				result.setEmail(signUpdto.getMail());
				result.setError(true);
				result.setErrorCode(userAuthDto.getErrorCode());
				result.setErrorMessage(userAuthDto.getErrorMessage());
				return result;
			}
			signUpdto.setUsername(userAuthDto.getUsername());// get the username generated value

			// Create profil
			// send attach person moral action
			CorporationDTO corporationDTO = signUpdto.getEmployer();
			if (corporationDTO != null) {
				if (StringUtils.isNotEmpty(corporationDTO.getId())) {
					CorporationDTO res = corporationRSA.getCorporation(corporationDTO.getId());
					if (!res.isError()) {
						notificationSA.sendAttachPMAction(userAuthDto.getUsername(), res.getId(), null,
								AttachPMActionType.ADD_PM.name());
					}
				} else {
					corporationDTO.setId(UUID.randomUUID().toString());
				}

			}
			profil = profilFactory.getEntityInstance(signUpdto);

			profil.setMediaId(UUID.randomUUID().toString());
			profil.setBackgroundId(UUID.randomUUID().toString());
			profil.setExportId(UUID.randomUUID().toString());

			// Update Creation date
			profil.setCreationDate(new Date());

			// TODO LIN-440
			// Generate key validation profile
			UUID keyValidateProfil = UUID.randomUUID();
			// Generate expiration date of key validation profile
			Calendar c = Calendar.getInstance();
			c.setTime(new Date());
			c.add(Calendar.DATE, 2);
			Date expirationKeyValidateProfil = c.getTime();

			profil.setKeyValidateProfil(keyValidateProfil.toString());
			profil.setExpirationKeyValidateProfil(expirationKeyValidateProfil);

			// Stocker le pseudoName dans MongoDB pour être disponible depuis le Front Chat
			// lors de la création de groupe de discussion
			profil.setChatId(userAuthDto.getPseudoName());
			profilCUDSM.update(profil);

			// Create a registration mongo collection
			registration = registrationFactory.getEntityInstance(profil.getUsername(), profil.getEmail());

			// Send to Back office

			registrationCUDSM.save(registration);

			// Create RabbitMq user credential
//			rabbitMQUserDTO = rabbitMQUserDTOFactory.getInstance(profil.getUsername());
//			rabbitMQUsersMCS.createUser(rabbitMQUserDTO);

//			// Create Neo4j node 
			ReseauSocialUserDTO reseauSocialUserDTO = reseauSocialUserDTOFactory.getInstance(profil);
			reseauxSociauxOAuthCredentialsMCS.createUser(reseauSocialUserDTO);

			// Send Notification mail.
			MailParametersDTO mailParametersDTO = mailParametersDTOFactory.getInstance(1, signUpdto.getLanguage(),
					profil.getEmail(), registration.getId(), profil.getUsername(), profil.getFirstname());

//			MailParametersDTO mailParametersDTO = mailParametersDTOFactory.getInstance(1, signUpdto.getLanguage(),
//					profil.getEmail(), registration.getId(), profil.getUsername(), profil.getFirstname(),
//					profil.getKeyValidateProfil());
			notificationMCS.sendEmail(mailParametersDTO);

			// send new Person Physique Action
			notificationSA.sendNewPPHAction(userAuthDto.getUsername());

		} catch (Exception e) {
			log.error("Error signing up", e);
			// Gérer manuellement la suppression des données enregistrées en cas d'erreur
			this.undoSignUp(signUpdto.getUsername(), profil == null ? null : profil.getId(),
					registration == null ? null : registration.getId());
			throw e;
		}
		profilCUDSA.sendNewAnnounceToBO(new ProfilForBODTO(profil));
		return profilMapper.profilToProfilDTO(profil);
	}

	// Test
//	@Override
//	public ProfilDTO doSignUp(SignUpDTO signUpdto) {
//		Profil profil = null;
//		Registration registration = null;
//			profil = profilFactory.getEntityInstance(signUpdto);
////
//			profil.setMediaId(UUID.randomUUID().toString());
//			profil.setBackgroundId(UUID.randomUUID().toString());
//			profil.setExportId(UUID.randomUUID().toString());
////
////			// Update Creation date
//			profil.setCreationDate(new Date());
//
////			// TODO LIN-440
////			// Generate key validation profile
//			UUID keyValidateProfil = UUID.randomUUID();
//			// Generate expiration date of key validation profile
//			Calendar c = Calendar.getInstance();
//			c.setTime(new Date());
//			c.add(Calendar.DATE, 2);
//			Date expirationKeyValidateProfil = c.getTime();
//
//			profil.setKeyValidateProfil(keyValidateProfil.toString());
//			profil.setExpirationKeyValidateProfil(expirationKeyValidateProfil);
////			// Create a registration mongo collection
//			registration = registrationFactory.getEntityInstance(profil.getUsername(), profil.getEmail());
//
//			// Send Notification mail.
//			MailParametersDTO mailParametersDTO = mailParametersDTOFactory.getInstance(1, signUpdto.getLanguage(),
//					profil.getEmail(), registration.getId(), profil.getUsername(), profil.getFirstname());
//
//			notificationMCS.sendEmail(mailParametersDTO);
//
//
//		return profilMapper.profilToProfilDTO(profil);
//	}

	private ProfilDTO doSignUpBetaTest(SignUpDTO signUpDTO, boolean isInLandingPage) {
		Profil profil = null;
		Registration registration = null;
		RabbitMQUserDTO rabbitMQUserDTO = null;
		ProfilDTO result = new ProfilDTO();

		try {
			UserAuthDTO userAuthDto = userAuthCUDSA.findUserAuthByUserName(signUpDTO.getUsername());
			// Create ldap user and check if mail and pseudo already exist
			if (userAuthDto == null) {
				userAuthDto = userAuthCUDSA.create(signUpDTO);
				if (userAuthDto.isError()) {
					result.setEmail(signUpDTO.getMail());
					result.setError(true);
					result.setErrorCode(userAuthDto.getErrorCode());
					result.setErrorMessage(userAuthDto.getErrorMessage());
					return result;
				}
			}
			signUpDTO.setUsername(userAuthDto.getUsername());// get the username generated value

			// Create profil
			// send attach person moral action
			CorporationDTO corporationDTO = signUpDTO.getEmployer();
			if (corporationDTO != null) {
				if (StringUtils.isNotEmpty(corporationDTO.getId())) {
					CorporationDTO res = corporationRSA.getCorporation(corporationDTO.getId());
					if (!res.isError()) {
						notificationSA.sendAttachPMAction(userAuthDto.getUsername(), res.getId(), null,
								AttachPMActionType.ADD_PM.name());
					}
				} else {
					corporationDTO.setId(UUID.randomUUID().toString());
				}

			}
			profil = profilFactory.getEntityInstance(signUpDTO);

			profil.setCreationDate(new Date());

			// TODO LIN-440
			// Generate key validation profile
			UUID keyValidateProfil = UUID.randomUUID();
			// Generate expiration date of key validation profile
			Calendar c = Calendar.getInstance();
			c.setTime(new Date());
			c.add(Calendar.DATE, 2);
			Date expirationKeyValidateProfil = c.getTime();

			profil.setKeyValidateProfil(keyValidateProfil.toString());
			profil.setExpirationKeyValidateProfil(expirationKeyValidateProfil);

			profil.setMediaId(UUID.randomUUID().toString());
			profil.setBackgroundId(UUID.randomUUID().toString());
			profil.setExportId(UUID.randomUUID().toString());
			// Stocker le pseudoName dans MongoDB pour être disponible depuis le Front Chat
			// lors de la création de groupe de discussion
			profil.setChatId(userAuthDto.getPseudoName());
			profilCUDSM.update(profil);

			// Create a registration mongo collection
			registration = registrationFactory.getEntityInstance(profil.getUsername(), profil.getEmail());
			registrationCUDSM.save(registration);

			// Create RabbitMq user credential
			rabbitMQUserDTO = rabbitMQUserDTOFactory.getInstance(profil.getUsername());
			rabbitMQUsersMCS.createUser(rabbitMQUserDTO);

			// Create Neo4j node
			ReseauSocialUserDTO reseauSocialUserDTO = reseauSocialUserDTOFactory.getInstance(profil);
			reseauxSociauxOAuthCredentialsMCS.createUser(reseauSocialUserDTO);

			// Send Notification mail.
			MailParametersDTO mailParametersDTO = mailParametersDTOFactory.getInstance((!isInLandingPage) ? 1 : 3,
					signUpDTO.getLanguage(), profil.getEmail(), registration.getId(), profil.getUsername(),
					profil.getFirstname());
			notificationMCS.sendEmail(mailParametersDTO);

			// send new Person Physique Action
			notificationSA.sendNewPPHAction(userAuthDto.getUsername());

			abonnementMCS.subscribeUser("uuid-subscription-premium", profil.getUsername(), true);

		} catch (Exception e) {
			log.error("Error signing up", e);
			// Gérer manuellement la suppression des données enregistrées en cas d'erreur
			this.undoSignUp(signUpDTO.getUsername(), profil == null ? null : profil.getId(),
					registration == null ? null : registration.getId());
			throw e;
		}
		return profilMapper.profilToProfilDTO(profil);
	}

	@Override
	public ProfilDTO doSignUp(InscriptionDTO inscriptionDTO) {
		Profil profil = null;
		Registration registration = null;
		RabbitMQUserDTO rabbitMQUserDTO = null;
		ProfilDTO result = new ProfilDTO();

		try {

			// Create ldap user and check if mail and pseudo already exist
			UserAuthDTO userAuthDto = userAuthCUDSA.create(inscriptionDTO);
			if (userAuthDto.isError()) {
				result.setEmail(inscriptionDTO.getMail());
				result.setError(true);
				result.setErrorCode(userAuthDto.getErrorCode());
				result.setErrorMessage(userAuthDto.getErrorMessage());
				return result;
			}
			inscriptionDTO.setUsername(userAuthDto.getUsername());// get the username generated value

			// Create profil
			// send attach person moral action
			CorporationDTO corporationDTO = inscriptionDTO.getEmployer();
			if (corporationDTO != null) {
				if (StringUtils.isNotEmpty(corporationDTO.getId())) {
					CorporationDTO res = corporationRSA.getCorporation(corporationDTO.getId());
					if (!res.isError()) {
						notificationSA.sendAttachPMAction(userAuthDto.getUsername(), res.getId(), null,
								AttachPMActionType.ADD_PM.name());
					}
				} else {
					corporationDTO.setId(UUID.randomUUID().toString());
				}

			}
			profil = profilFactory.getEntityInstance(inscriptionDTO);

			profil.setMediaId(UUID.randomUUID().toString());
			profil.setBackgroundId(UUID.randomUUID().toString());
			profil.setExportId(UUID.randomUUID().toString());
			// Stocker le pseudoName dans MongoDB pour être disponible depuis le Front Chat
			// lors de la création de groupe de discussion
			profil.setChatId(userAuthDto.getPseudoName());

			// Send to Back office
			profilCUDSA.sendUpdateAnnounceToBO(new ProfilForBODTO(profil));
			profilCUDSM.update(profil);

			// Create a registration mongo collection
			registration = registrationFactory.getEntityInstance(profil.getUsername(), profil.getEmail());
			registration = registrationCUDSM.save(registration);

			// Create RabbitMq user credential
			rabbitMQUserDTO = rabbitMQUserDTOFactory.getInstance(profil.getUsername());
			rabbitMQUsersMCS.createUser(rabbitMQUserDTO);

			// Create Neo4j node
			ReseauSocialUserDTO reseauSocialUserDTO = reseauSocialUserDTOFactory.getInstance(profil);
			reseauxSociauxOAuthCredentialsMCS.createUser(reseauSocialUserDTO);

			// Send Notification mail.
//			MailParametersDTO mailParametersDTO = mailParametersDTOFactory.getInstance(1, inscriptionDTO.getLanguage(),
//					profil.getEmail(), registration.getId(), profil.getUsername(), profil.getFirstname());
			MailParametersDTO mailParametersDTO = mailParametersDTOFactory.getInstance(1, inscriptionDTO.getLanguage(),
					profil.getEmail(), registration.getId(), profil.getKeyValidateProfil().toString(),
					profil.getFirstname());
			notificationMCS.sendEmail(mailParametersDTO);

			// send new Person Physique Action
			notificationSA.sendNewPPHAction(userAuthDto.getUsername());

			// send contact invitation if exists
			if (inscriptionDTO.getGuestUsers() != null && !inscriptionDTO.getGuestUsers().isEmpty()) {
				inscriptionDTO.getGuestUsers()
						.forEach(userId -> reseauxSociauxMCS.sendContactRequest(inscriptionDTO.getUsername(), userId));
			}

			// Affect user to default user subscription : freemium
			abonnementMCS.subscribeToFreemium(profil.getUsername());

		} catch (Exception e) {
			log.error("Error signing up", e);
			// Gérer manuellement la suppression des données enregistrées en cas d'erreur
			this.undoSignUp(inscriptionDTO.getUsername(), profil == null ? null : profil.getId(),
					registration == null ? null : registration.getId());
			throw e;
		}
		return profilRSA.getProfilAnonymous(profil.getUsername());
	}

	private void undoSignUp(String useruid, String profilId, String registrationId) {
		// rollback signup insertions
		try {
			userAuthCUDSM.deleteByUsername(useruid);
			profilCUDSM.deleteById(profilId);
			registrationCUDSM.deleteByUseruid(useruid);
			rabbitMQUsersMCS.deleteUser(useruid);
		} catch (Exception e) {
			// TODO: Qu'est ce qu'on fait en cas d'exception lors de la suppression ?
		}
	}

	@Override
	public UserAuthDTO choosePassword(UserAuthDTO userAuthDTO, Boolean validateAccount) {
		UserAuthDTO result = userAuthCUDSA.choosePassword(userAuthDTO, validateAccount);
		if (validateAccount && !result.isError()) {
			// On Supprime les données de registration
			registrationCUDSM.deleteByUseruid(result.getUsername());
			// Update creation date of activated user
			Profil profil = profilRSM.getInformation(userAuthDTO.getUsername());
			profil.setIsActiveAccount(true);
			if (profil != null) {
				profil.setCreationDate(new Date());
				profilCUDSM.update(profil);
				// Send to Back office
				profilCUDSA.sendUpdateAnnounceToBO(new ProfilForBODTO(profil));
			}
		}
		return result;
	}

	@Override
	public UserAuthDTO updatePassword(UserAuthDTO userAuthDTO, Boolean validateAccount) {
		return userAuthCUDSA.updatePassword(userAuthDTO, validateAccount);
	}

	@Override
	public UserAuthDTO retrievePassword(UserAuthDTO userAuthDTO) {
		if (StringUtils.isEmpty(userAuthDTO.getMail())) {
			userAuthDTO.setError(true);
			userAuthDTO.setErrorCode("ERR_MCS_PROFIL_PASSWORD_RECOVERY");
			userAuthDTO.setErrorMessage(ErrorsEnum.ERR_MCS_PROFIL_PASSWORD_RECOVERY.getErrorMessage());
			throw new FunctionalInvalidDataException(new UserAuthDTO(), ErrorsEnum.ERR_MCS_PROFIL_PASSWORD_RECOVERY);
		}

		// check if mail exist
		UserAuth user = userCUDSM.findByMail(userAuthDTO.getMail());
		if (user == null || StringUtils.isEmpty(user.getMail())) {
			userAuthDTO.setError(true);
			userAuthDTO.setErrorCode("ERR_MCS_PROFIL_PASSWORD_RECOVERY");
			userAuthDTO.setErrorMessage(ErrorsEnum.ERR_MCS_PROFIL_PASSWORD_RECOVERY.getErrorMessage());
			throw new FunctionalInvalidDataException(new UserAuthDTO(), ErrorsEnum.ERR_MCS_PROFIL_PASSWORD_RECOVERY);
		}

		if (user.getRoles().contains(RoleEnum.INACTIF.getValue())) {
			userAuthDTO.setError(true);
			userAuthDTO.setErrorCode("ERR_MCS_PROFIL_INACTIVE_ACCOUNT");
			userAuthDTO.setErrorMessage(ErrorsEnum.ERR_MCS_PROFIL_INACTIVE_ACCOUNT.getErrorMessage());
			throw new FunctionalInvalidDataException(new UserAuthDTO(), ErrorsEnum.ERR_MCS_PROFIL_INACTIVE_ACCOUNT);
		}

		if (user.getRoles().contains(RoleEnum.BLOCKED.getValue())) {
			userAuthDTO.setError(true);
			userAuthDTO.setErrorCode("ERR_MCS_PROFIL_BLOCKED_ACCOUNT");
			userAuthDTO.setErrorMessage(ErrorsEnum.ERR_MCS_PROFIL_BLOCKED_ACCOUNT.getErrorMessage());
			throw new FunctionalInvalidDataException(new UserAuthDTO(), ErrorsEnum.ERR_MCS_PROFIL_BLOCKED_ACCOUNT);
		}

		Profil profil = profilRSM.getInformation(user.getUsername());
		if (profil == null) {
			userAuthDTO.setError(true);
			userAuthDTO.setPassword(null);
			userAuthDTO.setErrorCode("ERR_MCS_PROFIL_0007");
			userAuthDTO.setErrorMessage(ErrorsEnum.ERR_MCS_PROFIL_0007.getErrorMessage());
			throw new ObjetNotFoundException(new UserAuthDTO(), ErrorsEnum.ERR_MCS_PROFIL_0007);
		}
		Registration registration = null;

		// Create a registration mongo collection
		registration = registrationFactory.getEntityInstance(user.getUsername(), user.getMail());
		registration = registrationCUDSM.save(registration);

		// Send Notification mail for changing mdp.
		MailParametersDTO mailParametersDTO = mailParametersDTOFactory.getInstance(2, userAuthDTO.getLanguage(),
				user.getMail(), registration.getId(), user.getUsername(), profil.getFirstname());
		notificationMCS.sendEmail(mailParametersDTO);

		userAuthDTO.setError(false);
		userAuthDTO.setMessage("password recovery succefull");

		return userAuthDTO;
	}

	@Override
	public UserAuthDTO updateMail(String userName, UserAuthDTO userAuthDTO) {
		// check if mail exist
		if (StringUtils.isEmpty(userAuthDTO.getMail()) || userCUDSM.isMailExist(userAuthDTO.getMail())) {
			userAuthDTO.setError(true);
			userAuthDTO.setPassword(null);
			userAuthDTO.setErrorCode("ERR_MCS_PROFIL_MAIL_ALREADY_EXIST");
			userAuthDTO.setErrorMessage(ErrorsEnum.ERR_MCS_PROFIL_MAIL_ALREADY_EXIST.getErrorMessage());
			throw new FunctionalInvalidDataException(new UserAuthDTO(), ErrorsEnum.ERR_MCS_PROFIL_MAIL_ALREADY_EXIST);
		}

		// Use ldap as datasource
		UserAuthDTO userDTO = userAuthRSA.findByUserName(userName);
		if (userDTO == null) {
			userAuthDTO.setError(true);
			userAuthDTO.setPassword(null);
			userAuthDTO.setErrorCode("ERR_MCS_PROFIL_0007");
			userAuthDTO.setErrorMessage(ErrorsEnum.ERR_MCS_PROFIL_0007.getErrorMessage());
			throw new FunctionalInvalidDataException(new UserAuthDTO(), ErrorsEnum.ERR_MCS_PROFIL_0007);
		}

		if (userDTO.getRoles().contains(RoleEnum.INACTIF.getValue())) {
			userAuthDTO.setError(true);
			userAuthDTO.setPassword(null);
			userAuthDTO.setErrorCode("ERR_MCS_PROFIL_INACTIVE_ACCOUNT");
			userAuthDTO.setErrorMessage(ErrorsEnum.ERR_MCS_PROFIL_INACTIVE_ACCOUNT.getErrorMessage());
			throw new FunctionalInvalidDataException(new UserAuthDTO(), ErrorsEnum.ERR_MCS_PROFIL_INACTIVE_ACCOUNT);
		}

		if (userDTO.getRoles().contains(RoleEnum.BLOCKED.getValue())) {
			userAuthDTO.setError(true);
			userAuthDTO.setPassword(null);
			userAuthDTO.setErrorCode("ERR_MCS_PROFIL_BLOCKED_ACCOUNT");
			userAuthDTO.setErrorMessage(ErrorsEnum.ERR_MCS_PROFIL_BLOCKED_ACCOUNT.getErrorMessage());
			throw new FunctionalInvalidDataException(new UserAuthDTO(), ErrorsEnum.ERR_MCS_PROFIL_BLOCKED_ACCOUNT);
		}

		if (!passwordEncoder.matches(userAuthDTO.getPassword(), userDTO.getPassword())) {
			userAuthDTO.setError(true);
			userAuthDTO.setPassword(null);
			userAuthDTO.setErrorCode("ERR_MCS_PROFIL_INVALID_PASSWORD");
			userAuthDTO.setErrorMessage(ErrorsEnum.ERR_MCS_PROFIL_INVALID_PASSWORD.getErrorMessage());
			throw new FunctionalInvalidDataException(new UserAuthDTO(), ErrorsEnum.ERR_MCS_PROFIL_INVALID_PASSWORD);
		}

		Profil profil = profilRSM.getInformation(userName);
		if (profil == null) {
			userAuthDTO.setError(true);
			userAuthDTO.setPassword(null);
			userAuthDTO.setErrorCode("ERR_MCS_PROFIL_0007");
			userAuthDTO.setErrorMessage(ErrorsEnum.ERR_MCS_PROFIL_0007.getErrorMessage());
			throw new ObjetNotFoundException(new UserAuthDTO(), ErrorsEnum.ERR_MCS_PROFIL_0007);
		}

		// update ldap
		userDTO.setMail(userAuthDTO.getMail());
		userDTO.setPassword(passwordEncoder.encode(userAuthDTO.getPassword()));
		UserAuth user = userAuthMapper.userAuthDTOtoUserAuth(userDTO);
		user.setId(LdapUtils.newLdapName("uid=" + userAuthDTO.getUsername() + "," + peopleDN));

		try {
			userCUDSM.create(user);
		} catch (NameAlreadyBoundException e) {
			userAuthDTO.setError(true);
			userAuthDTO.setPassword(null);
			userAuthDTO.setErrorCode("ERR_MCS_USER_ALREADY_EXIST");
			userAuthDTO.setErrorMessage(ErrorsEnum.ERR_MCS_USER_ALREADY_EXIST.getErrorMessage());
			throw new FunctionalInvalidDataException(new UserAuthDTO(), ErrorsEnum.ERR_MCS_USER_ALREADY_EXIST);
		}

		// update mongo & ES
		profil.setEmail(userAuthDTO.getMail());
		profilCUDSM.update(profil);

		userAuthDTO.setUsername(profil.getUsername());
		userAuthDTO.setPassword(null);
		userAuthDTO.setError(false);
		userAuthDTO.setMessage("mail modified succefully");
		return userAuthDTO;
	}

	@Override
	public UserAuthDTO validateAccount(UserAuthDTO userAuthDTO) {
		UserAuthDTO result = userAuthCUDSA.validateAccount(userAuthDTO);
		// On Supprime les données de registration
		registrationCUDSM.deleteByUseruid(result.getUsername());
		// Update creation date of activated user
		Profil profil = profilRSM.getInformation(userAuthDTO.getUsername());
		if (profil != null) {
			profil.setCreationDate(new Date());
			profilCUDSM.update(profil);
		}
		return result;
	}

	// TODO LIN-440
	@Override
	public IsLinkValidDTO isLinkValid(String keyValidateProfil) {
		List<Profil> profils = profilRSM.getBykeyValidateProfil(keyValidateProfil);
		IsLinkValidDTO isLinkValidDTO = new IsLinkValidDTO();

		if (profils != null && !profils.isEmpty()) {
			Profil profil = profils.get(0);
			if (profil.getExpirationKeyValidateProfil().compareTo(new Date()) >= 0) {
				isLinkValidDTO.setUserName(profil.getUsername());
				isLinkValidDTO.setIsValid(true);
				return isLinkValidDTO;
			}

			isLinkValidDTO.setUserName(profil.getUsername());
			isLinkValidDTO.setIsValid(false);
			return isLinkValidDTO;
		} else {
			isLinkValidDTO.setError(true);
			isLinkValidDTO.setErrorMessage("validation string is not found or not exist");
			return isLinkValidDTO;
		}
	}

	@Override
	public void importBetaTesteurs(List<SignUpDTO> signUpDTOS) {
		signUpDTOS.forEach(signUpDTO -> doSignUpBetaTest(signUpDTO, false));
	}

	@Override
	public IsMailSendDTO reSendMailValidate(String userId) {
		IsMailSendDTO isMailSendDTO = new IsMailSendDTO();
		if (StringUtils.isEmpty(userId))
			throw new FunctionalInvalidDataException(new Profil(), ErrorsEnum.ERR_MCS_PROFIL_0023);

		Profil profil = profilRSM.getInformation(userId);

		if (profil == null)
			throw new ObjetNotFoundException(new Profil(), ErrorsEnum.ERR_MCS_PROFIL_0007);

		UserAuth userAuth = userAuthRSM.findByUserName(userId);

		if (userAuth == null) {
			throw new ObjetNotFoundException(new Profil(), ErrorsEnum.ERR_MCS_PROFIL_0007);
		}

		// TODO LIN-440
		// Generate key validation profile
		UUID keyValidateProfil = UUID.randomUUID();
		// Generate expiration date of key validation profile
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DATE, 2);
		Date expirationKeyValidateProfil = c.getTime();

		profil.setKeyValidateProfil(keyValidateProfil.toString());
		profil.setExpirationKeyValidateProfil(expirationKeyValidateProfil);
		profilCUDSM.update(profil);

		Registration registration = null;

		// Create a registration mongo collection
		registration = registrationFactory.getEntityInstance(userAuth.getUsername(), userAuth.getMail());
		registration = registrationCUDSM.save(registration);

		// Send Notification mail.
//		MailParametersDTO mailParametersDTO = mailParametersDTOFactory.getInstance(1, "fr", profil.getEmail(),
//				registration.getId(), profil.getUsername(), profil.getFirstname());

		MailParametersDTO mailParametersDTO = mailParametersDTOFactory.getInstance(1, "fr", profil.getEmail(), null,
				profil.getUsername(), profil.getFirstname(), profil.getKeyValidateProfil());

		notificationMCS.sendEmail(mailParametersDTO);

		// send new Person Physique Action
		notificationSA.sendNewPPHAction(userAuth.getUsername());
		isMailSendDTO.setMailSend(true);

		return isMailSendDTO;
	}

	@Override
	public ProfilDTO importBetaTestInLandinPage(SignUpDTO signUpDTO) {

		LOGGER.info("" + signUpDTO.toString() + " Language is :" + signUpDTO.getLanguage() + "Type value : "
				+ signUpDTO.getType().getName());

		// Check if data is empty
		if (StringUtils.isEmpty(signUpDTO.getLastName()) || StringUtils.isEmpty(signUpDTO.getFirstName())
				|| StringUtils.isEmpty(signUpDTO.getMail()) || signUpDTO.getType() == null
				|| StringUtils.isEmpty(signUpDTO.getType().getName()))
			throw new FunctionalInvalidDataException(new SignUpDTO(), ErrorsEnum.ERR_MCS_PROFIL_DATA_BETATESTEUR);

		// Check mail if is good format
		Pattern regexForValiMailAddress = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
				Pattern.CASE_INSENSITIVE);

		Matcher matcher = regexForValiMailAddress.matcher(signUpDTO.getMail());
		boolean isMailValid = matcher.find();

		if (!isMailValid)
			throw new FunctionalInvalidDataException(new SignUpDTO(), ErrorsEnum.ERR_MCS_PROFIL_00041);

		// Check if user already exist
		UserAuthDTO userAuthDTO = userAuthRSA.findByUserName(signUpDTO.getMail());
		if (userAuthDTO != null)
			throw new FunctionalInvalidDataException(new SignUpDTO(), ErrorsEnum.ERR_MCS_PROFIL_0403);

		String typeName = "";
		if (signUpDTO.getType().getName().equals("0")) {
			typeName = "Industriel";
		} else if (signUpDTO.getType().getName().equals("1")) {
			// TODO LIN-433
			typeName = "Chercheur";
		} else if (signUpDTO.getType().getName().equals("2")) {
			typeName = "Autres acteurs de l'innovation";
		} else if (signUpDTO.getType().getName().equals("3")) {
			// TODO LIN-433
			typeName = "Académique";
		} else {
			typeName = signUpDTO.getType().getName();
		}

		LOGGER.info("Type name : " + typeName);
		CategoryDTO categoryDTO = categoryRSA.findByName(typeName);

		if (categoryDTO == null) {
			categoryDTO = new CategoryDTO();
			categoryDTO.setId("uuid-category-academique");
			categoryDTO.setName("Académique");
		}

		signUpDTO.setType(categoryDTO);

		return doSignUpBetaTest(signUpDTO, true);
	}

	@Override
	public MediaDTO generatePictureToken(String userId) {
		MediaDTO mediaDTO = null;
		Profil profil = profilRSM.getInformation(userId);
		if (profil != null) {
			mediaDTO = mediaMCS.generateProfilPictureToken(userId, MediaType.PICTURE.toString());
			if (mediaDTO == null) {
				throw new ObjectSaveException(new MediaDTO(), ErrorsEnum.ERR_MCS_PROFIL_0051);
			}
		} else {
			throw new ObjetNotFoundException(new MediaDTO(), ErrorsEnum.ERR_MCS_PROFIL_0040);
		}

		return mediaDTO;
	}
}
