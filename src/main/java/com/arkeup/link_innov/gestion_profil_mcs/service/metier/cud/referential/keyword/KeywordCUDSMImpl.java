package com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.referential.keyword;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.Keyword;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.KeywordRepository;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.elastic_search.KeywordESRepository;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs.KeywordMongoRepository;

@Service
public class KeywordCUDSMImpl implements KeywordCUDSM {

	@Autowired
	private KeywordMongoRepository keywordMongoRepository;

	@Autowired
	private KeywordESRepository keywordESRepository;

	@Autowired
	private KeywordRepository keywordRepository;

	@Override
	public Keyword mongoCreate(Keyword keyword) {
		keywordMongoRepository.save(keyword);
		return keyword;
	}

	@Override
	public Keyword esCreate(Keyword keyword) {
		keywordESRepository.save(keyword);
		return keyword;
	}

	@Override
	public Keyword mongoUpdate(Keyword keyword) {
		keywordMongoRepository.save(keyword);
		return keyword;
	}

	@Override
	public Keyword update(Keyword keyword) {
		return keywordRepository.save(keyword);
	}

	@Override
	public void delete(String id) {
		keywordRepository.deleteById(id);

	}

}
