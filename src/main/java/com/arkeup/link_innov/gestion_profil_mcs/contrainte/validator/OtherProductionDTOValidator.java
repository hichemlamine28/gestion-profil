package com.arkeup.link_innov.gestion_profil_mcs.contrainte.validator;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.arkeup.link_innov.gestion_profil_mcs.commun.logging.Log;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.OtherProductionDTO;

public class OtherProductionDTOValidator implements Validator {

	@Log
	Logger log;

	/**
	 * This Validator validates just OtherProduction instances
	 */
	public boolean supports(Class clazz) {
		return OtherProductionDTO.class.equals(clazz);
	}

	public void validate(Object obj, Errors e) {

		String urlRegex = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";

		Pattern pattern = Pattern.compile(urlRegex);

		OtherProductionDTO otherProduction = (OtherProductionDTO) obj;

		if (otherProduction.getPostType() == null) {
			e.rejectValue("postType", "otherProduction.postType.null", "Other Production post type cannot be null.");
		}

		if (StringUtils.isNotEmpty(otherProduction.getLink())
				&& !pattern.matcher(otherProduction.getLink()).matches()) {
			e.rejectValue("link", "otherProduction.link.invalid", "Other Production link must be a valid URL.");
		}

	}
}
