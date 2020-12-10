package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.referential.classification;

import org.springframework.data.domain.Pageable;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.referential.ClassificationsDTO;

public interface ClassificationRSA {
	ClassificationsDTO listClassifications(Pageable pageable);
}
