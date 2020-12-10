/**
 *
 */
package com.arkeup.link_innov.gestion_profil_mcs.infrastructure.inscription;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ErrorsEnum;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ObjetNotFoundException;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.TechnicalException;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ValidationException;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.validator.InscriptionDTOValidator;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.validator.SignUpDTOValidator;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.validator.UserAuthDTORGValidator;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.IsImportedDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.IsLinkValidDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.IsMailSendDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ProfilDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.UserAuthDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.inscription.InscriptionDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.inscription.RegistrationDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.inscription.SignUpDTO;
import com.arkeup.link_innov.gestion_profil_mcs.infrastructure.utils.PermissionsAndStatusUtils;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.ExternalSignUpSAImpl;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.SignUpSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.RegistrationSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.businessdelegate.UploadBetaTestBDL;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author mikajy
 *
 */
@RestController
@Api("Inscription")
public class SignUpController {

	@Autowired
	private SignUpDTOValidator signUpDTOValidator;

	@Autowired
	private UserAuthDTORGValidator userAuthDTORGValidator;

	@Autowired
	private SignUpSA signUpSA;

	@Autowired
	private RegistrationSA registrationSA;

	@Autowired
	private ExternalSignUpSAImpl externalSignUpSA;

	@Autowired
	private InscriptionDTOValidator inscriptionDTOValidator;

	@Autowired
	private UploadBetaTestBDL uploadBetaTestBDL;

	private static final Logger LOGGER = LoggerFactory.getLogger(SignUpController.class);

	@InitBinder("signUp")
	protected void dataSignUpBinding(WebDataBinder binder) {

		binder.setValidator(signUpDTOValidator);
	}

	@InitBinder("userAuthDTORG")
	protected void dataUserAuthDTORGBinding(WebDataBinder binder) {

		binder.addValidators(userAuthDTORGValidator);
	}

	/**
	 *
	 * @param signUpDTO
	 * @return
	 */
	@ApiOperation(value = "Signup user", notes = "This WS is used to register physical actor.")
	@PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
	public ProfilDTO signUp(
			@ApiParam(name = "SignUpDTO", value = "{\"language\": \"fr\",\"lastName\": \"DeLaFuente\", \"firstName\": \"Jean\", \"mail\": \"abc@yopmail.com\", \"employer\": { \"id\": \"employer_id\", \"name\": \"Universite de France\" }, \"type\": { \"id\": \"uuid-category-academique\", \"name\":\"Académique\", \"male\":\"true\"}}", required = true) @Valid @RequestBody SignUpDTO signUpDTO,
			Errors errors) {
		LOGGER.info("/register logger is ... : " + signUpDTO.toString() + " Language is :" + signUpDTO.getLanguage()
				+ " Type value : " + signUpDTO.getType() + " Catégorie name is : " + signUpDTO.getType().getName());

		if (errors.hasErrors()) {
			throw new ValidationException(errors);
		}
		// Data is valid, call signUp service...
		return signUpSA.doSignUp(signUpDTO);
	}

	@ApiOperation(value = "Validate Signup", notes = "Check if registration is still valid")
	@GetMapping(value = "/registration-checkout/{registrationId}/{useruid}", produces = MediaType.APPLICATION_JSON_VALUE)
	public RegistrationDTO checkoutRegistration(@PathVariable("registrationId") String registrationId,
			@PathVariable("useruid") String useruid) {
		return registrationSA.checkAndGetRegistration(registrationId, useruid);
	}

	@ApiOperation(value = "ResearchGate extract", notes = "Login to researchGate and get basic information")
	@PostMapping(value = "/rg-extract", produces = MediaType.APPLICATION_JSON_VALUE)
	public ProfilDTO researchGateExtract(
			@ApiParam(name = "UserAuthDTO", value = "{\"username\": \"tata@toto.titi\", \"password\": \"passwordTest\"}", required = true) @Valid @RequestBody UserAuthDTO userAuthDTO,
			Errors errors) {

		if (errors.hasErrors()) {
			throw new ValidationException(errors);
		}
		return externalSignUpSA.extractRGData(userAuthDTO);
	}

