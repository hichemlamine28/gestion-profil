package com.arkeup.link_innov.gestion_profil_mcs.infrastructure;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ErrorsEnum;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.FunctionalException;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.TechnicalException;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ValidationException;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.BaseDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.Oauth2ErrorDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.UnknownErrorDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.ValidationResponseDTO;

@RestControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<BaseDTO> handleUGlobalException(Exception e, WebRequest request) {

		String uuid = UUID.randomUUID().toString();
		log.error("Unhandled Exception {} ", uuid, e);
		UnknownErrorDTO unhandledErrorDTO = new UnknownErrorDTO();

		unhandledErrorDTO.setError(ErrorsEnum.ERR_MCS_UNKW.getError());
		unhandledErrorDTO.setWarning(ErrorsEnum.ERR_MCS_UNKW.getWarning());
		unhandledErrorDTO.setUuid(uuid);
		unhandledErrorDTO.setErrorCode(ErrorsEnum.ERR_MCS_UNKW.getErrorCode());
		unhandledErrorDTO.setErrorMessage(ErrorsEnum.ERR_MCS_UNKW.getErrorMessage());
		unhandledErrorDTO.setMessage(e.getMessage());

		return new ResponseEntity<>(unhandledErrorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(AccessDeniedException.class)
	public final ResponseEntity<Oauth2ErrorDTO> handleAccessDeniedException(AccessDeniedException e,
			WebRequest request) {
		String uuid = UUID.randomUUID().toString();
		log.error("Access Denied Error ID {} ", uuid, e);
		Oauth2ErrorDTO error = new Oauth2ErrorDTO();
		error.setError("access_denied");
		error.setErrorDescription("Accès refusé");
		return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(FunctionalException.class)
	public final ResponseEntity<BaseDTO> handleFunctionalException(FunctionalException e, WebRequest request) {
		String uuid = UUID.randomUUID().toString();
		log.error("Functional Error ID {} ", uuid, e);
		BaseDTO baseDTO = e.getBaseDTO();
		ErrorsEnum errorsEnum = e.getErrorsEnum();
		log.error("Error message : {} ", errorsEnum.getErrorMessage());

		baseDTO.setError(errorsEnum.getError());
		baseDTO.setWarning(errorsEnum.getWarning());
		baseDTO.setUuid(uuid);
		baseDTO.setErrorMessage(errorsEnum.getErrorMessage());
		baseDTO.setErrorCode(errorsEnum.getErrorCode());

		return new ResponseEntity<>(baseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(TechnicalException.class)
	public final ResponseEntity<BaseDTO> handleTechnicalException(TechnicalException e, WebRequest request) {
		String uuid = UUID.randomUUID().toString();
		log.error("Technical Error ID {} ", uuid, e);
		BaseDTO baseDTO = e.getBaseDTO();
		ErrorsEnum errorsEnum = e.getErrorsEnum();
		log.error("Error message : {} ", errorsEnum.getErrorMessage());

		baseDTO.setError(errorsEnum.getError());
		baseDTO.setWarning(errorsEnum.getWarning());
		baseDTO.setUuid(uuid);
		baseDTO.setErrorMessage(errorsEnum.getErrorMessage());
		baseDTO.setErrorCode(errorsEnum.getErrorCode());

		return new ResponseEntity<>(baseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ValidationException.class)
	public final ResponseEntity<ValidationResponseDTO> handleValidationException(ValidationException e,
			WebRequest request) {
		String uuid = UUID.randomUUID().toString();
		log.error("Validation Error ID {} ", uuid, e);

		ValidationResponseDTO validationResponseDTO = new ValidationResponseDTO();

		Map<String, String> mapErrors = new HashMap<>();

		Errors vaidationErrors = e.getErrors();
		List<ObjectError> errorsList = vaidationErrors.getAllErrors();
		for (ObjectError objectError : errorsList) {
			mapErrors.put(objectError.getCode(), objectError.getDefaultMessage());
		}
		validationResponseDTO.setMapErrors(mapErrors);
		validationResponseDTO.setIsError(true);

		return new ResponseEntity<>(validationResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public BaseDTO getBaseDTO(ErrorsEnum errorsEnum, BaseDTO baseDTO, String uuid, String exceptionMessage) {

		baseDTO.setError(errorsEnum.getError());
		baseDTO.setWarning(errorsEnum.getWarning());
		baseDTO.setUuid(uuid);
		baseDTO.setErrorCode(errorsEnum.getErrorCode());
		baseDTO.setErrorMessage(errorsEnum.getErrorMessage());
		baseDTO.setMessage(exceptionMessage);

		return baseDTO;
	}

}