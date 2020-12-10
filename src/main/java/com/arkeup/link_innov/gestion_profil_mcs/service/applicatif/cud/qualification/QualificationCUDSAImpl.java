package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.qualification;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ErrorsEnum;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.qualification.QualificationMapper;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Qualification;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ProfilDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.QualificationDTO;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.profil.ProfilRSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.qualification.QualificationCUDSM;

@Service
public class QualificationCUDSAImpl implements QualificationCUDSA {
	
	@Autowired
    private QualificationCUDSM qualificationCUDSM;
	
    @Autowired
    private QualificationMapper qualificationFactory;
    
    @Autowired
	ProfilRSA profilRSA;

	@Override
	public QualificationDTO create(String username, QualificationDTO qualificationDTO) {
		ProfilDTO user = profilRSA.getProfil(username);
		
		if (user.isError()) {
			qualificationDTO.setError(true);
			qualificationDTO.setErrorMessage("User is not in BD");
			qualificationDTO.setErrorMessage(ErrorsEnum.ERR_MCS_PROFIL_0007.getErrorMessage());
			qualificationDTO.setErrorCode("ERR_MCS_PROFIL_0007");
			return qualificationDTO;
		}
		
		if ((qualificationDTO.getInstitution() == null) || (qualificationDTO.getInstitution().equals(""))) {
			qualificationDTO.setError(true);
			qualificationDTO.setErrorMessage(ErrorsEnum.ERR_MCS_PROFIL_0008.getErrorMessage());
			qualificationDTO.setErrorCode("ERR_MCS_PROFIL_0008");
			return qualificationDTO;
		}
		
		if ((qualificationDTO.getDegree() == null) || (qualificationDTO.getDegree().equals(""))) {
			qualificationDTO.setError(true);
			qualificationDTO.setErrorMessage(ErrorsEnum.ERR_MCS_PROFIL_0009.getErrorMessage());
			qualificationDTO.setErrorCode("ERR_MCS_PROFIL_0009");
			return qualificationDTO;
		}
		
		if ((qualificationDTO.getField() == null) || (qualificationDTO.getField().equals(""))) {
			qualificationDTO.setError(true);
			qualificationDTO.setErrorMessage(ErrorsEnum.ERR_MCS_PROFIL_0010.getErrorMessage());
			qualificationDTO.setErrorCode("ERR_MCS_PROFIL_0010");
			return qualificationDTO;
		}
		
		if ((qualificationDTO.getStartDate() == null) || (qualificationDTO.getStartDate().after(new Date()))) {
			qualificationDTO.setError(true);
			qualificationDTO.setErrorMessage(ErrorsEnum.ERR_MCS_PROFIL_0011.getErrorMessage());
			qualificationDTO.setErrorCode("ERR_MCS_PROFIL_0011");
			return qualificationDTO;
		}
		
		qualificationDTO.setUserId(user.getId());
		
		QualificationDTO result = qualificationFactory.qualificationToQualificationDTO(qualificationCUDSM.create(qualificationFactory.qualificationDTOToQualification(qualificationDTO)));
		
		if (result.getId()== null) {
			qualificationDTO.setError(true);
			qualificationDTO.setErrorMessage(ErrorsEnum.ERR_MCS_PROFIL_0012.getErrorMessage());
			qualificationDTO.setErrorCode("ERR_MCS_PROFIL_0012");
		}
		else {
			qualificationDTO.setError(false);
			qualificationDTO.setMessage("Qualification added");
			qualificationDTO.setId(result.getId());
		}
		
		return qualificationDTO;
	}

	@Override
	public QualificationDTO update(QualificationDTO qualificationDTO) {
		if ((qualificationDTO.getInstitution() == null) || (qualificationDTO.getInstitution().equals(""))) {
			qualificationDTO.setError(true);
			qualificationDTO.setErrorMessage(ErrorsEnum.ERR_MCS_PROFIL_0008.getErrorMessage());
			qualificationDTO.setErrorCode("ERR_MCS_PROFIL_0008");
			return qualificationDTO;
		}
		
		if ((qualificationDTO.getDegree() == null) || (qualificationDTO.getDegree().equals(""))) {
			qualificationDTO.setError(true);
			qualificationDTO.setErrorMessage(ErrorsEnum.ERR_MCS_PROFIL_0009.getErrorMessage());
			qualificationDTO.setErrorCode("ERR_MCS_PROFIL_0009");
			return qualificationDTO;
		}
		
		if ((qualificationDTO.getField() == null) || (qualificationDTO.getField().equals(""))) {
			qualificationDTO.setError(true);
			qualificationDTO.setErrorMessage(ErrorsEnum.ERR_MCS_PROFIL_0010.getErrorMessage());
			qualificationDTO.setErrorCode("ERR_MCS_PROFIL_0010");
			return qualificationDTO;
		}
		
		if ((qualificationDTO.getStartDate() == null) || (qualificationDTO.getStartDate().equals(""))) {
			qualificationDTO.setError(true);
			qualificationDTO.setErrorMessage(ErrorsEnum.ERR_MCS_PROFIL_0011.getErrorMessage());
			qualificationDTO.setErrorCode("ERR_MCS_PROFIL_0011");
			return qualificationDTO;
		}
		
		qualificationCUDSM.update(qualificationFactory.qualificationDTOToQualification(qualificationDTO));
		qualificationDTO.setError(false);
		qualificationDTO.setMessage("Qualification updated");
		return qualificationDTO;
		
	}

	@Override
	public QualificationDTO delete(String id) {
		Optional<Qualification> entity = qualificationCUDSM.delete(id);
		
		QualificationDTO result = new QualificationDTO();
		if (entity.isPresent()) {
			result = qualificationFactory.qualificationToQualificationDTO(entity.get());
		}
		else {
			
			result.setError(true);
			result.setErrorMessage(ErrorsEnum.ERR_MCS_PROFIL_0015.getErrorMessage());
			result.setErrorCode("ERR_MCS_PROFIL_0015");
		}
		return result;
	}

}
