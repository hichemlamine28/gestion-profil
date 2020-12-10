package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.corporation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ErrorsEnum;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.FunctionalLimitExceededException;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ObjetNotFoundException;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.commun.PageableFactory;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.corporation.CorporationMapper;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Corporation;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.CorporationAdminDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.CorporationDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.CorporationsDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.CustomListDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.businessdelegate.ReseauSocialUserDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.CustomPageDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.utils.HelpPage;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.utils.HelpPageable;
import com.arkeup.link_innov.gestion_profil_mcs.service.businessdelegate.ReseauxSociauxMCS;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.corporation.CorporationRSM;

@Service
public class CorporationRSAImpl implements CorporationRSA {

	@Autowired
	private CorporationRSM corporationRSM;

	@Autowired
	private CorporationMapper corporationMapper;

	@Autowired
	private ReseauxSociauxMCS reseauxSociauxMCS;

	@Autowired
	private PageableFactory<Corporation> pageableFactory;

	@Override
	public CorporationDTO getCorporation(String id) {
		Optional<Corporation> res = corporationRSM.getCorporation(id);
		CorporationDTO result = new CorporationDTO();
		if (!res.isPresent()) {
			result.setError(true);
			result.setErrorCode("ERR_MCS_PROFIL_0016");
			result.setErrorMessage(ErrorsEnum.ERR_MCS_PROFIL_0016.getErrorMessage() + id);
			return result;
		}
		Corporation corporation = res.get();
		if (StringUtils.isEmpty(corporation.getMediaId())) {
			corporation.setMediaId(UUID.randomUUID().toString());
		}
		if (StringUtils.isEmpty(corporation.getBackgroundId())) {
			corporation.setBackgroundId(UUID.randomUUID().toString());
		}

		result = corporationMapper.corporationToCorporationDTO(corporation);

		result.setIsFollow(reseauxSociauxMCS.isFollowedByUser(id).getIsFollower());

		result.setError(false);
		result.setMessage("Corporation information");
		return result;
	}

	@Override
	public CorporationsDTO listCorporation(Pageable pageable) {
		CorporationsDTO result = new CorporationsDTO();
		Page<Corporation> res = corporationRSM.listCorporation(pageable);
		result.setCorporationDTOs(corporationMapper.corporationPageToCorporationDtoPage(res, pageable));
		result.setError(false);
		result.setMessage("Corporation list");
		return result;
	}

	@Override
	public CorporationsDTO findCorporation(String name, Pageable pageable) {
		CorporationsDTO result = new CorporationsDTO();
		Page<Corporation> res = corporationRSM.findCorporation(name, pageable);
		result.setCorporationDTOs(corporationMapper.corporationPageToCorporationDtoPage(res, pageable));
		result.setError(false);
		result.setMessage("Corporation list");
		return result;
	}

	@Override
	public CorporationsDTO listCorporationByAdmin(String admin, Pageable pageable) {
		CorporationsDTO result = new CorporationsDTO();
		Page<Corporation> res = corporationRSM.listCorporationByAdmin(admin, pageable);
		result.setCorporationDTOs(corporationMapper.corporationPageToCorporationDtoPage(res, pageable));
		result.setError(false);
		result.setMessage("Corporation list");
		return result;
	}

