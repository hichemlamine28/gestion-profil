package com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.referential.classification;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.Classification;

public interface ClassificationRSM {
	Page<Classification> listClassifications(Pageable pageable);
	Optional<Classification> findById(String id);
}