	@ApiOperation(value = "User Inscription", notes = "This WS is used to register physical actor.")
	@PostMapping(value = "/signup", produces = MediaType.APPLICATION_JSON_VALUE)
	public ProfilDTO register(
			@ApiParam(name = "InscriptionDTO", value = "{\"language\": \"fr\",\"lastName\": \"DeLaFuente\", \"firstName\": \"Jean\", \"mail\": \"abc@yopmail.com\", \"employer\": { \"id\": \"employer_id\", \"name\": \"Universite de France\" }, \"type\": { \"id\": \"uuid-category-academique\", \"name\":\"Académique\", \"male\":\"true\", \"profession\":\"Chercheur\"}", required = true) @RequestPart(name = "inscriptionDTO", required = true) @RequestBody InscriptionDTO inscriptionDTO) {

		Errors errors = inscriptionDTOValidator.validate(inscriptionDTO);
		if (errors.hasErrors()) {
			throw new ValidationException(errors);
		}
		// Data is valid, call signUp service...
		return signUpSA.doSignUp(inscriptionDTO);
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEADMIN)
	@ApiOperation(value = "Import beta test", notes = "This WS is used to import beta test.")
	@PostMapping(path = "/inscription/importBetaTest", produces = { MediaType.APPLICATION_JSON_VALUE })
	public IsImportedDTO importBetaTest(
			@ApiParam(name = "file", value = "{\"file\":\"Multipart file\"}", required = false) @RequestPart(required = false, name = "file") MultipartFile file) {
		if (file == null || file.isEmpty()) {
			throw new ObjetNotFoundException(new IsImportedDTO(), ErrorsEnum.ERR_MCS_MULTIPART_FILE_EMPTY);
		}
		String fileName = null;
		try {

			// Get the file and save it somewhere
			byte[] bytes = file.getBytes();
			Path path = Paths.get("/tmp/" + file.getOriginalFilename());
			Files.write(path, bytes);
			fileName = "/tmp/" + path.getFileName();

		} catch (Exception e) {
			e.printStackTrace();
			throw new TechnicalException(new IsImportedDTO(), ErrorsEnum.ERR_MCS_FILE_EXCEPTION);
		}

		if (!StringUtils.isEmpty(fileName))
			return uploadBetaTestBDL.uploadFileData(fileName);
		throw new TechnicalException(new IsImportedDTO(), ErrorsEnum.ERR_MCS_FILE_EXCEPTION);
	}

	@ApiOperation(value = "Re-send mail validate account", notes = "Re-send mail validate account")
	@GetMapping(value = "/inscription/re-send-mail-validate/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public IsMailSendDTO reSendMailValidate(@PathVariable("userId") String userId) {
		return signUpSA.reSendMailValidate(userId);
	}

	@ApiOperation(value = "Import beta test in anonymous", notes = "This WS is used to import beta test in anonymous.")
	@PostMapping(path = "/inscription/importBetaTest/anonymous", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ProfilDTO importBetaTestInLandingPage(
			@ApiParam(name = "SignUpDTO", value = "{\"language\": \"fr\",\"lastName\": \"DeLaFuente\", \"firstName\": \"Jean\", \"mail\": \"abc@yopmail.com\", \"employer\": { \"id\": \"employer_id\", \"name\": \"Universite de France\" }, \"type\": { \"id\": \"uuid-category-academique\", \"name\":\"Académique\", \"male\":\"true\"}}", required = true) @RequestBody SignUpDTO signUpDTO) {
		return signUpSA.importBetaTestInLandinPage(signUpDTO);
	}

	// TODO LIN-440
	@ApiOperation(value = "Is-link-valide", notes = "Is-link-valide")
	@GetMapping(value = "/inscription/isLinkValid/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public IsLinkValidDTO isLinkValid(@PathVariable("userId") String userId) {
		return signUpSA.isLinkValid(userId);
	}
}