	@Override
	public List<String> listCorporationIdsByAdmin(String admin, Pageable pageable) {
		CorporationsDTO corporationsDTO = listCorporationByAdmin(admin, pageable);
		List<String> ids = new ArrayList<>();
		if (corporationsDTO != null && corporationsDTO.getCorporationDTOs() != null) {
			corporationsDTO.getCorporationDTOs().getContent()
					.forEach(corporationDTO -> ids.add(corporationDTO.getId()));
		}
		return ids;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.corporation.
	 * CorporationRSA#getCorporationAdmins(java.lang.String)
	 */
	@Override
	public CustomListDTO getCorporationAdmins(String id) {
		Optional<Corporation> res = corporationRSM.getCorporation(id);
		CustomListDTO result = new CustomListDTO();

		if (!res.isPresent()) {
			throw new ObjetNotFoundException(result, ErrorsEnum.ERR_MCS_PROFIL_0016);
		}

		CorporationDTO corporationDTO = corporationMapper.corporationToCorporationDTO(res.get());

		List<String> admins = new ArrayList<>();
		admins.addAll(corporationDTO.getAdmins());

		final String superAdminId = corporationDTO.getSuperAdmin();

		// Avoid duplicate id
		if (!admins.contains(superAdminId)) {
			admins.add(superAdminId);
		}

		result.setCorporationAdmins(admins);
		result.setCorporationName(corporationDTO.getName());

		return result;
	}

	@Override
	public List<ReseauSocialUserDTO> getCertifiedUsers(String corporationId) {
		return reseauxSociauxMCS.getCertifiedUsers(corporationId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.corporation.
	 * CorporationRSA#getPMInformation(java.lang.String)
	 */
	@Override
	public CorporationDTO getPMInformation(String id) {
		Optional<Corporation> res = corporationRSM.getCorporation(id);
		CorporationDTO result = new CorporationDTO();

		if (!res.isPresent()) {
			throw new ObjetNotFoundException(result, ErrorsEnum.ERR_MCS_PROFIL_0016);
		}

		result = corporationMapper.corporationToCorporationDTO(res.get());
		result.setError(false);
		result.setMessage("Corporation information");

		return result;
	}

	@Override
	public CorporationAdminDTO verifyPermission(String corporationId) {
		return reseauxSociauxMCS.hasPermission(corporationId);
	}

	@Override
	public CustomPageDTO<CorporationDTO> getPaginatedCorporationByIds(List<String> corporationIds, Pageable pageable) {
		CustomPageDTO<CorporationDTO> customPageDTO = new CustomPageDTO<>();
		Page<Corporation> corporations = corporationRSM.getPaginatedCorporationsInformationsByIds(corporationIds,
				pageable);
		HelpPage<Corporation> helpPageCorporation = pageableFactory.pageToHelpPage(corporations);
		HelpPage<CorporationDTO> helpPageCorporationDTO = corporationMapper
				.helpPageCorporationToHelpPageCorporationDTOs(helpPageCorporation);

		customPageDTO.setPageDTOs(helpPageCorporationDTO);

		return customPageDTO;
	}

	@Override
	public CustomPageDTO<CorporationDTO> corporationPageableList(int pageIndex, int pageSize) {

		CustomPageDTO<CorporationDTO> res = new CustomPageDTO<>();
		if (pageSize > 100) {
			throw new FunctionalLimitExceededException(res, ErrorsEnum.ERR_MCS_MAX_PAGE_SIZE);
		}
		HelpPageable helpPageable = new HelpPageable(pageIndex, pageSize);

		HelpPage<CorporationDTO> helpPageDto = corporationMapper
				.helpPageCorporationToHelpPageCorporationDTOs(corporationRSM.listPageCorporation(helpPageable));

		res.setPageDTOs(helpPageDto);
		return res;
	}

	@Override
	public CustomPageDTO<CorporationDTO> getPaginatedCorporation(List<String> corporationIds, String filter,
			String categorie, Pageable pageable) {
		CustomPageDTO<CorporationDTO> customPageDTO = new CustomPageDTO<>();
		Page<Corporation> corporations = corporationRSM.getPaginatedCorporationsInformations(corporationIds, filter,
				categorie, pageable);
		HelpPage<Corporation> helpPageCorporation = pageableFactory.pageToHelpPage(corporations);
		HelpPage<CorporationDTO> helpPageCorporationDTO = corporationMapper
				.helpPageCorporationToHelpPageCorporationDTOs(helpPageCorporation);

		customPageDTO.setPageDTOs(helpPageCorporationDTO);

		return customPageDTO;
	}

	@Override
	public List<Corporation> listAllCorporation() {
		return corporationRSM.findAll();

	}
}
