package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.qualification;

import org.springframework.data.domain.Pageable;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.QualificationDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.QualificationsDTO;

public interface QualificationRSA {
	public QualificationDTO getQualification(String id);
	public QualificationsDTO listQualification(String username, Pageable pageable);
}
