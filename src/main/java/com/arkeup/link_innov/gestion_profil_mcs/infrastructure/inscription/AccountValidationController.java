/**
 *
 */
package com.arkeup.link_innov.gestion_profil_mcs.infrastructure.inscription;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ValidationException;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.validator.UserAuthDTOValidator;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.UserAuthDTO;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.SignUpSA;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Api("Validation de Compte")
@RequestMapping(value = "/account")
public class AccountValidationController {

	@Autowired
	private SignUpSA signUpSA;

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountValidationController.class);

	@InitBinder("userAuthDTO")
	protected void initUserAuthDTOValidatorBinder(WebDataBinder binder) {
		binder.setValidator(new UserAuthDTOValidator());
	}

	@ApiOperation(value = "Choose Password", notes = "This WS is used to change user's password when validating signup.")
	@PutMapping(value = "/validation/choosePassword", produces = MediaType.APPLICATION_JSON_VALUE)
	public UserAuthDTO choosePassword(
			@ApiParam(name = "UserAuthDTO", value = "{\"username\": \"uuid\", \"password\": \"passwordTest\"}", required = true) @Valid @RequestBody UserAuthDTO userAuthDTO,
			Errors errors) {

		if (errors.hasErrors()) {
			throw new ValidationException(errors);
		}
		if (signUpSA.choosePassword(userAuthDTO, true).getMail() != null) {
			LOGGER.info("Mail response Logger ... " + signUpSA.choosePassword(userAuthDTO, true).getMail());
		}

		return signUpSA.choosePassword(userAuthDTO, true);
	}

	@PreAuthorize("#userAuthDTO.username == principal.username") // Un utilisateur ne peux modifier que son propre mot
																	// de passe
	@ApiOperation(value = "Change Password", notes = "This WS is used to change user's password when changing password.")
	@PutMapping(value = "/changePassword", produces = MediaType.APPLICATION_JSON_VALUE)
	public UserAuthDTO changePassword(
			@ApiParam(name = "UserAuthDTO", value = "{\"username\": \"uuid\", \"password\": \"passwordTest\"}", required = true) @Valid @RequestBody UserAuthDTO userAuthDTO,
			Errors errors) {

		if (errors.hasErrors()) {
			throw new ValidationException(errors);
		}
		return signUpSA.updatePassword(userAuthDTO, false);
	}

	@ApiOperation(value = "password recovery", notes = "This WS is used to recover user's password.")
	@PutMapping(value = "/passwordRecovery", produces = MediaType.APPLICATION_JSON_VALUE)
	public UserAuthDTO passwordRecovery(
			@ApiParam(name = "UserAuthDTO", value = "{\"language\": \"fr\",\"mail\": \"email\"}", required = true) @RequestBody UserAuthDTO userAuthDTO) {
		return signUpSA.retrievePassword(userAuthDTO);
	}

	@PreAuthorize("#userAuthDTO.username == principal.username") // Un utilisateur ne peux modifier que son propre email
	@ApiOperation(value = "Change Email", notes = "This WS is used to change user's email when changing mail.")
	@PutMapping(value = "/changeMail", produces = MediaType.APPLICATION_JSON_VALUE)
	public UserAuthDTO changeMail(
			@ApiParam(name = "UserAuthDTO", value = "{\"username\": \"uuid\", \"mail\": \"mail@mail.fr\"}", required = true) @RequestBody UserAuthDTO userAuthDTO) {
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName = user.getUsername();
		return signUpSA.updateMail(userName, userAuthDTO);
	}

	@ApiOperation(value = "Validate Account", notes = "This WS is used to validate user account.")
	@PutMapping(value = "/validation", produces = MediaType.APPLICATION_JSON_VALUE)
	public UserAuthDTO validateAccount(
			@ApiParam(name = "UserAuthDTO", value = "{\"username\": \"uuid\"}", required = true) @RequestBody UserAuthDTO userAuthDTO) {

		return signUpSA.validateAccount(userAuthDTO);
	}

}
