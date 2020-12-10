package com.arkeup.link_innov.gestion_profil_mcs.contrainte.validator;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.arkeup.link_innov.gestion_profil_mcs.commun.logging.Log;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ProjectDTO;

/**
 * @author Stéphan R.
 *
 */
public class ProjectDTOValidator implements Validator {

	@Log
	Logger log;

	/**
	 * @param clazz
	 *
	 * @return
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return ProjectDTO.class.equals(clazz);
	}

	/**
	 * @param target
	 * @param errors
	 */
	@Override
	public void validate(Object target, Errors errors) {
		ProjectDTO projectDTO = (ProjectDTO) target;

		if(projectDTO.getDate() == null) {
			errors.rejectValue("date", "projectDTO.date.null", "The date cannot be null.");
		}

		if(projectDTO.getTitle() == null) {
			errors.rejectValue("title", "projectDTO.title.null", "The title cannot be null.");
		}

		if(StringUtils.isBlank(projectDTO.getTitle())) {
			errors.rejectValue("title", "projectDTO.title.empty", "The title cannot be empty.");
		}
	}
}
