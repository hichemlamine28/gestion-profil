package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.productions.other;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.CustomNullPointerException;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ErrorsEnum;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ObjetNotFoundException;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.production.other.OtherProductionMapper;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.OtherProduction;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.OtherProductionDTO;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.productions.other.OtherRSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.businessdelegate.ReseauxSociauxMCS;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.productions.other.OtherCUDSM;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Service
public class OtherCUDSAImpl implements OtherCUDSA {

	@Autowired
	private OtherCUDSM otherProductionCUDSM;

	@Autowired
	private OtherProductionMapper otherProductionMapper;

	@Autowired
	private ReseauxSociauxMCS socialNetworkMCS;
	
	@Autowired
	private OtherRSA otherRSA;

	@Override
	public OtherProductionDTO createOtherProduction(String ownerId, OtherProductionDTO otherProductionDTO) {

		if(StringUtils.isEmpty(otherProductionDTO.getMediaId()))
				otherProductionDTO.setMediaId(UUID.randomUUID().toString());
		OtherProduction otherProduction = otherProductionMapper.otherProductionDTOToOtherProduction(otherProductionDTO);

		if (otherProduction == null) {
			throw new CustomNullPointerException(otherProductionDTO, ErrorsEnum.ERR_MCS_NULL_POINTER);
		}

		otherProductionDTO = otherProductionMapper.otherProductionToOtherProductionDTO(otherProductionCUDSM.createOtherProduction(ownerId, otherProduction));

		if(otherProductionDTO != null) {
			// Create node
			return (this.socialNetworkMCS.relies(otherProductionDTO.getId()) != null) ? otherProductionDTO : this.deleteOtherProduction(otherProductionDTO.getId());
		}

		return otherProductionDTO;
	}

	@Override
	public OtherProductionDTO updateOtherProduction(OtherProductionDTO otherProductionDTO) {
		if(StringUtils.isEmpty(otherProductionDTO.getMediaId()))
			otherProductionDTO.setMediaId(UUID.randomUUID().toString());
		OtherProduction otherProduction = otherProductionMapper.otherProductionDTOToOtherProduction(otherProductionDTO);
		otherProduction = otherProductionCUDSM.updateOtherProduction(otherProduction);

		if (otherProduction == null) {
			throw new ObjetNotFoundException(otherProductionDTO, ErrorsEnum.ERR_MCS_OBJECT_NOT_FOUND);
		}
		return otherProductionMapper.otherProductionToOtherProductionDTO(otherProduction);
	}

	@Override
	public OtherProductionDTO deleteOtherProduction(String id) {

		OtherProductionDTO otherProductionDTO = otherRSA.getById(id);

		// Delete the node
		if(this.socialNetworkMCS.delete(id).getIsDeleted()) {
			otherProductionCUDSM.deleteOtherProduction(id);
		}
		return otherProductionDTO;
	}
	
}
