package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.qualification;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.QualificationDTO;

public interface QualificationCUDSA {
	
	public QualificationDTO create(String username, QualificationDTO qualificationDTO);
	public QualificationDTO update(QualificationDTO qualificationDTO);
	public QualificationDTO delete(String id);

}
