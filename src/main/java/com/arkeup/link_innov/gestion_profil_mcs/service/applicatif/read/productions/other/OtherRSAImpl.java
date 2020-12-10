package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.productions.other;

import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.productions.other.OtherCUDSA;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ErrorsEnum;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ObjetNotFoundException;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.production.other.OtherProductionMapper;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.OtherProduction;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.OtherProductionDTO;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.productions.other.OtherRSM;

import java.util.UUID;

@Service
public class OtherRSAImpl implements OtherRSA {

	@Autowired
	private OtherRSM otherProductionRSM;

	@Autowired
	private OtherCUDSA otherCUDSA;

	@Autowired
	private OtherProductionMapper otherProductionMapper;

	@Override
	public Page<OtherProductionDTO> getByOwnerId(String ownerId, Pageable pageable) {
		Page<OtherProduction> otherProductionPage = otherProductionRSM.getByOwnerId(ownerId, pageable);

		return otherProductionMapper
				.otherProductionPageToOtherProductionDtoPage(otherProductionPage, pageable);
	}


	@Override
	public OtherProductionDTO getById(String id) {
		OtherProductionDTO otherProductionDTO = null;
		OtherProduction otherProduction = otherProductionRSM.getById(id);
		if(otherProduction == null) {
			throw new ObjetNotFoundException(new OtherProductionDTO(), ErrorsEnum.ERR_MCS_PROFIL_0065);
		}
		boolean hasEmptyMediaId = false;
		otherProductionDTO = otherProductionMapper.otherProductionToOtherProductionDTO(otherProduction);
		if(StringUtils.isEmpty(otherProduction.getMediaId()))
		{
			hasEmptyMediaId = true;
			otherProductionDTO.setMediaId(UUID.randomUUID().toString());
		}
		if(hasEmptyMediaId)
			otherCUDSA.updateOtherProduction(otherProductionDTO);

		return otherProductionDTO;
	}

	@Override
	public int getOtherProductionByOwnerId(String ownerId) {
		return otherProductionRSM.getOtherProductionByOwnerId(ownerId);
	}

}
