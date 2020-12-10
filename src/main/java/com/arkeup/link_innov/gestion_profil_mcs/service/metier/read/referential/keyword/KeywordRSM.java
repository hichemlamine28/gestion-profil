package com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.referential.keyword;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.Keyword;

public interface KeywordRSM {
	Page<Keyword> listKeywords(Pageable pageable);

	Optional<Keyword> findById(String id);

	Keyword findByLabel(String label);
}
