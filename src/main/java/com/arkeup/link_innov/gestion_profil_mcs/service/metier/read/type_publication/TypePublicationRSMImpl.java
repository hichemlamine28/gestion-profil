package com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.type_publication;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.TypePublication;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs.TypePublicationMongoRepository;

@Service
public class TypePublicationRSMImpl implements TypePublicationRSM {

	@Autowired
	private TypePublicationMongoRepository mongoRepository;

	@Override
	public List<TypePublication> findAll() {
		return mongoRepository.findAll();
	}

}
