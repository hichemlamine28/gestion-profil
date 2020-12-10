package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.dynamic_page;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ErrorsEnum;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.dynamic_page.DynamicPageMapper;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.dynamic_page.DynamicPageDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.dynamic_page.TagGeneratorDTO;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.dynamic_page.DynamicPageRSM;

/**
 * @author Stéphan R.
 *
 */
@Service
public class DynamicPageRSAImpl implements DynamicPageRSA {

	@Autowired
	private DynamicPageRSM dynamicPageRSM;

	@Autowired
	private DynamicPageMapper dynamicPageMapper;

	/* (non-Javadoc)
	 * @see com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.dynamic_page.DynamicPageRSA#findById(java.lang.String)
	 */
	@Override
	public DynamicPageDTO findById(String dynamicPageId) {
		DynamicPageDTO dynamicPageDTO = this.dynamicPageMapper.toDTO(this.dynamicPageRSM.findById(dynamicPageId));

		if(dynamicPageDTO == null) {
			dynamicPageDTO = new DynamicPageDTO();
			dynamicPageDTO.setId(dynamicPageId);
			dynamicPageDTO.setTag(new TagGeneratorDTO().toString());
			dynamicPageDTO.setError(true);
			dynamicPageDTO.setErrorCode(ErrorsEnum.ERR_MCS_PAGE_NOT_EXIST.getErrorCode());
			dynamicPageDTO.setMessage(ErrorsEnum.ERR_MCS_PAGE_NOT_EXIST.getErrorMessage());
		}

		return dynamicPageDTO;
	}

	/* (non-Javadoc)
	 * @see com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.dynamic_page.DynamicPageRSA#findAll()
	 */
	@Override
	public List<DynamicPageDTO> findAll() {
		return this.dynamicPageMapper.toDTOs(this.dynamicPageRSM.findAll());
	}
}
