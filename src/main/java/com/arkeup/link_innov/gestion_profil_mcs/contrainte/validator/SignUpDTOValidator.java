/**
 * 
 */
package com.arkeup.link_innov.gestion_profil_mcs.contrainte.validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ErrorsEnum;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.inscription.SignUpDTO;


/**
 * @author mikajy
 *
 */
@Component
public class SignUpDTOValidator implements Validator {
	
	@Autowired
	private EmailValidator emailValidator;

	@Override
	public boolean supports(Class<?> clazz) {
		return SignUpDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// RG20 A minima, le champ Prénom + Nom + email + type sont obligatoires
		
		SignUpDTO signUpUser = (SignUpDTO) target;
		
		// Email
		if(StringUtils.isBlank(signUpUser.getMail())) {
			// Email vide ?
			errors.rejectValue("mail", "signup.mail.empty", ErrorsEnum.ERR_MCS_PROFIL_0004.getErrorMessage());
		} else if(!emailValidator.isValid(signUpUser.getMail())) {
			//Email format valid ?
			errors.rejectValue("mail", "signup.mail.invalid", ErrorsEnum.ERR_MCS_PROFIL_00041.getErrorMessage());
		}
		// Lastname
		if(StringUtils.isBlank(signUpUser.getLastName())){ 
			errors.rejectValue("lastName", "signup.lastname.empty", ErrorsEnum.ERR_MCS_PROFIL_0002.getErrorMessage());
		} else if(signUpUser.getLastName().length() > 80) { // RG19.1 80 caractères alphanumériques 
			errors.rejectValue("lastName", "signup.lastname.toolong", ErrorsEnum.ERR_MCS_PROFIL_00021.getErrorMessage());
		}
		// Firstname
		if(StringUtils.isBlank(signUpUser.getFirstName())){
			errors.rejectValue("firstName", "signup.firstname.empty", ErrorsEnum.ERR_MCS_PROFIL_0003.getErrorMessage());
		} else if(signUpUser.getFirstName().length() > 80) { // RG19.1 Prénom : 80 caractères alphanumériques
			errors.rejectValue("lastName", "signup.firstname.toolong", ErrorsEnum.ERR_MCS_PROFIL_00031.getErrorMessage());
		}
		// Validation Type
		if(signUpUser.getType() == null || (StringUtils.isBlank(signUpUser.getType().getId()) && StringUtils.isBlank(signUpUser.getType().getName()))) {
			errors.rejectValue("type.id", "signup.type.empty", ErrorsEnum.ERR_MCS_PROFIL_0401.getErrorMessage());
		}
		// Validation Employeur
//		if(signUpUser.getEmployer() == null || (StringUtils.isBlank(signUpUser.getEmployer().getId()) && StringUtils.isBlank(signUpUser.getEmployer().getName()))) {
//			errors.rejectValue("employer.id", "signup.employer.empty", ErrorsEnum.ERR_MCS_PROFIL_0400.getErrorMessage());
//		}
		if(signUpUser.getEmployer() == null || StringUtils.isBlank(signUpUser.getEmployer().getName())) {
			errors.rejectValue("employer.id", "signup.employer.empty", ErrorsEnum.ERR_MCS_PROFIL_0400.getErrorMessage());
		}
	}
	
	

	
	
}
