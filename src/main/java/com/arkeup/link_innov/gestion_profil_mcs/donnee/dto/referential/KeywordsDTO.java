package com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.referential;

import org.springframework.data.domain.Page;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.BaseDTO;

public class KeywordsDTO extends BaseDTO {

	private Page<KeywordDTO> keywordsDTO;

	public Page<KeywordDTO> getKeywordsDTO() {
		return keywordsDTO;
	}

	public void setKeywordsDTO(Page<KeywordDTO> keywordsDTO) {
		this.keywordsDTO = keywordsDTO;
	}

}
