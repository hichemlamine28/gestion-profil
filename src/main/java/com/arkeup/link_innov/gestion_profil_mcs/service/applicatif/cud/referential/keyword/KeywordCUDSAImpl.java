package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.referential.keyword;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ErrorsEnum;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.referential.KeywordMapper;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.Keyword;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.referential.KeywordDTO;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.referential.keyword.KeywordCUDSM;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.referential.keyword.KeywordRSM;

@Service
public class KeywordCUDSAImpl implements KeywordCUDSA {

	@Autowired
	private KeywordCUDSM keywordCUDSM;

	@Autowired
	private KeywordRSM keywordRSM;

	@Autowired
	private KeywordMapper keywordFactory;

	@Override
	public KeywordDTO addKeyword(KeywordDTO keywordDTO) {
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName = user.getUsername();

		Keyword result = null;

		if (StringUtils.isEmpty(keywordDTO.getLabel())) {
			keywordDTO.setError(true);
			keywordDTO.setErrorMessage(ErrorsEnum.ERR_MCS_PROFIL_0043.getErrorMessage());
			keywordDTO.setErrorCode("ERR_MCS_PROFIL_0043");
			return keywordDTO;
		}
		Keyword keyword = keywordRSM.findByLabel(keywordDTO.getLabel());
		if (keyword != null) {
			if (keyword.getUserIds().size() == 10) {
				return keywordFactory.keywordToKeywordDTO(keyword);
			} else if (keyword.getUserIds().size() == 9 && !keyword.getUserIds().contains(userName)) {
				keyword.getUserIds().add(userName);
				result = keywordCUDSM.update(keyword);
			} else if (!keyword.getUserIds().contains(userName)) {
				keyword.getUserIds().add(userName);
				result = keywordCUDSM.mongoUpdate(keyword);
			} else if (keyword.getUserIds().contains(userName)) {
				return keywordFactory.keywordToKeywordDTO(keyword);
			}
		} else {
			Keyword newKeyword = keywordFactory.keywordDTOToKeyword(keywordDTO);
			newKeyword.getUserIds().add(userName);
			result = keywordCUDSM.mongoCreate(newKeyword);
		}

		if (result.getId() == null) {
			keywordDTO.setError(true);
			keywordDTO.setErrorMessage(ErrorsEnum.ERR_MCS_PROFIL_0043.getErrorMessage());
			keywordDTO.setErrorCode("ERR_MCS_PROFIL_0043");
		} else {
			keywordDTO.setError(false);
			keywordDTO.setMessage("Keyword added");
			keywordDTO.setId(result.getId());
		}

		return keywordDTO;
	}

	@Override
	public KeywordDTO updateKeyword(KeywordDTO keywordDTO) {

		if (StringUtils.isEmpty(keywordDTO.getLabel())) {
			keywordDTO.setError(true);
			keywordDTO.setErrorMessage(ErrorsEnum.ERR_MCS_PROFIL_0043.getErrorMessage());
			keywordDTO.setErrorCode("ERR_MCS_PROFIL_0043");
			return keywordDTO;
		}

		keywordCUDSM.update(keywordFactory.keywordDTOToKeyword(keywordDTO));
		keywordDTO.setError(false);
		keywordDTO.setMessage("Keyword updated");
		return keywordDTO;
	}

	@Override
	public KeywordDTO deleteKeyword(String idKeyword) {
		Optional<Keyword> res = keywordRSM.findById(idKeyword);

		KeywordDTO result = new KeywordDTO();
		result.setId(idKeyword);
		if (res.isPresent()) {
			keywordCUDSM.delete(idKeyword);
			result.setError(false);
			result.setMessage("Keyword deleted");
		} else {
			result.setError(true);
			result.setErrorMessage(ErrorsEnum.ERR_MCS_OBJECT_NOT_FOUND.getErrorMessage());
			result.setErrorCode("ERR_MCS_OBJECT_NOT_FOUND");
		}
		return result;
	}

}
