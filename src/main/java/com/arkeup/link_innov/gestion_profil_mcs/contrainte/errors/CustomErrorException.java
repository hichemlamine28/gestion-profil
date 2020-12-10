package com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.BaseDTO;

public class CustomErrorException extends RuntimeException {

	private static final long serialVersionUID = 5828282428498541459L;
	
	private String errorCode;
	private BaseDTO dto;

	public CustomErrorException(String errorCode, BaseDTO dto) {
		super();
		this.errorCode = errorCode;
		this.dto = dto;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public BaseDTO getDto() {
		return dto;
	}

	public void setDto(BaseDTO dto) {
		this.dto = dto;
	}
	
	
	@ExceptionHandler(CustomErrorException.class)
	public ResponseEntity<BaseDTO> handleValidationExceptions(CustomErrorException ex) {
	    BaseDTO error = ex.getDto();
	    error.setError(true);
	    error.setErrorCode(ex.getErrorCode());
	    error.setErrorMessage(ErrorsEnum.getById(ex.getErrorCode()).getErrorMessage());
	    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}


