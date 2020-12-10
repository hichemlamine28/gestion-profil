/**
 * 
 */
package com.arkeup.link_innov.gestion_profil_mcs.contrainte.validator;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ErrorsEnum;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.CategoryDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.CorporationDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.referential.KeywordDTO;

@Component
public class CorporationDTOValidatorHandler {

	public boolean supports(Object obj) {
		boolean isSupported = false;
		if (obj != null) {
			isSupported = CorporationDTO.class.equals(obj.getClass());
		}

		return isSupported;
	}

	public Errors validate(Object obj) {
		Errors errors = new BindException(obj, "coporationDTO");
		boolean isValid = supports(obj);
		if (!isValid) {
			return errors;
		}

//		String urlRegex = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";

		String urlRegex = "((http?|https|ftp|file)://)?((W|w){3}.)?[a-zA-Z0-9]+\\.[a-zA-Z]+";

		Pattern pattern = Pattern.compile(urlRegex);

		CorporationDTO corporationDTO = (CorporationDTO) obj;
		CategoryDTO type = corporationDTO.getType();

		if (StringUtils.isEmpty(corporationDTO.getName())) {
			errors.rejectValue("name", "ERR_MCS_PROFIL_0013", ErrorsEnum.ERR_MCS_PROFIL_0013.getErrorMessage());
		}

		if (type == null) {
			errors.rejectValue("type", "ERR_MCS_PROFIL_0027", ErrorsEnum.ERR_MCS_PROFIL_0027.getErrorMessage());
		}

		if (type != null && StringUtils.equals("uuid-category-academique", type.getId())) {
			if ((corporationDTO.getTypology() == null)
					|| (StringUtils.isEmpty(corporationDTO.getTypology().getLabel()))) {
				errors.rejectValue("typology", "ERR_MCS_PROFIL_0028", ErrorsEnum.ERR_MCS_PROFIL_0028.getErrorMessage());
			}
			if ((corporationDTO.getThematicArea() == null)
					|| (StringUtils.isEmpty(corporationDTO.getThematicArea().getLabel()))) {
				errors.rejectValue("thematicArea", "ERR_MCS_PROFIL_0029",
						ErrorsEnum.ERR_MCS_PROFIL_0029.getErrorMessage());
			}
		}

		if (type != null && StringUtils.equals("uuid-category-industriel", type.getId())) {
			if (StringUtils.isEmpty(corporationDTO.getSiret())) {
				errors.rejectValue("siret", "ERR_MCS_PROFIL_0030", ErrorsEnum.ERR_MCS_PROFIL_0030.getErrorMessage());
			}
			if ((corporationDTO.getEmployeesNumber() == null)
					|| (StringUtils.isEmpty(corporationDTO.getEmployeesNumber().getLabel()))) {
				errors.rejectValue("employeesNumber", "ERR_MCS_PROFIL_0031",
						ErrorsEnum.ERR_MCS_PROFIL_0031.getErrorMessage());
			}

			if ((corporationDTO.getActivityArea() == null)
					|| (StringUtils.isEmpty(corporationDTO.getActivityArea().getLabel()))) {
				errors.rejectValue("activityArea", "ERR_MCS_PROFIL_0032",
						ErrorsEnum.ERR_MCS_PROFIL_0032.getErrorMessage());
			}
		}

		if (type != null && StringUtils.equals("uuid-category-autre-acteur", type.getId())) {
			if (StringUtils.isEmpty(corporationDTO.getClassification().getLabel())) {
				errors.rejectValue("classification", "ERR_MCS_PROFIL_0033",
						ErrorsEnum.ERR_MCS_PROFIL_0033.getErrorMessage());
			}
			if ((corporationDTO.getActivityArea() == null)
					|| (StringUtils.isEmpty(corporationDTO.getActivityArea().getLabel()))) {
				errors.rejectValue("activityArea", "ERR_MCS_PROFIL_0038",
						ErrorsEnum.ERR_MCS_PROFIL_0038.getErrorMessage());
			}
		}

		for (KeywordDTO keyword : corporationDTO.getKeywords()) {
			if (!StringUtils.isEmpty(keyword.getLabel()) && keyword.getLabel().length() > 50) {
				errors.rejectValue("keywords", "ERR_MCS_PROFIL_0034", ErrorsEnum.ERR_MCS_PROFIL_0034.getErrorMessage());
			}
		}

		if (!StringUtils.isEmpty(corporationDTO.getDescription()) && corporationDTO.getDescription().length() > 2000) {
			errors.rejectValue("description", "ERR_MCS_PROFIL_0036", ErrorsEnum.ERR_MCS_PROFIL_0036.getErrorMessage());
		}

		if (!StringUtils.isEmpty(corporationDTO.getSiret()) && corporationDTO.getSiret().length() > 14) {
			errors.rejectValue("siret", "ERR_MCS_PROFIL_0035", ErrorsEnum.ERR_MCS_PROFIL_0035.getErrorMessage());
		}

		if (StringUtils.isNotEmpty(corporationDTO.getWebSite())
				&& !pattern.matcher(corporationDTO.getWebSite()).matches()) {
			errors.rejectValue("webSite", "ERR_MCS_PROFIL_0037", ErrorsEnum.ERR_MCS_PROFIL_0037.getErrorMessage());
		}

		return errors;
	}

