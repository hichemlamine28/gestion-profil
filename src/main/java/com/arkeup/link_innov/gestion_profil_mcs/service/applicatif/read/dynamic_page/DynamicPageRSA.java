package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.dynamic_page;

import java.util.List;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.dynamic_page.DynamicPageDTO;

/**
 * @author Stéphan R.
 *
 */
public interface DynamicPageRSA {

	/**
	 * @param dynamicPageId
	 *
	 * @return
	 */
	DynamicPageDTO findById(String dynamicPageId);

	/**
	 * @return
	 */
	List<DynamicPageDTO> findAll();

}
