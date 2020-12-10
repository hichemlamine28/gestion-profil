package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.dynamic_page;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.dynamic_page.DynamicPageDTO;

/**
 * @author Stéphan R.
 *
 */
public interface DynamicPageCUDSA {

	/**
	 * @param dynamicPageDTO
	 *
	 * @return
	 */
	DynamicPageDTO save(DynamicPageDTO dynamicPageDTO);

	/**
	 * @param dynamicPageDTO
	 *
	 * @return
	 */
	DynamicPageDTO update(DynamicPageDTO dynamicPageDTO);

	/**
	 * @param dynamicPageId
	 *
	 * @return
	 */
	Boolean deleteById(String dynamicPageId);

	void initDatabase();

}
