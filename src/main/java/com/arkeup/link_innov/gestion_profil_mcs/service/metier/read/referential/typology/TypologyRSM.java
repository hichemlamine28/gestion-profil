package com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.referential.typology;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.Typology;

public interface TypologyRSM {
	Page<Typology> listTypologies(Pageable pageable);
	Optional<Typology> findById(String id);
}
