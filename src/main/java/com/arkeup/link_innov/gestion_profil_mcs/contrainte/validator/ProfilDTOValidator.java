package com.arkeup.link_innov.gestion_profil_mcs.contrainte.validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ErrorsEnum;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ProfilDTO;

@Component
public class ProfilDTOValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return ProfilDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		ProfilDTO profilDTO = (ProfilDTO) target;

		if ((profilDTO.getLastname() == null) || (profilDTO.getLastname().equals(""))
				|| (profilDTO.getLastname().length() > 80)) {
			errors.rejectValue("lastname", "ERR_MCS_PROFIL_0002", ErrorsEnum.ERR_MCS_PROFIL_0002.getErrorMessage());
		}

		if ((profilDTO.getFirstname() == null) || (profilDTO.getFirstname().equals(""))
				|| (profilDTO.getFirstname().length() > 80)) {
			errors.rejectValue("firstname", "ERR_MCS_PROFIL_0003", ErrorsEnum.ERR_MCS_PROFIL_0003.getErrorMessage());
		}

//		if (profilDTO.getCompany() != null && StringUtils.isEmpty(profilDTO.getCompany().getName())) {
//			profilDTO.setError(true);
//			errors.rejectValue("company", "ERR_MCS_PROFIL_0008", ErrorsEnum.ERR_MCS_PROFIL_0008.getErrorMessage());
//		}

		if (profilDTO.getCategory() != null && StringUtils.isEmpty(profilDTO.getCategory().getName())) {
			profilDTO.setError(true);
			errors.rejectValue("company", "ERR_MCS_PROFIL_0021", ErrorsEnum.ERR_MCS_PROFIL_0021.getErrorMessage());
		}

		if (profilDTO.getOccupation() != null && profilDTO.getOccupation().length() > 100) {
			profilDTO.setError(true);
			errors.rejectValue("occupation", "ERR_MCS_PROFIL_0076", ErrorsEnum.ERR_MCS_PROFIL_0076.getErrorMessage());
		}
	}

}
