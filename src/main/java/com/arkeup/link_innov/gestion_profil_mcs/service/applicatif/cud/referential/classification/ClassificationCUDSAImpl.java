package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.referential.classification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ErrorsEnum;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.referential.ClassificationMapper;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.Classification;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.referential.ClassificationDTO;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.referential.classification.ClassificationCUDSM;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.referential.classification.ClassificationRSM;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.ClassificationRepository;

@Service
public class ClassificationCUDSAImpl implements ClassificationCUDSA {

	@Autowired
	private ClassificationCUDSM classificationCUDSM;

	@Autowired
	private ClassificationRSM classificationRSM;

	@Autowired
	private ClassificationMapper classificationFactory;

	@Autowired
	private ClassificationRepository classificationRepository;

	@Override
	public ClassificationDTO addClassification(ClassificationDTO classificationDTO) {

		if (StringUtils.isEmpty(classificationDTO.getLabel())) {
			classificationDTO.setError(true);
			classificationDTO.setErrorMessage(ErrorsEnum.ERR_MCS_PROFIL_0043.getErrorMessage());
			classificationDTO.setErrorCode("ERR_MCS_PROFIL_0043");
			return classificationDTO;
		}

		Classification result = classificationCUDSM
				.create(classificationFactory.classificationDTOToClassification(classificationDTO));

		if (result.getId() == null) {
			classificationDTO.setError(true);
			classificationDTO.setErrorMessage(ErrorsEnum.ERR_MCS_PROFIL_0043.getErrorMessage());
			classificationDTO.setErrorCode("ERR_MCS_PROFIL_0043");
		} else {
			classificationDTO.setError(false);
			classificationDTO.setMessage("Classification added");
			classificationDTO.setId(result.getId());
		}

		return classificationDTO;
	}

	@Override
	public ClassificationDTO updateClassification(ClassificationDTO classificationDTO) {

		if (StringUtils.isEmpty(classificationDTO.getLabel())) {
			classificationDTO.setError(true);
			classificationDTO.setErrorMessage(ErrorsEnum.ERR_MCS_PROFIL_0043.getErrorMessage());
			classificationDTO.setErrorCode("ERR_MCS_PROFIL_0043");
			return classificationDTO;
		}

		classificationCUDSM.update(classificationFactory.classificationDTOToClassification(classificationDTO));
		classificationDTO.setError(false);
		classificationDTO.setMessage("Classification updated");
		return classificationDTO;
	}

	@Override
	public ClassificationDTO deleteClassification(String idClassification) {
		Optional<Classification> res = classificationRSM.findById(idClassification);

		ClassificationDTO result = new ClassificationDTO();
		result.setId(idClassification);
		if (res.isPresent()) {
			classificationCUDSM.delete(idClassification);
			result.setError(false);
			result.setMessage("Classification deleted");
		} else {
			result.setError(true);
			result.setErrorMessage(ErrorsEnum.ERR_MCS_OBJECT_NOT_FOUND.getErrorMessage());
			result.setErrorCode("ERR_MCS_OBJECT_NOT_FOUND");
		}
		return result;
	}

	@Override
	public void initDatabase() {

		List<Classification> classifications = new ArrayList<>();

		Classification classification = new Classification();
		classification.setId("uuid-classification-petite-entreprise");
		classification.setLabel("Petite entreprise");
		classifications.add(classification);

		classification = new Classification();
		classification.setId("uuid-classification-moyenne-entreprise");
		classification.setLabel("Moyenne entreprise");
		classifications.add(classification);

		classificationRepository.saveAll(classifications);

	}

}
