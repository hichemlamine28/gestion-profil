package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.referential.classification;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.referential.ClassificationDTO;

public interface ClassificationCUDSA {

	public ClassificationDTO addClassification(ClassificationDTO classificationDTO);

	public ClassificationDTO updateClassification(ClassificationDTO classificationsDTO);

	public ClassificationDTO deleteClassification(String idClassification);

	public void initDatabase();

}
