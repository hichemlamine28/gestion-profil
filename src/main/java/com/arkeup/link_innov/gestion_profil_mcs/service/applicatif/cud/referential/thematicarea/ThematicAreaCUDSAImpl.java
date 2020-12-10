package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.referential.thematicarea;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ErrorsEnum;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.referential.ThematicAreaMapper;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.ThematicArea;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.referential.ThematicAreaDTO;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.referential.thematicarea.ThematicAreaCUDSM;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.referential.thematicarea.ThematicAreaRSM;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.ThematicAreaRepository;

@Service
public class ThematicAreaCUDSAImpl implements ThematicAreaCUDSA {

	@Autowired
	private ThematicAreaCUDSM thematicAreaCUDSM;

	@Autowired
	private ThematicAreaRSM thematicAreaRSM;

	@Autowired
	private ThematicAreaMapper thematicAreaFactory;

	@Autowired
	private ThematicAreaRepository thematicAreaRepository;

	@Override
	public ThematicAreaDTO addThematicArea(ThematicAreaDTO thematicAreaDTO) {

		if (StringUtils.isEmpty(thematicAreaDTO.getLabel())) {
			thematicAreaDTO.setError(true);
			thematicAreaDTO.setErrorMessage(ErrorsEnum.ERR_MCS_PROFIL_0043.getErrorMessage());
			thematicAreaDTO.setErrorCode("ERR_MCS_PROFIL_0043");
			return thematicAreaDTO;
		}

		ThematicArea result = thematicAreaCUDSM
				.create(thematicAreaFactory.thematicAreaDTOToThematicArea(thematicAreaDTO));

		if (result.getId() == null) {
			thematicAreaDTO.setError(true);
			thematicAreaDTO.setErrorMessage(ErrorsEnum.ERR_MCS_PROFIL_0043.getErrorMessage());
			thematicAreaDTO.setErrorCode("ERR_MCS_PROFIL_0043");
		} else {
			thematicAreaDTO.setError(false);
			thematicAreaDTO.setMessage("Thematic Area added");
			thematicAreaDTO.setId(result.getId());
		}

		return thematicAreaDTO;
	}

	@Override
	public ThematicAreaDTO updateThematicArea(ThematicAreaDTO thematicAreaDTO) {

		if (StringUtils.isEmpty(thematicAreaDTO.getLabel())) {
			thematicAreaDTO.setError(true);
			thematicAreaDTO.setErrorMessage(ErrorsEnum.ERR_MCS_PROFIL_0043.getErrorMessage());
			thematicAreaDTO.setErrorCode("ERR_MCS_PROFIL_0043");
			return thematicAreaDTO;
		}

		thematicAreaCUDSM.update(thematicAreaFactory.thematicAreaDTOToThematicArea(thematicAreaDTO));
		thematicAreaDTO.setError(false);
		thematicAreaDTO.setMessage("Thematic Area updated");
		return thematicAreaDTO;
	}

	@Override
	public ThematicAreaDTO deleteThematicArea(String idThematicArea) {
		Optional<ThematicArea> res = thematicAreaRSM.findById(idThematicArea);

		ThematicAreaDTO result = new ThematicAreaDTO();
		result.setId(idThematicArea);
		if (res.isPresent()) {
			thematicAreaCUDSM.delete(idThematicArea);
			result.setError(false);
			result.setMessage("ThematicArea deleted");
		} else {
			result.setError(true);
			result.setErrorMessage(ErrorsEnum.ERR_MCS_OBJECT_NOT_FOUND.getErrorMessage());
			result.setErrorCode("ERR_MCS_OBJECT_NOT_FOUND");
		}
		return result;
	}

	@Override
	public void initDatabase() {

		List<ThematicArea> thematicAreas = new ArrayList<>();

		ThematicArea thematicArea = new ThematicArea();
		thematicArea.setId("uuid-thematic-area-technologie-information");
		thematicArea.setLabel("Technologie d'information");
		thematicAreas.add(thematicArea);

		thematicArea = new ThematicArea();
		thematicArea.setId("uuid-thematic-area-marche-filiere");
		thematicArea.setLabel("Marché, filière");
		thematicAreas.add(thematicArea);

		thematicAreaRepository.saveAll(thematicAreas);

	}

}
