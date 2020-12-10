package com.arkeup.link_innov.gestion_profil_mcs.contrainte.validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ErrorsEnum;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.GroupDTO;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.group.GroupRSM;

@Component
public class GroupDTOValidator implements Validator {

	@Autowired
	private GroupRSM groupRSM;

	@Override
	public boolean supports(Class<?> clazz) {
		return GroupDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		GroupDTO groupDTO = (GroupDTO) target;

		if (StringUtils.isBlank(groupDTO.getName())) {
			errors.rejectValue("name", "ERR_MCS_PROFIL_0072", ErrorsEnum.ERR_MCS_PROFIL_0072.getErrorMessage());
		}

		if (StringUtils.isNotEmpty(groupDTO.getName()) && groupDTO.getName().length() > 50) {
			errors.rejectValue("name", "ERR_MCS_PROFIL_0071", ErrorsEnum.ERR_MCS_PROFIL_0071.getErrorMessage());
		}

		if (StringUtils.isNotEmpty(groupDTO.getDescription()) && groupDTO.getDescription().length() > 2000) {
			errors.rejectValue("description", "ERR_MCS_PROFIL_0046", ErrorsEnum.ERR_MCS_PROFIL_0046.getErrorMessage());
		}

	}

	public GroupDTO validateDTO(Object target) {

		GroupDTO groupDTO = (GroupDTO) target;

		if (StringUtils.isBlank(groupDTO.getName())) {
			groupDTO.setErrorCode(ErrorsEnum.ERR_MCS_PROFIL_0072.getErrorMessage());
		}

		if (StringUtils.isNotEmpty(groupDTO.getName()) && groupDTO.getName().length() > 50) {
			groupDTO.setErrorCode(ErrorsEnum.ERR_MCS_PROFIL_0071.getErrorMessage());
		}

		if (StringUtils.isNotEmpty(groupDTO.getDescription()) && groupDTO.getDescription().length() > 2000) {

			groupDTO.setErrorCode(ErrorsEnum.ERR_MCS_PROFIL_0046.getErrorMessage());
		}
		return groupDTO;
	}

}