	public CorporationDTO validateDTO(Object obj) {
		boolean isValid = supports(obj);
		if (!isValid) {
			return new CorporationDTO();
		}

		String urlRegex = "((http?|https|ftp|file)://)?((W|w){3}.)?[a-zA-Z0-9]+\\.[a-zA-Z]+";

		Pattern pattern = Pattern.compile(urlRegex);

		CorporationDTO corporationDTO = (CorporationDTO) obj;
		CategoryDTO type = corporationDTO.getType();

		if (StringUtils.isEmpty(corporationDTO.getName())) {
			corporationDTO.setErrorCode(ErrorsEnum.ERR_MCS_PROFIL_0013.getErrorMessage());
		}

		if (type == null) {
			corporationDTO.setErrorCode(ErrorsEnum.ERR_MCS_PROFIL_0027.getErrorMessage());
		}

		if (type != null && StringUtils.equals("uuid-category-academique", type.getId())) {
			if ((corporationDTO.getTypology() == null)
					|| (StringUtils.isEmpty(corporationDTO.getTypology().getLabel()))) {
				corporationDTO.setErrorCode(ErrorsEnum.ERR_MCS_PROFIL_0028.getErrorMessage());
			}
			if ((corporationDTO.getThematicArea() == null)
					|| (StringUtils.isEmpty(corporationDTO.getThematicArea().getLabel()))) {
				corporationDTO.setErrorCode(ErrorsEnum.ERR_MCS_PROFIL_0029.getErrorMessage());
			}
		}

		if (type != null && StringUtils.equals("uuid-category-industriel", type.getId())) {
			if (StringUtils.isEmpty(corporationDTO.getSiret())) {
				corporationDTO.setErrorCode(ErrorsEnum.ERR_MCS_PROFIL_0030.getErrorMessage());
			}
			if ((corporationDTO.getEmployeesNumber() == null)
					|| (StringUtils.isEmpty(corporationDTO.getEmployeesNumber().getLabel()))) {
				corporationDTO.setErrorCode(ErrorsEnum.ERR_MCS_PROFIL_0031.getErrorMessage());
			}

			if ((corporationDTO.getActivityArea() == null)
					|| (StringUtils.isEmpty(corporationDTO.getActivityArea().getLabel()))) {
				corporationDTO.setErrorCode(ErrorsEnum.ERR_MCS_PROFIL_0032.getErrorMessage());
			}
		}

		if (type != null && StringUtils.equals("uuid-category-autre-acteur", type.getId())) {
			if (StringUtils.isEmpty(corporationDTO.getClassification().getLabel())) {
				corporationDTO.setErrorCode(ErrorsEnum.ERR_MCS_PROFIL_0033.getErrorMessage());
			}
			if ((corporationDTO.getActivityArea() == null)
					|| (StringUtils.isEmpty(corporationDTO.getActivityArea().getLabel()))) {
				corporationDTO.setErrorCode(ErrorsEnum.ERR_MCS_PROFIL_0038.getErrorMessage());
			}
		}

		for (KeywordDTO keyword : corporationDTO.getKeywords()) {
			if (!StringUtils.isEmpty(keyword.getLabel()) && keyword.getLabel().length() > 50) {
				corporationDTO.setErrorCode(ErrorsEnum.ERR_MCS_PROFIL_0034.getErrorMessage());
			}
		}

		if (!StringUtils.isEmpty(corporationDTO.getDescription()) && corporationDTO.getDescription().length() > 2000) {
			corporationDTO.setErrorCode(ErrorsEnum.ERR_MCS_PROFIL_0036.getErrorMessage());
		}

		if (!StringUtils.isEmpty(corporationDTO.getSiret()) && corporationDTO.getSiret().length() > 14) {
			corporationDTO.setErrorCode(ErrorsEnum.ERR_MCS_PROFIL_0035.getErrorMessage());
		}

		if (StringUtils.isNotEmpty(corporationDTO.getWebSite())
				&& !pattern.matcher(corporationDTO.getWebSite()).matches()) {
			corporationDTO.setErrorCode(ErrorsEnum.ERR_MCS_PROFIL_0037.getErrorMessage());
		}

		return corporationDTO;
	}
}
