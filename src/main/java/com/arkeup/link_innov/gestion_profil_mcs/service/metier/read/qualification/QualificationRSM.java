package com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.qualification;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Qualification;

public interface QualificationRSM {
	Optional<Qualification> getQualification(String id);
	Page<Qualification> listQualification(String userId, Pageable pageable);
}
