package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.referential.keyword;

import org.springframework.data.domain.Pageable;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.referential.KeywordDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.referential.KeywordsDTO;

public interface KeywordRSA {
	KeywordsDTO listKeywords(Pageable pageable);

	KeywordDTO findByLabel(String label);
}
