package com.arkeup.link_innov.gestion_profil_mcs.contrainte.validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.SkillDTO;

public class SkillValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return SkillDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		SkillDTO skill = (SkillDTO) target;
		
		if (skill.getName() != null && StringUtils.isEmpty(skill.getName())) {
			errors.rejectValue(null, "ERR_MCS_PROFIL_0013",
					"Name cannot be null.");
		}
		
	}

}
