package com.arkeup.link_innov.gestion_profil_mcs.contrainte.validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ErrorsEnum;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.dynamic_page.DynamicPageDTO;

/**
 * @author Stéphan R.
 *
 */
@Component
public class DynamicPageValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return DynamicPageDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		DynamicPageDTO dynamicPageDTO = (DynamicPageDTO) target;

		if(StringUtils.isBlank(dynamicPageDTO.getContent())) {
			errors.rejectValue("content", ErrorsEnum.ERR_MCS_EMPTY_CONTENT.getErrorCode(), ErrorsEnum.ERR_MCS_EMPTY_CONTENT.getErrorMessage());
		}

		if(StringUtils.isBlank(dynamicPageDTO.getUrlLabel())) {
			errors.rejectValue("urlLabel", ErrorsEnum.ERR_MCS_EMPTY_LABEL_URL.getErrorCode(), ErrorsEnum.ERR_MCS_EMPTY_LABEL_URL.getErrorMessage());
		}
	}
}
