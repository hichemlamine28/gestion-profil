package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.productions.patent;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.CustomNullPointerException;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ErrorsEnum;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.FunctionalInvalidDataException;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ObjetNotFoundException;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.production.patent.PatentMapper;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Patent;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.PatentDTO;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.productions.ProductionMediaCUDSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.productions.patent.PatentRSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.profil.SuggestionService;
import com.arkeup.link_innov.gestion_profil_mcs.service.businessdelegate.ReseauxSociauxMCS;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.productions.patent.PatentCUDSM;

@Service
public class PatentCUDSAImpl implements PatentCUDSA {

	@Autowired
	private PatentCUDSM patentCUDSM;

	@Autowired
	private PatentMapper patentMapper;

	@Autowired
	private ReseauxSociauxMCS socialNetworkMCS;

	@Autowired
	private ProductionMediaCUDSA productionMediaCUDSA;

	@Autowired
	private PatentRSA patentRSA;

	@Autowired
	private SuggestionService serviceSuggestionService;

	@Override
	public PatentDTO createPatent(String ownerId, PatentDTO patentDTO) {
		if (StringUtils.isEmpty(patentDTO.getMediaId()))
			patentDTO.setMediaId(UUID.randomUUID().toString());
		Patent patent = patentMapper.patentDTOToPatent(patentDTO);

		if (patent == null) {
			// insert suggestions
			serviceSuggestionService.saveSuggestions(ownerId);
			throw new CustomNullPointerException(patentDTO, ErrorsEnum.ERR_MCS_NULL_POINTER);
		} else {
			if (patentRSA.publicationNumberIsAlreadyExist(patentDTO.getPublicationNumber(), ownerId)) {
				throw new FunctionalInvalidDataException(patentDTO, ErrorsEnum.ERR_MCS_PROFIL_0083);
			}
			patentDTO = patentMapper.patentToPatentDTO(patentCUDSM.createPatent(ownerId, patent));

			// insert suggestions
			serviceSuggestionService.saveSuggestions(ownerId);
			if (patentDTO != null) {
				// Update Media if exists
				productionMediaCUDSA.updateMedia(patentDTO);
				// Create node
				return (this.socialNetworkMCS.relies(patentDTO.getId()) != null) ? patentDTO
						: this.deletePatent(patentDTO.getId());
			}
		}

		return patentDTO;
	}

	@Override
	public PatentDTO updatePatent(PatentDTO patentDTO) {
		if (StringUtils.isEmpty(patentDTO.getMediaId()))
			patentDTO.setMediaId(UUID.randomUUID().toString());
		Patent patent = patentMapper.patentDTOToPatent(patentDTO);
		patent = patentCUDSM.updatePatent(patent);

		if (patent == null) {
			throw new ObjetNotFoundException(patentDTO, ErrorsEnum.ERR_MCS_OBJECT_NOT_FOUND);
		}

		return patentMapper.patentToPatentDTO(patent);
	}

	@Override
	public PatentDTO deletePatent(String id) {
		PatentDTO response = new PatentDTO();
		response.setId(id);

		patentRSA.getById(id);

		// Delete the node
		if (this.socialNetworkMCS.delete(id).getIsDeleted()) {
			patentCUDSM.deletePatent(id);
		}

		return response;
	}
}
