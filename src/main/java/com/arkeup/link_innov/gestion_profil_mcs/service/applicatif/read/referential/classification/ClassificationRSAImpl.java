package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.referential.classification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.referential.ClassificationMapper;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.referential.ClassificationsDTO;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.referential.classification.ClassificationRSM;

@Service
public class ClassificationRSAImpl implements ClassificationRSA {

	@Autowired
	ClassificationRSM classificationRSM;
	
	@Autowired
	ClassificationMapper classificationFactory;
	
	@Override
	public ClassificationsDTO listClassifications(Pageable pageable) {
		
		ClassificationsDTO result = new ClassificationsDTO();
		result.setClassificationsDTO(classificationFactory.classificationPageToClassificationDTOPage(classificationRSM.listClassifications(pageable), pageable));
		result.setError(false);
		result.setMessage("List of all classifications");
		return result;
	}

}
