package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.productions.patent;

import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ErrorsEnum;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ObjetNotFoundException;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.production.patent.PatentMapper;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Patent;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.PatentDTO;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.productions.patent.PatentCUDSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.productions.patent.PatentRSM;

@Service
public class PatentRSAImpl implements PatentRSA {

	@Autowired
	private PatentRSM patentRSM;

	@Autowired
	private PatentMapper patentMapper;

	@Autowired
	private PatentCUDSA patentCUDSA;

	@Override
	public Page<PatentDTO> getByOwnerId(String ownerId, Pageable pageable) {
		Page<Patent> patentPage = patentRSM.getByOwnerId(ownerId, pageable);

		Page<PatentDTO> patentPageDTO = patentMapper.patentPageToPatentDtoPage(patentPage, pageable);

		return patentPageDTO;
	}

	@Override
	public List<PatentDTO> findAllByOwnerId(String ownerId) {
		List<Patent> patents = patentRSM.findAllByOwnerId(ownerId);
		List<PatentDTO> patentDTOs = patentMapper.patentToPatentDTOs(patents);
		return patentDTOs;
	}

	@Override
	public Boolean publicationNumberIsAlreadyExist(String publicationNumber, String ownerId) {
		return patentRSM.publicationNumberIsAlreadyExist(publicationNumber, ownerId);
	}

	@Override
	public PatentDTO getById(String patentId) {
		PatentDTO patentDTO = null;
		Patent patent = patentRSM.getById(patentId);
		if (patent == null) {
			throw new ObjetNotFoundException(new PatentDTO(), ErrorsEnum.ERR_MCS_PROFIL_0065);
		}
		boolean hasEmptyMediaId = false;
		patentDTO = patentMapper.patentToPatentDTO(patent);
		if (StringUtils.isEmpty(patentDTO.getMediaId())) {
			hasEmptyMediaId = true;
			patentDTO.setMediaId(UUID.randomUUID().toString());
		}
		if (hasEmptyMediaId)
			patentCUDSA.updatePatent(patentDTO);
		return patentDTO;
	}

	@Override
	public int getNumberPatentByOwnerId(String ownerId) {
		return patentRSM.getNumberPatentByOwnerId(ownerId);
	}

}
