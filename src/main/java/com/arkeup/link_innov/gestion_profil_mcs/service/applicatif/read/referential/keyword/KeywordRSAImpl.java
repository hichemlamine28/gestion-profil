package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.referential.keyword;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.referential.KeywordMapper;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.referential.KeywordDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.referential.KeywordsDTO;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.referential.keyword.KeywordRSM;

@Service
public class KeywordRSAImpl implements KeywordRSA {

	@Autowired
	KeywordRSM keywordRSM;

	@Autowired
	KeywordMapper keywordFactory;

	@Override
	public KeywordsDTO listKeywords(Pageable pageable) {

		KeywordsDTO result = new KeywordsDTO();
		result.setKeywordsDTO(keywordFactory.keywordPageToKeywordDTOPage(keywordRSM.listKeywords(pageable), pageable));
		result.setError(false);
		result.setMessage("List of all keywords");
		return result;
	}

	@Override
	public KeywordDTO findByLabel(String label) {
		return keywordFactory.keywordToKeywordDTO(keywordRSM.findByLabel(label));
	}

}
