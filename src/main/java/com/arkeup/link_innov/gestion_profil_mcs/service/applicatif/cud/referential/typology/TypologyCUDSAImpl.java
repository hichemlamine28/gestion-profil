package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.referential.typology;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ErrorsEnum;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.referential.TypologyMapper;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.Typology;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.referential.TypologyDTO;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.referential.typology.TypologyCUDSM;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.referential.typology.TypologyRSM;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.TypologyRepository;

@Service
public class TypologyCUDSAImpl implements TypologyCUDSA {

	@Autowired
	private TypologyCUDSM typologyCUDSM;

	@Autowired
	private TypologyRSM typologyRSM;

	@Autowired
	private TypologyMapper typologyFactory;

	@Autowired
	private TypologyRepository typologyRepository;

	@Override
	public TypologyDTO addTypology(TypologyDTO typologyDTO) {

		if (StringUtils.isEmpty(typologyDTO.getLabel())) {
			typologyDTO.setError(true);
			typologyDTO.setErrorMessage(ErrorsEnum.ERR_MCS_PROFIL_0043.getErrorMessage());
			typologyDTO.setErrorCode("ERR_MCS_PROFIL_0043");
			return typologyDTO;
		}

		Typology result = typologyCUDSM.create(typologyFactory.typologyDTOToTypology(typologyDTO));

		if (result.getId() == null) {
			typologyDTO.setError(true);
			typologyDTO.setErrorMessage(ErrorsEnum.ERR_MCS_PROFIL_0043.getErrorMessage());
			typologyDTO.setErrorCode("ERR_MCS_PROFIL_0043");
		} else {
			typologyDTO.setError(false);
			typologyDTO.setMessage("Typology added");
			typologyDTO.setId(result.getId());
		}

		return typologyDTO;
	}

	@Override
	public TypologyDTO updateTypology(TypologyDTO typologyDTO) {

		if (StringUtils.isEmpty(typologyDTO.getLabel())) {
			typologyDTO.setError(true);
			typologyDTO.setErrorMessage(ErrorsEnum.ERR_MCS_PROFIL_0043.getErrorMessage());
			typologyDTO.setErrorCode("ERR_MCS_PROFIL_0043");
			return typologyDTO;
		}

		typologyCUDSM.update(typologyFactory.typologyDTOToTypology(typologyDTO));
		typologyDTO.setError(false);
		typologyDTO.setMessage("Typology updated");
		return typologyDTO;
	}

	@Override
	public TypologyDTO deleteTypology(String idTypology) {
		Optional<Typology> res = typologyRSM.findById(idTypology);

		TypologyDTO result = new TypologyDTO();
		result.setId(idTypology);
		if (res.isPresent()) {
			typologyCUDSM.delete(idTypology);
			result.setError(false);
			result.setMessage("Typology deleted");
		} else {
			result.setError(true);
			result.setErrorMessage(ErrorsEnum.ERR_MCS_OBJECT_NOT_FOUND.getErrorMessage());
			result.setErrorCode("ERR_MCS_OBJECT_NOT_FOUND");
		}
		return result;
	}

	@Override
	public void initDatabase() {

		List<Typology> typologies = new ArrayList<>();

		Typology typology = new Typology();
		typology.setId("uuid-typologie-transport-telecom");
		typology.setLabel("Transport et Télécom");
		typologies.add(typology);

		typology = new Typology();
		typology.setId("uuid-thematic-area-service-marchand");
		typology.setLabel("Service marchand");
		typologies.add(typology);

		typologyRepository.saveAll(typologies);

	}

}
