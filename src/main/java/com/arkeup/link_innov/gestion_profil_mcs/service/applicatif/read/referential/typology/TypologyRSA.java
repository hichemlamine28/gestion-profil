package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.referential.typology;

import org.springframework.data.domain.Pageable;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.referential.TypologiesDTO;

public interface TypologyRSA {
	TypologiesDTO listTypologies(Pageable pageable);
}
