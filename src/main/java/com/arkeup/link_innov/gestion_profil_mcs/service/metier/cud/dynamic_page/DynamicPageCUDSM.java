package com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.dynamic_page;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.DynamicPage;

/**
 * @author Stéphan R.
 *
 */
public interface DynamicPageCUDSM {

	/**
	 * This method is used to save or update a dynamic page object
	 *
	 * @param dynamicPage
	 * @param isUpdateActions
	 *
	 * @return
	 */
	DynamicPage save(DynamicPage dynamicPage, Boolean... isUpdateActions);

	/**
	 * @param dynamicPageId
	 *
	 * @return
	 */
	Boolean deleteById(String dynamicPageId);

}
