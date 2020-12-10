/**
 * 
 */
package com.arkeup.link_innov.gestion_profil_mcs.contrainte.validator;

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ErrorsEnum;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.UserAuthDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


/**
 * UserAuthDTO validator in case of ReasearchGate extract
 */
@Component
public class UserAuthDTORGValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
		return UserAuthDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		UserAuthDTO userAuthDTO = (UserAuthDTO) target;
		
		// ReasearchGate username
		if(StringUtils.isBlank(userAuthDTO.getUsername())) {
			errors.rejectValue("username", "validtion.username.empty", ErrorsEnum.ERR_MCS_PROFIL_RG_LOGIN_ERROR.getErrorMessage());
		}

		// Password
		if(StringUtils.isBlank(userAuthDTO.getPassword())){
			errors.rejectValue("password", "validtion.password.empty", ErrorsEnum.ERR_MCS_PROFIL_0005.getErrorMessage());
		}
		
	}
	

	
	
}
