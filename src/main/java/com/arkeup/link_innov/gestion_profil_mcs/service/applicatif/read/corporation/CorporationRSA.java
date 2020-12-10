package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.corporation;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Corporation;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.CorporationAdminDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.CorporationDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.CorporationsDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.CustomListDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.businessdelegate.ReseauSocialUserDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.CustomPageDTO;

public interface CorporationRSA {

	public CorporationDTO getCorporation(String id);

	public CorporationsDTO listCorporation(Pageable pageable);

	public CorporationsDTO findCorporation(String name, Pageable pageable);

	public CorporationsDTO listCorporationByAdmin(String admin, Pageable pageable);

	public List<String> listCorporationIdsByAdmin(String admin, Pageable pageable);

	public CustomListDTO getCorporationAdmins(String id);

	public List<ReseauSocialUserDTO> getCertifiedUsers(String corporationId);

	public CorporationDTO getPMInformation(String id);

	public CorporationAdminDTO verifyPermission(String corporationId);

	public CustomPageDTO<CorporationDTO> getPaginatedCorporationByIds(List<String> corporationIds, Pageable pageable);

	public CustomPageDTO<CorporationDTO> corporationPageableList(int pageIndex, int pageSize);

	CustomPageDTO<CorporationDTO> getPaginatedCorporation(List<String> corporationIds, String filter, String categorie,
			Pageable pageable);

	public List<Corporation> listAllCorporation();

}
