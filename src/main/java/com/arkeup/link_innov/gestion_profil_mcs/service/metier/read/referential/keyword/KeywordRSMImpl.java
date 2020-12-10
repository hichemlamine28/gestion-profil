package com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.referential.keyword;

import java.util.List;
import java.util.Optional;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.Keyword;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.KeywordRepository;

@Service
public class KeywordRSMImpl implements KeywordRSM {

	@Autowired
	KeywordRepository keywordRepository;

	@Override
	public Page<Keyword> listKeywords(Pageable pageable) {
		return keywordRepository.findAll(pageable);
	}

	@Override
	public Optional<Keyword> findById(String id) {
		return keywordRepository.findById(id);
	}

	@Override
	public Keyword findByLabel(String label) {
		List<Keyword> keywords = keywordRepository.findByLabel(label);
		if (CollectionUtils.isNotEmpty(keywords)) {
			return keywords.get(0);
		}
		return null;
	}

}
