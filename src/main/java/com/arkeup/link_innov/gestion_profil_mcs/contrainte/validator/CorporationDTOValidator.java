package com.arkeup.link_innov.gestion_profil_mcs.contrainte.validator;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.arkeup.link_innov.gestion_profil_mcs.commun.logging.Log;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ErrorsEnum;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.CategoryDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.CorporationDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.referential.KeywordDTO;

@Component
public class CorporationDTOValidator implements Validator {

	@Log
	Logger log;

	/**
	 * This Validator validates just Corporation instances
	 */
	public boolean supports(Class clazz) {
		return CorporationDTO.class.equals(clazz);
	}

	public void validate(Object obj, Errors e) {

		String urlRegex = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";

		Pattern pattern = Pattern.compile(urlRegex);

		CorporationDTO corporationDTO = (CorporationDTO) obj;
		CategoryDTO type = corporationDTO.getType();

		if (StringUtils.isEmpty(corporationDTO.getName())) {
			e.rejectValue("name", "ERR_MCS_PROFIL_0013", ErrorsEnum.ERR_MCS_PROFIL_0013.getErrorMessage());
		}

		if (type == null) {
			e.rejectValue("type", "ERR_MCS_PROFIL_0027", ErrorsEnum.ERR_MCS_PROFIL_0027.getErrorMessage());
		}

		if (type != null && StringUtils.equals("uuid-category-academique", type.getId())) {
			if ((corporationDTO.getTypology() == null) || (StringUtils.isEmpty(corporationDTO.getTypology().getLabel()))) {
				e.rejectValue("typology", "ERR_MCS_PROFIL_0028", ErrorsEnum.ERR_MCS_PROFIL_0028.getErrorMessage());
			}
			if ((corporationDTO.getThematicArea() == null) || (StringUtils.isEmpty(corporationDTO.getThematicArea().getLabel()))) {
				e.rejectValue("thematicArea", "ERR_MCS_PROFIL_0029", ErrorsEnum.ERR_MCS_PROFIL_0029.getErrorMessage());
			}
		}

		if (type != null && StringUtils.equals("uuid-category-industriel", type.getId())) {
			if (StringUtils.isEmpty(corporationDTO.getSiret())) {
				e.rejectValue("siret", "ERR_MCS_PROFIL_0030", ErrorsEnum.ERR_MCS_PROFIL_0030.getErrorMessage());
			}
			if ((corporationDTO.getEmployeesNumber() == null) || (StringUtils.isEmpty(corporationDTO.getEmployeesNumber().getLabel()))) {
				e.rejectValue("employeesNumber", "ERR_MCS_PROFIL_0031",
						ErrorsEnum.ERR_MCS_PROFIL_0031.getErrorMessage());
			}

			if ((corporationDTO.getActivityArea() == null) || (StringUtils.isEmpty(corporationDTO.getActivityArea().getLabel()))) {
				e.rejectValue("activityArea", "ERR_MCS_PROFIL_0032", ErrorsEnum.ERR_MCS_PROFIL_0032.getErrorMessage());
			}
		}

		if (type != null && StringUtils.equals("uuid-category-autre-acteur", type.getId())) {
			if (StringUtils.isEmpty(corporationDTO.getClassification().getLabel())) {
				e.rejectValue("classification", "ERR_MCS_PROFIL_0033",
						ErrorsEnum.ERR_MCS_PROFIL_0033.getErrorMessage());
			}
			if ((corporationDTO.getActivityArea() == null) || (StringUtils.isEmpty(corporationDTO.getActivityArea().getLabel()))) {
				e.rejectValue("activityArea", "ERR_MCS_PROFIL_0038", ErrorsEnum.ERR_MCS_PROFIL_0038.getErrorMessage());
			}
		}

		for (KeywordDTO keyword : corporationDTO.getKeywords()) {
			if (!StringUtils.isEmpty(keyword.getLabel()) && keyword.getLabel().length() > 50) {
				e.rejectValue("keywords", "ERR_MCS_PROFIL_0034", ErrorsEnum.ERR_MCS_PROFIL_0034.getErrorMessage());
			}
		}

		if (!StringUtils.isEmpty(corporationDTO.getDescription()) && corporationDTO.getDescription().length() > 2000) {
			e.rejectValue("description", "ERR_MCS_PROFIL_0036", ErrorsEnum.ERR_MCS_PROFIL_0036.getErrorMessage());
		}

		if (!StringUtils.isEmpty(corporationDTO.getSiret()) && corporationDTO.getSiret().length() > 14) {
			e.rejectValue("siret", "ERR_MCS_PROFIL_0035", ErrorsEnum.ERR_MCS_PROFIL_0035.getErrorMessage());
		}

		if (StringUtils.isNotEmpty(corporationDTO.getWebSite())
				&& !pattern.matcher(corporationDTO.getWebSite()).matches()) {
			e.rejectValue("webSite", "ERR_MCS_PROFIL_0037", ErrorsEnum.ERR_MCS_PROFIL_0037.getErrorMessage());
		}

	}
}
