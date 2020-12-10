/**
 * 
 */
package com.arkeup.link_innov.gestion_profil_mcs.contrainte.validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ErrorsEnum;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.UserAuthDTO;


@Component
public class UserAuthDTOValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
		return UserAuthDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		UserAuthDTO userAuthDTO = (UserAuthDTO) target;
		
		// ProfileId
		if(StringUtils.isBlank(userAuthDTO.getUsername())) {
			errors.rejectValue("username", "validtion.profileId.empty", ErrorsEnum.ERR_MCS_PROFIL_0024.getErrorMessage());
		} /*else if(userAuthDTO.getUsername() == null) {
			errors.rejectValue("username", "validtion.profileId.null", ErrorsEnum.ERR_MCS_PROFIL_0023.getErrorMessage());
		}*/
		// Password
		if(StringUtils.isBlank(userAuthDTO.getPassword())){
			errors.rejectValue("password", "validtion.password.empty", ErrorsEnum.ERR_MCS_PROFIL_0005.getErrorMessage());
		} else if(userAuthDTO.getPassword().length() < 8) { 
			errors.rejectValue("password", "validtion.password.short", ErrorsEnum.ERR_MCS_PROFIL_0022.getErrorMessage());
		}
		
	}
	

	
	
}
