package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.qualification;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ErrorsEnum;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.qualification.QualificationMapper;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Profil;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Qualification;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.QualificationDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.QualificationsDTO;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.profil.ProfilRSM;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.qualification.QualificationRSM;

@Service
public class QualificationRSAImpl implements QualificationRSA {

	@Autowired
	private QualificationRSM qualificationRSM;
	@Autowired
	private ProfilRSM profilRSM;

	@Autowired
	private QualificationMapper qualificationFactory;

	@Override
	public QualificationDTO getQualification(String id) {
		Optional<Qualification> res = qualificationRSM.getQualification(id);
		QualificationDTO result = new QualificationDTO();
		if (!res.isPresent()) {
			result.setError(true);
			result.setErrorCode("ERR_MCS_PROFIL_0015");
			result.setErrorMessage(ErrorsEnum.ERR_MCS_PROFIL_0015.getErrorMessage() + id);
			return result;
		}

		result = qualificationFactory.qualificationToQualificationDTO(res.get());
		result.setError(false);
		result.setMessage("Qualification information");
		return result;
	}

	@Override
	public QualificationsDTO listQualification(String username, Pageable pageable) {
		QualificationsDTO result = new QualificationsDTO();
		Profil user = profilRSM.getInformation(username);
		if (user == null) {
			result.setError(true);
			result.setErrorMessage("User is not in BD");
			result.setErrorMessage(ErrorsEnum.ERR_MCS_PROFIL_0007.getErrorMessage());
			result.setErrorCode("ERR_MCS_PROFIL_0007");
			return result;
		}
		Page<Qualification> res = qualificationRSM.listQualification(user.getId(), pageable);
		result.setQualificationDTOs(qualificationFactory.qualificationPageToQualificationDtoPage(res, pageable));
		result.setError(false);
		result.setMessage("Qualification list");
		return result;
	}
}
