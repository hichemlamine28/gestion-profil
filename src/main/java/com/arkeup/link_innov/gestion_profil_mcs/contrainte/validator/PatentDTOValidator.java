package com.arkeup.link_innov.gestion_profil_mcs.contrainte.validator;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.arkeup.link_innov.gestion_profil_mcs.commun.logging.Log;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.PatentDTO;

public class PatentDTOValidator implements Validator {

	@Log
	Logger log;

	/**
	 * This Validator validates just Patent instances
	 */
	public boolean supports(Class clazz) {
		return PatentDTO.class.equals(clazz);
	}

	public void validate(Object obj, Errors e) {

		String urlRegex = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";

		Pattern pattern = Pattern.compile(urlRegex);

		PatentDTO patent = (PatentDTO) obj;

		/*
		 * if (StringUtils.isEmpty(patent.getPublicationNumber())) {
		 * e.rejectValue("publicationNumber", "patent.publicationNumber.null",
		 * "Patent publication number cannot be null."); }
		 */

		if (patent.getPriorityDepositDate() == null) {
			e.rejectValue("priorityDepositDate", "patent.priorityDepositDate.null",
					"Patent priority deposit date cannot be null.");
		}

		if (StringUtils.isNotEmpty(patent.getPublicationNumber()) && patent.getPublicationNumber().length() > 120) {
			e.rejectValue("description", "patent.publicationNumber.exceed",
					"Patent publication number must not exceed 120 characters.");
		}

		if (StringUtils.isEmpty(patent.getTitleNonConfidential())) {
			e.rejectValue("titleNonConfidential", "patent.titleNonConfidential.null",
					"Patent title non confidential cannot be null.");
		}

//		if (StringUtils.isNotEmpty(patent.getTitleNonConfidential())
//				&& patent.getTitleNonConfidential().length() > 120) {
//			e.rejectValue("description", "patent.titleNonConfidential.exceed",
//					"The title not confidential must not exceed 120 characters.");
//		}

//		if (StringUtils.isNotEmpty(patent.getTitle()) && patent.getTitle().length() > 120) {
//			e.rejectValue("title", "patent.title.exceed", "The title must not exceed 120 characters.");
//		}

		if (StringUtils.isNotEmpty(patent.getUrl()) && !pattern.matcher(patent.getUrl()).matches()) {
			e.rejectValue("url", "patent.url.invalid", "Patent url must be a valid URL.");
		}
	}
}
