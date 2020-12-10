package com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.recommandation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Recommandation;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs.RecommandationMongoRepository;


@Service
public class RecommandationActionCUDSMImpl implements RecommandationCUDSM {
	
	@Autowired
	private RecommandationMongoRepository repository;

	@Override
	public Recommandation saveRecommandation(Recommandation recommandation) {
		return repository.save(recommandation);
	}

	@Override
	public Recommandation getRecommandation(String id) {
		Optional<Recommandation> recommandationOptional = repository.findById(id);
		if (recommandationOptional.isPresent())  return recommandationOptional.get();
		return null;
	}

}
